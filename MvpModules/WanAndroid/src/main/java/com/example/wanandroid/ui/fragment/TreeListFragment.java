package com.example.wanandroid.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.example.wanandroid.Const;
import com.example.wanandroid.adapter.ArticleListAdapter;
import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.inter.OnArticleListItemClickListener;
import com.example.wanandroid.manager.UserInfoManager;
import com.example.wanandroid.mvp.contract.TreeListContract;
import com.example.wanandroid.mvp.presenter.TreeListPresenter;
import com.example.wanandroid.ui.activity.LoginActivity;
import com.example.wanandroid.utils.CommonInternalUtil;
import com.zhiyangstudio.commonlib.adapter.BaseListAdapter;
import com.zhiyangstudio.commonlib.mvp.BaseAbsListFragment;
import com.zhiyangstudio.commonlib.utils.IntentUtils;

import java.util.List;

/**
 * Created by zhiyang on 2018/4/19.
 */

public class TreeListFragment extends BaseAbsListFragment<TreeListPresenter, TreeListContract
        .ITreeListView, ArticleBean> implements TreeListContract.ITreeListView, OnArticleListItemClickListener {

    private int mActicleId;

    // 位置
    private int mPos;
    // 文章 id
    private int mId;

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
    public void loadDatas() {
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
        if (!UserInfoManager.isLogin()) {
            IntentUtils.forward(LoginActivity.class);
            return;
        }
        this.mPos = pos;
        this.mId = id;
        if (mListData.get(mPos).isCollect())
            mPresenter.unCollectArticle();
        else
            mPresenter.collectArticle();
    }

    @Override
    public void onTreeClick(int chapterId, String chapterName) {

    }

    @Override
    public int getCid() {
        return mActicleId;
    }

    @Override
    public int getArticleId() {
        return mId;
    }

    @Override
    public void collect(boolean isCollect, String msg) {
        notifyItemData(isCollect, msg);
    }

    /**
     * 刷新item
     *
     * @param isCollect
     * @param msg
     */
    private void notifyItemData(boolean isCollect, String msg) {
        mListData.get(mPos).setCollect(isCollect);
        // TODO: 2018/4/21 没有headerview不需要++
        mListAdapter.notifyItemDataChanged(mPos, recyclerView);
        ToastUtils.showShort(msg);
    }

    @Override
    public void showFilure(String msg) {

    }
}
