package com.example.bailan.ui.fragment;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bailan.base.BaseDaggerSupportListFragment;
import com.example.bailan.bean.RecommendBean;
import com.example.bailan.mvp.contract.RecommendContract;
import com.example.bailan.mvp.presenter.RecommendPresenter;

/**
 * Created by example on 2018/5/25.
 */

public class RecommendFragment extends BaseDaggerSupportListFragment<RecommendPresenter, RecommendContract.IListView, RecommendBean> implements RecommendContract.IListView {

    @Override
    protected void inject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void loadRemoteData() {
        mPresenter.loadListData();
    }

    @Override
    protected void initPageNumb() {

    }

    @Override
    protected BaseQuickAdapter<RecommendBean, BaseViewHolder> getListAdapter() {
        return null;
    }
}