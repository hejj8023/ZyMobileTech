package com.example.wanandroid.mvp.contract;

import com.example.wanandroid.bean.ArticleBean;

/**
 * Created by zhiyang on 2018/4/19.
 */

public interface TreeListContract {
    interface ITreeListView extends CollectContract.ICollectView<ArticleBean> {
        int getCid();
    }

    public interface ITreeListPresenter extends CollectContract.ICollectPresenter {
        void loadTreeList();
    }
}
