package com.example.xiaowusong.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bigkoo.pickerview.view.TimePickerView;
import com.example.xiaowusong.Const;
import com.example.xiaowusong.R;
import com.example.xiaowusong.bean.UserBean;
import com.example.xiaowusong.manager.UserInfoManager;
import com.example.xiaowusong.mvp.inter.SettingContract;
import com.example.xiaowusong.mvp.presenter.SettingPresenter;
import com.example.xiaowusong.ui.activity.AboutActivity;
import com.example.xiaowusong.ui.activity.ChangePwdActivity;
import com.tencent.android.tpush.XGPushManager;
import com.zhiyangstudio.commonlib.mvp.BaseMVPSupportFragment;
import com.zhiyangstudio.commonlib.utils.IntentUtils;
import com.zhiyangstudio.commonlib.utils.PreUtils;
import com.zhiyangstudio.commonlib.utils.UiUtils;
import com.zhiyangstudio.commonlib.widget.dialog.LoadingDialog;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import butterknife.OnClick;

/**
 * Created by zzg on 2018/4/29.
 */

public class SettingFragment extends BaseMVPSupportFragment<SettingPresenter, SettingContract.ISettingView>
        implements SettingContract.ISettingView {

    private TimePickerView pvTime;

    @OnClick({
            R.id.login_out_layout,
            R.id.login_out_btn,
            R.id.account_safe_layout,
            R.id.about_layout,
            R.id.secrete_layout
    })
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.login_out_layout:
            case R.id.login_out_btn:
                LoadingDialog.show(mActivity, UiUtils.getStr(R.string.tip_logoff), true, null);
                UserBean userInfo = UserInfoManager.getUserInfo();
                SecretKeySpec aesKey = UserInfoManager.getAesKey();
                PreUtils.clearAll();
                // TODO: 2018/5/16 用户名是不能被清除的
                UserInfoManager.saveAesKey(aesKey);
                UserInfoManager.saveUserInfo(userInfo);
                UserInfoManager.saveExitFromLogoff(true);
                // TODO: 2018/5/30 取消账号的绑定
                XGPushManager.registerPush(getActivity().getApplicationContext(), "*");
                //清除通知栏消息
                XGPushManager.cancelAllNotifaction(getActivity().getApplicationContext());
                Const.TMP_DATA.CURRENT_USER_NAME = "";
                UiUtils.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        LoadingDialog.hideDialog();
                        IntentUtils.forward(com.example.xiaowusong.ui.activity.LoginActivity.class);
                        mActivity.finish();
                    }
                }, 150);
                break;
            case R.id.account_safe_layout:
                IntentUtils.forward(ChangePwdActivity.class);
                break;
            case R.id.about_layout:
                IntentUtils.forward(AboutActivity.class);
                break;
            case R.id.secrete_layout:
                break;
        }
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.getTime());
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    @Override
    public int getContentId() {
        return R.layout.fragment_settings;
    }

    @Override
    public void initView() {

    }

    @Override
    public void addListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void refreshUi() {

    }

    @Override
    public void showLoading(String msg) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showFail(String msg) {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    protected SettingPresenter createPresenter() {
        return new SettingPresenter();
    }

    @Override
    protected void initArguments(Bundle bundle) {

    }
}
