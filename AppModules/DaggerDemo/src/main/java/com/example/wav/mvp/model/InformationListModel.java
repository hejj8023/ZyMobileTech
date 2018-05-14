package com.example.wav.mvp.model;

import com.example.wav.bean.InformationBean;
import com.example.wav.mvp.contract.InformationContract;
import com.zhiyangstudio.commonlib.utils.RxUtils;

import java.util.List;

import io.reactivex.Observer;

/**
 * Created by example on 2018/5/14.
 */

public class InformationListModel extends BaseAdvModel implements InformationContract
        .IInformationModel {
    @Override
    public void getList(Observer<List<InformationBean>> obsrever) {
        getApi().getInformation().compose(RxUtils.io_main()).subscribe(obsrever);
    }
}
