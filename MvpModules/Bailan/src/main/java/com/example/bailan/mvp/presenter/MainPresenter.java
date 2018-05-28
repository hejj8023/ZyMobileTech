package com.example.bailan.mvp.presenter;

import com.example.bailan.mvp.contract.MainContract;
import com.zhiyangstudio.commonlib.mvp.presenter.BasePresenter;

import javax.inject.Inject;

/**
 * Created by zzg on 2018/5/27.
 */

public class MainPresenter extends BasePresenter<MainContract.IMainView> implements MainContract.IMainPresenter {
    @Inject
    public MainPresenter() {

    }
}
