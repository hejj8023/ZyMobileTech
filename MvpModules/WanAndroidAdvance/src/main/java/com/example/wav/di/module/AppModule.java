package com.example.wav.di.module;

import com.example.wav.AdvApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zhiyang on 2018/4/25.
 */
@Module
public class AppModule {

    private final AdvApp application;

    public AppModule(AdvApp app) {
        this.application = app;
    }

    @Provides
    @Singleton
    AdvApp provideApplicationContext() {
        return application;
    }
}
