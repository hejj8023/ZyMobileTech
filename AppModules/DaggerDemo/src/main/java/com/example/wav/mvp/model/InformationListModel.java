package com.example.wav.mvp.model;

import com.example.wav.mvp.contract.InformationContract;
import com.zhiyangstudio.commonlib.utils.RxUtils;

import io.reactivex.Observer;
import okhttp3.ResponseBody;

/**
 * Created by example on 2018/5/14.
 */

public class InformationListModel extends BaseAdvModel implements InformationContract
        .IInformationModel {
    @Override
    public void getList(Observer<ResponseBody> obsrever) {
        getApi().getInformation().compose(RxUtils.io_main()).subscribe(obsrever);
    }
}
