package com.example.wav.mvp.model;

import com.example.wav.bean.AccountCustomerInfo;
import com.example.wav.mvp.contract.FilterCustomerListContract;
import com.zhiyangstudio.commonlib.utils.RxUtils;

import java.util.List;

import io.reactivex.Observer;

/**
 * Created by example on 2018/5/2.
 */

public class FilterCustomerListModel extends BaseAdvModel implements FilterCustomerListContract
        .IFilterCustomerListModel {

    @Override
    public void getData(Observer<List<AccountCustomerInfo>> consumer) {
        getApi().getCustomerList().compose(RxUtils.io_main()).subscribe(consumer);
    }
}
