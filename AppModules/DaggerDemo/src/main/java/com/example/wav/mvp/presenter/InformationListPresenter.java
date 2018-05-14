package com.example.wav.mvp.presenter;


import com.example.wav.bean.InformationBean;
import com.example.wav.mvp.contract.InformationContract;
import com.example.wav.mvp.model.InformationListModel;
import com.zhiyangstudio.commonlib.mvp.presenter.BasePresenter;
import com.zhiyangstudio.commonlib.net.callback.AbsBaseObserver;

import java.util.List;

/**
 * Created by example on 2018/5/14.
 */

public class InformationListPresenter extends BasePresenter<InformationContract.IInformationView>
        implements InformationContract.IInformationPresenter {

    private final InformationListModel mModel;

    public InformationListPresenter() {
        mModel = new InformationListModel();
    }

    @Override
    public void getInformationList() {
        mModel.getList(new AbsBaseObserver<List<InformationBean>>(this, InformationListModel
                .class.getName
                ()) {
            @Override
            public void onNext(List<InformationBean> informationBeans) {
                getView().setData(informationBeans);
            }
        });
    }
}
