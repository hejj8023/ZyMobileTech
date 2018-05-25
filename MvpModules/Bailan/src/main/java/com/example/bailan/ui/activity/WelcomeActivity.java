package com.example.bailan.ui.activity;

import android.view.View;

import com.example.bailan.R;
import com.example.bailan.UserInfoManager;
import com.example.bailan.base.BaseBLActivity;
import com.zhiyangstudio.commonlib.utils.IntentUtils;

import butterknife.OnClick;

/**
 * Created by example on 2018/5/25.
 */

public class WelcomeActivity extends BaseBLActivity {

    @Override
    public void beforeSubContentInit() {
        super.beforeSubContentInit();
    }

    @Override
    protected void initInject() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        if (!UserInfoManager.isFirstLogin()) {
            goMain();
        }
    }

    private void goMain() {
        IntentUtils.forward(MainActivity.class);
        finish();
    }

    @Override
    public void refreshUi() {

    }

    @Override
    protected boolean initToolBar() {
        return false;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_welcome;
    }

    @OnClick(R.id.btn_login)
    public void onViewClick(View view) {
        UserInfoManager.saveIsFirstLogin(false);
        goMain();
    }
}
