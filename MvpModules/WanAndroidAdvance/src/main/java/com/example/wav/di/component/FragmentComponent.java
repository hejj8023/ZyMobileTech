package com.example.wav.di.component;

import com.example.wav.di.module.FragmentModule;
import com.example.wav.di.scope.FragmentScope;
import com.example.wav.ui.fragment.HomeFragment;

import dagger.Component;

/**
 * Created by example on 2018/4/28.
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    void inject(HomeFragment fragment);
}
