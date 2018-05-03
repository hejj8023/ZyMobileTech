package com.example.daw;

import android.support.design.widget.TextInputEditText;
import android.view.View;

import com.example.daw.base.BaseAdvActivity;
import com.example.daw.mvp.contract.LoginContract;
import com.example.daw.mvp.presenter.LoginPresenter;
import com.zhiyangstudio.commonlib.utils.IntentUtils;

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

    @OnClick({R.id.btn_login, R.id.btn_account_reg})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                break;
            case R.id.btn_account_reg:
                IntentUtils.forward(RegisterActivity.class);
                finish();
                break;
        }
    }
}
