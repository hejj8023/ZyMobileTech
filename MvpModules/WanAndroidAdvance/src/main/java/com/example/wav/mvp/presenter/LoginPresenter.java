package com.example.wav.mvp.presenter;

import com.example.wav.Const;
import com.example.wav.DataManager;
import com.example.wav.bean.AccountInfo;
import com.example.wav.mvp.contract.LoginContract;
import com.example.wav.mvp.model.LoginModel;
import com.zhiyangstudio.commonlib.mvp.presenter.BasePresenter;
import com.zhiyangstudio.commonlib.net.callback.AbsBaseObserver;
import com.zhiyangstudio.commonlib.utils.LoggerUtils;

import javax.inject.Inject;

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
                AbsBaseObserver<AccountInfo>(this, LoginModel.class.getName()) {
                    @Override
                    public void onNext(AccountInfo accountInfo) {
                        LoggerUtils.loge("accountInfo = " + accountInfo);
                        printExtInfo();
                        mILoginView.changeState(accountInfo.getFlag());
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideLoading();
                        // TODO: 2018/5/2 这里说明登录接口请求成功的
                        AccountInfo accountInfo = Const.TMP_DATA.ACCOUNT_INFO;
                        printExtInfo();
                        if (accountInfo != null) {
                            mILoginView.changeState(accountInfo.getFlag());
                            return;
                        }
                        // 只单独处理登录
                        mILoginView.changeState(0);
                    }
                });
    }

    private void printExtInfo() {
        String defaultUserId = DataManager.getDefaultUserId();
        String defaultGroupId = DataManager.getDefaultGroupId();
        LoggerUtils.loge("defaultUserId = " + defaultUserId + " , defaultGroupId = " + defaultGroupId);
    }
}
