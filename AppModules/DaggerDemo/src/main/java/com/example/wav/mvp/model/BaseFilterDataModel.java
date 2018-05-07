package com.example.wav.mvp.model;

import com.example.wav.manager.DataManager;
import com.example.wav.bean.CustomerGroupInfo;
import com.example.wav.bean.CustomerInfo;
import com.example.wav.bean.DeviceInfo;
import com.example.wav.mvp.contract.BaseFilterContract;
import com.zhiyangstudio.commonlib.utils.LoggerUtils;
import com.zhiyangstudio.commonlib.utils.RxUtils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zhiyang on 2018/4/26.
 */

public class BaseFilterDataModel  implements BaseFilterContract.IFilterModel{
    @Override
    public void getDataList(Consumer<List<CustomerInfo>> customerConsumer, Consumer<List<CustomerGroupInfo>> customerGroupInfoConsumer, Observer<List<DeviceInfo>> observer) {
        LoggerUtils.loge("getFilterData");
        /*getApi().getCustomerList().compose(RxUtils.io_main()).doOnNext(customerConsumer)
                .observeOn(Schedulers.io())
                .flatMap(new Function<List<CustomerInfo>, Observable<List<CustomerGroupInfo>>>() {
                    @Override
                    public Observable<List<CustomerGroupInfo>> apply(List<CustomerInfo> customerInfos) throws Exception {
                        LoggerUtils.loge("getFilterData 获取客户列表数据");
                        return getApi().getCustomerGroupList();
                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);*/


        Observable.create(new ObservableOnSubscribe<List<CustomerInfo>>() {
            @Override
            public void subscribe(ObservableEmitter<List<CustomerInfo>> e) throws Exception {
                LoggerUtils.loge("生成客户列表");

                e.onNext(DataManager.getCustomerList());
                e.onComplete();
            }
        })
                .compose(RxUtils.io_main()).doOnNext(customerConsumer)
                .observeOn(Schedulers.io())
                .flatMap(new Function<List<CustomerInfo>, Observable<List<CustomerGroupInfo>>>() {
                    @Override
                    public Observable<List<CustomerGroupInfo>> apply(List<CustomerInfo> customerInfos) throws Exception {
                        LoggerUtils.loge("getFilterData 获取客户列表数据");

                        return RxUtils.createObservableData(DataManager.getCustGroupList());
                    }
                })
                .doOnNext(customerGroupInfoConsumer)
                .observeOn(Schedulers.io())
                .flatMap(new Function<List<CustomerGroupInfo>, Observable<List<DeviceInfo>>>() {
                    @Override
                    public Observable<List<DeviceInfo>> apply(List<CustomerGroupInfo> source)
                            throws Exception {
                        LoggerUtils.loge("getFilterData 获取设备列表数据");

                        return RxUtils.createObservableData(DataManager.getDeviceList());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
}
