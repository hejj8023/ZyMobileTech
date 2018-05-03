package com.example.daw.di.module;


import com.example.daw.DawApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zhiyang on 2018/4/25.
 */
@Module
public class AppModule {

    private final DawApp application;

    public AppModule(DawApp app) {
        this.application = app;
    }

    @Provides
    @Singleton
    DawApp provideApplicationContext() {
        return application;
    }
}
