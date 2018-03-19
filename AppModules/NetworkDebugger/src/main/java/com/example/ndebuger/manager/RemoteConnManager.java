package com.example.ndebuger.manager;

import android.content.Context;
import android.os.Handler;

import com.example.ndebuger.OnMsgSendComplete;
import com.example.ndebuger.RoleType;

import java.net.Socket;

/**
 * Created by zzg on 2018/3/18.
 * 远程连接管理器
 */

public class RemoteConnManager {

    private static RemoteConnManager mInstance;

    private final TcpConnManager tcpConnManage;
    private final UdpConnManager udpConnManage;

    private RemoteConnManager(Context context, Handler handler) {
        tcpConnManage = TcpConnManager.getInstance(context, handler);
        udpConnManage = UdpConnManager.getInstance(context, handler);
    }

    public static RemoteConnManager getInstance(Context context, Handler handler) {
        if (mInstance == null) {
            synchronized (RemoteConnManager.class) {
                if (mInstance == null) {
                    mInstance = new RemoteConnManager(context, handler);
                }
            }
        }
        return mInstance;
    }

    /**
     * 当状发生改变的时候把原来的连接断开:
     * 1. tcp->udp
     * 2. port 发生变化
     */
    public void chageStateOnServiceTypeChange(RoleType roleType) {
        switch (roleType) {
            case TCP_CLIENT:
            case TCP_SERVER:
                closeTcpSocket(roleType);
                break;
            case UDP_SERVER:
            case UDP_CLIENT:
                closeUdpSocket(roleType);
                break;
        }
    }

    private void closeUdpSocket(RoleType type) {
        switch (type) {
            case TCP_CLIENT:
                tcpConnManage.closeClient();
                break;
            case TCP_SERVER:
                tcpConnManage.closeServer();
                break;
            case UDP_SERVER:
                udpConnManage.closeServer();
                break;
            case UDP_CLIENT:
                udpConnManage.closeClient();
                break;
        }
    }

    /**
     * 连接远程服务器
     */
    public void connectRemoteTcpServer(String ip, String port) {
        tcpConnManage.connectRemoteTcpServer(ip, port);
    }

    private void closeTcpSocket(RoleType type) {
        // 状态发生改变，断开连接
        switch (type) {
            case TCP_CLIENT:
                tcpConnManage.closeClient();
                break;
            case TCP_SERVER:
                tcpConnManage.closeServer();
                break;
        }
    }

    public Socket getTcpServerSocket() {
        return tcpConnManage.getTcpServerSocket();
    }

    public void sendTestToTcpClient(OnMsgSendComplete listener) {
        tcpConnManage.sendTestMsgToTcpServer(listener);
    }

    public void sendTestMsgToTcpServer(OnMsgSendComplete listener) {
        tcpConnManage.sendTestMsgToTcpServer(listener);
    }

    public Socket getTcpClientSocket() {
        return tcpConnManage.getTcpClientSocket();
    }
}
