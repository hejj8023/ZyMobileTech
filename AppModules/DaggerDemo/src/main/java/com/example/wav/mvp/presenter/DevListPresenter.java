package com.example.wav.mvp.presenter;

import com.example.wav.bean.AccountDeviceInfo;
import com.example.wav.manager.DataManager;
import com.example.wav.mvp.contract.DevListCotract;
import com.example.wav.mvp.model.DevListModel;
import com.zhiyangstudio.commonlib.mvp.presenter.BasePresenter;
import com.zhiyangstudio.commonlib.utils.LoggerUtils;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Created by example on 2018/5/9.
 */

public class DevListPresenter extends BasePresenter<DevListCotract.IDevListView> implements
        DevListCotract.IDevListPresenter {

    private final DevListModel mModel;
    private DevListCotract.IDevListView mFragmentView;

    public DevListPresenter() {
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
                            mFragmentView.setDataCount(accountDeviceInfo.getTotal());
                            if (infos != null && infos.size() > 0) {
                                mFragmentView.setData(infos);
                                mFragmentView.showContent();
                            } else {
                                if (page > 1) {
                                    mFragmentView.showNoMoreData();
                                } else {
                                    mFragmentView.showEmpty();
                                }
                            }
                        } else {
                            mFragmentView.showError();
                        }
                        mFragmentView.hideLoading();
                    }
                });
    }

}
