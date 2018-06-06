package com.example.wanandroid.mvp.presenter;

import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.bean.BannerBean;
import com.example.wanandroid.mvp.contract.HomeListContract;
import com.example.wanandroid.mvp.model.HomeListModel;
import com.zysdk.vulture.clib.mvp.presenter.BasePresenter;
import com.zysdk.vulture.clib.net.callback.RxConsumer;
import com.zysdk.vulture.clib.net.callback.RxObserver;
import com.zysdk.vulture.clib.net.callback.RxPageListObserver;
import com.zysdk.vulture.clib.utils.LoggerUtils;

import java.util.List;

/**
 * Created by zhiyang on 2018/4/10.
 */

public class HomeListPresenter extends BasePresenter<HomeListContract.IHomeListView> implements
        HomeListContract.IHomeListPresenter {

    private final HomeListModel mHomeModel;
    private HomeListContract.IHomeListView homeListView;

    public HomeListPresenter() {
        this.mHomeModel = new HomeListModel();
    }

    @Override
    public void getHomeList() {
        homeListView = getView();
        int page = homeListView.getPage();
        mHomeModel.getHomeData(page, new RxConsumer<List<BannerBean>>() {
            @Override
            protected void onSucess(List<BannerBean> data) {
                LoggerUtils.loge("HomeListPresenter getHomeData onSucess");
                homeListView.setBannerData(data);
            }

            @Override
            protected void onFail(String errorMsg) {
                LoggerUtils.loge("HomeListPresenter getHomeData onFail");
                homeListView.showFail(errorMsg);

            }
        }, new RxPageListObserver<ArticleBean>(this, HomeListModel.class.getName()) {
            @Override
            protected void onSucess(List<ArticleBean> list) {
                LoggerUtils.loge("HomeListPresenter getHomeData onSucess");
                homeListView.setData(list);
                if (homeListView.getData().size() == 0) {
                    homeListView.showEmpty();
                } else {
                    homeListView.showContent();
                }
            }

            @Override
            protected void onFailure(int errorCode, String errorMsg) {
                LoggerUtils.loge("HomeListPresenter getHomeData onFailure");
                homeListView.showFail(errorMsg);
            }
        });
    }

    @Override
    public void unCollectArticle() {
        homeListView = getView();
        mHomeModel.unCollect(homeListView.getArticleId(), new RxObserver<String>(this) {
            @Override
            protected void onSucess(String data) {
                homeListView.collect(false, "取消收藏成功");
            }

            @Override
            protected void onFailure(int errorCode, String errorMsg) {
                homeListView.showFilure(errorMsg);
            }
        });
    }

    @Override
    public void collectArticle() {
        homeListView = getView();
        mHomeModel.collect(homeListView.getArticleId(), new RxObserver<String>(this) {
            @Override
            protected void onSucess(String data) {
                homeListView.collect(true, "收藏成功");
            }

            @Override
            protected void onFailure(int errorCode, String errorMsg) {
                homeListView.showFilure(errorMsg);
            }
        });
    }
}
