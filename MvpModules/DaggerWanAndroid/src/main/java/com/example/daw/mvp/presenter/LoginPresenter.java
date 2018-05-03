package com.example.daw.mvp.presenter;

import com.example.daw.mvp.contract.LoginContract;
import com.zhiyangstudio.commonlib.mvp.presenter.BasePresenter;

import javax.inject.Inject;

/**
 * Created by example on 2018/5/3.
 */

public class LoginPresenter extends BasePresenter<LoginContract.ILoginView> implements
        LoginContract.ILoginPresenter {
    @Inject
    public LoginPresenter() {
        
    }
}
