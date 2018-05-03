package com.example.daw.di.component;


import com.example.daw.di.module.FragmentModule;
import com.example.daw.di.scope.FragmentScope;

import dagger.Component;

/**
 * Created by example on 2018/4/28.
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
}
