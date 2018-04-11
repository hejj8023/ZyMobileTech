package com.example.wanandroid.ui.activity;

import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseWanAndroidActivity;
import com.example.wanandroid.manager.UserInfoManager;
import com.example.wanandroid.mvp.contract.LoginContract;
import com.example.wanandroid.mvp.presenter.LoginPresenter;
import com.zhiyangstudio.commonlib.CommonConst;

/**
 * Created by example on 2018/4/9.
 */

public class SplashActivity extends BaseWanAndroidActivity<LoginPresenter, LoginContract
        .ILoginView> implements LoginContract.ILoginView {

    @Override
    public String getUserName() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public void onAccountError(String msg) {

    }

    @Override
    public void showResult(String msg) {

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

    @Override
    protected PermissionListener getPermissonCallBack() {
        return new PermissionListener() {
            @Override
            public void onGrant(int code) {
                if (code == CommonConst.PERMISSION.REQ_SDCARD_PERMISSION) {

                }
                goMain();
            }

            @Override
            public void onDeny(int code) {
                if (code == CommonConst.PERMISSION.REQ_SDCARD_PERMISSION) {

                }
                goMain();
            }
        };
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
        }
        forward(HomeActivity.class);

    }

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
    public void release() {

    }


}
