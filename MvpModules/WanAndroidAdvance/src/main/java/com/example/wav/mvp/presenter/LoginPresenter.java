package com.example.wav.mvp.presenter;

import com.example.wav.DataManager;
import com.example.wav.bean.AccountInfo;
import com.example.wav.mvp.contract.LoginContract;
import com.example.wav.mvp.model.LoginModel;
import com.zhiyangstudio.commonlib.mvp.presenter.BasePresenter;
import com.zhiyangstudio.commonlib.utils.LoggerUtils;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by example on 2018/4/28.
 */

public class LoginPresenter extends BasePresenter<LoginContract.ILoginView> implements
        LoginContract.ILoginPresenter {

    private final LoginModel mLoginModel;
    private LoginContract.ILoginView mILoginView;

    @Inject
    public LoginPresenter() {
        mLoginModel = new LoginModel();
    }

    @Override
    public void login() {
        mILoginView = getView();
        mLoginModel.login(mILoginView.getUserName(), mILoginView.getPwd(), "03", new
                Consumer<AccountInfo>() {
            @Override
            public void accept(AccountInfo accountInfo) throws Exception {
                LoggerUtils.loge("accountInfo = " + accountInfo);
                String defaultUserId = DataManager.getDefaultUserId();
                String defaultGroupId = DataManager.getDefaultGroupId();
                LoggerUtils.loge("defaultUserId = " + defaultUserId + " , defaultGroupId = " + defaultGroupId);
                mILoginView.changeState(accountInfo.getFlag());
            }
        });
    }
}
