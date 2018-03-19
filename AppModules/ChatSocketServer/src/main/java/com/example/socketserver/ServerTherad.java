package com.example.socketserver;

import android.os.Handler;
import android.os.Message;

import com.example.algorithm.utils.LogListener;
import com.example.algorithm.utils.LoggerUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ServerTherad implements Runnable, LogListener {
    private final Handler mHandler;
    private Socket s = null;
    private BufferedReader buRead = null;

    public ServerTherad(Socket s, Handler mH) throws IOException {
        this.mHandler = mH;
        this.s = s;
        this.buRead = new BufferedReader(new InputStreamReader(
                this.s.getInputStream(), "utf-8"));
    }

    @Override
    public void run() {
        String connet = null;
        try {
            while ((connet = readFromClient()) != null) {
                LoggerUtils.loge(this, "客户端说：" + connet);
                Message me = Message.obtain();
                InetAddress inetAddress = s.getInetAddress();
                String extMsg = inetAddress.getHostName() + " , " + inetAddress.getHostAddress();
                me.obj = extMsg + " , " + connet;
                me.what = 0x122;
                mHandler.sendMessage(me);

                for (Socket ss : MainActivity.socketList) {
                    OutputStream out = ss.getOutputStream();
                    out.write(("服务端说 -> " + connet + "\n").getBytes("utf-8"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readFromClient() {
        try {
            return buRead.readLine();
        } catch (Exception e) {
            //删除此Socket
            MainActivity.socketList.remove(s);
        }
        return null;
    }

}
