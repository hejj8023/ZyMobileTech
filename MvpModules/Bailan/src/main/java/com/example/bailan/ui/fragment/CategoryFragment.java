package com.example.bailan.ui.fragment;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bailan.base.BaseDaggerSupportListFragment;
import com.example.bailan.bean.CategoryBean;
import com.example.bailan.mvp.contract.CategoryContract;
import com.example.bailan.mvp.presenter.CategoryPresenter;

/**
 * Created by example on 2018/5/25.
 */

public class CategoryFragment extends BaseDaggerSupportListFragment<CategoryPresenter, CategoryContract.ICategoryView, CategoryBean> implements CategoryContract.ICategoryView {
    @Override
    protected void inject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void loadRemoteData() {

    }

    @Override
    protected void initPageNumb() {

    }

    @Override
    protected BaseQuickAdapter<CategoryBean, BaseViewHolder> getListAdapter() {
        return null;
    }
}
