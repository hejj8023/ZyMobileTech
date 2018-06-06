package com.example.daw.mvp.presenter;

import com.example.daw.mvp.contract.RegisterContract;
import com.zysdk.vulture.clib.mvp.presenter.BasePresenter;

import javax.inject.Inject;

/**
 * Created by example on 2018/5/3.
 */

public class RegisterPresenter extends BasePresenter<RegisterContract.IRegisterView> implements
        RegisterContract.IRegisterPresenter {
    @Inject
    public RegisterPresenter() {

    }
}
