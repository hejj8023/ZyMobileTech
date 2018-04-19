package com.example.wanandroid.mvp.model;

import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.bean.BannerBean;
import com.zhiyangstudio.commonlib.net.callback.RxConsumer;
import com.zhiyangstudio.commonlib.net.callback.RxObserver;
import com.zhiyangstudio.commonlib.net.callback.RxPageListObserver;

import java.util.List;

/**
 * Created by example on 2018/4/11.
 */

public interface IHomeListModel {
    /**
     * 获取首页文章列表和Banner
     *
     * @param page
     * @param consumer
     * @param observer
     */
    void getHomeData(int page, RxConsumer<List<BannerBean>> consumer,
                     RxPageListObserver<ArticleBean> observer);

    /**
     * 收藏
     *
     * @param articleId
     * @param rxObserver
     */
    void collect(int articleId, RxObserver<String> rxObserver);

    /**
     * 取消收藏
     *
     * @param articleId
     * @param rxObserver
     */
    void unCollect(int articleId, RxObserver<String> rxObserver);
}
