package com.example.wanandroid.mvp.model;

import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.bean.FriendBean;
import com.example.wanandroid.bean.HotwordBean;
import com.example.wanandroid.mvp.model.inter.ISearchModel;
import com.zysdk.vulture.clib.mvp.model.BaseModel;
import com.zysdk.vulture.clib.net.callback.RxObserver;
import com.zysdk.vulture.clib.net.callback.RxPageListObserver;
import com.zysdk.vulture.clib.utils.RxUtils;

import java.util.List;

/**
 * Created by example on 2018/4/17.
 */

public class SearchModel extends BaseWanModel implements ISearchModel {
    @Override
    public void getHotWord(RxObserver<List<HotwordBean>> observer) {
        mApiServer.getHotKeyword().compose(RxUtils.io_main()).subscribe(observer);
    }

    @Override
    public void getFriend(RxObserver<List<FriendBean>> observer) {
        mApiServer.getFriend().compose(RxUtils.io_main()).subscribe(observer);
    }

    @Override
    public void searchArticle(int page, String keyword, RxPageListObserver<ArticleBean> observer) {
        mApiServer.searchArticle(page, keyword).compose(RxUtils.io_main()).subscribe(observer);
    }
}
