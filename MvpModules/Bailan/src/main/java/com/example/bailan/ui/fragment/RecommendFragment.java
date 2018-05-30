package com.example.bailan.ui.fragment;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bailan.adapter.RecommendListAdapter;
import com.example.bailan.base.BaseDaggerSupportListFragment;
import com.example.bailan.bean.RecommonFinalBean;
import com.example.bailan.mvp.contract.RecommendContract;
import com.example.bailan.mvp.presenter.RecommendPresenter;

/**
 * Created by example on 2018/5/25.
 */

public class RecommendFragment extends BaseDaggerSupportListFragment<RecommendPresenter,
        RecommendContract.IListView, RecommonFinalBean.FianlLayoutData> implements RecommendContract
        .IListView {

    @Override
    protected void inject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected boolean hasSupportItemDivider() {
        return false;
    }

    @Override
    protected boolean hasEnableRereshAndLoadMore() {
        return false;
    }

    @Override
    protected void loadRemoteData() {
        mPresenter.loadListData();
    }

    @Override
    protected void initPageNumb() {

    }

    @Override
    protected BaseQuickAdapter<RecommonFinalBean.FianlLayoutData, BaseViewHolder> getListAdapter() {
        RecommendListAdapter listAdapter = new RecommendListAdapter(mList);
        // TODO: 2018/5/28 使用多条目类型
        return listAdapter;
    }

}