package com.example.daw.mvp.model;

import com.example.daw.base.BaseDawModel;
import com.example.daw.bean.UserBean;
import com.example.daw.mvp.contract.LoginContract;
import com.zysdk.vulture.clib.bean.BaseBean;
import com.zysdk.vulture.clib.utils.RxUtils;

import io.reactivex.Observer;

/**
 * Created by example on 2018/5/4.
 */

public class LoginModel extends BaseDawModel implements LoginContract.ILoginModel{
    @Override
    public void login(String userName, String pwd, Observer<BaseBean<UserBean>> observer) {
        mApiServer.login(userName,pwd).compose(RxUtils.io_main()).subscribe(observer);
    }
}
