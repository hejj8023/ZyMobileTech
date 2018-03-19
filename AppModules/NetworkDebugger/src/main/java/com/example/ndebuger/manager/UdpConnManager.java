package com.example.ndebuger.manager;

import android.content.Context;
import android.os.Handler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zzg on 2018/3/18.
 */

public class UdpConnManager {
    private static UdpConnManager mInstance;
    private final Context mContext;

    private ExecutorService threadPool = Executors.newCachedThreadPool();

    private UdpConnManager(Context context, Handler handler) {
        this.mContext = context;
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
}
