package com.example.wav.mvp.presenter;

import com.example.wav.mvp.contract.SRLContract;
import com.zhiyangstudio.commonlib.mvp.presenter.BasePresenter;

import javax.inject.Inject;

/**
 * Created by example on 2018/5/9.
 */

public class SRLPresenter extends BasePresenter<SRLContract.SRLIView> implements SRLContract
        .SRLIPresenter {
    @Inject
    public SRLPresenter() {

    }

}
