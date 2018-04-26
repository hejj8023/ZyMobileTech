package com.example.wav.mvp.model;

/**
 * Created by zhiyang on 2018/4/26.
 */

public class DeviceModel extends BaseFilterDataModel {
    private void test() {
        //        Observable.just(Const.TMP_DATA.DEV_LIST).compose(RxUtils.io_main()).subscribe(observer);
        // 两种方式都是可以的
//        RxUtils.createObservableData(Const.TMP_DATA.DEV_LIST).compose(RxUtils.io_main()).subscribe(observer);
    }

}
