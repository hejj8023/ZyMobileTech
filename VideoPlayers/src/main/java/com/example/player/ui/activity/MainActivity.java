package com.example.player.ui.activity;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.player.R;
import com.example.player.dialog.PlayModeSelectDialog;
import com.example.player.ui.fragment.VideoListMenuFragment;
import com.zhiyangstudio.commonlib.corel.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @BindView(R.id.toolbar2)
    Toolbar mToolbar;
    @BindView(R.id.drawer_layout2)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.navgation_view)
    NavigationView mNavigationView;

    @Override
    public int getContentId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        mToolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    @Override
    public void addListener() {
        mNavigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_test1:

                    break;
                case R.id.action_test2:
                    break;
                case R.id.action_test3:
                    break;
                case R.id.action_test4:
                    break;
            }
            return true;
        });
    }

    @Override
    public void initData() {

        getSupportFragmentManager().beginTransaction().replace(R.id.rl_video_list, new
                VideoListMenuFragment()).commitAllowingStateLoss();
    }

    @Override
    public void refreshUi() {

    }

    @Override
    public void release() {

    }

    @Override
    protected PermissionListener getPermissonCallBack() {
        return null;
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
            case R.id.action_vlist:
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
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void closeDrawer() {
        mDrawerLayout.closeDrawers();
    }

    @OnClick(R.id.btn_test)
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_test:
                PlayModeSelectDialog modeSelectDialog = new PlayModeSelectDialog
                        (MainActivity.this);
                modeSelectDialog.show();
                modeSelectDialog.setTitle("更新提示");
                modeSelectDialog.setMessage("app升级中,请耐心等待...");
                break;
        }
    }
}
