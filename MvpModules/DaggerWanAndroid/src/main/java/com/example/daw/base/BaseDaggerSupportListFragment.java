package com.example.daw.base;

import android.content.Context;

import com.example.daw.DawApp;
import com.example.daw.di.component.DaggerFragmentComponent;
import com.example.daw.di.component.FragmentComponent;
import com.example.daw.di.module.FragmentModule;
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
        return DaggerFragmentComponent.builder().appComponent(DawApp.getAppComponent())
                .fragmentModule(new FragmentModule(this)).build();
    }
}
