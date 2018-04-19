package com.example.wanandroid.mvp.model;

import com.example.wanandroid.bean.UserBean;
import com.zhiyangstudio.commonlib.net.callback.RxObserver;

/**
 * Created by zhiyang on 2018/4/19.
 */

public interface ILoginModel {
    /*登录*/
    void login(String userName, String password, RxObserver<UserBean> rxObserver);

    /*注册*/
    void register(String userName, String password, RxObserver<String> rxObserver);
}
