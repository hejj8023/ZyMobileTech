package com.example.wanandroid.mvp.presenter;

import com.example.wanandroid.Const;
import com.example.wanandroid.R;
import com.example.wanandroid.bean.UserBean;
import com.example.wanandroid.manager.UserInfoManager;
import com.example.wanandroid.mvp.contract.LoginContract;
import com.example.wanandroid.mvp.model.LoginModel;
import com.zhiyangstudio.commonlib.mvp.presenter.BasePresenter;
import com.zhiyangstudio.commonlib.net.callback.RxObserver;
import com.zhiyangstudio.commonlib.utils.EmptyUtils;
import com.zhiyangstudio.commonlib.utils.UiUtils;

import io.reactivex.disposables.Disposable;

/**
 * Created by example on 2018/4/9.
 */

public class LoginPresenter extends BasePresenter<LoginContract.ILoginView> implements
        LoginContract.IUserPresenter {

    private final LoginModel mModel;
    private LoginContract.ILoginView mILoginView;
    private String mUserName;
    private String mPassword;

    public LoginPresenter() {
        mModel = new LoginModel();
    }

    @Override
    public void login() {
        if (!verifyAccount())
            return;
        mModel.login(mUserName, mPassword, new RxObserver<UserBean>(this, LoginModel.class.getName()) {
            @Override
            protected void onSucess(UserBean data) {
                // 加密保存用户信息和密钥
                UserInfoManager.saveUserInfo(data);
                UserInfoManager.saveIsLogin(true);
                mILoginView.hideLoading();
                mILoginView.showResult(UiUtils.getStr(R.string.login_sucessed));
                mILoginView.onLoginStatusChange(Const.LOGIN_REG_STATUS.LOGIN_SUCESS);
            }

            @Override
            public void onSubscribe(Disposable d) {
                mILoginView.showLoading(UiUtils.getStr(R.string.loginging));
            }

            @Override
            protected void onFailure(int errorCode, String errorMsg) {
                mILoginView.hideLoading();
                mILoginView.showResult(errorMsg);
                mILoginView.onLoginStatusChange(Const.LOGIN_REG_STATUS.LOGIN_FAIL);
            }
        });
    }

    private boolean verifyAccount() {
        mILoginView = getView();
        mUserName = mILoginView.getUserName();
        mPassword = mILoginView.getPassword();
        if (EmptyUtils.isEmpty(mUserName)) {
            mILoginView.onAccountError("用户名不能为空");
            return false;
        }
        if (EmptyUtils.isEmpty(mPassword)) {
            mILoginView.onAccountError("密码不能为空");
        }
        return true;
    }

    @Override
    public void register() {
        if (!verifyAccount())
            return;
        mModel.register(mUserName, mPassword, new RxObserver<String>(this, LoginModel.class.getName
                ()) {
            @Override
            protected void onSucess(String data) {
                mILoginView.hideLoading();
                mILoginView.onLoginStatusChange(Const.LOGIN_REG_STATUS.REG_SUCESS);
            }

            @Override
            protected void onFailure(int errorCode, String errorMsg) {
                mILoginView.hideLoading();
                mILoginView.onLoginStatusChange(Const.LOGIN_REG_STATUS.REG_FAIL);
            }

            @Override
            public void onSubscribe(Disposable d) {
                mILoginView.showLoading(UiUtils.getStr(R.string.reging));
            }
        });
    }
}
