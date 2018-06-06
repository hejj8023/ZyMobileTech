package com.example.wanandroid.mvp.model;

import com.example.wanandroid.bean.TreeBean;
import com.example.wanandroid.mvp.model.inter.ITreeModel;
import com.zysdk.vulture.clib.net.callback.RxObserver;
import com.zysdk.vulture.clib.utils.RxUtils;

import java.util.List;

/**
 * Created by zhiyang on 2018/4/19.
 */

public class TreeModel extends BaseWanModel implements ITreeModel {

    @Override
    public void getTreeData(RxObserver<List<TreeBean>> rxObserver) {
        getApi().getTree().compose(RxUtils.io_main()).subscribe(rxObserver);
    }
}
