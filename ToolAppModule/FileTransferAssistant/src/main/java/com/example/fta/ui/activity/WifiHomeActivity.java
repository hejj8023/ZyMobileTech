package com.example.fta.ui.activity;

import com.example.fta.R;
import com.zysdk.vulture.clib.corel.BaseActivity;

/**
 * Created by zzg on 2018/4/5.
 * 技术借鉴:https://github.com/leavesC/WifiP2P
 */

public class WifiHomeActivity extends BaseActivity {
    @Override
    protected int getContentViewId() {
        return R.layout.activity_wifi_home;
    }

    @Override
    protected void initView() {
        setTitle("Wifi-P2P传输");
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
}
