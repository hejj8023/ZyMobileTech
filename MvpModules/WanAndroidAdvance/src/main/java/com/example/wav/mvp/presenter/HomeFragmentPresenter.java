package com.example.wav.mvp.presenter;

import com.example.wav.mvp.contract.HomeFragmentContract;
import com.zhiyangstudio.commonlib.mvp.presenter.BasePresenter;

import javax.inject.Inject;

/**
 * Created by example on 2018/4/28.
 */

public class HomeFragmentPresenter extends BasePresenter<HomeFragmentContract
        .IHomeFragmentView> implements HomeFragmentContract.IHomeFragmentPresenter {
    @Inject
    public HomeFragmentPresenter() {

    }
}
