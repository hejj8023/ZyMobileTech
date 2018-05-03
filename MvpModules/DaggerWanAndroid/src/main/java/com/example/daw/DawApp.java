package com.example.daw;

import com.example.daw.di.component.AppComponent;
import com.example.daw.di.component.DaggerAppComponent;
import com.example.daw.di.module.AppModule;
import com.zhiyangstudio.commonlib.corel.BaseApp;

/**
 * Created by example on 2018/5/3.
 */

public class DawApp extends BaseApp {

    private static AppComponent mAppComponent;

    public static AppComponent getAppComponent() {
        return mAppComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initComponent();
    }

    private void initComponent() {
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }
}
