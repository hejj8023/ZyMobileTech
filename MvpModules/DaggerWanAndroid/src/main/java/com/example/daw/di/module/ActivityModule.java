package com.example.daw.di.module;

import android.app.Activity;

import com.example.daw.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zhiyang on 2018/4/25.
 */

@Module
public class ActivityModule {
    private final Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityScope
    Activity provideActivity() {
        return mActivity;
    }
}
