package com.example.daw.mvp.model;

import com.example.daw.base.BaseDawModel;
import com.example.daw.mvp.contract.LoginContract;
import com.zhiyangstudio.commonlib.utils.RxUtils;

import io.reactivex.Observer;
import okhttp3.ResponseBody;

/**
 * Created by example on 2018/5/4.
 */

public class LoginModel extends BaseDawModel implements LoginContract.ILoginModel{
    @Override
    public void login(String userName, String pwd, Observer<ResponseBody> observer) {
        mApiServer.login(userName,pwd).compose(RxUtils.io_main()).subscribe(observer);
    }
}
