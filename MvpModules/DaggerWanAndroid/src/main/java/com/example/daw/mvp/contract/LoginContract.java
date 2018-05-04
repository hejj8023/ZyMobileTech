package com.example.daw.mvp.contract;

import com.example.daw.bean.UserBean;
import com.zhiyangstudio.commonlib.bean.BaseBean;
import com.zhiyangstudio.commonlib.mvp.inter.IView;

import io.reactivex.Observer;

/**
 * Created by example on 2018/5/3.
 */

public interface LoginContract {
    interface ILoginView extends IView {
        String getUserName();

        String getUserPassword();

        void loginFailure();

        void loginSucess();
    }

    interface ILoginPresenter {
        void login();
    }

    interface ILoginModel {
        void login(String userName, String pwd, Observer<BaseBean<UserBean>> observer);
    }
}
