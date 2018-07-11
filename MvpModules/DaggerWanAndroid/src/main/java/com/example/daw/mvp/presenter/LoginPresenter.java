package com.example.daw.mvp.presenter;

import com.example.daw.R;
import com.example.daw.bean.UserBean;
import com.example.daw.manager.DataManager;
import com.example.daw.mvp.contract.LoginContract;
import com.example.daw.mvp.model.LoginModel;
import com.zysdk.vulture.clib.mvp.presenter.BasePresenter;
import com.zysdk.vulture.clib.net.callback.RxObserver;
import com.zysdk.vulture.clib.utils.EmptyUtils;
import com.zysdk.vulture.clib.utils.UiUtils;

import javax.inject.Inject;

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
        mLoginView.showLoading(ResourceUtils.getStr(R.string.tip_login));

        // {"data":null,"errorCode":-1,"errorMsg":"账号密码不匹配！"}
        // {"data":{"collectIds":[2835],"email":"","icon":"","id":4642,"password":"12345678",
        // "type":0,"username":"xfgczzg"},"errorCode":0,"errorMsg":""}
        /*mLoginModel.login(mUserName, mPwd, new AbsBaseObserver<ResponseBody>(this, LoginModel
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
        });*/
        mLoginModel.login(mUserName, mPwd,
                new RxObserver<UserBean>(this, LoginModel.class.getName()) {
                    @Override
                    protected void onSucess(UserBean data) {
                        if (data != null) {
                            DataManager.saveUserBean(data);
                            DataManager.saveLogin(true);
                            // 登录成功
                            mLoginView.showFail("登录成功");
                            mLoginView.loginSucess();
                        } else {
                            mLoginView.showFail("登录失败");
                            mLoginView.loginFailure();
                        }
                    }

                    @Override
                    protected void onFailure(int errorCode, String errorMsg) {
                        mLoginView.showFail(errorMsg);
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
