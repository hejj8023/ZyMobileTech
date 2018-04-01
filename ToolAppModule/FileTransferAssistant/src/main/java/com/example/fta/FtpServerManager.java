package com.example.fta;

import com.example.fta.server.manager.ApacheFtpManager;
import com.example.fta.server.manager.SwiftpManager;

import org.apache.ftpserver.ftplet.FtpException;

/**
 * Created by zzg on 2018/3/31.
 */

public class FtpServerManager {

    private static FtpServerManager INSTANCE = null;

    // 0 server,1 client
    private int mType;

    // 0 apache,1 swiftp
    private int mMode;

    private ApacheFtpManager apacheFtpManager;
    private SwiftpManager swiftpManager;
    private String mUserName, mUserPwd, mServerIp, mServerPort;

    private FtpServerManager() {

    }

    public static FtpServerManager getInstance() {
        if (INSTANCE == null) {
            synchronized (FtpServerManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FtpServerManager();
                }
            }
        }
        return INSTANCE;
    }


    public void setServerType(int type) {
        this.mType = type;
    }

    public void seterverMode(int mode) {
        this.mMode = mode;
    }

    public void setUserName(String name) {
        this.mUserName = name;
    }

    public void setServerIp(String ipAddress) {
        this.mServerIp = ipAddress;
    }

    public void setServerPort(String port) {
        this.mServerPort = port;
    }

    public void setUserPwd(String pwd) {
        this.mUserPwd = pwd;
    }

    public void init() {
        if (mType == Const.TYPE_SERVER) {
            switch (mMode) {
                case Const.MODE_APACHE_FTP:
                    if (apacheFtpManager == null)
                        apacheFtpManager = new ApacheFtpManager(mServerIp, mServerPort, mUserName, mUserPwd);

                    apacheFtpManager.init();
                    break;
                case Const.MODE_SWI_FTP:
                    if (swiftpManager == null)
                        swiftpManager = new SwiftpManager();
                    swiftpManager.init();
                    break;
            }
        }

    }

    public void start() throws FtpException {
        if (mType == Const.TYPE_SERVER) {
            switch (mMode) {
                case Const.MODE_APACHE_FTP:
                    apacheFtpManager.start();
                    break;
                case Const.MODE_SWI_FTP:
                    swiftpManager.start();
                    break;
            }
        }
    }
}
