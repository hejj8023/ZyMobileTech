package com.example.wanandroid.mvp.model;

import com.example.wanandroid.bean.UserBean;
import com.example.wanandroid.mvp.model.inter.ILoginModel;
import com.zysdk.vulture.clib.net.callback.RxObserver;
import com.zysdk.vulture.clib.utils.RxUtils;

/**
 * Created by zhiyang on 2018/4/19.
 */

public class LoginModel extends BaseWanModel implements ILoginModel {
    @Override
    public void login(String userName, String password, RxObserver<UserBean> rxObserver) {
        getApi().login(userName, password).compose(RxUtils.io_main()).subscribe(rxObserver);
    }

    @Override
    public void register(String userName, String password, RxObserver<String> rxObserver) {
        getApi().register(userName, password, password).compose(RxUtils.io_main()).subscribe(rxObserver);
    }
}
