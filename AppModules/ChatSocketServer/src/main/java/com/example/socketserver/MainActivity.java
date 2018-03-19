package com.example.socketserver;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.blankj.utilcode.util.NetworkUtils;
import com.example.common.corel.BaseActivity;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tv_result)
    TextView textView;

    @BindView(R.id.tv_msg)
    TextView tvMsg;

    public static ArrayList<Socket> socketList = new ArrayList<Socket>();

    private Handler mH = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 0x122) {
                String msgStr = (String) msg.obj;
                if (msg != null) {
                    tvMsg.append(msgStr + "\n");
                }
            }
            return false;
        }
    });

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

        textView.setText(NetworkUtils.getIPAddress(true));

        new Thread() {
            @Override
            public void run() {
                ServerSocket ss = null;
                try {
                    ss = new ServerSocket(30000);
                    while (true) {
                        Socket s = ss.accept();
                        socketList.add(s);
                        new Thread(new ServerTherad(s, mH)).start();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }

    @Override
    protected PermissionListener getPermissonCallBack() {
        return null;
    }
}
