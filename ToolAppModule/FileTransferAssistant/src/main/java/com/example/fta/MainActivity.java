package com.example.fta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.blankj.utilcode.util.NetworkUtils;
import com.example.common.corel.BaseActivity;
import com.example.utils.LoggerUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    private FtpServerManager ftpServerManager;
    private boolean isFtpServerRuning;
    private boolean hasSdCardPermission;

    @BindView(R.id.et_server_ip)
    EditText etServerIp;
    @BindView(R.id.et_server_port)
    EditText etServerPort;
    @BindView(R.id.et_server_uname)
    EditText etServerName;
    @BindView(R.id.et_server_pwd)
    EditText etServerPwd;

    private PermissionListener mPermissionListener = new PermissionListener() {
        @Override
        public void onGrant(int code) {
            LoggerUtils.loge(this, "onGrant");
            if (code == REQ_SDCARD_PERMISSION) {
                hasSdCardPermission = true;
                openFtpServer();
            }
        }

        @Override
        public void onDeny(int code) {

        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        openFtpServer();
    }

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
        LoggerUtils.loge(this, "initData");
        ftpServerManager = FtpServerManager.getInstance();
    }

    @Override
    protected PermissionListener getPermissonCallBack() {
        return mPermissionListener;
    }


    @OnClick({R.id.btn_create_or_open})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_create_or_open:
                openFtpServer();
                break;
        }
    }

    private void openFtpServer() {
        if (!hasSdCardPermission) {
            LoggerUtils.loge(this, "权限校验失败,返回");
            return;
        }
        if (isFtpServerRuning) {
            LoggerUtils.loge(this, "服务器在运行中,返回");
            return;
        }

        if (ftpServerManager == null) {
            LoggerUtils.loge(this, "ftpServerManager为空,返回");
            return;
        }

        LoggerUtils.loge(this, "开启服务器...");

        String ipAddress = NetworkUtils.getIPAddress(true);
        etServerIp.setText(ipAddress);

        String serverIp = etServerIp.getText().toString().trim();
        if (TextUtils.isEmpty(serverIp)) {
            return;
        }

        if (serverIp.length() == 0) {
            return;
        }

        ftpServerManager.setServerIp(ipAddress);

        ftpServerManager.setServerType(0);
        ftpServerManager.seterverMode(0);

        String serverName = etServerName.getText().toString().trim();
        if (TextUtils.isEmpty(serverName))
            return;
        if (serverName.length() == 0) {
            return;
        }
        ftpServerManager.setUserName(serverName);

        String serverPort = etServerPort.getText().toString().trim();
        if (TextUtils.isEmpty(serverPort))
            return;
        if (serverPort.length() == 0) {
            return;
        }
        ftpServerManager.setServerPort(serverPort);

        String serverPwd = etServerPwd.getText().toString().trim();
        if (TextUtils.isEmpty(serverPwd))
            return;
        if (serverPwd.length() == 0) {
            return;
        }
        ftpServerManager.setUserPwd(serverPwd);

        ftpServerManager.init();
        etServerIp.setEnabled(false);
        etServerPort.setEnabled(false);
        etServerName.setEnabled(false);
        etServerPwd.setEnabled(false);
        ftpServerManager.start();
        isFtpServerRuning = true;
    }
}
