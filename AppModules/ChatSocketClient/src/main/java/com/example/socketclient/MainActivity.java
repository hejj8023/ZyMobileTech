package com.example.socketclient;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.NetworkUtils;
import com.example.common.corel.BaseActivity;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.textview)
    TextView ipText;

    @BindView(R.id.test)
    TextView testview;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.edit)
    EditText text;
    protected Handler handler = null;
    private OutputStream out = null;
    private Socket s = null;

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

        ipText.setText(NetworkUtils.getIPAddress(true));

        this.handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 0x123) {
                    testview.append("接收的消息：" + msg.obj.toString() + "\n");
                }
            }
        };
        //4.0之后访问网络不能在主程序中进行，要将代码放在线程中，不然会报错。
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    s = new Socket("192.168.86.35", 30000);
                    new Thread(new ClientThread(s, handler)).start();
                    out = s.getOutputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected PermissionListener getPermissonCallBack() {
        return null;
    }

    @OnClick(R.id.button)
    public void onViewClick(View view) {
        try {
            out.write((text.getText().toString() + "\n").getBytes("utf-8"));
            text.setText("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
