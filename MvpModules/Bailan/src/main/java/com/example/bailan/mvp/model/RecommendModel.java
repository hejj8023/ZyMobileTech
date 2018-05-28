package com.example.bailan.mvp.model;

import com.example.bailan.base.BaseWLModel;
import com.example.bailan.bean.RecommendBean;
import com.example.bailan.mvp.contract.RecommendContract;
import com.zhiyangstudio.commonlib.utils.RxUtils;

import io.reactivex.Observer;

/**
 * Created by zzg on 2018/5/27.
 */

public class RecommendModel extends BaseWLModel implements RecommendContract.IListModel {
    @Override
    public void loadData(Observer<RecommendBean> observer) {
        mNetApi.getRecommendList().compose(RxUtils.io_main()).subscribe(observer);
    }
}
