package com.example.bailan.ui.fragment;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bailan.R;
import com.example.bailan.adapter.RecommendListAdapter;
import com.example.bailan.base.BaseDaggerSupportListFragment;
import com.example.bailan.bean.RecommonBean;
import com.example.bailan.mvp.contract.RecommendContract;
import com.example.bailan.mvp.presenter.RecommendPresenter;
import com.zysdk.vulture.clib.utils.UiUtils;

/**
 * Created by example on 2018/5/25.
 */

public class RecommendFragment extends BaseDaggerSupportListFragment<RecommendPresenter,
        RecommendContract.IListView, RecommonBean.RecommendLayoutData> implements RecommendContract
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
    protected void initOtherProperty() {
        mRecyclerView.setBackgroundColor(UiUtils.getColor(R.color.c_f0f0f0));
    }

    @Override
    protected void loadRemoteData() {
        mPresenter.loadListData();
    }

    @Override
    protected void initPageNumb() {

    }

    @Override
    protected BaseQuickAdapter<RecommonBean.RecommendLayoutData, BaseViewHolder> getListAdapter() {
        RecommendListAdapter listAdapter = new RecommendListAdapter(mList);
        // TODO: 2018/5/28 使用多条目类型
        return listAdapter;
    }

}