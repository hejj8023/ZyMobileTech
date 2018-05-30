package com.example.bailan.ui.fragment;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bailan.base.BaseDaggerSupportListFragment;
import com.example.bailan.bean.RankBean;
import com.example.bailan.mvp.presenter.RankContract;
import com.example.bailan.mvp.presenter.RankPrsenter;

/**
 * Created by example on 2018/5/25.
 */

public class RankFragment extends BaseDaggerSupportListFragment<RankPrsenter, RankContract.IRankView, RankBean>
        implements RankContract.IRankView {
    @Override
    protected void inject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void loadRemoteData() {
        mPresenter.loadData();
    }

    @Override
    protected void initPageNumb() {

    }

    @Override
    protected BaseQuickAdapter<RankBean, BaseViewHolder> getListAdapter() {
        return null;
    }
}
