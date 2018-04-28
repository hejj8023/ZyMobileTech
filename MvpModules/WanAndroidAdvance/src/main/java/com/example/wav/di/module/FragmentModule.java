package com.example.wav.di.module;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.example.wav.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by example on 2018/4/28.
 */
@Module
public class FragmentModule {
    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    public Activity provideActivity() {
        return fragment.getActivity();
    }

}
