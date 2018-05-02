package com.example.wav.mvp.model;

import com.example.wav.bean.AccountDeviceInfo;
import com.example.wav.mvp.contract.FilterDeviceListContract;
import com.zhiyangstudio.commonlib.utils.RxUtils;

import io.reactivex.Observer;

/**
 * Created by example on 2018/5/2.
 */

public class FilteDeviceListModel extends BaseAdvModel implements FilterDeviceListContract
        .IFilteDeviceListModel {
    @Override
    public void getData(String customerId, String groupId, int status, int pageNum, int pageSize, Observer<AccountDeviceInfo> consumer) {
        getApi().getDeviceList(customerId, groupId, status, pageNum, pageSize)
                .compose(RxUtils.io_main()).subscribe(consumer);
    }
}
