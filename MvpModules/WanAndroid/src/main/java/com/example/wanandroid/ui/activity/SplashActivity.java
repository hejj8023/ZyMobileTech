package com.example.wanandroid.ui.activity;

import com.blankj.utilcode.util.ToastUtils;
import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseWanAndroidActivity;
import com.example.wanandroid.bean.UserBean;
import com.example.wanandroid.manager.UserInfoManager;
import com.example.wanandroid.mvp.contract.LoginContract;
import com.example.wanandroid.mvp.presenter.LoginPresenter;
import com.zhiyangstudio.commonlib.utils.CommonUtils;
import com.zhiyangstudio.commonlib.utils.IntentUtils;

/**
 * Created by example on 2018/4/9.
 */

public class SplashActivity extends BaseWanAndroidActivity<LoginPresenter, LoginContract
        .ILoginView> implements LoginContract.ILoginView {

    private UserBean userBean;

    @Override
    public String getUserName() {
        return userBean.getUserName();
    }

    @Override
    public String getPassword() {
        return userBean.getPassword();
    }

    @Override
    public void onAccountError(String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void showResult(String msg) {

    }

    @Override
    public void onLoginStatusChange(int status) {

    }

    @Override
    public void initView() {
        if (!CommonUtils.hasNeedCheckPermission()) {
            goMain();
        }
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

    private void goMain() {
        mH.postDelayed(() -> {
            // 自动登录
            autoLogin();
            finish();
        }, 2000);
    }

    /**
     * 自动登录
     */
    private void autoLogin() {
        if (UserInfoManager.isLogin()) {
            // 自动登录
            userBean = UserInfoManager.getUserInfo();
            if (userBean != null) {
                mPresenter.login();
            }
        }
        IntentUtils.forward(HomeActivity.class);

    }

    @Override
    protected void onPermissonGrant(int code) {
        goMain();
    }

    @Override
    protected void onPermissionDeny(int code) {
        goMain();
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected boolean initToolBar() {
        return false;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_splash;
    }


}
