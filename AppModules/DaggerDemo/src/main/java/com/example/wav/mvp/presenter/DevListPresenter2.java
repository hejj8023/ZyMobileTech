package com.example.wav.mvp.presenter;

import com.example.wav.bean.AccountDeviceInfo;
import com.example.wav.manager.DataManager;
import com.example.wav.mvp.contract.DevListCotract2;
import com.example.wav.mvp.model.DevListModel;
import com.zhiyangstudio.commonlib.mvp.presenter.BasePresenter;
import com.zhiyangstudio.commonlib.utils.LoggerUtils;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Created by example on 2018/5/10.
 */

public class DevListPresenter2 extends BasePresenter<DevListCotract2.IDevListView> implements
        DevListCotract2.IDevListPresenter {

    private final DevListModel mModel;
    private DevListCotract2.IDevListView mFragmentView;

    public DevListPresenter2() {
        mModel = new DevListModel();
    }

    @Override
    public void loadList() {
        mFragmentView = getView();
        mFragmentView.showLoading("数据加载中,请稍候...");
        String defaultUserId = DataManager.getDefaultUserId();
        String defaultGroupId = DataManager.getDefaultGroupId();
        LoggerUtils.loge(this, "defaultUserId = " + defaultUserId + " , defaultGroupId = " +
                defaultGroupId);


        int page = mFragmentView.getPage();
        mModel.loadDeviceList(
                defaultUserId,
                defaultGroupId,
                mFragmentView.getStatus(),
                page,
                mFragmentView.getPageSize(),
                new Consumer<AccountDeviceInfo>() {
                    @Override
                    public void accept(AccountDeviceInfo accountDeviceInfo) throws Exception {
                        if (accountDeviceInfo != null) {
                            List<AccountDeviceInfo.DeviceDetailInfo> infos = accountDeviceInfo
                                    .getRows();
                            mFragmentView.steDataCount(accountDeviceInfo.getTotal());
                            mFragmentView.setData(infos);
                        } else {
                            mFragmentView.showError();
                        }
                        mFragmentView.hideLoading();
                    }
                });
    }
}
