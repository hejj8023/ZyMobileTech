package com.example.wav.mvp.contract;

import com.example.wav.bean.AccountDeviceInfo;
import com.zhiyangstudio.commonlib.mvp.inter.IListDataView;

import io.reactivex.Observer;

/**
 * Created by example on 2018/5/2.
 */

public interface FilterDeviceListContract {
    interface IFilteDeviceListView extends IListDataView<AccountDeviceInfo.DeviceDetailInfo> {
        int getStatus();

        int getPageSize();
    }

    interface IFilteDeviceListPresenter {
        void getData();
    }

    interface IFilteDeviceListModel {
        void getData(String customerId, String groupId, int status, int pageNum, int
                pageSize, Observer<AccountDeviceInfo> consumer);
    }
}
