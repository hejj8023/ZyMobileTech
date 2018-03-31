package com.example.fta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.common.corel.BaseActivity;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    private FtpServerManager ftpServerManager;
    private boolean isFtpServerRuning;

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
        ftpServerManager = FtpServerManager.getInstance();
    }

    @Override
    protected PermissionListener getPermissonCallBack() {
        return null;
    }


    @OnClick({R.id.btn_create_or_open})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_create_or_open:
                if (isFtpServerRuning)
                    return;

                ftpServerManager.setServerType(0);
                ftpServerManager.seterverMode(0);
                ftpServerManager.init();
                ftpServerManager.start();
                isFtpServerRuning = true;
                break;
        }
    }
}
