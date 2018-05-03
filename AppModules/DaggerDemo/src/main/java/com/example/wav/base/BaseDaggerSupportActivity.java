package com.example.wav.base;

import com.example.wav.AdvApp;
import com.example.wav.di.component.ActivityComponent;
import com.example.wav.di.component.DaggerActivityComponent;
import com.example.wav.di.module.ActivityModule;
import com.zhiyangstudio.commonlib.mvp.BaseMVPSupportActivivty;
import com.zhiyangstudio.commonlib.mvp.inter.IView;
import com.zhiyangstudio.commonlib.mvp.presenter.BasePresenter;

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
        ActivityComponent mActivityComponent = DaggerActivityComponent.builder().appComponent(AdvApp.getAppComponent())
                .activityModule(new ActivityModule(this))
                .build();
        return mActivityComponent;
    }
}
