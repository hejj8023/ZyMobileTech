package com.example.wav.mvp.presenter;

import com.example.wav.Const;
import com.example.wav.bean.CustomerGroupInfo;
import com.example.wav.bean.CustomerInfo;
import com.example.wav.bean.DeviceInfo;
import com.example.wav.mvp.contract.FilterContract;
import com.example.wav.mvp.model.FilterModel;
import com.zhiyangstudio.commonlib.mvp.presenter.BasePresenter;
import com.zhiyangstudio.commonlib.net.callback.AbsBaseObserver;
import com.zhiyangstudio.commonlib.utils.LoggerUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by zhiyang on 2018/4/26.
 */

public class FilterPresenter extends BasePresenter<FilterContract.IFilterView> implements
        FilterContract.IFilterPresenter {

    private final FilterModel mFilterModel;
    private FilterContract.IFilterView mFilterView;

    @Inject
    public FilterPresenter() {
        mFilterModel = new FilterModel();
    }

    @Override
    public void loadList() {
        mFilterView = getView();
        mFilterModel.getDataList(new Consumer<List<CustomerInfo>>() {
            @Override
            public void accept(List<CustomerInfo> customerInfos) throws Exception {
                LoggerUtils.loge("accept CustomerInfo list");
                Const.TMP_DATA.CUSTOMER_LIST.clear();
                Const.TMP_DATA.CUSTOMER_LIST.addAll(customerInfos);
                mFilterView.setCustomerInfoData(customerInfos);
            }
        }, new Consumer<List<CustomerGroupInfo>>() {
            @Override
            public void accept(List<CustomerGroupInfo> customerGroupInfos) throws Exception {
                LoggerUtils.loge("onNext CustomerGroupInfo list");
                Const.TMP_DATA.CUSTOMERGROUP_LIST.clear();
                Const.TMP_DATA.CUSTOMERGROUP_LIST.addAll(customerGroupInfos);
                mFilterView.setCustomerGroupInfoData(customerGroupInfos);
            }
        }, new AbsBaseObserver<List<DeviceInfo>>(this, FilterModel.class.getName()) {
            @Override
            public void onNext(List<DeviceInfo> deviceInfos) {
                LoggerUtils.loge("onNext DeviceInfo onNext");
                Const.TMP_DATA.DEV_LIST.clear();
                Const.TMP_DATA.DEV_LIST.addAll(deviceInfos);
                mFilterView.setDeviceList(deviceInfos);
                mFilterView.refreshContentUi();
            }
        });
        LoggerUtils.loge(this, "loadList");
    }
}
