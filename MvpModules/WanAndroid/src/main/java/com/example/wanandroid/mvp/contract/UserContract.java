package com.example.wanandroid.mvp.contract;

import com.example.wanandroid.bean.ArticleBean;
import com.zhiyangstudio.commonlib.mvp.inter.IListDataView;
import com.zhiyangstudio.commonlib.net.callback.RxPageListObserver;

/**
 * Created by zzg on 2018/4/21.
 */

public interface UserContract {
    public interface IPresenter {
        void loadDataList();
    }

    public interface IUserView extends IListDataView<ArticleBean> {
    }

    public interface IUserModel {
        void getCollectArticleList(int page, RxPageListObserver<ArticleBean> observer);
    }
}
