package com.example.wanandroid.mvp.model;

import com.example.wanandroid.bean.ArticleBean;
import com.zhiyangstudio.commonlib.net.callback.RxObserver;
import com.zhiyangstudio.commonlib.net.callback.RxPageListObserver;

/**
 * Created by zhiyang on 2018/4/19.
 */

public interface ITreeListModel {
    void getTreeList(int page, int cid, RxPageListObserver<ArticleBean> observer);

    void unCollect(int articleId, RxObserver<String> observer);

    void collect(int articleId, RxObserver<String> rxObserver);
}
