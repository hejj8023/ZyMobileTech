package com.example.bailan.di.module;

import com.example.bailan.BLApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zhiyang on 2018/4/25.
 */
@Module
public class AppModule {

    private final BLApp application;

    public AppModule(BLApp app) {
        this.application = app;
    }

    @Provides
    @Singleton
    BLApp provideApplicationContext() {
        return application;
    }
}
