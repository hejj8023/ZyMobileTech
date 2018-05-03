package com.example.daw.di.component;

import android.app.Activity;

import com.example.daw.LoginActivity;
import com.example.daw.RegisterActivity;
import com.example.daw.di.module.ActivityModule;
import com.example.daw.di.scope.ActivityScope;

import dagger.Component;

/**
 * Created by zhiyang on 2018/4/25.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();

    void inject(LoginActivity activity);

    void inject(RegisterActivity activity);
}
