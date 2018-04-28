package com.example.wav.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.example.wav.base.BaseDaggerSupportListFragment;
import com.example.wav.bean.AccountDeviceInfo;
import com.example.wav.mvp.contract.HomeFragmentContract;
import com.example.wav.mvp.presenter.HomeFragmentPresenter;
import com.zhiyangstudio.commonlib.adapter.BaseListAdapter;

import java.util.List;

/**
 * Created by example on 2018/4/28.
 */

public class HomeFragment extends BaseDaggerSupportListFragment<HomeFragmentPresenter, HomeFragmentContract
        .IHomeFragmentView, AccountDeviceInfo> implements HomeFragmentContract.IHomeFragmentView {

    @Override
    public void addListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void refreshUi() {

    }

    @Override
    public void setData(List<AccountDeviceInfo> data) {

    }

    @Override
    protected void inject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initArguments(Bundle bundle) {

    }

    @Override
    protected void loadDatas() {

    }

    @Override
    protected boolean isCanLoadMore() {
        return false;
    }

    @Override
    protected BaseListAdapter getListAdapter() {
        return null;
    }

    @Override
    protected View initHeaderView() {
        return null;
    }
}
