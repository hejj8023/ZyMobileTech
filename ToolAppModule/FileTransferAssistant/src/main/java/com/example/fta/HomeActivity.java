package com.example.fta;

import android.view.View;


import com.zhiyangstudio.sdklibrary.common.corel.BaseActivity;

import butterknife.OnClick;

/**
 * Created by zzg on 2018/4/1.
 */

public class HomeActivity extends BaseActivity {
    @Override
    protected int getContentViewId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected PermissionListener getPermissonCallBack() {
        return null;
    }

    @OnClick({R.id.btn_ftp, R.id.btn_bluebooth, R.id.btn_hot_point,
            R.id.btn_tcp, R.id.btn_udp, R.id.btn_wifi,
            R.id.btn_nfc})
    public void onViewClick(View view) {
        Class<? extends BaseActivity> tCls = null;
        switch (view.getId()) {
            case R.id.btn_ftp:
                tCls = FtpHomeActivity.class;
                break;
            case R.id.btn_hot_point:
                break;
            case R.id.btn_bluebooth:
                break;
            case R.id.btn_udp:
                break;
            case R.id.btn_tcp:
                break;
            case R.id.btn_wifi:
                break;
            case R.id.btn_nfc:
                tCls = NFCHomeActivity.class;
                break;
        }
        if (tCls != null) {
            forward(tCls);
        }
    }
}
