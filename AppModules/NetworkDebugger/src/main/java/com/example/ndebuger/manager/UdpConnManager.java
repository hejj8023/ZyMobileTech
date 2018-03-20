package com.example.ndebuger.manager;

import android.content.Context;
import android.os.Handler;

import com.example.utils.LogListener;
import com.example.utils.LoggerUtils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zzg on 2018/3/18.
 */

public class UdpConnManager extends CommonConnManager implements LogListener {
    private static UdpConnManager mInstance;

    private ExecutorService threadPool = Executors.newCachedThreadPool();
    private DatagramSocket datagramSocket;

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
                    datagramSocket = new DatagramSocket(port);
                    // 定义数据包，用于存储接收到的数据，先定义字节数组，数据包会把数据存储到字节数组中
                    byte[] buff = new byte[1024 * 1024];
                    DatagramPacket dp = new DatagramPacket(buff, buff.length);
                    notifyUIByWaitConnClient();
                    while (true) {
                        // 通过数据包的方法获取数据名的具体内容，如果:ip，端口，数据....
                        datagramSocket.receive(dp);
                        // TODO: 2018/3/20 有消息的时候这里才会调用否则一直是阻塞的
                        String hostAddress = dp.getAddress().getHostAddress();
                        int dpPort = dp.getPort();
                        notifyUIByMsgConnClient("Udp Server -> 连接的客户端:ip = " + hostAddress);

                        // TODO: 2018/3/20 读取客户端发送过来的数据
                        // 将数据读取出来
                        String str = new String(dp.getData(), 0, dp.getLength());
                        LoggerUtils.loge(UdpConnManager.this, "client ip = " + hostAddress + " " +
                                ", msg = " + str);
                        notifyUIByMsgRec(str);

                        // String text = "我是udp服务端，收到了请求的:" + str + "数据，已阅";

                        String text = " am udp server, msg has read\n";
                        // TODO: 2018/3/20 回复消息给客户端
                        DatagramPacket msgDp = new DatagramPacket(text.getBytes("utf-8"), text
                                .getBytes().length, InetAddress
                                .getByName(hostAddress), dpPort);
                        datagramSocket.send(msgDp);
                        notifyUIByMsgSend(text);
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
}
