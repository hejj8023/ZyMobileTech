package com.example.wav.mvp.presenter;

import com.example.wav.bean.AccountCustomerInfo;
import com.example.wav.bean.AccountDeviceInfo;
import com.example.wav.bean.AccountGroupInfo;
import com.example.wav.mvp.contract.FilterNewContract;
import com.example.wav.mvp.model.FilterNewModel;
import com.zhiyangstudio.commonlib.mvp.presenter.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by example on 2018/4/28.
 */

public class FilterNewPresenter extends BasePresenter<FilterNewContract.IFilterNewView>
        implements FilterNewContract.IFilterNewPresenter {

    private final FilterNewModel mModel;
    private FilterNewContract.IFilterNewView mFilterNewView;

    @Inject
    public FilterNewPresenter() {
        mModel = new FilterNewModel();
    }

    @Override
    public void loadData() {
        mFilterNewView = getView();
        mModel.getData(new Consumer<List<AccountCustomerInfo>>() {
            @Override
            public void accept(List<AccountCustomerInfo> accountCustomerInfos) throws Exception {
                mFilterNewView.setCustomerListData(accountCustomerInfos);
            }
        }, new Consumer<List<AccountGroupInfo>>() {
            @Override
            public void accept(List<AccountGroupInfo> accountGroupInfos) throws Exception {
                mFilterNewView.setCustomerGroupListData(accountGroupInfos);
            }
        }, new Consumer<AccountDeviceInfo>() {
            @Override
            public void accept(AccountDeviceInfo accountDeviceInfo) throws Exception {
                mFilterNewView.setDeviceListData(accountDeviceInfo);
            }
        });
    }
}
