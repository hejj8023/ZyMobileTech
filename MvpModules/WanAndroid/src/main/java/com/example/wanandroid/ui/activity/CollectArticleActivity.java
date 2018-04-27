package com.example.wanandroid.ui.activity;

import android.view.View;

import com.example.wanandroid.Const;
import com.example.wanandroid.base.BaseWanListActivity;
import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.inter.OnArticleListItemClickListener;
import com.example.wanandroid.mvp.contract.UserContract;
import com.example.wanandroid.mvp.presenter.UserPresenter;

import java.util.List;

/**
 * Created by zzg on 2018/4/21.
 * 收藏的文章
 */

public class CollectArticleActivity extends BaseWanListActivity<UserPresenter, UserContract.IUserView,
        ArticleBean> implements UserContract.IUserView, OnArticleListItemClickListener {

    private int mId;
    private int mOriginId;
    private int mPos;

    @Override
    public void setData(List<ArticleBean> data) {
        mListData.addAll(data);
    }

    @Override
    protected UserPresenter createPresenter() {
        return new UserPresenter();
    }

    @Override
    protected int getType() {
        return Const.LIST_TYPE.COLLECT;
    }

    @Override
    protected View initHeaderView() {
        return null;
    }

    @Override
    protected void loadDatas() {
        mPresenter.loadDataList();
    }

    @Override
    protected String getCurrentTitle() {
        return "收藏的文章";
    }

    @Override
    public void onCollectClick(int pos, int id, int originId) {
        // 取消 收藏
        this.mId = id;
        this.mOriginId = originId;
        this.mPos = pos;
        mPresenter.deleteCollectArticle();
    }

    @Override
    public int getArticleId() {
        return mId;
    }

    @Override
    public int getOriginId() {
        return mOriginId;
    }

    @Override
    public void onDeleteCollectAtricleSucess() {
        if (mListData.size() > 1) {
            mListData.remove(mPos);
            mListAdapter.notifyItemDataRemove(mPos, recyclerView);
        } else {
            loadDatas();
        }
    }
}
