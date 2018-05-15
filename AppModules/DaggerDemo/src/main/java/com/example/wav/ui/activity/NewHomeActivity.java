package com.example.wav.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

import com.blankj.utilcode.util.ToastUtils;
import com.example.wav.Const;
import com.example.wav.R;
import com.example.wav.base.BaseAdvActivity;
import com.example.wav.bean.MainPagerInfo;
import com.example.wav.manager.DataManager;
import com.example.wav.manager.XGNotificationManager;
import com.example.wav.mvp.contract.NewHomeContract;
import com.example.wav.mvp.presenter.NewHomePresenter;
import com.example.wav.ui.fragment.FilterDeviceStatusFragment;
import com.example.wav.ui.fragment.HomeFragment;
import com.example.wav.ui.fragment.filter.FilterAlarmTypeFragment;
import com.example.wav.ui.fragment.filter.FilterCustomerGroupListFragment;
import com.example.wav.ui.fragment.filter.FilterCustomerListFragment;
import com.example.wav.ui.fragment.filter.FilterDateRangeFragment;
import com.example.wav.ui.fragment.filter.FilterDeviceNamesFragment;
import com.example.wav.widget.TabIndicator;
import com.tencent.android.tpush.XGCustomPushNotificationBuilder;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.android.tpush.rpc.XGRemoteService;
import com.tencent.android.tpush.service.XGPushServiceV3;
import com.zhiyangstudio.commonlib.CommonConst;
import com.zhiyangstudio.commonlib.utils.LoggerUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by example on 2018/4/28.
 */

public class NewHomeActivity extends BaseAdvActivity<NewHomePresenter, NewHomeContract
        .INewHomeView> implements TabHost.OnTabChangeListener {

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.navgation_view)
    NavigationView mNavigationView;
    @BindView(R.id.nav_filter_view)
    NavigationView mFilterNavigationView;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    @BindView(android.R.id.tabhost)
    FragmentTabHost mFragmentTabHost;
    private List<MainPagerInfo> mMainPagerInfos;
    private MainPagerAdapter mPagerAdapter;
    private Menu menu;
    private List<Fragment> mFilterFragments;
    private MsgReceiver updateListViewReceiver;
    private Message m;

    @Override
    public void beforeSubContentInit() {
        super.beforeSubContentInit();
        initXg();
        initCustomPushNotificationBuilder(this);
    }

    private void initXg() {
        //清除通知栏消息
        //XGPushManager.cancelAllNotifaction();

        //代码内动态注册access ID
        //XGPushConfig.setAccessId(this,2100250470);

        XGPushConfig.enableDebug(this, true);
        startService(new Intent(mContext, XGPushServiceV3.class));
        startService(new Intent(mContext, XGRemoteService.class));

        //开启信鸽的日志输出，线上版本不建议调用
        XGPushConfig.enableDebug(this, true);
        XGPushConfig.getToken(this);
        //注册数据更新监听器
        updateListViewReceiver = new MsgReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.wav.activity.UPDATE_LISTVIEW");
        registerReceiver(updateListViewReceiver, intentFilter);
        // 1.获取设备Token
        Handler handler = new HandlerExtension(this);
        m = handler.obtainMessage();
        /*
        注册信鸽服务的接口
        如果仅仅需要发推送消息调用这段代码即可
        */
        XGPushManager.registerPush(getApplicationContext(),
                new XGIOperateCallback() {
                    @Override
                    public void onSuccess(Object data, int flag) {
                        LoggerUtils.loge("+++ register push sucess. token:" + data +
                                "flag" + flag);

                        m.obj = "+++ register push sucess. token:" + data;
                        m.sendToTarget();
                    }

                    @Override
                    public void onFail(Object data, int errCode, String msg) {
                        LoggerUtils.loge(
                                "+++ register push fail. token:" + data
                                        + ", errCode:" + errCode + ",msg:"
                                        + msg);
                        m.obj = "+++ register push fail. token:" + data
                                + ", errCode:" + errCode + ",msg:" + msg;
                        m.sendToTarget();
                    }
                });

        // 获取token
        XGPushConfig.getToken(this);

    }

    /**
     * 设置通知自定义View，这样在下发通知时可以指定build_id。编号由开发者自己维护,build_id=0为默认设置
     *
     * @param context
     */
    @SuppressWarnings("unused")
    private void initCustomPushNotificationBuilder(Context context) {


        XGCustomPushNotificationBuilder commonBuilder = XGNotificationManager.createCommonBuilder();
        // 客户端保存build_id
        XGPushManager.setPushNotificationBuilder(this, 1, commonBuilder);
        XGPushManager.setDefaultNotificationBuilder(this, commonBuilder);
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void initView() {
        mFragmentTabHost.setup(mContext, getSupportFragmentManager(), R.id.viewpager);

        toolbar.setNavigationIcon(R.drawable.ic_menu);
    }

    @Override
    public void initData() {

        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        mMainPagerInfos = DataManager.getMainPagerDatas();
        if (mMainPagerInfos != null && mMainPagerInfos.size() > 0) {
            MainPagerInfo mainPagerInfo = null;
            TabIndicator indicator = null;
            for (int i = 0; i < mMainPagerInfos.size(); i++) {
                mainPagerInfo = mMainPagerInfos.get(i);
                indicator = new TabIndicator(mContext);
                indicator.setText(mainPagerInfo.getTitle());
                mFragmentTabHost.addTab(mFragmentTabHost.newTabSpec(mainPagerInfo.getTitle())
                        .setIndicator(indicator), mainPagerInfo.getFragmentCls(), null);
            }

            mPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), mMainPagerInfos);
            mViewPager.setAdapter(mPagerAdapter);
            mViewPager.setCurrentItem(0, false);
        }
        mFragmentTabHost.getTabWidget().setDividerDrawable(new ColorDrawable(Color.TRANSPARENT));

        mFilterFragments = new ArrayList<>();
        mFilterFragments.add(new FilterCustomerListFragment());
        mFilterFragments.add(new FilterCustomerGroupListFragment());
        mFilterFragments.add(new FilterDeviceNamesFragment());
        mFilterFragments.add(new FilterDeviceStatusFragment());
        mFilterFragments.add(new FilterAlarmTypeFragment());
        mFilterFragments.add(new FilterDateRangeFragment());
    }

    @Override
    public void refreshUi() {

    }

    @Override
    protected boolean initToolBar() {
        setTitle("设备列表");
        return true;
    }

    @Override
    protected boolean hasShowHome() {
        return true;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_new_home;
    }

    @Override
    public void addListener() {
        mFragmentTabHost.setOnTabChangedListener(this);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int
                    positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mFragmentTabHost.setCurrentTab(position);
                switch (position) {
                    case 0:
                        setTitle("设备列表");
                        // TODO: 2018/4/28 显示菜单
                        if (menu != null)
                            menu.findItem(R.id.action_filter).setVisible(true);
                        break;
                    case 1:
                        // 关闭手势滑动
                        setTitle("设置");
                        // TODO: 2018/4/28 隐藏菜单
                        if (menu != null)
                            menu.findItem(R.id.action_filter).setVisible(false);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mNavigationView.setNavigationItemSelectedListener(item -> {
            mDrawerLayout.closeDrawers();
            switch (item.getItemId()) {
                case R.id.action_test1:
                    menu.findItem(R.id.action_filter).setVisible(true);
                    break;
                case R.id.action_test2:
                    menu.findItem(R.id.action_filter).setVisible(true);
                    break;
                case R.id.action_test3:
                    // 禁止右侧菜单的滑出
                    menu.findItem(R.id.action_filter).setVisible(false);
                    break;
                case R.id.action_test4:
                    menu.findItem(R.id.action_filter).setVisible(true);
                    break;
                case R.id.action_test5:
                    // 禁止右侧菜单的滑出
                    menu.findItem(R.id.action_filter).setVisible(false);
                    break;
                case R.id.action_test6:
                    // 禁止右侧菜单的滑出
                    menu.findItem(R.id.action_filter).setVisible(false);
                    break;
            }
            return true;
        });


        mFilterNavigationView.setNavigationItemSelectedListener(item -> {
            int index = 0;
            switch (item.getItemId()) {
                case R.id.action_filter_customer_list:
                    index = 0;
                    break;
                case R.id.action_filter_customer_group_list:
                    index = 1;
                    break;
                case R.id.action_filter_dev_names:
                    index = 2;
                    break;
                case R.id.action_filter_dev_status:
                    index = 3;
                    break;
                case R.id.action_filter_alarm_types:
                    index = 4;
                    break;
                case R.id.action_filter_date_range:
                    index = 5;
                    break;
            }
            showTargetFilterFragment(mFilterFragments.get(index));
            return true;
        });
    }

    public void showTargetFilterFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.rl_right_root, fragment)
                .commit();
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(updateListViewReceiver);
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        XGPushManager.onActivityStoped(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        XGPushClickedResult click = XGPushManager.onActivityStarted(this);
        // click.getCustomContent()
        LoggerUtils.loge("onResumeXGPushClickedResult:" + click);
        if (click != null) { // 判断是否来自信鸽的打开方式
            ToastUtils.showShort("通知被点击:" + click.toString());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mDrawerLayout.closeDrawers();
        switch (item.getItemId()) {
            case android.R.id.home:
                if (mDrawerLayout.isDrawerOpen(Gravity.START)) {
                    mDrawerLayout.closeDrawer(Gravity.START);
                } else {
                    mDrawerLayout.openDrawer(Gravity.START);
                }
                break;
            case R.id.action_filter:
                ToastUtils.showShort("筛选");
                showTargetFilterFragment(mFilterFragments.get(0));
                if (mDrawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                    mDrawerLayout.closeDrawer(Gravity.RIGHT);
                } else {
                    mDrawerLayout.openDrawer(Gravity.RIGHT);
                }
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_device, menu);
        this.menu = menu;
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Const.UI_ACTION.REQ_DEVICE_LIST2 &&
                resultCode == Const.UI_ACTION.RESULT_REFRESH_UI) {
            Fragment fragment = mMainPagerInfos.get(0).getFragment();
            HomeFragment homeFragment = (HomeFragment) fragment;
            homeFragment.state = CommonConst.PAGE_STATE.STATE_REFRESH;
            // 重新 请求数据
            homeFragment.loadDatas();
        }
    }

    @Override
    public void onTabChanged(String tabId) {
        int currentTab = mFragmentTabHost.getCurrentTab();
        mViewPager.setCurrentItem(currentTab, false);
    }

    private static class HandlerExtension extends Handler {
        WeakReference<NewHomeActivity> mActivity;

        HandlerExtension(NewHomeActivity activity) {
            mActivity = new WeakReference<NewHomeActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            NewHomeActivity theActivity = mActivity.get();
            if (theActivity == null) {
                theActivity = new NewHomeActivity();
            }
            if (msg != null) {
                LoggerUtils.loge(XGPushConfig.getToken(theActivity));
            }
        }
    }

    private class MainPagerAdapter extends FragmentPagerAdapter {
        private List<MainPagerInfo> mList;

        public MainPagerAdapter(FragmentManager manager, List<MainPagerInfo> list) {
            super(manager);
            this.mList = list;
        }

        @Override
        public Fragment getItem(int position) {
            return mList.get(position).getFragment();
        }

        @Override
        public int getCount() {
            return mList.size();
        }
    }

    public class MsgReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub

            LoggerUtils.loge("MsgReceiver onReceive");
        }
    }
}
