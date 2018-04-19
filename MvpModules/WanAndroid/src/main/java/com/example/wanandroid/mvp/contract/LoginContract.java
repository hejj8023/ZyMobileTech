package com.example.wanandroid.mvp.contract;

import com.zhiyangstudio.commonlib.mvp.inter.IView;

/**
 * Created by example on 2018/4/9.
 */

public interface LoginContract {
    interface IUserPresenter {
        void login();

        void register();
    }

    interface ILoginView extends IView {
        /**
         * 获取用户名
         *
         * @return
         */
        String getUserName();

        /**
         * 获取密码
         *
         * @return
         */
        String getPassword();

        /**
         * 账号错误
         *
         * @param msg
         */
        void onAccountError(String msg);

        /**
         * 登录 或注册结果
         *
         * @param msg
         */
        void showResult(String msg);

        /**
         * 登录状态 发生变化
         *
         * @param status
         */
        void onLoginStatusChange(int status);
    }
}
