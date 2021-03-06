package com.example.wanandroid.mvp.model;

import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.bean.BannerBean;
import com.example.wanandroid.mvp.model.inter.IHomeListModel;
import com.zysdk.vulture.clib.net.callback.RxConsumer;
import com.zysdk.vulture.clib.net.callback.RxFunction;
import com.zysdk.vulture.clib.net.callback.RxPageListObserver;
import com.zysdk.vulture.clib.utils.LoggerUtils;
import com.zysdk.vulture.clib.utils.RxUtils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by example on 2018/4/11.
 */

public class HomeListModel extends CollectModel implements IHomeListModel {

    @Override
    public void getHomeData(int page, RxConsumer<List<BannerBean>> consumer,
                            RxPageListObserver<ArticleBean> rxObserver) {
        LoggerUtils.loge(this, "getHomeData");
        getApi().getBannerList()
                .compose(RxUtils.io_main())
                .doOnNext(consumer)
                .observeOn(Schedulers.io())
                .flatMap(new RxFunction<List<BannerBean>, ArticleBean>() {
                    @Override
                    protected Observable doOnNextRequest() {
                        LoggerUtils.loge(this, "getArticleList");
                        return getApi().getArticleList(page);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(rxObserver);
    }
}
