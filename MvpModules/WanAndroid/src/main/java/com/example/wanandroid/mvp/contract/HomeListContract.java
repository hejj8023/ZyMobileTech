package com.example.wanandroid.mvp.contract;

import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.bean.BannerBean;
import com.zhiyangstudio.commonlib.mvp.inter.IListDataView;

import java.util.List;

/**
 * Created by zhiyang on 2018/4/10.
 */

public interface HomeListContract {
    interface IHomeListView extends IListDataView<ArticleBean> {
        void setBannerData(List<BannerBean> list);

        int getArticleId();

        void collect(boolean isCollect, String msg);

        void showFilure(String msg);
    }

    interface IHomeListPresenter {
        /**
         * 获取首页列表及轮播图数据
         */
        void getHomeList();

        /**
         * 取消收藏
         */
        void unCollectArticle();

        /**
         * 收藏
         */
        void collectArticle();
    }
}
