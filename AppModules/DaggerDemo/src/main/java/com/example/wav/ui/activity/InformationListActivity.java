package com.example.wav.ui.activity;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wav.bean.InformationBean;
import com.example.wav.mvp.contract.InformationContract;
import com.example.wav.mvp.presenter.InformationListPresenter;
import com.zhiyangstudio.commonlib.refreshsupport.smartrefresh.BaseMVPSRRListActivity;

import java.util.List;

/**
 * Created by example on 2018/5/14.
 */

public class InformationListActivity extends BaseMVPSRRListActivity<InformationListPresenter,
        InformationContract.IInformationView, InformationBean> implements InformationContract
        .IInformationView {
    @Override
    protected InformationListPresenter createPresenter() {
        return new InformationListPresenter();
    }

    @Override
    protected void loadRemoteData() {
        mPresenter.getInformationList();
    }

    @Override
    protected void initPageNumb() {

    }

    @Override
    protected BaseQuickAdapter<InformationBean, BaseViewHolder> getListAdapter() {
        return null;
    }

    @Override
    public void setData(List data) {

    }
}
