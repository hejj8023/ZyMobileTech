package com.example.mftpclient;

import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.NetworkUtils;
import com.example.utils.LoggerUtils;
import com.example.common.corel.BaseActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tv_ip)
    TextView tvIP;

    @BindView(R.id.imageview)
    ImageView imageView;

    @BindView(R.id.et_server_ip)
    EditText etSIp;

    @BindView(R.id.et_server_port)
    EditText etSPort;

    @BindView(R.id.et_server_ftp_port)
    EditText etSFtpPort;

    @BindView(R.id.et_uname)
    EditText etUserName;

    @BindView(R.id.et_pwd)
    EditText etPwd;

    @BindView(R.id.et_filepath)
    EditText etFilePath;

    private FtpHelper ftpHelper;

    private ExecutorService threadPool = Executors.newCachedThreadPool();

    private PermissionListener mPermissionListener = new PermissionListener() {
        @Override
        public void onGrant(int code) {

        }

        @Override
        public void onDeny(int code) {

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
        tvIP.setText(NetworkUtils.getIPAddress(true));
    }

    @Override
    protected PermissionListener getPermissonCallBack() {
        return mPermissionListener;
    }

    @OnClick(R.id.btn_download_file)
    public void onViewClick(View view) {
        Editable eIP = etSIp.getText();
        Editable ePort = etSPort.getText();
        if (eIP == null || ePort == null) {
            return;
        }

        String ipStr = eIP.toString().trim();
        String ePortStr = ePort.toString().trim();
        if (ipStr.length() == 0 || ePortStr.length() == 0) {
            return;
        }

        Editable eUserName = etUserName.getText();
        Editable ePwd = etPwd.getText();
        if (eUserName == null || ePwd == null) {
            return;
        }

        String userNameStr = eUserName.toString().trim();
        String pwdStr = ePwd.toString().trim();

        if (userNameStr.length() == 0 || pwdStr.length() == 0) {
            return;
        }

        Editable eFtpPort = etSFtpPort.getText();
        if (eFtpPort == null) {
            return;
        }

        String ftpPortStr = eFtpPort.toString().trim();

        if (ftpPortStr.length() == 0) {
            return;
        }

        Editable eFilePath = etFilePath.getText();
        if (eFilePath == null) {
            return;
        }

        String filePathStr = eFilePath.toString().trim();

        if (filePathStr.length() == 0) {
            return;
        }

        threadPool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socket = new Socket(ipStr, Integer.parseInt(ePortStr));
                    // TODO: 2018/3/14  获取服务器发送过来的消息
                    InputStream inputStream = socket.getInputStream();
                    byte[] buff = new byte[4 * 1024];
                    int len = -1;
                    while ((len = inputStream.read(buff)) != -1) {
                        String msg = new String(buff, 0, len, "utf-8");
                        LoggerUtils.loge(MainActivity.this, msg);
                        if (!TextUtils.isEmpty(msg)) {
                            // TODO: 2018/3/14 这里需要服务器告诉客户端文件名和路径，端口号最好也需要
                            // 使用FTP客户端下载图片
                            ftpHelper = FtpHelper.getInstance(ipStr, Integer.parseInt(ftpPortStr),
                                    userNameStr, pwdStr);
                            ftpHelper.initFTPSetting();
                            ftpHelper.downLoadFile(filePathStr, "a.png");
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
