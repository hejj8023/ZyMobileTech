package com.example.wanandroid.ui.activity;

import android.view.Menu;
import android.view.View;

import com.example.wanandroid.R;
import com.example.wanandroid.bean.SearchBean;
import com.example.wanandroid.mvp.contract.SearchContract;
import com.example.wanandroid.mvp.presenter.SearchPresenter;
import com.zhiyangstudio.commonlib.adapter.BaseListAdapter;
import com.zhiyangstudio.commonlib.mvp.BaseAbsListActivity;
import com.zhiyangstudio.commonlib.widget.recyclerview.CommonRViewHolder;

import java.util.List;

/**
 * Created by example on 2018/4/13.
 */

public class SearchActivity extends BaseAbsListActivity<SearchPresenter, SearchContract
        .ISearchView, SearchBean> implements SearchContract.ISearchView {

    @Override
    public void preProcess() {

    }

    @Override
    public void initData() {
        if (mPresenter != null)
            mPresenter.loadList();
    }

    @Override
    public void refreshUi() {

    }

    @Override
    public void release() {

    }

    @Override
    protected SearchPresenter createPresenter() {
        return new SearchPresenter();
    }

    @Override
    protected boolean isCanLoadMore() {
        return false;
    }

    @Override
    protected BaseListAdapter getListAdapter() {
        return new SearchListAdapter();
    }

    @Override
    protected View initHeaderView() {
        return null;
    }

    @Override
    protected void loadDatas() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.search_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void setData(List<SearchBean> data) {
        mListData.clear();
        mListData.addAll(data);
    }

    private class SearchListAdapter extends BaseListAdapter<SearchBean> {
        @Override
        protected int getLayoutId(int viewType) {
            return R.layout.layout_item_test_list;
        }

        @Override
        protected void bindDatas(CommonRViewHolder holder, SearchBean bean, int itemViewType, int position) {
            holder.getHolderHelper().setText(R.id.tv_name, bean.getName());
        }
    }
}
