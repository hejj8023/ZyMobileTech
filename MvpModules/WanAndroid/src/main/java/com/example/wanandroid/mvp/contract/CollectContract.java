package com.example.wanandroid.mvp.contract;

import com.zysdk.vulture.clib.mvp.inter.IListDataView;
import com.zysdk.vulture.clib.net.callback.RxObserver;

/**
 * Created by zzg on 2018/4/21.
 */

public interface CollectContract {
    interface ICollectModel {
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

    interface ICollectPresenter {
        /**
         * 取消收藏
         */
        void unCollectArticle();

        /**
         * 收藏
         */
        void collectArticle();
    }

    interface ICollectView<T> extends IListDataView<T> {
        int getArticleId();

        void collect(boolean isCollect, String msg);

        void showFilure(String msg);
    }
}
