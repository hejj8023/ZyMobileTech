package com.example.wav.mvp.contract;

import com.example.wav.bean.AccountDeviceInfo;
import com.zhiyangstudio.commonlib.mvp.inter.IListDataView;

import io.reactivex.functions.Consumer;

/**
 * Created by example on 2018/4/28.
 */

public interface HomeFragmentContract {
    public interface IHomeFragmentView extends IListDataView<AccountDeviceInfo.DeviceDetailInfo> {
        int getStatus();

        int getPageSize();
    }

    public interface IHomeFragmentPresenter {
        void loadDeviceList();
    }

    public interface IHomeFragmentModel {
        void loadDeviceList(String customerId, String groupId, int status, int pageNum, int
                pageSize, Consumer<AccountDeviceInfo> consumer);
    }
}
