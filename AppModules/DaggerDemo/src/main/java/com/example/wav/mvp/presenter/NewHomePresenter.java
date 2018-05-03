package com.example.wav.mvp.presenter;

import com.example.wav.mvp.contract.NewHomeContract;
import com.zhiyangstudio.commonlib.mvp.presenter.BasePresenter;

import javax.inject.Inject;

/**
 * Created by example on 2018/4/28.
 */

public class NewHomePresenter extends BasePresenter<NewHomeContract.INewHomeView> implements
        NewHomeContract.INewHomePresenter {
    @Inject
    public NewHomePresenter() {

    }
}
