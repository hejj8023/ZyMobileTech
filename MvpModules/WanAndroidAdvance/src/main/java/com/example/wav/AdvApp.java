package com.example.wav;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.example.wav.di.component.AppComponent;
import com.example.wav.di.component.DaggerAppComponent;
import com.example.wav.di.module.AppModule;
import com.zhiyangstudio.commonlib.corel.BaseApp;

/**
 * Created by zhiyang on 2018/4/25.
 */

public class AdvApp extends BaseApp {

    private static AppComponent mAppComponent;

    public static AppComponent getAppComponent() {
        return mAppComponent;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initComponent();
    }

    private void initComponent() {
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this))
                .build();
    }

    @Override
    protected String getLogTag() {
        return "wanandroidadv";
    }
}
