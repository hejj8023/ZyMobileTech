package com.example.wanandroid.ui.fragment;

import com.example.wanandroid.bean.HomeBean;
import com.example.wanandroid.mvp.contract.HomeListContract;
import com.example.wanandroid.mvp.presenter.HomeListPresenter;
import com.zhiyangstudio.commonlib.mvp.BaseAbsListFragment;

/**
 * Created by zhiyang on 2018/4/10.
 */

public class HomeFragment extends BaseAbsListFragment<HomeListPresenter, HomeListContract
        .IHomeListView, HomeBean> implements HomeListContract.IHomeListView {

    @Override
    public void showLoading(String msg) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showFail(String msg) {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showEmpty() {

    }

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
    protected HomeListPresenter createPresenter() {
        return null;
    }

    @Override
    protected void loadDatas() {

    }

    @Override
    protected boolean isCanLoadMore() {
        return false;
    }
}
