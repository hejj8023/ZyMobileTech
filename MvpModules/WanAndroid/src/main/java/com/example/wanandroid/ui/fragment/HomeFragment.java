package com.example.wanandroid.ui.fragment;

import android.view.View;

import com.example.wanandroid.Const;
import com.example.wanandroid.R;
import com.example.wanandroid.adapter.ArticleListAdapter;
import com.example.wanandroid.adapter.BannerAdapter;
import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.bean.BannerBean;
import com.example.wanandroid.inter.OnArticleListItemClickListener;
import com.example.wanandroid.mvp.contract.HomeListContract;
import com.example.wanandroid.mvp.presenter.HomeListPresenter;
import com.zhiyangstudio.commonlib.adapter.BaseListAdapter;
import com.zhiyangstudio.commonlib.mvp.BaseAbsListFragment;
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
        mViewPager = (BannerViewPager) headerView.findViewById(R.id.viewPager);
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
        }
    }

    @Override
    public void setData(List<ArticleBean> data) {
        mListData.addAll(data);
    }

    @Override
    public void onItemClick(String title, String url) {

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
    public void setBannerData(List<BannerBean> list) {
        mBannerList.clear();
        mBannerList.addAll(list);
    }
}
