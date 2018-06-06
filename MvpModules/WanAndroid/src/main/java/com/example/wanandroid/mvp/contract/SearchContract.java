package com.example.wanandroid.mvp.contract;

import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.bean.FriendBean;
import com.example.wanandroid.bean.HotwordBean;
import com.zysdk.vulture.clib.mvp.inter.IListDataView;

import java.util.List;

/**
 * Created by example on 2018/4/13.
 */

public interface SearchContract {
    public interface ISearchPresenter {
        /**
         * 搜索热词
         */
        void getHotWord();

        /**
         * 获取常用网站
         */
        void getFriend();

        /**
         * 搜索
         */
        void search();
    }

    public interface ISearchView extends IListDataView<ArticleBean> {
        void setHotwordData(List<HotwordBean> data);

        void setFriendData(List<FriendBean> data);

        String getKeyword();
    }
}
