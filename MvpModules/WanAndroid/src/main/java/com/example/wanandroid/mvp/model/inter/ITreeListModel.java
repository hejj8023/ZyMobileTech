package com.example.wanandroid.mvp.model.inter;

import com.example.wanandroid.bean.ArticleBean;
import com.zysdk.vulture.clib.net.callback.RxPageListObserver;

/**
 * Created by zhiyang on 2018/4/19.
 */

public interface ITreeListModel {
    void getTreeList(int page, int cid, RxPageListObserver<ArticleBean> observer);
}
