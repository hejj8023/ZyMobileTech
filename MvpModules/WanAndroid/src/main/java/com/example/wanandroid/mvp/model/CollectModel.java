package com.example.wanandroid.mvp.model;

import com.example.wanandroid.mvp.contract.CollectContract;
import com.zhiyangstudio.commonlib.net.callback.RxObserver;
import com.zhiyangstudio.commonlib.utils.RxUtils;

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
