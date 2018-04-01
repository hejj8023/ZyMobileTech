package com.example.fta;

/**
 * Created by zzg on 2018/4/1.
 */

public class FtpClientManager {
    private static FtpClientManager INSTANCE = null;
    private String mUserName, mUserPwd, mServerIp, mServerPort;

    private FtpClientManager() {
    }

    public static FtpClientManager getInstance() {
        if (INSTANCE == null) {
            synchronized (FtpClientManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FtpClientManager();
                }
            }
        }
        return INSTANCE;
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

    }

    public void connect() {

    }
}
