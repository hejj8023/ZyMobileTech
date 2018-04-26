package com.example.wav.api;

import com.example.wav.bean.CustomerGroupInfo;
import com.example.wav.bean.CustomerInfo;
import com.example.wav.bean.DeviceInfo;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by zhiyang on 2018/4/26.
 */

public interface ApiServer {
    Observable<List<CustomerInfo>> getCustomerList();

    Observable<List<CustomerGroupInfo>> getCustomerGroupList();

    Observable<List<DeviceInfo>> getDevList();
}
