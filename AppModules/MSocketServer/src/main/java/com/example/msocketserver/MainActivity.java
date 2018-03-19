package com.example.msocketserver;

import android.view.View;

import com.example.algorithm.utils.LoggerUtils;
import com.example.common.corel.BaseActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    private ServerSocket mServerSocket;

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

    @OnClick({R.id.btn_open_server})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_open_server:
                new Thread() {
                    public void run() {
                        //创建服务器端的socket对象使用tcp协议。并且设置要监听的端口
                        try {
                            Socket clSocket = initServerSocket();
                            parseReceiveData(clSocket);
                            askClient(clSocket);

                        } catch (IOException e) {

                            e.printStackTrace();
                        }
                    }

                    ;
                }.start();
                break;
        }
    }

    private Socket initServerSocket() throws IOException {
        LoggerUtils.loge(this, "初始化服务端socket对象基于tcp协议");
        mServerSocket = new ServerSocket(5033);
        //得到连接的客户端对象
        Socket clSocket = mServerSocket.accept();
        //得到客户端地址
        InetAddress inet4Address = clSocket.getInetAddress();
        LoggerUtils.loge(this, "客户端ip地址:" + inet4Address.getHostAddress());
        return clSocket;
    }

    private void parseReceiveData(Socket clSocket) throws IOException {
        LoggerUtils.loge(this, "解析客户端传递过来的数据");
        //使用连接的客户端对象的inputstream解析客户端数据
        InputStream is = clSocket.getInputStream(); //使用服务器端的inputstream解析客户端发送过来的数据
        //构建一个4的空间做为缓存。可以让用户传递文件
        byte[] buff = new byte[4 * 1024];
        //记录接收到的数据的长度
        int len = 0;
        while ((len = is.read(buff)) != -1) {
            //将接收到的数据输出
            //只写读取到的数据
            LoggerUtils.loge(this, "接收到的数据:" + new String(buff, 0, len));
        }
    }

    private void askClient(Socket clSocket) throws IOException {
        LoggerUtils.loge(this, "对客户端的请求做出响应");
        //使用连接的客户端对象的outputstream往外写数据
        OutputStream osOutputStream = clSocket.getOutputStream();
        osOutputStream.write("数据已接收到".getBytes());
        osOutputStream.flush();
    }

}
