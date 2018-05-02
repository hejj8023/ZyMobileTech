package com.example.wav.mvp.contract;

import com.example.wav.bean.AccountCustomerInfo;
import com.zhiyangstudio.commonlib.mvp.inter.IListDataView;

import java.util.List;

import io.reactivex.Observer;

/**
 * Created by example on 2018/5/2.
 */

public interface FilterCustomerListContract {
    interface IFilterCustomerListView extends IListDataView<AccountCustomerInfo> {
    }

    interface IFilterCustomerListModel {
        void getData(Observer<List<AccountCustomerInfo>> consumer);
    }

    interface IFilterCustomerListPresenter {
        void getData();
    }
}
