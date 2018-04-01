package com.example.fta;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.common.corel.BaseActivity;
import com.example.utils.LoggerUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class FtpHomeActivity extends BaseActivity {

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

    @BindView(R.id.rg_server_role_type)
    RadioGroup rgRole;
    @BindView(R.id.rg_server_type)
    RadioGroup rgType;

    @BindView(R.id.btn_open_server)
    Button btnOpenServer;
    @BindView(R.id.btn_connect_server)
    Button btnConnServer;

    @BindView(R.id.rb_swiftp)
    View vSwiftp;
    @BindView(R.id.rb_apacheftp)
    View vApacheFtp;

    // 是否是服务器角色
    private boolean hasServerRole;
    private String ipAddress;
    // 是否采用apache框架
    private boolean hasApacheFtp;

    private FtpClientManager ftpClientManager;

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
        ipAddress = NetworkUtils.getIPAddress(true);
        etServerIp.setText(ipAddress);
        etServerIp.setEnabled(false);
    }

    @Override
    protected void addListener() {
        rgRole.setOnCheckedChangeListener(((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rb_role_server_type:
                    hasServerRole = true;
                    vSwiftp.setEnabled(true);
                    etServerIp.setText(ipAddress);
                    etServerIp.setEnabled(false);
                    vApacheFtp.setEnabled(true);
                    btnOpenServer.setEnabled(true);
                    btnConnServer.setEnabled(false);
                    break;
                case R.id.rb_role_client_type:
                    hasServerRole = false;
                    etServerIp.setText("");
                    vSwiftp.setEnabled(false);
                    etServerIp.setEnabled(true);
                    vApacheFtp.setEnabled(false);
                    btnOpenServer.setEnabled(false);
                    btnConnServer.setEnabled(true);
                    break;
            }
        }));

        rgType.setOnCheckedChangeListener(((group, checkedId) -> {
            if (!hasServerRole)
                return;
            switch (checkedId) {
                case R.id.rb_apacheftp:
                    hasApacheFtp = true;
                    break;
                case R.id.rb_swiftp:
                    hasApacheFtp = false;
                    break;
            }
        }));
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

    @OnClick({R.id.btn_open_server, R.id.btn_connect_server})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_open_server:
                openFtpServer();
                break;
            case R.id.btn_connect_server:
                connectFtpServer();
                break;
        }
    }

    private void connectFtpServer() {
        if (ftpClientManager == null) {
            LoggerUtils.loge(this, "ftpClientManager,返回");
            return;
        }

        String serverIp = etServerIp.getText().toString().trim();
        if (TextUtils.isEmpty(serverIp)) {
            return;
        }

        if (serverIp.length() == 0) {
            return;
        }

        ftpClientManager.setServerIp(serverIp);

        String serverName = etServerName.getText().toString().trim();
        if (TextUtils.isEmpty(serverName))
            return;
        if (serverName.length() == 0) {
            return;
        }
        ftpClientManager.setUserName(serverName);

        String serverPort = etServerPort.getText().toString().trim();
        if (TextUtils.isEmpty(serverPort))
            return;
        if (serverPort.length() == 0) {
            return;
        }
        ftpClientManager.setServerPort(serverPort);

        String serverPwd = etServerPwd.getText().toString().trim();
        if (TextUtils.isEmpty(serverPwd))
            return;
        if (serverPwd.length() == 0) {
            return;
        }
        ftpClientManager.setUserPwd(serverPwd);

        ftpClientManager.init();
        etServerIp.setEnabled(false);
        etServerPort.setEnabled(false);
        etServerName.setEnabled(false);
        etServerPwd.setEnabled(false);
        ftpClientManager.connect();
        ToastUtils.showShort("ftp服务器开启成功...");
        isFtpServerRuning = true;
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

        String serverIp = etServerIp.getText().toString().trim();
        if (TextUtils.isEmpty(serverIp)) {
            return;
        }

        if (serverIp.length() == 0) {
            return;
        }

        ftpServerManager.setServerIp(serverIp);

        ftpServerManager.setServerType(hasServerRole ? Const.TYPE_SERVER : Const.TYPE_CLIENT);
        ftpServerManager.seterverMode(hasApacheFtp ? Const.MODE_APACHE_FTP : Const.MODE_SWI_FTP);

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
        ToastUtils.showShort("ftp服务器开启成功...");
        isFtpServerRuning = true;
    }
}
