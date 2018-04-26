package com.example.wav.mvp.contract;

import com.example.wav.bean.DeviceInfo;
import com.zhiyangstudio.commonlib.mvp.inter.IListDataView;

import java.util.List;

import io.reactivex.Observer;

/**
 * Created by zhiyang on 2018/4/26.
 */

public interface DeviceListContract {
    public interface IDeviceView extends IListDataView<DeviceInfo> {
    }

    public interface IDevicePresenter {
        void getDeviceList();
    }

    public interface IDeviceModel {
        void getDeviceList(Observer<List<DeviceInfo>> observer);
    }
}
