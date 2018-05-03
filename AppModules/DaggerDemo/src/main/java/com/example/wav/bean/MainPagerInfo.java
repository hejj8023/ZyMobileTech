package com.example.wav.bean;

import android.support.v4.app.Fragment;

import com.example.wav.ui.fragment.HomeFragment;
import com.example.wav.ui.fragment.SettingFragment;
import com.zhiyangstudio.commonlib.corel.BaseFragment;

public enum MainPagerInfo {
    HOMEPAGE("首页", 0, HomeFragment.class, new HomeFragment()),
    SETTING("设置", 0, SettingFragment.class, new
            SettingFragment());

    private final Fragment mFragment;
    private String mTitle;
    private Class<? extends BaseFragment> mFragCls;
    private int mIndicatorIcon;

    MainPagerInfo(String title, int indicatorIcon, Class<? extends BaseFragment> fragCls, Fragment pFragment) {
        this.mTitle = title;
        this.mIndicatorIcon = indicatorIcon;
        this.mFragCls = fragCls;
        this.mFragment = pFragment;
    }

    public String getTitle() {
        return mTitle;
    }

    public Class<? extends BaseFragment> getFragmentCls() {
        return mFragCls;
    }

    public Fragment getFragment() {
        return mFragment;
    }

    public int getIndicatorIcon() {
        return mIndicatorIcon;
    }
}