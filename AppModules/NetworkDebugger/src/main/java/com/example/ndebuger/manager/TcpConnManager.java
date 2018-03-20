package com.example.ndebuger.manager;

import android.content.Context;
import android.os.Handler;

import com.example.ndebuger.GlobalConst;
import com.example.ndebuger.OnMsgSendComplete;
import com.example.utils.LogListener;
import com.example.utils.LoggerUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zzg on 2018/3/18.
 * <p>
 * tcp连接管理器
 */

public class TcpConnManager extends CommonConnManager implements LogListener {
    private static TcpConnManager mInstance;

    // 客户端角色时的clientSocket
    private Socket clientSocket;

    private ExecutorService threadPool = Executors.newCachedThreadPool();
    // 服务器端的socket
    private ServerSocket serverSocket;

    // 服务端角色时的clientsocket
    private Socket sClientSocket;

    private TcpConnManager(Context context, Handler handler) {
        super(context, handler);
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

    public ServerSocket getTcpServerSocket() {
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
                                        notifyUIByMsgRec(msg);
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
        if (serverSocket != null && !serverSocket.isClosed()) {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
                    notifyUIByMsgSend(msgStr);
                    if (listener != null) {
                        listener.sucess();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    /**
     * 创建并开启服务器
     *
     * @param port
     */
    public void createAndOpenServer(int port) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    serverSocket = new ServerSocket(port);
                    while (true) {
                        sClientSocket = serverSocket.accept();
                        InetAddress inetAddress = serverSocket.getInetAddress();
                        LoggerUtils.loge(TcpConnManager.this, "连接的客户端:ip = " + inetAddress.getHostAddress());
                        InputStream inputStream = sClientSocket.getInputStream();
                        threadPool.execute(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    byte[] buffer = new byte[4 * 1024];
                                    int len = -1;
                                    // todo 只要在连接中，就不会退出循环，退出循环了，只有一种可能，连接中断
                                    while ((len = inputStream.read(buffer)) != -1) {
                                        String msgStr = new String(buffer, 0, len, "utf-8");
                                        LoggerUtils.loge(TcpConnManager.this, " 接收到的数据: " + msgStr);
                                        notifyUIByMsgRec(msgStr);

                                        // TODO: 2018/3/20 回复一条数据给客户端
                                        OutputStream outputStream = sClientSocket.getOutputStream();
                                        String dataStr = "我是server,收到了你发过来的:" + msgStr + "," +
                                                "打了一个标记<阅>";
                                        dataStr = "am tcp server,msg has read\n";
                                        outputStream.write(dataStr.getBytes());
                                        outputStream.flush();
                                        notifyUIByMsgSend(dataStr);
                                    }
                                    LoggerUtils.loge(TcpConnManager.this, "与客户端断开了连接:client = "
                                            + inetAddress.getHostAddress());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
