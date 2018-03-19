package com.example.common.corel;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.blankj.utilcode.util.Utils;
import com.example.common.BuildConfig;
import com.example.common.CommonConst;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * Created by example on 2018/2/9.
 */

public class BaseApp extends Application {

    private static BaseApp mAppInstance;
    private static Context mContext;
    private static int mThreadId;
    private static String mThreadName;
    private static Handler handler;
    private static Looper looper;
    private static int myPid;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppInstance = this;
        mContext = getApplicationContext();
        handler = new Handler();
        looper = getMainLooper();
        myPid = android.os.Process.myPid();
        mThreadId = android.os.Process.myTid();
        mThreadName = Thread.currentThread().getName();

        // TODO: 2018/3/14 Utils init
        Utils.init(this);
        
        initLogger();
    }

    private void initLogger() {
        // TODO: 2018/2/2 logger高级用法
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(0)         // (Optional) How many method line to show. Default 2
                .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
                .tag(CommonConst.LOG_TAG)   // (Optional) Global tag for every log. Default
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                /*是否需要打印日志*/
                return BuildConfig.DEBUG;
            }
        });
    }

    public static String getMainThreadName() {
        return mThreadName;
    }

    public static int getAppProcessId() {
        return myPid;
    }

    public static int getMainThreadId() {
        return mThreadId;
    }

    public static Looper getAppLooper() {
        return looper;
    }

    public static Handler getAppHandler() {
        return handler;
    }

    public static Context getAppInstance() {
        return mAppInstance;
    }

    public static Context getContext() {
        return mContext;
    }
}
