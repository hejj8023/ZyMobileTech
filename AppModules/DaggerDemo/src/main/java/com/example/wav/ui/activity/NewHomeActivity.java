package com.example.wav.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import com.example.wav.mvp.contract.NewHomeContract;
import com.example.wav.mvp.presenter.NewHomePresenter;
import com.example.wav.ui.fragment.HomeFragment;
import com.example.wav.widget.TabIndicator;
import com.zhiyangstudio.commonlib.CommonConst;

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
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    @BindView(android.R.id.tabhost)
    FragmentTabHost mFragmentTabHost;
    private List<MainPagerInfo> mMainPagerInfos;
    private MainPagerAdapter mPagerAdapter;
    private Menu menu;

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
    }

    @Override
    public void refreshUi() {

    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_device, menu);
        this.menu = menu;
        return true;
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
}
