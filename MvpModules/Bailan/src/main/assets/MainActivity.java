package com.example.xiaowusong.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.xiaowusong.Const;
import com.example.xiaowusong.R;
import com.example.xiaowusong.manager.UserInfoManager;
import com.example.xiaowusong.manager.XGNotificationManager;
import com.example.xiaowusong.ui.fragment.AlarmListFragment;
import com.example.xiaowusong.ui.fragment.DeviceListFragment;
import com.example.xiaowusong.ui.fragment.HelpFragment;
import com.example.xiaowusong.ui.fragment.ScanFragment;
import com.example.xiaowusong.ui.fragment.SettingFragment;
import com.example.xiaowusong.ui.fragment.StatisticsFragment;
import com.example.xiaowusong.ui.fragment.filter.FilterAlarmTypeListFragment;
import com.example.xiaowusong.ui.fragment.filter.FilterCustomerGroupListFragment;
import com.example.xiaowusong.ui.fragment.filter.FilterCustomerListFragment;
import com.example.xiaowusong.ui.fragment.filter.FilterDateRangeListFragment;
import com.example.xiaowusong.ui.fragment.filter.FilterDevNameListFragment;
import com.example.xiaowusong.ui.fragment.filter.FilterDeviceStatusListFragment;
import com.example.xiaowusong.widget.CommonConfrimCancelDialog;
import com.example.xiaowusong.zxing.activity.CaptureActivity;
import com.tencent.android.tpush.XGCustomPushNotificationBuilder;
import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;
import com.zhiyangstudio.commonlib.CommonConst;
import com.zhiyangstudio.commonlib.corel.BaseActivity;
import com.zhiyangstudio.commonlib.utils.CommonUtils;
import com.zhiyangstudio.commonlib.utils.EmptyUtils;
import com.zhiyangstudio.commonlib.utils.IntentUtils;
import com.zhiyangstudio.commonlib.utils.LoggerUtils;
import com.zhiyangstudio.commonlib.utils.UiUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.xiaowusong.zxing.activity.CaptureActivity.RESULT_CODE_QR_SCAN;

public class MainActivity extends BaseActivity {

    private long mExitTime;
    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;
    @BindView(R.id.nav_filter_menu)
    NavigationView mNavigationFilterView;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.filter_iv)
    ImageView ivFilter;
    @BindView(R.id.announcement_iv)
    ImageView ivAnnounComment;

    private List<Fragment> mFragmentList;

    private boolean hasSdCardPermission, hasCameraPermission;

    protected PermissionListener mPermissionListener = new PermissionListener() {
        @Override
        public void onGrant(int code) {
            switch (code) {
                case CommonConst.PERMISSION.REQ_SDCARD_PERMISSION:
                    hasSdCardPermission = true;
                    checkCameraPermission(mPermissionListener);
                    break;
                case CommonConst.PERMISSION.REQ_CAMERA_PERMISSION:
                    hasCameraPermission = true;
                    if (hasAutoOpenScanUi) {
                        IntentUtils.forwardForResult(CaptureActivity.class, Const.UI_SCAN_CODE_REQCODE);
                    }
                    break;
            }
        }

        @Override
        public void onDeny(int code) {
            switch (code) {
                case CommonConst.PERMISSION.REQ_SDCARD_PERMISSION:
                    checkCameraPermission(mPermissionListener);
                    break;
            }
        }
    };

    private boolean hasAutoOpenScanUi;
    private Menu mFilterMenu;
    private Menu mNavMenu;
    private List<Fragment> mFilterFragments;
    private int mMenuIndex;
    private BroadcastReceiver updateListViewReceiver;
    private Message m;

    @Override
    public void beforeSubContentInit() {
        super.beforeSubContentInit();
        initXg();
        // initCustomPushNotificationBuilder(this);
        // 运行时权限申请
        checkSDCardPermission(mPermissionListener);
    }

    /**
     * 初始化消息推送组件
     */
    private void initXg() {
        //清除通知栏消息
        XGPushManager.cancelAllNotifaction(mContext);

//        startService(new Intent(mContext, XGPushServiceV3.class));
//        startService(new Intent(mContext, XGRemoteService.class));
//
//        //开启信鸽的日志输出，线上版本不建议调用
//        XGPushConfig.enableDebug(this, true);
//        // 1.获取设备Token
//        // 新增收到通知后弹出通知前的回调接口。
//        // 为保证弹出通知前一定调用本方法，需要在application的onCreate注册
//        // 收到通知时，会调用本回调函数。
//        // 相当于这个回调会在信鸽的弹出通知之前被截取
//        // 一般上针对需要获取通知内容、标题，设置通知点击的跳转逻辑等等
//        XGPushManager.setNotifactionCallback(new XGPushNotifactionCallback() {
//
//            @Override
//            public void handleNotify(XGNotifaction xGNotifaction) {
//                LoggerUtils.loge("处理信鸽通知：" + xGNotifaction);
////                // 获取标签、内容、自定义内容
//                String title = xGNotifaction.getTitle();
//                String content = xGNotifaction.getContent();
////                String customContent = xGNotifaction.getCustomContent();
////                // 其它的处理
////                // 如果还要弹出通知，可直接调用以下代码或自己创建Notifaction，否则，本通知将不会弹出在通知栏中。
////                xGNotifaction.doNotify();
//                CommonUtil.notifyPushMsg(mContext, title, content);
//            }
//        });
//        UserBean userInfo = UserInfoManager.getUserInfo();
//        String userName = Const.TMP_DATA.CURRENT_USER_NAME;
//        if (userInfo != null && EmptyUtils.isNotEmpty(userInfo.getUsername())) {
//            userName = userInfo.getUsername();
//        }
//        /*
//        注册信鸽服务的接口
//        如果仅仅需要发推送消息调用这段代码即可
//        */
//        XGPushManager.registerPush(
//                getApplicationContext(),
//                userName,
//                new XGIOperateCallback() {
//                    @Override
//                    public void onSuccess(Object data, int flag) {
//                        LoggerUtils.loge("+++ register push sucess. token:" + data +
//                                "flag" + flag);
//                    }
//
//                    @Override
//                    public void onFail(Object data, int errCode, String msg) {
//                        LoggerUtils.loge(
//                                "+++ register push fail. token:" + data
//                                        + ", errCode:" + errCode + ",msg:"
//                                        + msg);
//                    }
//                });
//
//        // 获取token
//        XGPushConfig.getToken(this);
    }

    /**
     * 设置通知自定义View，这样在下发通知时可以指定build_id。编号由开发者自己维护,build_id=0为默认设置
     */
    private void initCustomPushNotificationBuilder(Context context) {
        XGCustomPushNotificationBuilder commonBuilder = XGNotificationManager.createCommonBuilder();
//        // 客户端保存build_id
        XGPushManager.setPushNotificationBuilder(context, 1, commonBuilder);
        XGPushManager.setDefaultNotificationBuilder(context, commonBuilder);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - mExitTime < 2000) {
                UserInfoManager.saveExitFromHome(true);
                mDrawerLayout.closeDrawers();
                finish();
                return true;
            } else {
                mExitTime = System.currentTimeMillis();
                ToastUtils.showShort("请再按一次退出程序...");
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected PermissionListener getPermissonCallBack() {
        return null;
    }

    @Override
    public int getContentId() {
        return R.layout.activity_new_main;
    }

    @Override
    public void initView() {
        initToolbar();
        initNavView();
        initDrawerLayout();
    }

    private void initDrawerLayout() {
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    private void initToolbar() {
        mToolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void initNavView() {
        // TODO: 2018/5/5 设置naview item textcolor
        ColorStateList navItemCsl = UiUtils.getColorStateList(R.color.title_color);
        mNavigationView.setItemTextColor(navItemCsl);

        CommonUtils.disableNavigationViewScrollbars(mNavigationView);
        CommonUtils.disableNavigationViewScrollbars(mNavigationFilterView);

        // TODO: 2018/5/12 筛选菜单
        mFilterMenu = mNavigationFilterView.getMenu();
        mFilterMenu.findItem(R.id.action_item_dev_names).setVisible(false);
        mFilterMenu.findItem(R.id.action_item_warn_type).setVisible(false);
        mFilterMenu.findItem(R.id.action_item_date_range).setVisible(false);

        ivFilter.setVisibility(View.VISIBLE);
        ivAnnounComment.setVisibility(View.GONE);
    }

    private int lastNavItemId, lastNavFilterId;

    @Override
    public void addListener() {
        mNavMenu = mNavigationView.getMenu();

        mNavigationView.getHeaderView(0).setOnClickListener(v -> {
            if (lastNavItemId != 0) {
                mNavMenu.findItem(lastNavItemId).setChecked(false);
            }
            mDrawerLayout.closeDrawers();
            showAnnounComment();
            ivFilter.setVisibility(View.GONE);
            mToolbar.setTitle("主页");
            showTargetFragment(mFragmentList.get(0));
        });

        // TODO: 2018/5/12 左边菜单
        mNavigationView.setNavigationItemSelectedListener(item -> {
            String title = "";
            hideTopMenus();
            clearFilterData();
            mToolbar.setNavigationIcon(R.mipmap.icon_back);
            switch (item.getItemId()) {
                case R.id.nav_list:
                    showFilter();
                    mMenuIndex = 0;
                    title = "设备列表";
                    mFilterMenu.findItem(R.id.action_item_dev_names).setVisible(false);
                    mFilterMenu.findItem(R.id.action_item_warn_type).setVisible(false);
                    mFilterMenu.findItem(R.id.action_item_date_range).setVisible(false);
                    mFilterMenu.findItem(R.id.action_item_dev_status).setVisible(true);
                    showTargetFragment(mFragmentList.get(0));
                    break;
                case R.id.nav_alarm:
                    showFilter();
                    mMenuIndex = 1;
                    title = "警告日志";
                    showTargetFragment(mFragmentList.get(1));
                    mFilterMenu.findItem(R.id.action_item_customer_list).setVisible(true);
                    mFilterMenu.findItem(R.id.action_item_group_list).setVisible(true);
                    mFilterMenu.findItem(R.id.action_item_dev_names).setVisible(true);
                    mFilterMenu.findItem(R.id.action_item_dev_status).setVisible(false);
                    mFilterMenu.findItem(R.id.action_item_date_range).setVisible(true);
                    mFilterMenu.findItem(R.id.action_item_warn_type).setVisible(true);
                    break;
                case R.id.nav_statistica:
                    mMenuIndex = 2;
                    mFilterMenu.findItem(R.id.action_item_warn_type).setVisible(false);
                    mFilterMenu.findItem(R.id.action_item_dev_status).setVisible(false);
                    mFilterMenu.findItem(R.id.action_item_dev_names).setVisible(true);
                    mFilterMenu.findItem(R.id.action_item_date_range).setVisible(false);
                    showFilter();
                    showTargetFragment(mFragmentList.get(2));
                    title = "统计";
                    break;
                case R.id.nav_feedback:
                    mMenuIndex = 3;
                    IntentUtils.forward(FeedBackActivity.class);
                    break;
                case R.id.nav_help:
                    mMenuIndex = 4;
                    title = "帮助";
                    showTargetFragment(mFragmentList.get(3));
                    break;
                case R.id.nav_scan:
                    mMenuIndex = 5;
                    title = "扫一扫";
                    showTargetFragment(mFragmentList.get(4));
                    //打开二维码扫描界面
                    if (hasCameraPermission) {
                        IntentUtils.forwardForResult(CaptureActivity.class, Const.UI_SCAN_CODE_REQCODE);
                    } else {
                        // 重新检查权限
                        checkCameraPermission(mPermissionListener);
                        hasAutoOpenScanUi = true;
                    }
                    break;
                case R.id.nav_setting:
                    mMenuIndex = 6;
                    title = "设置";
                    showTargetFragment(mFragmentList.get(5));
                    break;
            }
            item.setChecked(true);
            lastNavItemId = item.getItemId();
            if (EmptyUtils.isNotEmpty(title)) {
                mToolbar.setTitle(title);
            }
            mDrawerLayout.closeDrawers();
            return true;
        });

        // TODO: 2018/5/12 右侧筛选菜单
        mNavigationFilterView.setNavigationItemSelectedListener(item -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            int index = -1;
            switch (item.getItemId()) {
                case R.id.action_item_customer_list:
                    index = 0;
                    break;
                case R.id.action_item_group_list:
                    index = 1;
                    break;
                case R.id.action_item_dev_names:
                    index = 2;
                    break;
                case R.id.action_item_dev_status:
                    index = 3;
                    break;
                case R.id.action_item_warn_type:
                    index = 4;
                    break;
                case R.id.action_item_date_range:
                    index = 5;
                    break;
            }
            if (lastNavFilterId != 0) {
                mFilterMenu.findItem(lastNavFilterId).setChecked(false);
            }
            item.setCheckable(true);//设置选项可选
            item.setChecked(true);//设置选型被选中
            lastNavFilterId = item.getItemId();
            if (index != -1) {
                fragmentTransaction.replace(R.id.rl_filer_container, mFilterFragments.get(index))
                        .commit();
            }
            return true;
        });
    }

    private void clearFilterData() {
        UserInfoManager.saveDefaultFilterCustomerId("");
        UserInfoManager.saveDefaultFilterGroupID(0);
        UserInfoManager.saveDefaultFilterDeviceStatusID(0);
        UserInfoManager.saveDefaultFilterDeviceID("");
        UserInfoManager.saveDefaultFilterAlarmType("");
        UserInfoManager.saveDefaultFilterStartDateRange("");
        UserInfoManager.saveDefaultFilterEndDateRange("");
    }

    public void hideTopMenus() {
        ivFilter.setVisibility(View.GONE);
        ivAnnounComment.setVisibility(View.GONE);
    }

    private void showAnnounComment() {
        ivAnnounComment.setVisibility(View.VISIBLE);
    }

    private void showFilter() {
        ivFilter.setVisibility(View.VISIBLE);
    }

    @Override
    public void initData() {
        // TODO: 2018/5/16 清除数据
        UserInfoManager.saveExitFromHome(false);
        UserInfoManager.saveExitFromLogoff(false);

        mFragmentList = new ArrayList<>();
//        mFragmentList.add(new InformationListFragment());
        mFragmentList.add(new DeviceListFragment());
        mFragmentList.add(new AlarmListFragment());
        mFragmentList.add(new StatisticsFragment());
        mFragmentList.add(new HelpFragment());
        mFragmentList.add(new ScanFragment());
        mFragmentList.add(new SettingFragment());
        showTargetFragment(mFragmentList.get(0));

        mFilterFragments = new ArrayList<>();
        // 客户名称
        mFilterFragments.add(new FilterCustomerListFragment());
        // 客户分组
        mFilterFragments.add(new FilterCustomerGroupListFragment());
        // 设备名称
        mFilterFragments.add(new FilterDevNameListFragment());
        // 设备状态
        mFilterFragments.add(new FilterDeviceStatusListFragment());
        // 报警类型
        mFilterFragments.add(new FilterAlarmTypeListFragment());
        // 日期范围
        mFilterFragments.add(new FilterDateRangeListFragment());
    }

    private void showTargetFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, fragment).commit();
    }

    @Override
    public void refreshUi() {

    }

    @Override
    public void release() {

    }

    @OnClick({R.id.filter_iv, R.id.announcement_iv, R.id.tv_menu_filter_confrim, R.id.rl_filter_divider})
    public void onViewClick(View view) {
        mDrawerLayout.closeDrawers();
        switch (view.getId()) {
            case R.id.filter_iv:
                if (mDrawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                    mDrawerLayout.closeDrawer(Gravity.RIGHT);
                } else {
                    if (lastNavFilterId != 0) {
                        mFilterMenu.findItem(lastNavFilterId).setChecked(false);
                    }
                    mFilterMenu.findItem(R.id.action_item_customer_list).setChecked(true);
                    getSupportFragmentManager().beginTransaction().replace(R.id.rl_filer_container, mFilterFragments
                            .get(0)).commit();
                    mDrawerLayout.openDrawer(Gravity.RIGHT);
                }
                break;
            case R.id.announcement_iv:
                ToastUtils.showShort(UiUtils.getStr(R.string.tips_fun_is_building));
                break;
            case R.id.rl_filter_divider:
                mDrawerLayout.closeDrawer(Gravity.RIGHT);
                break;
            case R.id.tv_menu_filter_confrim:
                mDrawerLayout.closeDrawer(Gravity.RIGHT);
                // TODO: 2018/5/12 刷新界面,设备列表，警告，统计，其它均不刷新
                Bundle bundle = new Bundle();
                bundle.putBoolean("fromOtherAction", true);
                switch (mMenuIndex) {
                    case 0:
                        // TODO: 2018/5/18 直接newfragment然后 替换
                        DeviceListFragment listFragment = new DeviceListFragment();
                        listFragment.setArguments(bundle);
                        showTargetFragment(listFragment);
                        break;
                    case 1:
                        // TODO: 2018/5/18 直接newfragment然后 替换
                        AlarmListFragment alarmListFragment = new AlarmListFragment();
                        alarmListFragment.setArguments(bundle);
                        showTargetFragment(alarmListFragment);
                        break;
                    case 2:
                        // TODO: 2018/5/18 直接newfragment然后 替换
                        StatisticsFragment statisticsFragment = new StatisticsFragment();
                        statisticsFragment.setArguments(bundle);
                        showTargetFragment(statisticsFragment);
                        break;
                }
                break;
        }
    }

    @Override
    protected boolean hasSupportTransStatusBar() {
        return true;
    }

    @Override
    protected int getStatusbarColor() {
        return R.color.c_212434;
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
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //扫描结果回调
        if (resultCode == RESULT_CODE_QR_SCAN) { //RESULT_OK = -1
            Bundle bundle = data.getExtras();
            CommonConfrimCancelDialog commonConfrimCancelDialog = new CommonConfrimCancelDialog(this);
            commonConfrimCancelDialog.show();
            commonConfrimCancelDialog.setTitle("扫描成功!");
            commonConfrimCancelDialog.setConfirmTitle("录入设备");
            commonConfrimCancelDialog.setCancelTitle("新注册");
            commonConfrimCancelDialog.hideMsg();
            commonConfrimCancelDialog.setConfirmClick(() -> {
                bundle.putString("apendFlag", 1 + "");
                IntentUtils.forward(DeviceRegActivity.class, bundle);
                commonConfrimCancelDialog.dismiss();
            });
            commonConfrimCancelDialog.setCancelClick(() -> {
                bundle.putString("apendFlag", 0 + "");
                IntentUtils.forward(DeviceRegActivity.class, bundle);
                commonConfrimCancelDialog.dismiss();
            });
            commonConfrimCancelDialog.setExtProperty();

        }
    }

    @Override
    protected void onDestroy() {
        LoggerUtils.loge(this, "onDestroy");
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
        LoggerUtils.loge("onResumeXGPushClickedResult:" + click);
    }

    private static class HandlerExtension extends Handler {
        WeakReference<com.example.xiaowusong.ui.activity.MainActivity> mActivity;

        HandlerExtension(com.example.xiaowusong.ui.activity.MainActivity activity) {
            mActivity = new WeakReference<com.example.xiaowusong.ui.activity.MainActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            com.example.xiaowusong.ui.activity.MainActivity theActivity = mActivity.get();
            if (theActivity == null) {
                return;
            }
            if (msg != null) {
                LoggerUtils.loge(XGPushConfig.getToken(theActivity));
            }
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
