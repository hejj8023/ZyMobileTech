package com.example.wav.di.component;

import com.example.wav.di.module.FragmentModule;
import com.example.wav.di.scope.FragmentScope;
import com.example.wav.ui.fragment.HomeFragment;
import com.example.wav.ui.fragment.filter.FilterCustomerGroupListFragment;
import com.example.wav.ui.fragment.filter.FilterCustomerListFragment;
import com.example.wav.ui.fragment.filter.FilterDeviceListFragment;

import dagger.Component;

/**
 * Created by example on 2018/4/28.
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    void inject(HomeFragment fragment);

    void inject(FilterCustomerGroupListFragment fragment);

    void inject(FilterCustomerListFragment fragment);

    void inject(FilterDeviceListFragment fragment);
}
