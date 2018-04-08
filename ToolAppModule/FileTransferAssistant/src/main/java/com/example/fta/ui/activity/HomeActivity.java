package com.example.fta.ui.activity;

import android.view.View;

import com.example.fta.R;
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

    @OnClick({R.id.btn_ftp, R.id.btn_bluebooth, R.id.btn_socket,
            R.id.btn_socket_wifi_hotspot, R.id.btn_wifi, R.id.btn_nfc,
            R.id.btn_http
    })
    public void onViewClick(View view) {
        Class<? extends BaseActivity> tCls = null;
        switch (view.getId()) {
            case R.id.btn_ftp:
                tCls = FtpHomeActivity.class;
                break;
            case R.id.btn_bluebooth:
                break;
            case R.id.btn_http:
                tCls = HttpHomeActivity.class;
                break;
            case R.id.btn_socket:
                tCls = Socket1HomeActivity.class;
                break;
            case R.id.btn_socket_wifi_hotspot:
                tCls = WifiHotspotHomeActivity.class;
                break;
            case R.id.btn_wifi:
                tCls = WifiHomeActivity.class;
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
