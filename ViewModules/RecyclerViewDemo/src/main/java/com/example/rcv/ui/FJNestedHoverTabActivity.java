package com.example.rcv.ui;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;

import com.example.common.corel.BaseActivity;
import com.example.rcv.DemoFragment;
import com.example.rcv.R;
import com.example.rcv.adapter.CommonTabPagerAdapter;

import java.util.Arrays;

import butterknife.BindView;

/**
 * Created by zzg on 2018/3/29.
 */

public class FJNestedHoverTabActivity extends BaseActivity implements CommonTabPagerAdapter.TabPagerListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.appbar)
    AppBarLayout appBarLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @BindView(R.id.collapsingToolbar)
    CollapsingToolbarLayout collapsingToolbar;
    private CommonTabPagerAdapter adapter;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_fjnestedhovertab;
    }

    @Override
    protected void preprocess() {
        //当系统版本为4.4或者4.4以上时可以使用沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }


    @Override
    protected void initView() {
        setTitle("返回");
        collapsingToolbar.setTitle("返回");
        collapsingToolbar.setExpandedTitleColor(Color.parseColor("#00ffffff"));//设置还没收缩时状态下字体颜色
        collapsingToolbar.setCollapsedTitleTextColor(Color.WHITE);
    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void initData() {
//设置收缩后Toolbar上字体的
        adapter = new CommonTabPagerAdapter(getSupportFragmentManager()
                , 4, Arrays.asList("美食", "电影", "玩乐", "评价"), this);
        adapter.setListener(this);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    protected PermissionListener getPermissonCallBack() {
        return null;
    }

    @Override
    public Fragment getFragment(int position) {
        return DemoFragment.newInstance(position);
    }
}
