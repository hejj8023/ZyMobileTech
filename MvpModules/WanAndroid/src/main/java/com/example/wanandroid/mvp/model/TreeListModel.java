package com.example.wanandroid.mvp.model;

import com.example.wanandroid.bean.ArticleBean;
import com.zhiyangstudio.commonlib.net.callback.RxObserver;
import com.zhiyangstudio.commonlib.net.callback.RxPageListObserver;
import com.zhiyangstudio.commonlib.utils.RxUtils;

/**
 * Created by zhiyang on 2018/4/19.
 */

public class TreeListModel extends BaseWanModel implements ITreeListModel {
    @Override
    public void getTreeList(int page, int cid, RxPageListObserver<ArticleBean> observer) {
        getApi().getTreeList(page, cid).compose(RxUtils.io_main()).subscribe(observer);
    }

    @Override
    public void unCollect(int articleId, RxObserver<String> observer) {
        getApi().unCollect(articleId).compose(RxUtils.io_main()).subscribe(observer);
    }

    @Override
    public void collect(int articleId, RxObserver<String> rxObserver) {
        getApi().collect(articleId).compose(RxUtils.io_main()).subscribe(rxObserver);
    }

}
