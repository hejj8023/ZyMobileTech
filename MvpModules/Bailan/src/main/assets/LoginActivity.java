package com.example.xiaowusong.ui.activity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.blankj.utilcode.util.ToastUtils;
import com.example.xiaowusong.R;
import com.example.xiaowusong.base.BaseWSActivity;
import com.example.xiaowusong.bean.UserBean;
import com.example.xiaowusong.manager.UserInfoManager;
import com.example.xiaowusong.mvp.inter.LoginContract;
import com.example.xiaowusong.mvp.presenter.LoginPreseneter;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;
import com.zhiyangstudio.commonlib.corel.BaseActivity;
import com.zhiyangstudio.commonlib.utils.EmptyUtils;
import com.zhiyangstudio.commonlib.utils.IntentUtils;
import com.zhiyangstudio.commonlib.utils.LoggerUtils;
import com.zhiyangstudio.commonlib.utils.UiUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zzg on 2018/4/20.
 * 登录界面
 */

public class LoginActivity extends BaseWSActivity<LoginPreseneter, LoginContract.ILoginView> implements LoginContract
        .ILoginView {

    @BindView(R.id.name_et)
    EditText etUserName;

    @BindView(R.id.password_et)
    EditText etPwd;

    // 是否在执行登录操作
    private boolean isDoLoginging = false;

    @Override
    protected BaseActivity.PermissionListener getPermissonCallBack() {
        return null;
    }

    @Override
    protected LoginPreseneter createPresenter() {
        return new LoginPreseneter();
    }

    @Override
    public void release() {

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
        etUserName.setOnEditorActionListener((v, actionID, event) -> {
            etPwd.requestFocus();
            return true;
        });
        etPwd.setOnEditorActionListener((v, actionID, event) -> {
            doLogin();
            return true;
        });

        etPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                LoggerUtils.loge("etPwd beforeTextChanged");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                LoggerUtils.loge("etPwd onTextChanged");
            }

            @Override
            public void afterTextChanged(Editable s) {
                LoggerUtils.loge("etPwd afterTextChanged");
                if (s != null) {
                    if (s.length() < 6) {
                        etPwd.setError(UiUtils.getStr(R.string.trip_pwd_min_str));
                        findViewById(R.id.login_btn).setBackgroundResource(R.drawable.login_bg_selector);
                    } else if (s.length() > 16) {
                        etPwd.setError(UiUtils.getStr(R.string.tip_pwd_max_str));
                        findViewById(R.id.login_btn).setBackgroundResource(R.drawable.login_bg_selector);
                    } else {
                        findViewById(R.id.login_btn).setBackgroundResource(R.drawable.shape_login_btn_focus);
                    }
                }
            }
        });
    }

    @Override
    protected boolean hasSupportTransStatusBar() {
        return false;
    }

    /**
     * 执行登录操作
     */
    private void doLogin() {
        showLoading("登录中,请稍候...");
        isDoLoginging = true;
        mPresenter.login();
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
    protected void onResume() {
        super.onResume();
        // TODO: 2018/5/16 还原主界面退出或注销退出前的登录状态
        if (UserInfoManager.getExitFromHome()) {
            UserBean userInfo = UserInfoManager.getUserInfo();
            if (userInfo != null) {
                String username = userInfo.getUsername();
                if (EmptyUtils.isNotEmpty(username)) {
                    etUserName.setText(username);
                }

                String password = userInfo.getPassword();
                if (EmptyUtils.isNotEmpty(password)) {
                    etPwd.setText(password);
                }
            }
        }

        if (UserInfoManager.getExitFromLogoff()) {
            UserBean userInfo = UserInfoManager.getUserInfo();
            if (userInfo != null) {
                String username = userInfo.getUsername();
                if (EmptyUtils.isNotEmpty(username)) {
                    etUserName.setText(username);
                }
            }
        }

        // TODO: 2018/5/16 根据密码长度修改登录按钮的状态
        Editable text = etPwd.getText();
        if (text != null) {
            String str = text.toString().trim();
            if (str.length() >= 6) {
                findViewById(R.id.login_btn).setBackgroundResource(R.drawable.shape_login_btn_focus);
            }
        }
    }

    @OnClick({R.id.login_btn})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                doLogin();
                break;
        }
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
    public void onLoginComplete(int code) {
        hideLoading();
        LoggerUtils.loge(this, "currentThreadId = " + Thread.currentThread().getId());
        if (code != 1) {
            UiUtils.showToastSafe("登录失败");
            return;
        }

        /*UserBean userInfo = UserInfoManager.getUserInfo();
        String userName = Const.TMP_DATA.CURRENT_USER_NAME;
        if (userInfo != null && EmptyUtils.isNotEmpty(userInfo.getUsername())) {
            userName = userInfo.getUsername();
        }*/
        /*
        注册信鸽服务的接口
        如果仅仅需要发推送消息调用这段代码即可
        登录成功了这里肯定能取到用户名
        */
        LoggerUtils.loge("XGPushManager.registerPush");
        XGPushManager.registerPush(
                getApplicationContext(),
                getUserName(),
                new XGIOperateCallback() {
                    @Override
                    public void onSuccess(Object data, int flag) {
                        LoggerUtils.loge("+++ register push sucess. token:" + data +
                                "flag" + flag);
                    }

                    @Override
                    public void onFail(Object data, int errCode, String msg) {
                        LoggerUtils.loge(
                                "+++ register push fail. token:" + data
                                        + ", errCode:" + errCode + ",msg:"
                                        + msg);
                    }
                });

        // 获取token
        XGPushConfig.getToken(this);
        UiUtils.showToastSafe("登录成功");
        IntentUtils.forward(com.example.xiaowusong.ui.activity.MainActivity.class);
        finish();
    }


    @Override
    public void onAccountError(String errorMsg) {
        ToastUtils.showShort(errorMsg);
    }
}
