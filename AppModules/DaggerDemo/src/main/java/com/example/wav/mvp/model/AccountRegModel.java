package com.example.wav.mvp.model;

import com.example.wav.mvp.contract.AccountRegContract;
import com.example.wav.utils.MD5Utils;
import com.zhiyangstudio.commonlib.utils.RxUtils;

import io.reactivex.Observer;
import okhttp3.ResponseBody;

/**
 * Created by example on 2018/5/3.
 */

public class AccountRegModel extends LoginModel implements AccountRegContract.IAccountRegModel {
    @Override
    public void regAccount(String userName, String pwd, Observer<ResponseBody> observer) {
        getApi().accountReg(userName, MD5Utils.getMd5(pwd)).compose(RxUtils.io_main()).subscribe
                (observer);
    }
}
