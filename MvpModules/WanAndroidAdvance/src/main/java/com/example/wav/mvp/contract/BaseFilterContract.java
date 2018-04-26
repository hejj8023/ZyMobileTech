package com.example.wav.mvp.contract;

import com.example.wav.bean.CustomerGroupInfo;
import com.example.wav.bean.CustomerInfo;
import com.example.wav.bean.DeviceInfo;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.functions.Consumer;

/**
 * Created by zhiyang on 2018/4/26.
 */

public interface BaseFilterContract {
    public interface IFilterModel {
        void getDataList(Consumer<List<CustomerInfo>> customerConsumer,
                         Consumer<List<CustomerGroupInfo>>
                                 customerGroupInfoConsumer, Observer<List<DeviceInfo>> observer);
    }
}
