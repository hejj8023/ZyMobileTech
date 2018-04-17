package com.example.wanandroid.mvp.model;

import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.bean.FriendBean;
import com.example.wanandroid.bean.HotwordBean;
import com.zhiyangstudio.commonlib.net.callback.RxObserver;
import com.zhiyangstudio.commonlib.net.callback.RxPageListObserver;

import java.util.List;

/**
 * Created by example on 2018/4/17.
 */

public interface ISearchModel {
    /**
     * 搜索热词
     *
     * @param observer
     */
    void getHotWord(RxObserver<List<HotwordBean>> observer);

    /**
     * 获取常用网站
     *
     * @param observer
     */
    void getFriend(RxObserver<List<FriendBean>> observer);

    /**
     * 搜索数据
     *
     * @param page
     * @param keyword
     * @param observer
     */
    void searchArticle(int page, String keyword, RxPageListObserver<ArticleBean> observer);
}
