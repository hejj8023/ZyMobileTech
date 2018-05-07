package com.example.wav.mvp.presenter;

import com.example.wav.bean.DevHistoryBean;
import com.example.wav.mvp.contract.DDContract;
import com.example.wav.mvp.model.DDModel;
import com.zhiyangstudio.commonlib.mvp.presenter.BasePresenter;
import com.zhiyangstudio.commonlib.net.callback.AbsBaseObserver;

import javax.inject.Inject;

/**
 * Created by example on 2018/5/7.
 */

public class DDPresenter extends BasePresenter<DDContract.IDDView> implements DDContract
        .IDDPresenter {

    private final DDModel mDdModel;
    private DDContract.IDDView mIDDView;

    @Inject
    public DDPresenter() {
        mDdModel = new DDModel();
    }

    @Override
    public void getDevHistory() {
        mIDDView = getView();
        mDdModel.getDevHistory(mIDDView.getDevId(), new AbsBaseObserver<DevHistoryBean>(this,
                DDModel.class.getName()) {
            @Override
            public void onNext(DevHistoryBean responseBody) {
                if (responseBody != null) {
                    mIDDView.setData(responseBody);
                }
            }
        });
    }
}
