package com.example.fta;

import com.example.fta.manager.ApacheFtpManager;
import com.example.fta.manager.SwiftpManager;

/**
 * Created by zzg on 2018/3/31.
 */

public class FtpServerManager {

    private static FtpServerManager INSTANCE = null;
    // 0 apache,1 swiftp
    // 0 server,1 client
    private int mType, mMode;
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
        if (mType == 0) {
            if (apacheFtpManager == null)
                apacheFtpManager = new ApacheFtpManager(mServerIp, mServerPort, mUserName, mUserPwd);

            apacheFtpManager.setMode(mMode);
            apacheFtpManager.init();
        } else {
            if (swiftpManager == null)
                swiftpManager = new SwiftpManager();
            swiftpManager.setMode(mMode);
            swiftpManager.init();
        }
    }

    public void start() {
        if (mType == 0) {
            apacheFtpManager.start();
        } else {
            swiftpManager.start();
        }
    }
}
