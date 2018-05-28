package com.example.bailan.di.component;

import android.app.Activity;

import com.example.bailan.di.module.ActivityModule;
import com.example.bailan.di.scope.ActivityScope;
import com.example.bailan.ui.activity.MainActivity;

import dagger.Component;

/**
 * Created by zhiyang on 2018/4/25.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();

    void inject(MainActivity activity);
}
