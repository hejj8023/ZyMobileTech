package com.example.wanandroid.mvp.model.inter;

import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.bean.BannerBean;
import com.zysdk.vulture.clib.net.callback.RxConsumer;
import com.zysdk.vulture.clib.net.callback.RxPageListObserver;

import java.util.List;

/**
 * Created by example on 2018/4/11.
 */

public interface IHomeListModel{
    /**
     * 获取首页文章列表和Banner
     *
     * @param page
     * @param consumer
     * @param observer
     */
    void getHomeData(int page, RxConsumer<List<BannerBean>> consumer,
                     RxPageListObserver<ArticleBean> observer);
}
