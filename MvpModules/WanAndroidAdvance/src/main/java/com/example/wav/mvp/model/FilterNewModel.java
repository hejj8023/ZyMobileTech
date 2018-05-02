package com.example.wav.mvp.model;

import com.example.wav.DataManager;
import com.example.wav.bean.AccountCustomerInfo;
import com.example.wav.bean.AccountDeviceInfo;
import com.example.wav.bean.AccountGroupInfo;
import com.example.wav.mvp.contract.FilterNewContract;
import com.zhiyangstudio.commonlib.utils.RxUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by example on 2018/4/28.
 */

public class FilterNewModel extends BaseAdvModel implements FilterNewContract.IFilterNewModel {
    @Override
    public void getAllData(Consumer<List<AccountCustomerInfo>> consumer1,
                           Consumer<List<AccountGroupInfo>> consumer2,
                           Consumer<AccountDeviceInfo> consumer3) {
        // 链式调用
        // 取所有用户-所有分组-所有设备状态
        getApi().getCustomerList().compose(RxUtils.io_main())
                .doOnNext(consumer1)
                .observeOn(Schedulers.io())
                .flatMap(new Function<List<AccountCustomerInfo>, Observable<List<AccountGroupInfo>>>() {
                    @Override
                    public Observable<List<AccountGroupInfo>> apply(List<AccountCustomerInfo> accountCustomerInfos) throws Exception {
                        // TODO: 2018/4/28 成功之后更新数据状态，防止在登录之后后台数据有变化所以数据是从网络获取的
                        if (accountCustomerInfos != null && accountCustomerInfos.size() > 0) {
                            AccountCustomerInfo customerInfo = accountCustomerInfos.get(0);
                            String customerID = customerInfo.getId() + "";
                            DataManager.saveDefaultUserId(customerID);
                            return getApi().getCustomerGroupList2(customerID);
                        }
                        return RxUtils.createObservableData(new ArrayList<>());
                    }
                })
                .doOnNext(consumer2)
                .flatMap(new Function<List<AccountGroupInfo>, Observable<AccountDeviceInfo>>() {
                    @Override
                    public Observable<AccountDeviceInfo> apply(List<AccountGroupInfo> accountCustomerInfos) throws Exception {
                        if (accountCustomerInfos != null && accountCustomerInfos.size() > 0) {
                            AccountGroupInfo accountGroupInfo = accountCustomerInfos.get(0);
                            // TODO: 2018/4/28 成功之后更新数据状态，防止在登录之后后台数据有变化所以数据是从网络获取的
                            if (accountGroupInfo != null) {
                                String accountGroupInfoId = accountGroupInfo.getId();
                                String defaultUserId = DataManager.getDefaultUserId();
                                DataManager.saveDefaultGroupId(accountGroupInfoId);
                                // TODO: 2018/4/28 获取全部的设备列表数据
                                return getApi().getDeviceList(defaultUserId, accountGroupInfoId, 0, 1, 20);
                            }
                        }
                        return RxUtils.createObservableData(new AccountDeviceInfo());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer3);
    }

    @Override
    public void getCustomerListData(Observer<List<AccountCustomerInfo>> consumer) {
        getApi().getCustomerList().compose(RxUtils.io_main()).subscribe(consumer);
    }

    @Override
    public void getCustomerGroupListData(String customerId, Observer<List<AccountGroupInfo>> consumer) {
        getApi().getCustomerGroupList2(customerId).compose(RxUtils.io_main()).subscribe(consumer);
    }

    @Override
    public void getDevListData(String customerId, String groupId, int status, int pageNum, int
            pageSize, Observer<AccountDeviceInfo> consumer) {
        getApi().getDeviceList(customerId, groupId, status, pageNum, pageSize)
                .compose(RxUtils.io_main())
                .subscribe(consumer);
    }
}
