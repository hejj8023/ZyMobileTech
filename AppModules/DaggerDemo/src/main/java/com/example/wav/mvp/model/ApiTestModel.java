package com.example.wav.mvp.model;

import com.example.wav.bean.AccountCustomerInfo;
import com.example.wav.bean.AccountDeviceInfo;
import com.example.wav.bean.AccountGroupInfo;
import com.example.wav.bean.AccountInfo;
import com.example.wav.mvp.contract.ApiTestContract;
import com.example.wav.utils.MD5Utils;
import com.zhiyangstudio.commonlib.utils.LoggerUtils;
import com.zhiyangstudio.commonlib.utils.RxUtils;

import java.util.List;

import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;

/**
 * Created by example on 2018/4/27.
 */

public class ApiTestModel extends BaseAdvModel implements ApiTestContract.IApiTestModel {


    @Override
    public void login(String userName, String pwd, Consumer<ResponseBody> observer) {
        String md5Str = MD5Utils.getMd5(pwd);
        LoggerUtils.loge("md5Str = " + md5Str);
//        mApiServer.login(userName, md5Str).compose(RxUtils.io_main()).subscribe(observer);
    }

    @Override
    public void deviceReg(Consumer<ResponseBody> observer) {

    }

    @Override
    public void getCustomerList(Consumer<List<AccountCustomerInfo>> observer) {
        mApiServer.getCustomerList().compose(RxUtils.io_main()).subscribe(observer);
    }

    @Override
    public void getCustomerGroupList(Consumer<ResponseBody> observer, String customerID) {
        mApiServer.getCustomerGroupList(customerID).compose(RxUtils.io_main()).subscribe(observer);
    }

    @Override
    public void getDeviceList(Consumer<AccountDeviceInfo> observer, String customerID, String groupID, int status, int page, int rows) {
        mApiServer.getDeviceList(customerID, groupID, status, page, rows).compose(RxUtils.io_main())
                .subscribe(observer);
    }

    @Override
    public void login2(String uName, String pwd, String sourceType, Consumer<AccountInfo> consumer) {
        String md5Str = MD5Utils.getMd5(pwd);
        LoggerUtils.loge("md5Str = " + md5Str);
        /**
         * 查询结果：
         md5(88888888,32) = 8ddcff3a80f4189ca1c9d4d902c3c909
         md5(88888888,16) = 80f4189ca1c9d4d9

         本地结果：8ddcff3a80f4189ca1c9d4d902c3c909
         */
        mApiServer.login2(uName, md5Str, sourceType).compose(RxUtils.io_main()).subscribe(consumer);
    }

    @Override
    public void getCustomerGroupList2(Consumer<List<AccountGroupInfo>> consumer, String custoemrId) {
        mApiServer.getCustomerGroupList2(custoemrId).compose(RxUtils.io_main()).subscribe(consumer);
    }


}
