package com.example.wav.mvp.contract;

import com.example.wav.bean.CustomerGroupInfo;
import com.example.wav.bean.CustomerInfo;
import com.example.wav.bean.DeviceInfo;
import com.zhiyangstudio.commonlib.mvp.inter.IView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.functions.Consumer;

/**
 * Created by zhiyang on 2018/4/26.
 */

public interface FilterContract {
    interface IFilterView extends IView {
        void setCustomerInfoData(List<CustomerInfo> list);

        void setCustomerGroupInfoData(List<CustomerGroupInfo> list);

        void setDeviceList(List<DeviceInfo> list);

        void refreshContentUi();
    }

    interface IFilterPresenter {
        void loadList();
    }

    interface IFilterModel {
        void getFilterData(Consumer<List<CustomerInfo>> customerConsumer, Consumer<List<CustomerGroupInfo>>
                customerGroupInfoConsumer, Observer<List<DeviceInfo>> observer);
    }
}
