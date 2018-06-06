package com.example.wanandroid.mvp.model.inter;

import com.example.wanandroid.bean.UserBean;
import com.zysdk.vulture.clib.net.callback.RxObserver;

/**
 * Created by zhiyang on 2018/4/19.
 */

public interface ILoginModel {
    /*登录*/
    void login(String userName, String password, RxObserver<UserBean> rxObserver);

    /*注册*/
    void register(String userName, String password, RxObserver<String> rxObserver);
}
