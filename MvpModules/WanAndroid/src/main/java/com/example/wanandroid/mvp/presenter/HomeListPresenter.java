package com.example.wanandroid.mvp.presenter;

import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.bean.BannerBean;
import com.example.wanandroid.mvp.contract.HomeListContract;
import com.example.wanandroid.mvp.model.HomeListModel;
import com.zhiyangstudio.commonlib.mvp.presenter.BasePresenter;
import com.zhiyangstudio.commonlib.net.callback.RxConsumer;
import com.zhiyangstudio.commonlib.net.callback.RxPageListObserver;
import com.zhiyangstudio.commonlib.utils.LoggerUtils;

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
                LoggerUtils.loge(this,"accept");
            }

            @Override
            protected void onFail(String errorMsg) {
                LoggerUtils.loge(this,"accept");
            }
        }, new RxPageListObserver<ArticleBean>(this, HomeListModel.class.getName()) {
            @Override
            protected void onSucess(List<ArticleBean> list) {
                LoggerUtils.loge(this,"accept");
            }

            @Override
            protected void onFailure(int errorCode, String errorMsg) {
                LoggerUtils.loge(this,"accept");
            }
        });
    }
}
