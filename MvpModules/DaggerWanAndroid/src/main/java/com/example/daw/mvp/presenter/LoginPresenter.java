package com.example.daw.mvp.presenter;

import com.example.daw.R;
import com.example.daw.mvp.contract.LoginContract;
import com.example.daw.mvp.model.LoginModel;
import com.zhiyangstudio.commonlib.mvp.presenter.BasePresenter;
import com.zhiyangstudio.commonlib.net.callback.AbsBaseObserver;
import com.zhiyangstudio.commonlib.utils.EmptyUtils;
import com.zhiyangstudio.commonlib.utils.LoggerUtils;
import com.zhiyangstudio.commonlib.utils.UiUtils;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.ResponseBody;

/**
 * Created by example on 2018/5/3.
 */

public class LoginPresenter extends BasePresenter<LoginContract.ILoginView> implements
        LoginContract.ILoginPresenter {

    private final LoginModel mLoginModel;
    private LoginContract.ILoginView mLoginView;
    private String mUserName;
    private String mPwd;

    @Inject
    public LoginPresenter() {
        mLoginModel = new LoginModel();
    }

    @Override
    public void login() {
        mLoginView = getView();
        if (!verifyAccount())
            return;
        mLoginView.showLoading(UiUtils.getStr(R.string.tip_login));
        mLoginModel.login(mUserName, mPwd, new AbsBaseObserver<ResponseBody>(this, LoginModel
                .class.getName()) {
            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String result = responseBody.string();
                    LoggerUtils.loge(result);
                    if (result.startsWith("<html><head>")) {
                        mLoginView.showFail("无网络访问权限");
                        mLoginView.loginFailure();
                        return;
                    }
                    mLoginView.loginSucess();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {
                mLoginView.showFail("登录失败");
                mLoginView.loginFailure();
            }
        });
    }

    private boolean verifyAccount() {
        LoginContract.ILoginView loginView = getView();
        mUserName = loginView.getUserName();
        mPwd = loginView.getUserPassword();

        if (EmptyUtils.isEmpty(mUserName)) {
            mLoginView.showFail("用户名不能为空");
            return false;
        }

        if (EmptyUtils.isEmpty(mPwd)) {
            mLoginView.showFail("密码不能为空");
            return false;
        }

        return true;
    }
}
