package com.example.ndebuger.manager;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;

import com.example.ndebuger.common.OnMsgSendComplete;
import com.example.utils.LogListener;
import com.example.utils.LoggerUtils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zzg on 2018/3/18.
 * server:
 * client 由于是无连接，client给服务器发送一条消息才会处于连接状态
 * server服务器端绑定指定接口，客户端连接服务器ip和指定端口
 * client:
 * 不需要指定端口
 */

public class UdpConnManager extends CommonConnManager implements LogListener {
    private static UdpConnManager mInstance;

    private ExecutorService threadPool = Executors.newCachedThreadPool();
    // 服务器->Client socket
    private DatagramSocket ds;
    // 连接的客户端的数据包
    private DatagramPacket serverClientDp;
    // 客户端->Server Socket
    private DatagramSocket cSSocket;
    private InetAddress cSAddress;
    private int serverCont = 0;

    private UdpConnManager(Context context, Handler handler) {
        super(context, handler);
    }

    public static UdpConnManager getInstance(Context context, Handler handler) {
        if (mInstance == null) {
            synchronized (UdpConnManager.class) {
                if (mInstance == null) {
                    mInstance = new UdpConnManager(context, handler);
                }
            }
        }
        return mInstance;
    }

    public void closeServer() {

    }

    public void closeClient() {

    }

    public void createAndOpenServer(int port) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    // 创建udp的socket服务
                    ds = new DatagramSocket(port);
                    // 定义数据包，用于存储接收到的数据，先定义字节数组，数据包会把数据存储到字节数组中
                    byte[] buff = new byte[1024 * 1024];
                    serverClientDp = new DatagramPacket(buff, buff.length);
                    notifyUIByWaitConnClient();
                    while (true) {
                        // 通过数据包的方法获取数据名的具体内容，如果:ip，端口，数据....
                        ds.receive(serverClientDp);
                        // TODO: 2018/3/20 有消息的时候这里才会调用否则一直是阻塞的
                        String hostAddress = serverClientDp.getAddress().getHostAddress();
                        int dpPort = serverClientDp.getPort();
                        notifyUIByMsgConnClient("Udp Server -> 连接的客户端:ip = " + hostAddress);

                        // TODO: 2018/3/20 读取客户端发送过来的数据
                        // 将数据读取出来
                        String str = new String(serverClientDp.getData(), 0, serverClientDp.getLength());
                        LoggerUtils.loge(UdpConnManager.this, "client ip => " + hostAddress + " " +
                                ", msg = " + str);
                        notifyUIByMsgRec(str);

                        // String text = "我是udp服务端，收到了请求的:" + str + "数据，已阅";
                        String text = " am udp server, msg has read\n";
                        // TODO: 2018/3/20 回复消息给客户端
                        sendMsg(serverClientDp, text);
                    }
                } catch (SocketException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                    // TODO: 2018/3/20  receive是会发生异常的
                }
            }
        });
    }

    private void sendMsg(DatagramPacket dp, String text) throws IOException {
        if (dp == null)
            return;

        if (TextUtils.isEmpty(text))
            return;

        InetAddress address = dp.getAddress();
        DatagramPacket msgDp = new DatagramPacket(text.getBytes("utf-8"), text
                .getBytes().length, InetAddress.getByName(address.getHostAddress()), dp.getPort());
        ds.send(msgDp);
        notifyUIByMsgSend(text);
    }

    /**
     * server角色使用
     */
    public void sendMsgToClient(String msg, OnMsgSendComplete listener) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    sendMsg(serverClientDp, msg);
                    if (listener != null) {
                        listener.sucess();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    if (listener != null) {
                        listener.error();
                    }
                }
            }
        });
    }

    public void sendMsgToServer(String msg, OnMsgSendComplete listener) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                if (cSSocket != null && cSAddress != null) {
                    try {
                        DatagramPacket datagramPacket = new DatagramPacket(msg.getBytes(), msg.getBytes().length, cSAddress, serverPort);
                        cSSocket.send(datagramPacket);
                        if (listener != null) {
                            listener.sucess();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        if (listener != null) {
                            listener.error();
                        }
                    }
                }
            }
        });
    }

    public void connectRemoteServer(String ip, String port) {
        this.serverPort = Integer.parseInt(port);
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    cSSocket = new DatagramSocket();
                    cSAddress = InetAddress.getByName(ip);
                    String testMsg = "this is a test msg from udp server";
                    DatagramPacket datagramPacket = new DatagramPacket(testMsg.getBytes(), testMsg.getBytes().length, cSAddress, serverPort);
                    cSSocket.send(datagramPacket);
                    notifyUIByWaitConnClient();
                    // 循环的读取服务器发送过来的消息
                    byte[] dataBuff = new byte[1024 * 1024];
                    DatagramPacket csDp = new DatagramPacket(dataBuff, dataBuff.length);
                    while (true) {
                        // 阻塞式接收消息放到DatagramPacket中
                        cSSocket.receive(csDp);
                        serverCont++;
                        if (serverCont == 1) {
                            notifyUIByMsgConnServer("Udp Server -> ip = " + ip);
                        }
                        String str = new String(csDp.getData(), 0, csDp.getLength());
                        LoggerUtils.loge(UdpConnManager.this, "server ip => " + ip + " " +
                                ", msg = " + str);
                        notifyUIByMsgRec(str);
                    }
                } catch (SocketException e) {
                    e.printStackTrace();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
