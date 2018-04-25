package com.example.wav.di.component;

import com.example.wav.AdvApp;
import com.example.wav.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by zhiyang on 2018/4/25.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    AdvApp getContext();


}
