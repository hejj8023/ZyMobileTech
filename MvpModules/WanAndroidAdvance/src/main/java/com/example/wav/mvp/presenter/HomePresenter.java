package com.example.wav.mvp.presenter;

import com.example.wav.mvp.contract.HomeContract;
import com.zhiyangstudio.commonlib.mvp.presenter.BasePresenter;

import javax.inject.Inject;

/**
 * Created by example on 2018/4/27.
 */

public class HomePresenter extends BasePresenter<HomeContract.IHomeView> implements HomeContract
        .IHomePresenter {
    @Inject
    public HomePresenter() {

    }
}
