package com.example.wanandroid.mvp.model;

import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.mvp.model.inter.ITreeListModel;
import com.zysdk.vulture.clib.net.callback.RxPageListObserver;
import com.zysdk.vulture.clib.utils.RxUtils;

/**
 * Created by zhiyang on 2018/4/19.
 */

public class TreeListModel extends CollectModel implements ITreeListModel {
    @Override
    public void getTreeList(int page, int cid, RxPageListObserver<ArticleBean> observer) {
        getApi().getTreeList(page, cid).compose(RxUtils.io_main()).subscribe(observer);
    }
}
