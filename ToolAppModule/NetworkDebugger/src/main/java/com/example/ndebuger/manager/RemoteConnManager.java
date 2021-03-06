package com.example.ndebuger.manager;

import android.content.Context;
import android.os.Handler;

import com.example.ndebuger.common.OnMsgSendComplete;
import com.example.ndebuger.common.RoleType;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by zzg on 2018/3/18.
 * 远程连接管理器
 */

public class RemoteConnManager {

    private static RemoteConnManager mInstance;

    private final TcpConnManager tcpConnManager;
    private final UdpConnManager udpConnManager;

    private RemoteConnManager(Context context, Handler handler) {
        tcpConnManager = TcpConnManager.getInstance(context, handler);
        udpConnManager = UdpConnManager.getInstance(context, handler);
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

    private void closeTcpSocket(RoleType type) {
        // 状态发生改变，断开连接
        switch (type) {
            case TCP_CLIENT:
                tcpConnManager.closeClient();
                break;
            case TCP_SERVER:
                tcpConnManager.closeServer();
                break;
        }
    }

    private void closeUdpSocket(RoleType type) {
        switch (type) {
            case TCP_CLIENT:
                tcpConnManager.closeClient();
                break;
            case TCP_SERVER:
                tcpConnManager.closeServer();
                break;
            case UDP_SERVER:
                udpConnManager.closeServer();
                break;
            case UDP_CLIENT:
                udpConnManager.closeClient();
                break;
        }
    }

    /**
     * 连接远程服务器
     */
    public void connectRemoteTcpServer(String ip, String port) {
        tcpConnManager.connectRemoteTcpServer(ip, port);
    }

    public ServerSocket getTcpServerSocket() {
        return tcpConnManager.getTcpServerSocket();
    }

    /**
     * server 角色
     */
    public void sendToTcpClient(String str, OnMsgSendComplete listener) {
        tcpConnManager.sendMsgToTcpClient(str, listener);
    }

    /**
     * client角色
     */
    public void sendMsgToTcpServer(String str, OnMsgSendComplete listener) {
        tcpConnManager.sendMsgToTcpServer(str, listener);
    }

    public void sendMsgToUdpClient(String msg, OnMsgSendComplete listener) {
        udpConnManager.sendMsgToClient(msg, listener);
    }

    public void sendMsgToUdpServer(String msg, OnMsgSendComplete listener) {
        udpConnManager.sendMsgToServer(msg, listener);
    }

    public Socket getTcpClientSocket() {
        return tcpConnManager.getTcpClientSocket();
    }

    public void openTcpServer(int port) {
        tcpConnManager.createAndOpenServer(port);
    }

    public void openUdpServer(int port) {
        udpConnManager.createAndOpenServer(port);
    }


    public void connectRemoteUdpServer(String ip, String port) {
        udpConnManager.connectRemoteServer(ip, port);
    }
}
