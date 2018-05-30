package com.example.bailan.mvp.model;

import com.example.bailan.base.BaseWLModel;
import com.example.bailan.mvp.presenter.RankContract;
import com.zhiyangstudio.commonlib.utils.RxUtils;

import io.reactivex.Observer;
import okhttp3.ResponseBody;

/**
 * Created by example on 2018/5/30.
 */

public class RankModel extends BaseWLModel implements RankContract.IRankModel{
    @Override
    public void loadData(Observer<ResponseBody> observer) {
        mNetApi.getRankList().compose(RxUtils.io_main()).subscribe(observer);
    }
}
