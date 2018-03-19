package com.example.mftpserver;

import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.example.utils.LogListener;
import com.example.utils.LoggerUtils;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.ftplet.DefaultFtplet;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.ftplet.FtpRequest;
import org.apache.ftpserver.ftplet.FtpSession;
import org.apache.ftpserver.ftplet.Ftplet;
import org.apache.ftpserver.ftplet.FtpletResult;
import org.apache.ftpserver.ftplet.UserManager;
import org.apache.ftpserver.listener.Listener;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;
import org.apache.ftpserver.usermanager.SaltedPasswordEncryptor;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.apache.ftpserver.usermanager.impl.WritePermission;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FtpServerlet extends DefaultFtplet implements LogListener {

    private FtpServer mFtpServer;

    private int mPort = 2121;

    private String mDirectory = Environment.getExternalStorageDirectory().getPath() + "/FtpFileTest";

    private String mUser = "way";

    private String mPassword = "way";

    private static FtpServerlet mInstance;

    private FtpServerlet(String portStr, String nameStr, String pwdStr, String filePath) {
        mPort = Integer.parseInt(portStr);
        mUser = nameStr;
        mPassword = pwdStr;
        mDirectory = filePath;
    }

    public static FtpServerlet getInstance(String portStr, String nameStr, String pwdStr, String filePath) {
        if (mInstance == null) {
            mInstance = new FtpServerlet(portStr, nameStr, pwdStr, filePath);
        }
        return mInstance;
    }

    /**
     * FTP启动
     */
    public void start() throws FtpException {
        if (null != mFtpServer && !mFtpServer.isStopped()) {
            return;
        }

        File file = new File(mDirectory);
        if (!file.exists()) {
            file.mkdirs();
        }

        FtpServerFactory serverFactory = new FtpServerFactory();
        ListenerFactory listenerFactory = new ListenerFactory();

        // 设定端末番号
        listenerFactory.setPort(mPort);

        // 通过PropertiesUserManagerFactory创建UserManager然后向配置文件添加用户
        PropertiesUserManagerFactory userManagerFactory = new PropertiesUserManagerFactory();
        userManagerFactory.setPasswordEncryptor(new SaltedPasswordEncryptor());
        UserManager userManager = userManagerFactory.createUserManager();

        List<Authority> auths = new ArrayList<Authority>();
        Authority auth = new WritePermission();
        auths.add(auth);

        //添加用户
        BaseUser user = new BaseUser();
        user.setName(mUser);
        user.setPassword(mPassword);
        user.setHomeDirectory(mDirectory);
        user.setAuthorities(auths);
        userManager.save(user);

        // 设定Ftplet
        Map<String, Ftplet> ftpletMap = new HashMap<String, Ftplet>();
        ftpletMap.put("Ftplet", this);

        serverFactory.setUserManager(userManager);
        Listener listener = listenerFactory.createListener();
        serverFactory.addListener("default", listener);
        serverFactory.setFtplets(ftpletMap);

        // 创建并启动FTPServer
        mFtpServer = serverFactory.createServer();
        mFtpServer.start();
    }

    /**
     * FTP停止
     */
    public void stop() {
        // FtpServer不存在和FtpServer正在运行中
        if (null != mFtpServer && !mFtpServer.isStopped()) {
            mFtpServer.stop();
        }
    }

    @Override
    public FtpletResult onAppendStart(FtpSession session, FtpRequest request)
            throws FtpException, IOException {
        LoggerUtils.loge(this, "onAppendStart");
        return super.onAppendStart(session, request);
    }

    @Override
    public FtpletResult onAppendEnd(FtpSession session, FtpRequest request)
            throws FtpException, IOException {
        LoggerUtils.loge(this, "onAppendEnd");
        return super.onAppendEnd(session, request);
    }

    @Override
    public FtpletResult onLogin(FtpSession session, FtpRequest request)
            throws FtpException, IOException {
        LoggerUtils.loge(this, "onLogin");
        return super.onLogin(session, request);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public FtpletResult onConnect(FtpSession session) throws FtpException,
            IOException {
        LoggerUtils.loge(this, "onConnect");
        InetSocketAddress serverAddress = session.getServerAddress();
        InetSocketAddress clientAddress = session.getClientAddress();
        Log.i("TAGxc FtpServer:", "hostName: " + serverAddress.getHostName() +
                ", hostString: " +
                serverAddress.getHostString()
                + ", address: "
                + serverAddress.getAddress()
                + ", port: "
                + serverAddress.getPort());
        Log.i("TAGxc FtpClient:", "hostName: " + clientAddress.getHostName() +
                ", hostString: " +
                clientAddress.getHostString()
                + ", address: "
                + clientAddress.getAddress()
                + ", port: "
                + clientAddress.getPort());
        return super.onConnect(session);
    }

    @Override
    public FtpletResult onDisconnect(FtpSession session) throws FtpException,
            IOException {
        LoggerUtils.loge(this, "onDisconnect");
        return super.onDisconnect(session);
    }

    @Override
    public FtpletResult onUploadStart(FtpSession session, FtpRequest request)
            throws FtpException, IOException {
        LoggerUtils.loge(this, "onUploadStart");
        return super.onUploadStart(session, request);
    }

    @Override
    public FtpletResult onUploadEnd(FtpSession session, FtpRequest request)
            throws FtpException, IOException {
        String FtpUploadPath = mDirectory + "/" + request.getArgument();
        //接收到文件后立即删除
        File uploadFile = new File(FtpUploadPath);
        uploadFile.delete();
        return super.onUploadEnd(session, request);
    }
}