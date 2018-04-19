package com.example.wanandroid.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.example.wanandroid.Const;
import com.example.wanandroid.adapter.ArticleListAdapter;
import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.inter.OnArticleListItemClickListener;
import com.example.wanandroid.mvp.contract.TreeListContract;
import com.example.wanandroid.mvp.presenter.TreeListPresenter;
import com.example.wanandroid.utils.CommonInternalUtil;
import com.zhiyangstudio.commonlib.adapter.BaseListAdapter;
import com.zhiyangstudio.commonlib.mvp.BaseAbsListFragment;

import java.util.List;

/**
 * Created by zhiyang on 2018/4/19.
 */

public class TreeListFragment extends BaseAbsListFragment<TreeListPresenter, TreeListContract
        .ITreeListView, ArticleBean> implements TreeListContract.ITreeListView, OnArticleListItemClickListener {

    private int mActicleId;

    public static Fragment instance(int id) {
        TreeListFragment fragment = new TreeListFragment();
        Bundle b = new Bundle();
        b.putInt(Const.BUNDLE_KEY.ID, id);
        fragment.setArguments(b);
        return fragment;
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
    public void setData(List<ArticleBean> data) {
        mListData.addAll(data);
    }

    @Override
    protected TreeListPresenter createPresenter() {
        return new TreeListPresenter();
    }

    @Override
    protected void initArguments(Bundle bundle) {
        if (bundle != null) {
            mActicleId = bundle.getInt(Const.BUNDLE_KEY.ID);
        }
    }

    @Override
    protected void loadDatas() {
        mPresenter.loadTreeList();
    }

    @Override
    protected boolean isCanLoadMore() {
        return true;
    }

    @Override
    protected BaseListAdapter getListAdapter() {
        return new ArticleListAdapter(this, Const
                .LIST_TYPE.TREE);
    }

    @Override
    protected View initHeaderView() {
        return null;
    }

    @Override
    public void onItemClick(String title, String url) {
        CommonInternalUtil.goWebView(title, url);
    }

    @Override
    public void onCollectClick(int pos, int id, int originId) {

    }

    @Override
    public void onCollectClick(int pos, int id) {

    }

    @Override
    public void onTreeClick(int chapterId, String chapterName) {

    }

    @Override
    public int getCid() {
        return mActicleId;
    }
}
