package com.example.wav.mvp.presenter;

import com.example.wav.mvp.contract.SplashContract;
import com.zhiyangstudio.commonlib.mvp.presenter.BasePresenter;
import com.zhiyangstudio.commonlib.utils.LoggerUtils;

import javax.inject.Inject;

/**
 * Created by zhiyang on 2018/4/25.
 */

public class SplashPresenter extends BasePresenter<SplashContract.ISplashView> implements
        SplashContract.ISplashPresenter {
    @Inject
    public SplashPresenter() {

    }

    @Override
    public void log(String data) {
        LoggerUtils.loge("data");
    }
}
