package com.example.ndebuger.manager;

import android.content.Context;
import android.os.Handler;

import com.example.ndebuger.common.OnMsgSendComplete;
import com.example.utils.LogListener;
import com.example.utils.LoggerUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zzg on 2018/3/18.
 * <p>
 * // * tcp连接管理器
 * // *   client: 直接和服务器建立连接，发送数据给服务器，并且接收服务器发送过来的数据
 * // *   server:
 * // *      1. 服务器状态 :
 * // *        1. 等待客户端连接
 * // *        2. 当有客户端与服务器建立连接时，展示客户端的ip
 * // *        3. 当客户端与服务器断开连接时，更新界面
 * // *     2. 循环接收客户端发送过来的消息，连接不会中断
 * // *     3. 当收到一条客户端的消息之后，会回复一条数据给客户端
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

    private List<Socket> socketList = new LinkedList<>();
    private int serverCount = 0;

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
                    notifyUIByWaitConntTcpClient();
                    InputStream inputStream = clientSocket.getInputStream();
                    threadPool.execute(new Runnable() {
                        @Override
                        public void run() {
                            if (inputStream != null) {
                                byte[] buffer = new byte[2 * 1024];
                                int len = -1;
                                try {
                                    while ((len = inputStream.read(buffer)) != -1) {
                                        serverCount++;
                                        if (serverCount == 1) {
                                            notifyUIByMsgConnServer("Tcp Server -> 连接的客户端:ip = " + ip);
                                        }
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
        try {
            for (Socket socket : socketList) {
                if (socket.isConnected()) {
                    socket.close();
                }
            }
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送测试消息给tcp server
     */
    public void sendMsgToTcpServer(String str, OnMsgSendComplete listener) {
        sendMsg(clientSocket, str, listener);
    }

    /**
     * 发送消息给客户端
     */
    public void sendMsgToTcpClient(String str, OnMsgSendComplete listener) {
        sendMsg(sClientSocket, str, listener);
    }

    /**
     * 创建并开启服务器
     */
    public void createAndOpenServer(int port) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    serverSocket = new ServerSocket(port);
                    // TODO: 2018/3/20 通知ui当前状态为等待客户端连接
                    notifyUIByWaitConntTcpClient();
                    while (true) {
                        sClientSocket = serverSocket.accept();
                        if (socketList.contains(sClientSocket)) {
                            socketList.remove(sClientSocket);
                        }
                        socketList.add(sClientSocket);
                        InetAddress inetAddress = sClientSocket.getInetAddress();
                        LoggerUtils.loge(TcpConnManager.this, "连接的客户端:ip = " + inetAddress.getHostName());

                        // TODO: 2018/3/20 通知ui当前有客户端连接，进行ui展示
                        notifyUIByMsgConnClient("Tcp Server -> 连接的客户端:ip = " + inetAddress.getHostName());
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

                                        // TODO: 2018/3/20 回复一条数据给所有客户端
                                        String dataStr = "我是server,收到了你发过来的:" + msgStr + "," +
                                                "打了一个标记<阅>";
                                        dataStr = "am tcp server,msg has read\n";
                                        for (Socket socket : socketList) {
                                            // 回复所有的客户端
                                            sendMsg(socket, dataStr, null);
                                        }
                                    }

                                    // TODO: 2018/3/20 通知ui连接断开,
                                    LoggerUtils.loge(TcpConnManager.this, "与客户端断开了连接:client = "
                                            + inetAddress.getHostAddress());
                                    notifyUIByConnDis("与客户端断开了连接:client = "
                                            + inetAddress.getHostAddress());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    notifyUIByError();
                }
            }
        });
    }

    /**
     * 发送消息
     */
    private void sendMsg(Socket socket, String str, OnMsgSendComplete listener) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                // TODO: 2018/3/17 发送测试消息给服务器
                try {
                    OutputStream outputStream = socket.getOutputStream();
                    String msgStr = str + "\n";
                    outputStream.write(msgStr.getBytes("utf-8"));
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
}
