package com.example.wanandroid.ui.activity;

import android.os.Process;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseWanAndroidActivity;
import com.example.wanandroid.mvp.contract.HomeContract;
import com.example.wanandroid.mvp.presenter.HomePresenter;
import com.example.wanandroid.ui.fragment.HomeFragment;
import com.example.wanandroid.ui.fragment.TreeFragment;
import com.zhiyangstudio.commonlib.components.receiver.AppInstallReceiver;
import com.zhiyangstudio.commonlib.utils.IntentUtils;
import com.zhiyangstudio.commonlib.utils.UiUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zzg on 2018/4/9.
 */

public class HomeActivity extends BaseWanAndroidActivity<HomePresenter, HomeContract.IHomeView> implements HomeContract.IHomeView {

    @BindView(R.id.drawer_home)
    DrawerLayout drawerLayout;

    @BindView(R.id.navigation_view)
    NavigationView navigationView;

    private Button[] buttons = new Button[2];
    private ActionBarDrawerToggle mToggle;
    private int index;
    private int currentPosition;
    private ImageView menuUserIcon;
    private TextView menuLoginStatus;
    private Fragment[] fragments;
    private String[] appTitles = new String[]{
            UiUtils.getStr(R.string.app_name), "知识体系"
    };

    private long mExitTime;
    private AppInstallReceiver mAppInstallReceiver;

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    protected boolean initToolBar() {
        toolbar.setTitle(appTitles[0]);
        toolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);
        return true;
    }

    @Override
    protected void onNavigationClick() {

    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onDestroy() {
        mAppInstallReceiver.unRegister();
        super.onDestroy();
    }

    @Override
    public void addListener() {
        drawerLayout.addDrawerListener(mToggle);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener((item -> {
            switch (item.getItemId()) {
                case R.id.action_about:
                    ToastUtils.showShort("关于我们");
                    break;
                case R.id.action_logout:
                    ToastUtils.showShort("退出登录");
                    break;
                case R.id.action_subjects:
                    ToastUtils.showShort("喜欢的文章");
                    break;
            }
            return true;
        }));
    }

    @Override
    public void initView() {
        initMainMenus();
        initDrawerLayout();
        initNavigationHeaderView();
    }

    private void initMainMenus() {
        buttons[0] = findViewById(R.id.btn_home);
        buttons[0].setSelected(true);
        buttons[1] = findViewById(R.id.btn_knowtechs);

        for (int i = 0; i < buttons.length; i++) {
            if (i != currentPosition) {
                buttons[i].setScaleX(0.9f);
                buttons[i].setScaleY(0.9f);
            }
        }
    }

    private void initDrawerLayout() {
        mToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.open, R.string.close);
        mToggle.syncState();
    }

    private void initNavigationHeaderView() {
        View headerView = navigationView.getHeaderView(0);
        menuUserIcon = headerView.findViewById(R.id.iv_user_icon);
        menuLoginStatus = headerView.findViewById(R.id.tv_login_status);
    }

    @Override
    public void initData() {
        initFragments();
        mAppInstallReceiver = new AppInstallReceiver();
        mAppInstallReceiver.register();
    }

    @Override
    public void refreshUi() {

    }

    @Override
    public void release() {

    }

    private void initFragments() {
        fragments = new Fragment[]{new HomeFragment(), new TreeFragment()};
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fl_home_container, fragments[0]).show(fragments[0]).commitAllowingStateLoss();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                ToastUtils.showShort("搜索");
                IntentUtils.forward(SearchActivity.class);
                break;
        }
        return true;
    }

    @OnClick({R.id.btn_home, R.id.btn_knowtechs})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_home:
                index = 0;
                break;
            case R.id.btn_knowtechs:
                index = 1;
                break;
        }
        if (currentPosition != index) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.hide(fragments[currentPosition]);
            if (!fragments[index].isAdded()) {
                ft.add(R.id.fl_home_container, fragments[index]);
            }
            ft.show(fragments[index]).commitAllowingStateLoss();
            buttons[currentPosition].setSelected(false);
            buttons[index].setSelected(true);
            scaleView();
            currentPosition = index;
            setAppTitles(currentPosition);
        }
    }

    private void scaleView() {
        buttons[currentPosition].animate().scaleX(0.9f).scaleY(0.9f).setDuration(150).start();
        buttons[index].animate().scaleX(1.0f).scaleY(1.0f).setDuration(150).start();
    }

    private void setAppTitles(int position) {
        toolbar.setTitle(appTitles[position]);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (drawerLayout.isDrawerOpen(Gravity.START)) {
                drawerLayout.closeDrawer(Gravity.START);
                return true;
            }

            if (System.currentTimeMillis() - mExitTime < 2000) {
                finish();
                Process.killProcess(Process.myPid());
            } else {
                mExitTime = System.currentTimeMillis();
                ToastUtils.showShort("请再按一次退出程序...");
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
