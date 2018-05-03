package com.example.daw.di.component;


import com.example.daw.DawApp;
import com.example.daw.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by zhiyang on 2018/4/25.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    DawApp getContext();
}
