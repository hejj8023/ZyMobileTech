package com.example.daw.base;

import android.content.Context;

import com.example.daw.DawApp;
import com.example.daw.di.component.DaggerFragmentComponent;
import com.example.daw.di.component.FragmentComponent;
import com.example.daw.di.module.FragmentModule;
import com.zysdk.vulture.clib.mvp.BaseMVPSupportFragment;
import com.zysdk.vulture.clib.mvp.inter.IView;
import com.zysdk.vulture.clib.mvp.presenter.BasePresenter;

import javax.inject.Inject;

/**
 * Created by example on 2018/4/28.
 */

public abstract class BaseDaggerSupportFragment<P extends BasePresenter<V>, V extends IView> extends
        BaseMVPSupportFragment<P, V> {

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
