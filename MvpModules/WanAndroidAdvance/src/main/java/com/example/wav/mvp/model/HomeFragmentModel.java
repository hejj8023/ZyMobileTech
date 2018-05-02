package com.example.wav.mvp.model;

import com.example.wav.bean.AccountDeviceInfo;
import com.example.wav.mvp.contract.HomeFragmentContract;
import com.zhiyangstudio.commonlib.utils.RxUtils;

import io.reactivex.functions.Consumer;

/**
 * Created by example on 2018/4/28.
 */

public class HomeFragmentModel extends BaseAdvModel implements HomeFragmentContract
        .IHomeFragmentModel {
    @Override
    public void loadDeviceList(String customerId, String groupId, int status, int pageNum, int
            pageSize, Consumer<AccountDeviceInfo> consumer) {
        getApi().getDeviceList(customerId, groupId, status, pageNum, pageSize).compose(RxUtils
                .io_main()).subscribe(consumer);
    }
}
