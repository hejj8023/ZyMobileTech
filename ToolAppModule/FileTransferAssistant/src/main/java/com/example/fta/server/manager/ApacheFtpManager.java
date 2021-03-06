package com.example.fta.server.manager;

import org.apache.ftpserver.ftplet.FtpException;

/**
 * Created by zzg on 2018/3/31.
 */

public class ApacheFtpManager {

    private final FtpServerlet ftpServerlet;

    public ApacheFtpManager(String mServerIp, String mServerPort, String mUserName, String mUserPwd) {
        ftpServerlet = FtpServerlet.getInstance(mServerPort, mUserName, mUserPwd);
    }

    public void init() {

    }

    public void start() throws FtpException {
        ftpServerlet.start();
    }
}
