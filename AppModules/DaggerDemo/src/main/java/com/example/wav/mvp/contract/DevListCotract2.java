package com.example.wav.mvp.contract;

import com.example.wav.bean.AccountDeviceInfo;
import com.zhiyangstudio.commonlib.mvp.inter.ISampleRefreshView;

/**
 * Created by example on 2018/5/10.
 */

public interface DevListCotract2 {
    public interface IDevListView extends ISampleRefreshView<AccountDeviceInfo.DeviceDetailInfo> {
        int getStatus();

        int getPageSize();
    }

    public interface IDevListPresenter {
        void loadList();
    }
}
