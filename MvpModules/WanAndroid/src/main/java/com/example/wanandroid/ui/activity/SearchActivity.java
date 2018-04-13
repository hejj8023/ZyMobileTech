package com.example.wanandroid.ui.activity;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;

import com.example.wanandroid.R;
import com.example.wanandroid.bean.SearchBean;
import com.example.wanandroid.mvp.contract.SearchContract;
import com.example.wanandroid.mvp.presenter.SearchPresenter;
import com.zhiyangstudio.commonlib.adapter.BaseListAdapter;
import com.zhiyangstudio.commonlib.mvp.BaseAbsListActivity;
import com.zhiyangstudio.commonlib.utils.UiUtils;

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

    }

    @Override
    public void refreshUi() {

    }

    @Override
    public void release() {

    }

    @Override
    public void setData(List<SearchBean> data) {

    }

    @Override
    protected SearchPresenter createPresenter() {
        return null;
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

    @Override
    protected void loadDatas() {

    }

    @Override
    public void setContentView(int layoutResID) {
        LinearLayout linearLayout = new LinearLayout(mContext);
        View contentView = UiUtils.inflateView(R.layout.activity_base_wan_android, linearLayout);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        Toolbar toolbar = contentView.findViewById(R.id.toolbar);
        toolbar.setTitle(UiUtils.getStr(R.string.search));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        LinearLayout llContainer = contentView.findViewById(R.id.frameLayout);
        llContainer.addView(UiUtils.inflateView(layoutResID, linearLayout));
        linearLayout.addView(contentView);
        super.setContentView(linearLayout);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.search_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
