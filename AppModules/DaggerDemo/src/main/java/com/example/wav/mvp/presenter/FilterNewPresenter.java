package com.example.wav.mvp.presenter;

import com.example.wav.DataManager;
import com.example.wav.bean.AccountCustomerInfo;
import com.example.wav.bean.AccountDeviceInfo;
import com.example.wav.bean.AccountGroupInfo;
import com.example.wav.mvp.contract.FilterNewContract;
import com.example.wav.mvp.model.FilterNewModel;
import com.zhiyangstudio.commonlib.mvp.presenter.BasePresenter;
import com.zhiyangstudio.commonlib.net.callback.AbsBaseObserver;

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
    public void getAllData() {
        mFilterNewView = getView();
        mModel.getAllData(new Consumer<List<AccountCustomerInfo>>() {
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

    @Override
    public void getCustomerListData() {
        mFilterNewView = getView();
        mModel.getCustomerListData(
                new AbsBaseObserver<List<AccountCustomerInfo>>(this,
                        FilterNewModel.class.getName()) {
                    @Override
                    public void onNext(List<AccountCustomerInfo> accountCustomerInfos) {
                        mFilterNewView.setCustomerListData(accountCustomerInfos);
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideLoading();
                    }
                });
    }

    @Override
    public void getCustomerGroupListData() {
        mFilterNewView = getView();
        String defaultUserId = DataManager.getDefaultUserId();
        mModel.getCustomerGroupListData(defaultUserId,
                new AbsBaseObserver<List<AccountGroupInfo>>(this,
                        FilterNewModel.class.getName()) {
                    @Override
                    public void onNext(List<AccountGroupInfo> accountGroupInfos) {
                        mFilterNewView.setCustomerGroupListData(accountGroupInfos);
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideLoading();
                    }
                });
    }

    @Override
    public void getDevListData() {
        mFilterNewView = getView();
        String defaultUserId = DataManager.getDefaultUserId();
        String defaultGroupId = DataManager.getDefaultGroupId();
        mModel.getDevListData(defaultUserId,
                defaultGroupId,
                mFilterNewView.getStatus(),
                mFilterNewView.getPage(),
                mFilterNewView.getPageSize(),
                new AbsBaseObserver<AccountDeviceInfo>(this,
                        FilterNewModel.class.getName()) {
                    @Override
                    public void onNext(AccountDeviceInfo accountDeviceInfo) {
                        mFilterNewView.setDeviceListData(accountDeviceInfo);
                    }
                });
    }
}
