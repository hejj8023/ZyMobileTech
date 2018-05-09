package com.example.wav.mvp.model;

import com.example.wav.bean.AccountDeviceInfo;
import com.example.wav.mvp.contract.DevListCotract;
import com.zhiyangstudio.commonlib.utils.RxUtils;

import io.reactivex.functions.Consumer;

/**
 * Created by example on 2018/5/9.
 */

public class DevListModel extends BaseAdvModel implements DevListCotract.IDevListModel {
    @Override
    public void loadDeviceList(String id, String groupId, int status, int page, int pageSize,
                               Consumer<AccountDeviceInfo> consumer) {
        getApi().getDeviceList(id, groupId, status, page, pageSize).compose(RxUtils.io_main())
                .subscribe(consumer);
    }
}
