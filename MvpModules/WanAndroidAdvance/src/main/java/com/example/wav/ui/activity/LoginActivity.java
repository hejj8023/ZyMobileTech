package com.example.wav.ui.activity;

import android.support.design.widget.TextInputEditText;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.example.wav.R;
import com.example.wav.base.BaseAdvActivity;
import com.example.wav.mvp.contract.LoginContract;
import com.example.wav.mvp.presenter.LoginPresenter;
import com.zhiyangstudio.commonlib.utils.IntentUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by example on 2018/4/28.
 */

public class LoginActivity extends BaseAdvActivity<LoginPresenter, LoginContract.ILoginView>
        implements LoginContract.ILoginView {
    @BindView(R.id.et_uname)
    TextInputEditText etUName;

    @BindView(R.id.et_pwd)
    TextInputEditText etPwd;
    // 是否在执行登录操作
    private boolean isDoLoginging = false;

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
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected boolean initToolBar() {
        return false;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void addListener() {
        etUName.setOnEditorActionListener((v, actionID, event) -> {
            etPwd.requestFocus();
            return true;
        });
        etPwd.setOnEditorActionListener((v, actionID, event) -> {
            doLogin();
            return true;
        });
    }

    private void doLogin() {
        if (isDoLoginging)
            return;
        showLoadingDialog();
        isDoLoginging = true;
        mPresenter.login();
    }

    @OnClick(R.id.btn_login)
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                doLogin();
                break;
        }
    }

    @Override
    public String getUserName() {
        return etUName.getText().toString().trim();
    }

    @Override
    public String getPwd() {
        return etPwd.getText().toString().trim();
    }

    @Override
    public void changeState(int flag) {
        hideLoadingDialog();
        isDoLoginging = false;
        if (flag != 1) {
            ToastUtils.showShort("登录失败");
            return;
        }

        ToastUtils.showShort("登录成功");
        IntentUtils.forward(NewHomeActivity.class);
        finish();
    }
}
