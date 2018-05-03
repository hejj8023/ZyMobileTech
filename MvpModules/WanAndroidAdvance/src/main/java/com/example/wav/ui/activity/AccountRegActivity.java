package com.example.wav.ui.activity;

import android.view.View;
import android.widget.EditText;

import com.example.wav.R;
import com.example.wav.base.BaseAdvActivity;
import com.example.wav.mvp.contract.AccountRegContract;
import com.example.wav.mvp.presenter.AccountRegPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by example on 2018/5/3.
 * 注册成功之后调用登录接口，登录成功之后缓存用户数据，调用获取客户列表和客户分组接口
 */

public class AccountRegActivity extends BaseAdvActivity<AccountRegPresenter, AccountRegContract
        .IAccountRegView> implements AccountRegContract.IAccountRegView {

    @BindView(R.id.et_uname)
    EditText etUserName;

    @BindView(R.id.et_pwd)
    EditText etPwd;

    @BindView(R.id.et_con_pwd)
    EditText etConPwd;

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
        return R.layout.activity_account_reg;
    }

    @Override
    public String getConfrimPwd() {
        return etConPwd.getText().toString().trim();
    }

    @Override
    public String getUserName() {
        return etUserName.getText().toString().trim();
    }

    @Override
    public String getPwd() {
        return etPwd.getText().toString().trim();
    }

    @Override
    public void changeState(int flag) {

    }

    @OnClick(R.id.btn_account_reg)
    public void onViewClick(View view) {
        mPresenter.regAccount();
    }
}
