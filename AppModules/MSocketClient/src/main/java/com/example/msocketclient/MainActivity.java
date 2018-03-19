package com.example.msocketclient;

import android.view.View;

import com.example.algorithm.utils.LoggerUtils;
import com.example.common.corel.BaseActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    private Socket socket;

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
    protected boolean hasCheckPermission() {
        return false;
    }

    @Override
    protected PermissionListener getPermissonCallBack() {
        return null;
    }

    @OnClick({R.id.btn_send_msg})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_send_msg:
                new Thread() {
                    public void run() {
                        try {
                            initSocket();
                            sendData();
                            parseData();
                        } catch (UnknownHostException e) {

                            e.printStackTrace();
                        } catch (IOException e) {

                            e.printStackTrace();
                        }
                    }
                }.start();
                break;
        }
    }

    /**
     * 解析服务器的响应的数据
     */
    protected void parseData() {
        try {
            LoggerUtils.loge(this, "解析服务端发送过来的数据");
            InputStream stream = socket.getInputStream();
            byte[] buff = new byte[1024 * 4];
            int len = 0;
            while ((len = stream.read(buff)) != -1) {
                LoggerUtils.loge(this, "服务端响应的数据:" + new String(buff, 0, len));
            }

        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    private void initSocket() throws UnknownHostException, IOException {
        LoggerUtils.loge(this, "初始化客户端socket对象基于tcp协议");
        //创建客户端的socket对象
        //指定服务器ip地址和端口号
        socket = new Socket("192.168.1.101", 5033);
    }

    private void sendData() throws IOException {
        LoggerUtils.loge(this, "发送客户端数据到服务端");
        //使用socket的输出流往外写数据
        OutputStream osStream = socket.getOutputStream();
        osStream.write("测试用的数据".getBytes());
        osStream.flush(); // 将数据刷新到服务器
    }

}
