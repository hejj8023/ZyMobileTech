package com.example.wanandroid.mvp.model;

import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.bean.FriendBean;
import com.example.wanandroid.bean.HotwordBean;
import com.example.wanandroid.mvp.model.inter.ISearchModel;
import com.zhiyangstudio.commonlib.mvp.model.BaseModel;
import com.zhiyangstudio.commonlib.net.callback.RxObserver;
import com.zhiyangstudio.commonlib.net.callback.RxPageListObserver;
import com.zhiyangstudio.commonlib.utils.RxUtils;

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
