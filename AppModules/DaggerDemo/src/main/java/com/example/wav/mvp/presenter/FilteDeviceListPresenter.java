package com.example.wav.mvp.presenter;

import com.example.wav.manager.DataManager;
import com.example.wav.bean.AccountDeviceInfo;
import com.example.wav.mvp.contract.FilterDeviceListContract;
import com.example.wav.mvp.model.FilteDeviceListModel;
import com.example.wav.mvp.model.FilterNewModel;
import com.zhiyangstudio.commonlib.mvp.presenter.BasePresenter;
import com.zhiyangstudio.commonlib.net.callback.AbsBaseObserver;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by example on 2018/5/2.
 */

public class FilteDeviceListPresenter extends BasePresenter<FilterDeviceListContract
        .IFilteDeviceListView> implements FilterDeviceListContract.IFilteDeviceListPresenter {

    private final FilteDeviceListModel mListModel;
    private FilterDeviceListContract.IFilteDeviceListView mDeviceListView;

    @Inject
    public FilteDeviceListPresenter() {
        mListModel = new FilteDeviceListModel();
    }

    @Override
    public void getData() {
        mDeviceListView = getView();
        String defaultUserId = DataManager.getDefaultUserId();
        String defaultGroupId = DataManager.getDefaultGroupId();
        mListModel.getData(defaultUserId,
                defaultGroupId,
                mDeviceListView.getStatus(),
                mDeviceListView.getPage(),
                mDeviceListView.getPageSize(),
                new AbsBaseObserver<AccountDeviceInfo>(this,
                        FilterNewModel.class.getName()) {
                    @Override
                    public void onNext(AccountDeviceInfo accountDeviceInfo) {
                        if (accountDeviceInfo != null) {
                            List<AccountDeviceInfo.DeviceDetailInfo> list = accountDeviceInfo.getRows();
                            mDeviceListView.setData(list);
                            if (list.size() > 0) {
                                mDeviceListView.showContent();
                            } else {
                                mDeviceListView.showEmpty();
                            }
                        } else {
                            mDeviceListView.showError();
                        }
                    }
                });
    }
}
