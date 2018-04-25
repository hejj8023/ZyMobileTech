package com.example.wav.di.component;

import android.app.Activity;

import com.example.wav.ui.activity.MainActivity;
import com.example.wav.ui.activity.SplashActivity;
import com.example.wav.di.module.ActivityModule;
import com.example.wav.di.scope.ActivityScope;

import dagger.Component;

/**
 * Created by zhiyang on 2018/4/25.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();

    void inject(MainActivity mainActivity);

    void inject(SplashActivity activity);
}
