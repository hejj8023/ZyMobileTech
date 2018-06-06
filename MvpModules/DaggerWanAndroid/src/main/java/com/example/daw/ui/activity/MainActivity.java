package com.example.daw.ui.activity;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.daw.R;
import com.example.daw.ui.fragment.HomeFragment;
import com.example.daw.ui.fragment.KnowledgeFragment;
import com.example.daw.ui.fragment.NavigationFragment;
import com.example.daw.ui.fragment.ProjectFragment;
import com.example.daw.utils.BottomNavigationViewHelper;
import com.gyf.barlibrary.ImmersionBar;
import com.zysdk.vulture.clib.corel.BaseFragment;
import com.zysdk.vulture.clib.corel.BaseToolbarSupportActivity;
import com.zysdk.vulture.clib.utils.EmptyUtils;
import com.zysdk.vulture.clib.utils.LoggerUtils;
import com.zysdk.vulture.clib.utils.StatusBarUtils;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseToolbarSupportActivity {

    @BindView(R.id.bottom_menu)
    BottomNavigationView mNavigationView;

    @BindView(R.id.common_top_title_tv)
    TextView tvTitle;

    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    private ImmersionBar mImmersionBar;
    private ArrayList<BaseFragment> mFragments;

    @Override
    protected boolean initToolBar() {
        return false;
    }

    @Override
    protected int getToolbarBgColor() {
        return R.color._0091ea;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        setTitle("");
        setMainTitle("主界面");

        initDrawerLayout();
        // TODO: 2018/5/4 需要设置这个要不然其它条目不显示
        BottomNavigationViewHelper.disableShiftMode(mNavigationView);
    }

    private void initDrawerLayout() {
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                mToolbar,
                R.string.open_drawer,
                R.string.close_drawer) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                MainActivity listener = MainActivity.this;
                LoggerUtils.loge(listener, "onDrawerSlide slideOffset = " + slideOffset);

                // 获取Drawlayout中的第一个子布局
                // 获取抽屉的view
                View mContent = mDrawerLayout.getChildAt(0);
                float scale = 1 - slideOffset;
                LoggerUtils.loge(listener, "scale = " + scale);
                float endScale = 0.8f + scale * 0.2f;
                LoggerUtils.loge(listener, "endScale = " + endScale);
                float startScale = 1 - 0.3f * scale;
                LoggerUtils.loge(listener, "startScale = " + startScale);

                // 设置左边菜单滑动后占据屏幕大小
                drawerView.setScaleX(startScale);
                drawerView.setScaleY(startScale);
                // 设置菜单透明度
                float alpha = 0.6f + 0.4f * (1 - scale);
                LoggerUtils.loge(listener, "alpha = " + alpha);
                drawerView.setAlpha(alpha);

                // 设置内容界面水平和垂直方向偏移量
                // 在滑动时内容界面的宽度为: 屏幕的宽度-菜单界面所占的宽度
                float rotationX = drawerView.getMeasuredWidth() * (1 - scale);
                LoggerUtils.loge(listener, "rotationX = " + rotationX);
                mContent.setTranslationX(rotationX);
                // 设置内容界面操作无效(如果有buttom就会点击无效)
                mContent.invalidate();
                // 设置右边菜单滑动后的占据屏幕大小
                mContent.setScaleX(endScale);
                mContent.setScaleY(endScale);

            }
        };
        drawerToggle.syncState();
        mDrawerLayout.addDrawerListener(drawerToggle);
    }

    @Override
    public void addListener() {
        mNavigationView.setOnNavigationItemSelectedListener(item -> {
            int index = -1;
            String titleStr = "";
            switch (item.getItemId()) {
                case R.id.tab_main_pager:
                    index = 0;
                    titleStr = "主界面";
                    break;
                case R.id.tab_knowledge_hierarchy:
                    index = 1;
                    titleStr = "知识体系";
                    break;
                case R.id.tab_navigation:
                    index = 2;
                    titleStr = "导航";
                    break;
                case R.id.tab_project:
                    index = 3;
                    titleStr = "项目";
                    break;
            }
            if (index != -1) {
                showFragment(mFragments.get(index));
                setMainTitle(titleStr);
                return true;
            }
            return false;
        });
    }

    @Override
    public void initData() {
        mFragments = new ArrayList<>();
        mFragments.add(new HomeFragment());
        mFragments.add(new KnowledgeFragment());
        mFragments.add(new NavigationFragment());
        mFragments.add(new ProjectFragment());
        showFragment(mFragments.get(0));
    }

    private void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_content_container, fragment)
                .commitAllowingStateLoss();
    }

    public void setMainTitle(String str) {
        if (EmptyUtils.isEmpty(str))
            return;
        tvTitle.setText(str);
    }

    @Override
    public void beforeSetContentView() {
        // TODO: 2018/5/5 第三方框架
//        mImmersionBar = ImmersionBar.with(this);
//        mImmersionBar.statusBarColor(R.color._0091ea);
//        mImmersionBar.init();

        // sdk库中的工具方法——》setWindowStatusBarColor
//        StatusBarUtils.setWindowStatusBarColor(this, R.color._0091ea);
    }


    @Override
    public void beforeSubContentInit() {
        StatusBarUtils.immersive(this);
//        StatusBarUtils.setPaddingSmart(this, mToolbar);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //必须调用该方法，防止内存泄漏
//        if (mImmersionBar != null) {
//            mImmersionBar.destroy();
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
