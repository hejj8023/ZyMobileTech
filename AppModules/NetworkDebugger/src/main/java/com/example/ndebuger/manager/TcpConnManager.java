package com.example.ndebuger.manager;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.example.ndebuger.GlobalConst;
import com.example.ndebuger.OnMsgSendComplete;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zzg on 2018/3/18.
 */

public class TcpConnManager {
    private static TcpConnManager mInstance;
    private final Context mContext;
    private final Handler mH;

    private Socket serverSocket;
    private Socket clientSocket;

    private ExecutorService threadPool = Executors.newCachedThreadPool();

    private TcpConnManager(Context context, Handler handler) {
        this.mContext = context;
        this.mH = handler;
    }

    public static TcpConnManager getInstance(Context context, Handler handler) {
        if (mInstance == null) {
            synchronized (TcpConnManager.class) {
                if (mInstance == null) {
                    mInstance = new TcpConnManager(context, handler);
                }
            }
        }
        return mInstance;
    }

    public Socket getTcpServerSocket() {
        return serverSocket;
    }

    public Socket getTcpClientSocket() {
        return clientSocket;
    }

    /**
     * 连接远程服务器
     */
    public void connectRemoteTcpServer(String ip, String port) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    clientSocket = new Socket(ip, Integer.parseInt(port));
                    mH.sendEmptyMessage(GlobalConst.ENABLE_BTNS);
                    InputStream inputStream = clientSocket.getInputStream();
                    threadPool.execute(new Runnable() {
                        @Override
                        public void run() {
                            if (inputStream != null) {
                                byte[] buffer = new byte[2 * 1024];
                                int len = -1;
                                try {
                                    while ((len = inputStream.read(buffer)) != -1) {
                                        String msg = new String
                                                (buffer, 0, len, "utf-8");
                                        // LoggerUtils.loge(MainActivity.this, "接收 的数据:" + msg);
                                        Message message = Message.obtain();
                                        message.what = GlobalConst.UPDATE_RECE_MSG;
                                        message.obj = msg;
                                        mH.sendMessage(message);
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void closeTcpSocket(Socket socket) {
        // 状态发生改变，断开连接
        if (socket != null && socket.isConnected()) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void closeClient() {
        closeTcpSocket(clientSocket);
    }

    public void closeServer() {
        closeTcpSocket(serverSocket);
    }

    /**
     * 发送测试消息给tcp server
     */
    public void sendTestMsgToTcpServer(OnMsgSendComplete listener) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                // TODO: 2018/3/17 发送测试消息给服务器
                try {
                    OutputStream outputStream = clientSocket.getOutputStream();
                    String msgStr = "test msg";
                    outputStream.write("\n".getBytes());
                    outputStream.flush();
                    Message message = Message.obtain();
                    message.what = GlobalConst.UPDATE_SEND_MSG;
                    message.obj = msgStr;
                    mH.sendMessage(message);
                    if (listener != null) {
                        listener.sucess();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
