package com.example.wanandroid.mvp.model;

import com.example.wanandroid.mvp.contract.CollectContract;
import com.zysdk.vulture.clib.net.callback.RxObserver;
import com.zysdk.vulture.clib.utils.RxUtils;

/**
 * Created by zzg on 2018/4/21.
 */

public class CollectModel extends BaseWanModel implements CollectContract.ICollectModel {
    @Override
    public void collect(int articleId, RxObserver<String> rxObserver) {
        getApi().collect(articleId).compose(RxUtils.io_main()).subscribe(rxObserver);
    }

    @Override
    public void unCollect(int articleId, RxObserver<String> rxObserver) {
        getApi().unCollect(articleId).compose(RxUtils.io_main()).subscribe(rxObserver);
    }
}
