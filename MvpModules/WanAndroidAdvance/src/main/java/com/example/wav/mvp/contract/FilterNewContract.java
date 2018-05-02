package com.example.wav.mvp.contract;

import com.example.wav.bean.AccountCustomerInfo;
import com.example.wav.bean.AccountDeviceInfo;
import com.example.wav.bean.AccountGroupInfo;
import com.zhiyangstudio.commonlib.mvp.inter.IView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.functions.Consumer;

/**
 * Created by example on 2018/4/28.
 */

public interface FilterNewContract {
    interface IFilterNewView extends IView {
        void setCustomerListData(List<AccountCustomerInfo> list);

        void setCustomerGroupListData(List<AccountGroupInfo> list);

        void setDeviceListData(AccountDeviceInfo list);

        int getStatus();

        int getPage();

        int getPageSize();
    }

    interface IFilterNewPresenter {
        void getAllData();

        void getCustomerListData();

        void getCustomerGroupListData();

        void getDevListData();
    }

    interface IFilterNewModel {
        void getAllData(Consumer<List<AccountCustomerInfo>> consumer1,
                        Consumer<List<AccountGroupInfo>> consumer2,
                        Consumer<AccountDeviceInfo> consumer3);

        void getCustomerListData(Observer<List<AccountCustomerInfo>> consumer);

        void getCustomerGroupListData(String customerId, Observer<List<AccountGroupInfo>> consumer);

        void getDevListData(String customerId, String groupId, int status, int pageNum, int
                pageSize, Observer<AccountDeviceInfo> consumer);
    }
}
