package com.example.wav.mvp.presenter;

import com.example.wav.bean.AccountCustomerInfo;
import com.example.wav.mvp.contract.FilterCustomerListContract;
import com.example.wav.mvp.model.FilterCustomerListModel;
import com.example.wav.mvp.model.FilterNewModel;
import com.zhiyangstudio.commonlib.mvp.presenter.BasePresenter;
import com.zhiyangstudio.commonlib.net.callback.AbsBaseObserver;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by example on 2018/5/2.
 */

public class FilterCustomerListPresenter extends BasePresenter<FilterCustomerListContract
        .IFilterCustomerListView> implements FilterCustomerListContract
        .IFilterCustomerListPresenter {

    private final FilterCustomerListModel mModel;
    private FilterCustomerListContract.IFilterCustomerListView mFilterNewView;

    @Inject
    public FilterCustomerListPresenter() {
        mModel = new FilterCustomerListModel();
    }

    @Override
    public void getData() {
        mFilterNewView = getView();
        mModel.getData(
                new AbsBaseObserver<List<AccountCustomerInfo>>(this,
                        FilterNewModel.class.getName()) {
                    @Override
                    public void onNext(List<AccountCustomerInfo> accountCustomerInfos) {
                        mFilterNewView.setData(accountCustomerInfos);
                        mFilterNewView.showContent();
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideLoading();
                    }
                });
    }
}
