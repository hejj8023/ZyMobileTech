package com.example.daw.base;

import com.example.daw.DawApp;
import com.example.daw.di.component.ActivityComponent;
import com.example.daw.di.component.DaggerActivityComponent;
import com.example.daw.di.module.ActivityModule;
import com.zysdk.vulture.clib.mvp.BaseMVPSupportActivivty;
import com.zysdk.vulture.clib.mvp.inter.IView;
import com.zysdk.vulture.clib.mvp.presenter.BasePresenter;

import javax.inject.Inject;

/**
 * Created by zhiyang on 2018/4/25.
 * dagger支持的activity
 */

public abstract class BaseDaggerSupportActivity<P extends BasePresenter<V>, V extends IView> extends
        BaseMVPSupportActivivty<P, V> {
    @Inject
    protected P mT;

    @Override
    public void beforeSubContentInit() {
        initInject();
        mPresenter = mT;
        attachView();
    }

    protected abstract void initInject();

    @Override
    protected P createPresenter() {
        return null;
    }

    protected ActivityComponent getActivityComponent() {
        ActivityComponent mActivityComponent = DaggerActivityComponent.builder().appComponent(DawApp
                .getAppComponent())
                .activityModule(new ActivityModule(this))
                .build();
        return mActivityComponent;
    }
}
