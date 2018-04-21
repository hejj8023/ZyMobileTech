package com.example.wanandroid.mvp.contract;

import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.bean.BannerBean;

import java.util.List;

/**
 * Created by zhiyang on 2018/4/10.
 */

public interface HomeListContract {
    interface IHomeListView extends CollectContract.ICollectView<ArticleBean> {
        void setBannerData(List<BannerBean> list);
    }

    interface IHomeListPresenter extends CollectContract.ICollectPresenter{
        /**
         * 获取首页列表及轮播图数据
         */
        void getHomeList();

    }
}
