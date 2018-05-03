package com.example.daw;

import com.example.daw.base.BaseAdvActivity;
import com.example.daw.mvp.contract.RegisterContract;
import com.example.daw.mvp.presenter.RegisterPresenter;

/**
 * Created by example on 2018/5/3.
 */

public class RegisterActivity extends BaseAdvActivity<RegisterPresenter, RegisterContract
        .IRegisterView> implements RegisterContract.IRegisterView {
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
        return false;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_register;
    }
}
