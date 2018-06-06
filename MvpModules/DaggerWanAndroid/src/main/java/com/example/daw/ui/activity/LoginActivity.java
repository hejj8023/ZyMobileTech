package com.example.daw.ui.activity;

import android.support.design.widget.TextInputEditText;
import android.view.View;

import com.example.daw.R;
import com.example.daw.base.BaseAdvActivity;
import com.example.daw.mvp.contract.LoginContract;
import com.example.daw.mvp.presenter.LoginPresenter;
import com.zysdk.vulture.clib.utils.IntentUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by example on 2018/5/3.
 */

public class LoginActivity extends BaseAdvActivity<LoginPresenter, LoginContract.ILoginView>
        implements LoginContract.ILoginView {

    @BindView(R.id.et_uname)
    TextInputEditText etUName;

    @BindView(R.id.et_pwd)
    TextInputEditText etPwd;
    private boolean isLogining;

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
        etUName.setOnEditorActionListener((v, actionId, event) -> {
            etPwd.requestFocus();
            return true;
        });
        etPwd.setOnEditorActionListener((v, actionId, event) -> {
            doLogin();
            return true;
        });
    }

    private void doLogin() {
        if (isLogining)
            return;
        isLogining = true;
        mPresenter.login();
    }

    @OnClick({R.id.btn_login, R.id.btn_account_reg})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                doLogin();
                break;
            case R.id.btn_account_reg:
                IntentUtils.forward(RegisterActivity.class);
                finish();
                break;
        }
    }

    @Override
    public String getUserName() {
        return etUName.getText().toString().trim();
    }

    @Override
    public String getUserPassword() {
        return etPwd.getText().toString().trim();
    }

    @Override
    public void loginFailure() {
        isLogining = false;
    }

    @Override
    public void loginSucess() {
        isLogining = false;
        IntentUtils.forward(MainActivity.class);
        finish();
    }
}
