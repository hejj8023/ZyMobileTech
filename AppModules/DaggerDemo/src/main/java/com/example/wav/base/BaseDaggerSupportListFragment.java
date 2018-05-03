package com.example.wav.base;

import android.content.Context;

import com.example.wav.AdvApp;
import com.example.wav.di.component.DaggerFragmentComponent;
import com.example.wav.di.component.FragmentComponent;
import com.example.wav.di.module.FragmentModule;
import com.zhiyangstudio.commonlib.mvp.BaseAbsListFragment;
import com.zhiyangstudio.commonlib.mvp.inter.IView;
import com.zhiyangstudio.commonlib.mvp.presenter.BasePresenter;

import javax.inject.Inject;

/**
 * Created by example on 2018/4/28.
 */

public abstract class BaseDaggerSupportListFragment<P extends BasePresenter<V>, V extends IView, T>
        extends BaseAbsListFragment<P, V, T> {

    @Inject
    P tPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        inject();
        mPresenter = tPresenter;
        attachView();
    }

    protected abstract void inject();

    @Override
    protected boolean hasSupport() {
        return false;
    }

    @Override
    protected P createPresenter() {
        return null;
    }

    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder().appComponent(AdvApp.getAppComponent())
                .fragmentModule(new FragmentModule(this)).build();
    }
}
