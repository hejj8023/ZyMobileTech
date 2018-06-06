package com.example.wanandroid.mvp.contract;

import com.example.wanandroid.bean.ArticleBean;
import com.zysdk.vulture.clib.mvp.inter.IListDataView;
import com.zysdk.vulture.clib.net.callback.RxObserver;
import com.zysdk.vulture.clib.net.callback.RxPageListObserver;

/**
 * Created by zzg on 2018/4/21.
 */

public interface UserContract {
    public interface IPresenter {
        void loadDataList();

        void deleteCollectArticle();
    }

    public interface IUserView extends IListDataView<ArticleBean> {
        int getArticleId();

        int getOriginId();

        void onDeleteCollectAtricleSucess();
    }

    public interface IUserModel {
        void getCollectArticleList(int page, RxPageListObserver<ArticleBean> observer);

        void deleteCollectArticle(int articleId, int originId, RxObserver<String> observer);
    }
}
