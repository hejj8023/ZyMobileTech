package com.example.wav.di.component;

import android.app.Activity;

import com.example.wav.di.module.ActivityModule;
import com.example.wav.di.scope.ActivityScope;
import com.example.wav.ui.activity.AccountRegActivity;
import com.example.wav.ui.activity.ApiTestActivity;
import com.example.wav.ui.activity.DeviceListActivity;
import com.example.wav.ui.activity.DeviceRegActivity;
import com.example.wav.ui.activity.FilterActivity;
import com.example.wav.ui.activity.FilterNewActivity;
import com.example.wav.ui.activity.HomeActivity;
import com.example.wav.ui.activity.LoginActivity;
import com.example.wav.ui.activity.MainActivity;
import com.example.wav.ui.activity.NewFilterActivity;
import com.example.wav.ui.activity.NewHomeActivity;
import com.example.wav.ui.activity.SplashActivity;

import dagger.Component;

/**
 * Created by zhiyang on 2018/4/25.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();

    void inject(MainActivity activity);

    void inject(SplashActivity activity);

    void inject(FilterActivity activity);

    void inject(DeviceListActivity activity);

    void inject(ApiTestActivity activity);

    void inject(HomeActivity activity);

    void inject(LoginActivity activity);

    void inject(NewHomeActivity activity);

    void inject(FilterNewActivity activity);

    void inject(NewFilterActivity activity);

    void inject(DeviceRegActivity activity);

    void inject(AccountRegActivity activity);
}
