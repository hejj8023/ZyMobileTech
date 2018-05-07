package com.example.wav.mvp.presenter;

import com.example.wav.manager.DataManager;
import com.example.wav.bean.AccountGroupInfo;
import com.example.wav.mvp.contract.FilterCustomerGroupListContract;
import com.example.wav.mvp.model.FilterCustomerGroupListModel;
import com.example.wav.mvp.model.FilterNewModel;
import com.zhiyangstudio.commonlib.mvp.presenter.BasePresenter;
import com.zhiyangstudio.commonlib.net.callback.AbsBaseObserver;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by example on 2018/5/2.
 */

public class FilterCustomerGroupListPresenter extends
        BasePresenter<FilterCustomerGroupListContract.IFilterCustomerGroupListView>
        implements FilterCustomerGroupListContract.IFilterCustomerGroupListPresenter {

    private final FilterCustomerGroupListModel mModel;
    private FilterCustomerGroupListContract.IFilterCustomerGroupListView mFilterNewView;

    @Inject
    public FilterCustomerGroupListPresenter() {
        mModel = new FilterCustomerGroupListModel();
    }

    @Override
    public void getData() {
        mFilterNewView = getView();
        String defaultUserId = DataManager.getDefaultUserId();
        mModel.getData(defaultUserId,
                new AbsBaseObserver<List<AccountGroupInfo>>(this,
                        FilterNewModel.class.getName()) {
                    @Override
                    public void onNext(List<AccountGroupInfo> accountGroupInfos) {
                        mFilterNewView.setData(accountGroupInfos);
                        mFilterNewView.showContent();
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideLoading();
                    }
                });
    }
}
