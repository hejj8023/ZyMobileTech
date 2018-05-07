package com.example.wav.mvp.model;

import com.example.wav.bean.DevHistoryBean;
import com.example.wav.mvp.contract.DDContract;
import com.zhiyangstudio.commonlib.utils.RxUtils;

import io.reactivex.Observer;

/**
 * Created by example on 2018/5/7.
 */

public class DDModel extends BaseAdvModel implements DDContract.IDDModel {
    @Override
    public void getDevHistory(String devId, Observer<DevHistoryBean> observer) {
        getApi().getDevHistory(devId).compose(RxUtils.io_main()).subscribe(observer);
    }
}
