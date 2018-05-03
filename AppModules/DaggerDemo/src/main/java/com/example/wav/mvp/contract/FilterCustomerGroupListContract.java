package com.example.wav.mvp.contract;

import com.example.wav.bean.AccountGroupInfo;
import com.zhiyangstudio.commonlib.mvp.inter.IListDataView;

import java.util.List;

import io.reactivex.Observer;

/**
 * Created by example on 2018/5/2.
 */

public interface FilterCustomerGroupListContract {
    interface IFilterCustomerGroupListView extends IListDataView<AccountGroupInfo> {
    }

    interface IFilterCustomerGroupListPresenter {
        void getData();
    }

    interface IFilterCustomerGroupListModel {
        void getData(String customerId, Observer<List<AccountGroupInfo>> consumer);
    }
}
