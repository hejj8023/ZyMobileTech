package com.example.wanandroid.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.example.wanandroid.Const;
import com.example.wanandroid.R;
import com.example.wanandroid.adapter.ArticleListAdapter;
import com.example.wanandroid.adapter.BannerAdapter;
import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.bean.BannerBean;
import com.example.wanandroid.inter.OnArticleListItemClickListener;
import com.example.wanandroid.manager.UserInfoManager;
import com.example.wanandroid.mvp.contract.HomeListContract;
import com.example.wanandroid.mvp.presenter.HomeListPresenter;
import com.example.wanandroid.ui.activity.LoginActivity;
import com.example.wanandroid.ui.activity.TreeActivity;
import com.example.wanandroid.utils.CommonInternalUtil;
import com.zhiyangstudio.commonlib.adapter.BaseListAdapter;
import com.zhiyangstudio.commonlib.mvp.BaseAbsListFragment;
import com.zhiyangstudio.commonlib.utils.IntentUtils;
import com.zhiyangstudio.commonlib.utils.UiUtils;
import com.zhiyangstudio.commonlib.widget.BannerViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhiyang on 2018/4/10.
 */

public class HomeFragment extends BaseAbsListFragment<HomeListPresenter, HomeListContract
        .IHomeListView, ArticleBean> implements HomeListContract.IHomeListView, OnArticleListItemClickListener {

    private List<BannerBean> mBannerList = new ArrayList<>();
    private BannerViewPager mViewPager;
    private BannerAdapter mBannerAdapter;
    // 位置
    private int mPos;
    // 文章 id
    private int mId;

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
    protected HomeListPresenter createPresenter() {
        return new HomeListPresenter();
    }

    @Override
    protected void loadDatas() {
        mPresenter.getHomeList();
    }

    @Override
    protected boolean isCanLoadMore() {
        return true;
    }

    @Override
    protected BaseListAdapter getListAdapter() {
        return new ArticleListAdapter(this, Const.LIST_TYPE.HOME);
    }

    @Override
    protected View initHeaderView() {
        View headerView = UiUtils.inflateView(R.layout.main_header_banner, recyclerView);
        mViewPager = (BannerViewPager) headerView.findViewById(R.id.banner_viewpager);
        return headerView;
    }

    @Override
    public void showContent() {
        nofityDatas();
        super.showContent();
    }

    private void nofityDatas() {
        // TODO: 2018/4/11 对轮播图的数据进行初始化
        if (mBannerAdapter == null) {
            mBannerAdapter = new BannerAdapter(mBannerList);
            // 设置预加载两个界面
            mViewPager.setAdapter(mBannerAdapter);
            mViewPager.setOffscreenPageLimit(2);
            setCurrentItem(1000 * mBannerList.size());
            mViewPager.start();
        }
        mBannerAdapter.notifyDatas(mBannerList);
    }

    private void setCurrentItem(int position) {
        mViewPager.setCurrentItem(position, false);
    }

    @Override
    public void setData(List<ArticleBean> data) {
        mListData.addAll(data);
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
        Intent intent = new Intent(mContext, TreeActivity.class);
        intent.putExtra(Const.BUNDLE_KEY.ACTION_TYPE, Const.BUNDLE_KEY.ACTION_LIST);
        intent.putExtra(Const.BUNDLE_KEY.CHAPTER_ID, chapterId);
        intent.putExtra(Const.BUNDLE_KEY.CHAPTER_NAME, chapterName);
        IntentUtils.forward(intent);
    }

    @Override
    public void setBannerData(List<BannerBean> list) {
        mBannerList.clear();
        mBannerList.addAll(list);
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
        // TODO: 2018/4/21 因为有headerview所以需要pos+1
        mPos++;
        mListAdapter.notifyItemDataChanged(mPos, recyclerView);
        ToastUtils.showShort(msg);
    }

    @Override
    public void showFilure(String msg) {

    }

    @Override
    public void onResume() {
        super.onResume();
        mViewPager.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        mViewPager.stop();
    }

    @Override
    protected void initArguments(Bundle bundle) {

    }
}
