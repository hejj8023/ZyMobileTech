package com.example.wav.mvp.presenter;

import com.example.wav.mvp.contract.MainContract;
import com.zhiyangstudio.commonlib.mvp.presenter.BasePresenter;
import com.zhiyangstudio.commonlib.utils.LoggerUtils;

import javax.inject.Inject;

/**
 * Created by zhiyang on 2018/4/25.
 */

public class MainPresenter extends BasePresenter<MainContract.IMainView> implements MainContract
        .IMainPresenter {
    @Inject
    public MainPresenter() {

    }

    @Override
    public void log(String s) {
        LoggerUtils.loge(s);
    }
}
