package com.example.wav.mvp.contract;

import com.example.wav.bean.AccountCustomerInfo;
import com.example.wav.bean.AccountDeviceInfo;
import com.example.wav.bean.AccountGroupInfo;
import com.zhiyangstudio.commonlib.mvp.inter.IView;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Created by example on 2018/4/28.
 */

public interface FilterNewContract {
    interface IFilterNewView extends IView {
        void setCustomerListData(List<AccountCustomerInfo> list);

        void setCustomerGroupListData(List<AccountGroupInfo> list);

        void setDeviceListData(AccountDeviceInfo list);
    }

    interface IFilterNewPresenter {
        void loadData();
    }

    interface IFilterNewModel {
        void getData(Consumer<List<AccountCustomerInfo>> consumer1,
                     Consumer<List<AccountGroupInfo>> consumer2,
                     Consumer<AccountDeviceInfo> consumer3);
    }
}
