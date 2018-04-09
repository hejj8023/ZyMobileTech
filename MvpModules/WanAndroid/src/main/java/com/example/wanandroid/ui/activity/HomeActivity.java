package com.example.wanandroid.ui.activity;

import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseWanAndroidActivity;
import com.example.wanandroid.mvp.contract.HomeContract;
import com.example.wanandroid.mvp.presenter.HomePresenter;

/**
 * Created by zzg on 2018/4/9.
 */

public class HomeActivity extends BaseWanAndroidActivity<HomePresenter, HomeContract.IHomeView> implements HomeContract.IHomeView {
    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    protected boolean hasShowToolbar() {
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
    protected void initContentView() {

    }

    @Override
    protected PermissionListener getPermissonCallBack() {
        return null;
    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void initData() {

    }
}
