package com.example.wav.ui.activity;

import android.view.Menu;

import com.example.wav.R;
import com.example.wav.base.BaseAdvActivity;
import com.example.wav.mvp.contract.HomeContract;
import com.example.wav.mvp.presenter.HomePresenter;

/**
 * Created by example on 2018/4/27.
 */

public class HomeActivity extends BaseAdvActivity<HomePresenter, HomeContract.IHomeView>
        implements HomeContract.IHomeView {
    @Override
    public void initView() {

    }

    @Override
    public void initData() {

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
        setTitle("玩Android-Adv");
        return false;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }
}
