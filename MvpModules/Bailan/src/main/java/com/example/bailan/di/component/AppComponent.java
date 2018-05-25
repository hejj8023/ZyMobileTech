package com.example.bailan.di.component;


import com.example.bailan.BLApp;
import com.example.bailan.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by zhiyang on 2018/4/25.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    BLApp getContext();
}
