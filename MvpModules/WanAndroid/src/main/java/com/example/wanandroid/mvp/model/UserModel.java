package com.example.wanandroid.mvp.model;

import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.mvp.contract.UserContract;
import com.zysdk.vulture.clib.net.callback.RxObserver;
import com.zysdk.vulture.clib.net.callback.RxPageListObserver;
import com.zysdk.vulture.clib.utils.RxUtils;

/**
 * Created by zzg on 2018/4/21.
 */

public class UserModel extends BaseWanModel implements UserContract.IUserModel {
    @Override
    public void getCollectArticleList(int page, RxPageListObserver<ArticleBean> observer) {
        mApiServer.collectArticleList(page).compose(RxUtils.io_main()).subscribe(observer);
    }

    @Override
    public void deleteCollectArticle(int articleId, int originId, RxObserver<String> observer) {
        mApiServer.deleteCollectArticle(articleId,originId).compose(RxUtils.io_main()).subscribe(observer);
    }


}
