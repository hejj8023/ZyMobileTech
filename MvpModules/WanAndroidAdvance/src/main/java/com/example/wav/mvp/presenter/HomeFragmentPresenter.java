package com.example.wav.mvp.presenter;

import com.example.wav.bean.AccountDeviceInfo;
import com.example.wav.mvp.contract.HomeFragmentContract;
import com.example.wav.mvp.model.HomeFragmentModel;
import com.zhiyangstudio.commonlib.mvp.presenter.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by example on 2018/4/28.
 */

public class HomeFragmentPresenter extends BasePresenter<HomeFragmentContract
        .IHomeFragmentView> implements HomeFragmentContract.IHomeFragmentPresenter {

    private final HomeFragmentModel mFragmentModel;
    private HomeFragmentContract.IHomeFragmentView mFragmentView;

    @Inject
    public HomeFragmentPresenter() {
        mFragmentModel = new HomeFragmentModel();
    }

    @Override
    public void loadDeviceList() {
        mFragmentView = getView();
        mFragmentView.showLoading("数据加载中,请稍候...");
        mFragmentModel.loadDeviceList(new Consumer<AccountDeviceInfo>() {
            @Override
            public void accept(AccountDeviceInfo accountDeviceInfo) throws Exception {
                if (accountDeviceInfo != null) {
                    List<AccountDeviceInfo.DeviceDetailInfo> infos = accountDeviceInfo.getRows();
                    if (infos != null && infos.size() > 0) {
                        mFragmentView.setData(infos);
                        mFragmentView.showContent();
                    } else {
                        mFragmentView.showEmpty();
                    }
                } else {
                    mFragmentView.showError();
                }
                mFragmentView.hideLoading();
            }
        });
    }
}
