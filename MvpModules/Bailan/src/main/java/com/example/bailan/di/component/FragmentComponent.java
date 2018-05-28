package com.example.bailan.di.component;


import com.example.bailan.di.module.FragmentModule;
import com.example.bailan.di.scope.FragmentScope;
import com.example.bailan.ui.fragment.CategoryFragment;
import com.example.bailan.ui.fragment.RankFragment;
import com.example.bailan.ui.fragment.RecommendFragment;

import dagger.Component;

/**
 * Created by example on 2018/4/28.
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    void inject(RecommendFragment fragment);

    void inject(CategoryFragment fragment);

    void inject(RankFragment fragment);
}
