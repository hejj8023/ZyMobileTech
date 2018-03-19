package com.example.mftpserver;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.utils.LogListener;
import com.example.utils.LoggerUtils;

import org.apache.ftpserver.ftplet.FtpException;

/**
 * Created by zzg on 2018/3/14.
 */

public class FtpServices extends Service implements LogListener {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        LoggerUtils.loge(this, "onBind");
        return new MyBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LoggerUtils.loge(this, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int command = super.onStartCommand(intent, flags, startId);
        LoggerUtils.loge(this, "onStartCommand");
        return command;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        LoggerUtils.loge(this, "onStart");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LoggerUtils.loge(this, "onDestroy");
    }

    public void startService(String portStr, String nameStr, String pwdStr,String filePath) {
        try {
            FtpServerlet.getInstance(portStr, nameStr, pwdStr,filePath).start();
        } catch (FtpException e) {
            e.printStackTrace();
        }
    }

    public class MyBinder extends Binder {
        public void start(String portStr, String nameStr, String pwdStr,String filePath) {
            startService(portStr, nameStr, pwdStr,filePath);
        }
    }
}
