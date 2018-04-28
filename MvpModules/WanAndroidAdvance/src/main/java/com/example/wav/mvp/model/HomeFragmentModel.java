package com.example.wav.mvp.model;

import com.example.wav.DataManager;
import com.example.wav.bean.AccountDeviceInfo;
import com.example.wav.mvp.contract.HomeFragmentContract;
import com.zhiyangstudio.commonlib.utils.LoggerUtils;
import com.zhiyangstudio.commonlib.utils.RxUtils;

import io.reactivex.functions.Consumer;

/**
 * Created by example on 2018/4/28.
 */

public class HomeFragmentModel extends BaseAdvModel implements HomeFragmentContract
        .IHomeFragmentModel {
    @Override
    public void loadDeviceList(Consumer<AccountDeviceInfo> consumer) {
        String defaultUserId = DataManager.getDefaultUserId();
        String defaultGroupId = DataManager.getDefaultGroupId();
        LoggerUtils.loge(this, "defaultUserId = " + defaultUserId + " , defaultGroupId = " + defaultGroupId);
        getApi().getDeviceList(defaultUserId, defaultGroupId, 0, 1, 20).compose(RxUtils
                .io_main()).subscribe(consumer);
    }
}
