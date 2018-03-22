package com.example.gcamera;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.common.corel.BaseActivity;
import com.example.gcamera.activity.JCameraViewActivity;

import butterknife.BindView;
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

    @OnClick(R.id.btn_jcamera_view)
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_jcamera_view:
                forward(JCameraViewActivity.class);
                break;
        }

    }
}
