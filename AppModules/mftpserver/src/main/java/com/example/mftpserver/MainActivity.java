package com.example.mftpserver;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ServiceUtils;
import com.example.common.corel.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    private Intent intent;

    private FtpServices.MyBinder binder;

    @BindView(R.id.et_server_ip)
    TextView tvServerIp;

    @BindView(R.id.et_server_port)
    EditText etServerPort;

    @BindView(R.id.et_uname)
    EditText etName;

    @BindView(R.id.et_pwd)
    EditText etPwd;

    @BindView(R.id.et_file_path)
    EditText etFilePath;

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (FtpServices.MyBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


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
        tvServerIp.setText(NetworkUtils.getIPAddress(true));
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!ServiceUtils.isServiceRunning("com.example.mftpserver.FtpServices")) {
            intent = new Intent(mContext, FtpServices.class);
            startService(intent);
            bindService(intent, conn, Context.BIND_AUTO_CREATE);
        }
    }

    @Override
    protected PermissionListener getPermissonCallBack() {
        return null;
    }

    @OnClick({R.id.btn_open_server})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_open_server:

                Editable ePort = etServerPort.getText();
                if (ePort == null) {
                    return;
                }

                String portStr = ePort.toString();
                if (portStr.trim().length() == 0) {
                    return;
                }
                Editable eName = etName.getText();
                Editable ePwd = etPwd.getText();
                if (eName == null || ePwd == null) {
                    return;
                }

                String nameStr = eName.toString();
                String pwdStr = ePort.toString();
                if (nameStr.trim().length() == 0 || pwdStr.trim().length() == 0) {
                    return;
                }

                Editable eFilePath = etFilePath.getText();
                if (eFilePath == null) {
                    return;
                }

                String filePathStr = etFilePath.toString();
                if (filePathStr.trim().length() == 0) {
                    return;
                }

                if (binder == null) {
                    return;
                }

                if (binder != null) {
                    binder.start(portStr, nameStr, pwdStr,filePathStr);
                }
                break;
        }
    }
}
