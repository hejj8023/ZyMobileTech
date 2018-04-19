package com.example.wanandroid.ui.activity;

import android.view.View;
import android.widget.EditText;

import com.blankj.utilcode.util.ToastUtils;
import com.example.wanandroid.Const;
import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseWanAndroidActivity;
import com.example.wanandroid.mvp.contract.LoginContract;
import com.example.wanandroid.mvp.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zhiyang on 2018/4/19.
 */

public class LoginActivity extends BaseWanAndroidActivity<LoginPresenter, LoginContract
        .ILoginView> implements LoginContract.ILoginView {

    @BindView(R.id.et_username)
    EditText etUserName;

    @BindView(R.id.et_password)
    EditText etPwd;

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

    @Override
    public String getUserName() {
        return etUserName.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return etPwd.getText().toString().trim();
    }

    @Override
    public void onAccountError(String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void showResult(String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void onLoginStatusChange(int status) {
        if (status == Const.LOGIN_REG_STATUS.REG_SUCESS || status == Const.LOGIN_REG_STATUS.LOGIN_SUCESS) {
            finish();
        }
    }

    @Override
    public void showLoading(String msg) {
        showLoadingDialog(msg);
    }

    @Override
    public void hideLoading() {
        hideLoadingDialog();
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
        return R.layout.activity_login;
    }

    @OnClick({R.id.bt_login, R.id.bt_register})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                mPresenter.login();
                break;
            case R.id.bt_register:
                mPresenter.register();
                break;
        }
    }
}
