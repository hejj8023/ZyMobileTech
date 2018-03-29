package com.example.zykc.ui.activity;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.example.common.corel.BaseActivity;
import com.example.zykc.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by example on 2018/3/28.
 */

public class HomeActivity extends BaseActivity {

    @BindView(R.id.drawerlayout)
    DrawerLayout drawerLayout;

    @BindView(R.id.navigation_view)
    NavigationView navigationView;

    private PermissionListener mListener = new PermissionListener() {
        @Override
        public void onGrant(int code) {
            if (code == REQ_SDCARD_PERMISSION) {
                doInit();
            }
        }

        @Override
        public void onDeny(int code) {

        }
    };

    private void doInit() {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, null,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    @Override
    protected void addListener() {

        // TODO: 2018/3/28 侧滑菜单item点击事件
        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_action_about_author:
                case R.id.menu_action_help_feedback:
                case R.id.menu_action_trans_by_pc:
                case R.id.menu_action_trans_by_web:
                case R.id.menu_action_update_app:
                    ToastUtils.showShort(getString(R.string.app_func_create_desc));
                    break;
            }
            drawerLayout.closeDrawers();
            return true;
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected PermissionListener getPermissonCallBack() {
        return mListener;
    }

    @OnClick({R.id.rl_hoem_icon, R.id.rl_conn_dev, R.id.rl_files, R.id.rl_throttle})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.rl_hoem_icon:
                drawerLayout.openDrawer(Gravity.START);
                break;
            case R.id.rl_conn_dev:
                break;
            case R.id.rl_files:
                break;
            case R.id.rl_throttle:
                break;
        }
    }

    @Override
    protected boolean hasSupportTransStatusBar() {
        return true;
    }
}
