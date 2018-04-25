package com.example.wav.ui.activity;

import android.os.Bundle;

import com.example.wav.R;
import com.example.wav.base.BaseDIPresenterActivity;
import com.example.wav.mvp.contract.MainContract;
import com.example.wav.mvp.presenter.MainPresenter;
import com.gyf.barlibrary.ImmersionBar;

public class MainActivity extends BaseDIPresenterActivity<MainPresenter, MainContract.IMainView> implements MainContract.IMainView {

    private ImmersionBar mImmersionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void beforeSetContentView() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarColor(R.color._0091ea);
        mImmersionBar.fitsSystemWindows(true);
        mImmersionBar.init();
    }

    @Override
    protected PermissionListener getPermissonCallBack() {
        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //必须调用该方法，防止内存泄漏
        if (mImmersionBar != null) {
            mImmersionBar.destroy();
        }
    }

    @Override
    public int getContentId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        mPresenter.log("initView");
    }

    @Override
    public void addListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void refreshUi() {

    }

    @Override
    public void release() {

    }


    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
}
