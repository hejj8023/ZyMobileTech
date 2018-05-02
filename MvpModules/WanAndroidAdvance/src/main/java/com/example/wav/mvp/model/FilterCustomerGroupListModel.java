package com.example.wav.mvp.model;

import com.example.wav.bean.AccountGroupInfo;
import com.example.wav.mvp.contract.FilterCustomerGroupListContract;
import com.zhiyangstudio.commonlib.utils.RxUtils;

import java.util.List;

import io.reactivex.Observer;

/**
 * Created by example on 2018/5/2.
 */

public class FilterCustomerGroupListModel extends
        BaseAdvModel implements FilterCustomerGroupListContract.IFilterCustomerGroupListModel {
    @Override
    public void getData(String customerId, Observer<List<AccountGroupInfo>> consumer) {
        getApi().getCustomerGroupList2(customerId).compose(RxUtils.io_main()).subscribe(consumer);
    }
}
