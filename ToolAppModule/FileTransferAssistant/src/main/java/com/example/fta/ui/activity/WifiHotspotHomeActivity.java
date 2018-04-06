package com.example.fta.ui.activity;

import android.view.View;

import com.example.fta.R;
import com.zhiyangstudio.sdklibrary.common.corel.BaseActivity;

import butterknife.OnClick;

/**
 * Created by zzg on 2018/4/5.
 */

public class WifiHotspotHomeActivity extends BaseActivity {

    @Override
    protected int getContentViewId() {
        return R.layout.activity_socket2_home;
    }

    @Override
    protected void initView() {
        setTitle("Wifi热点传输");
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

    @OnClick({R.id.btn_sendFiles, R.id.btn_receiveFiles})
    public void onViewClick(View view) {
        Class<? extends BaseActivity> clsT = null;
        switch (view.getId()) {
            case R.id.btn_sendFiles:
                clsT = WifiHotspotSendFilesActivity.class;
                break;
            case R.id.btn_receiveFiles:
                clsT = WifiHotspotReceiveFilesActivity.class;
                break;
        }
        forward(clsT);
    }

}
