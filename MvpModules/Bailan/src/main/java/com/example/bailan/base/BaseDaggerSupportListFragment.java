package com.example.bailan.base;

import android.content.Context;

import com.example.bailan.BLApp;
import com.example.bailan.di.component.DaggerFragmentComponent;
import com.example.bailan.di.component.FragmentComponent;
import com.example.bailan.di.module.FragmentModule;
import com.zysdk.vulture.clib.mvp.inter.ISampleRefreshView;
import com.zysdk.vulture.clib.mvp.presenter.BasePresenter;
import com.zysdk.vulture.clib.refreshsupport.smartrefresh.BaseMVPSRRListFragment;

import javax.inject.Inject;

/**
 * Created by example on 2018/4/28.
 */

public abstract class BaseDaggerSupportListFragment<P extends BasePresenter<V>, V extends ISampleRefreshView, T>
        extends BaseMVPSRRListFragment<P, V, T> {

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
        return DaggerFragmentComponent.builder().appComponent(BLApp.getAppComponent())
                .fragmentModule(new FragmentModule(this)).build();
    }
}
