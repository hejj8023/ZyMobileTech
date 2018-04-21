package com.example.wanandroid.mvp.model;

import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.mvp.contract.UserContract;
import com.zhiyangstudio.commonlib.net.callback.RxPageListObserver;
import com.zhiyangstudio.commonlib.utils.RxUtils;

/**
 * Created by zzg on 2018/4/21.
 */

public class UserModel extends BaseWanModel implements UserContract.IUserModel {
    @Override
    public void getCollectArticleList(int page, RxPageListObserver<ArticleBean> observer) {
        mApiServer.collectArticleList(page).compose(RxUtils.io_main()).subscribe(observer);
    }
}
