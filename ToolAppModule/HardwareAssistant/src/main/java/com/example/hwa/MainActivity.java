package com.example.hwa;

import android.view.View;

import com.zhiyangstudio.commonlib.corel.BaseActivity;

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
        switch (view.getId()) {
            case R.id.btn_blue_booth:
                break;
            case R.id.btn_wifi_hotspot:
                break;
        }
    }
}
