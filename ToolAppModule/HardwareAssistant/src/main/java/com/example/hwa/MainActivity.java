package com.example.hwa;

import android.view.View;

import com.example.hwa.ui.activity.BlueBoothManageActivity;
import com.example.hwa.ui.activity.WifiManageActivity;
import com.zysdk.vulture.clib.corel.BaseActivity;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
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

    @OnClick({R.id.btn_blue_booth, R.id.btn_wifi_hotspot})
    public void onViewClick(View view) {
        Class<? extends BaseActivity> targetCls = null;
        switch (view.getId()) {
            case R.id.btn_blue_booth:
                targetCls = BlueBoothManageActivity.class;
                break;
            case R.id.btn_wifi_hotspot:
                targetCls = WifiManageActivity.class;
                break;
        }
        forward(targetCls);
    }
}
