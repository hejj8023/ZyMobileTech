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
    private final ApacheFtpManager apacheFtpManager;
    private final SwiftpManager swiftpManager;

    private FtpServerManager() {
        apacheFtpManager = new ApacheFtpManager();
        swiftpManager = new SwiftpManager();
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

    public void init() {
        if (mType == 0) {
            apacheFtpManager.setMode(mMode);
            apacheFtpManager.init();
        } else {
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
