package com.example.wav.mvp.contract;

import com.example.wav.bean.AccountInfo;
import com.zhiyangstudio.commonlib.mvp.inter.IView;

import io.reactivex.Observer;

/**
 * Created by example on 2018/4/28.
 */

public interface LoginContract {
    interface ILoginView extends IView {
        String getUserName();

        String getPwd();

        void changeState(int flag);
    }

    interface ILoginPresenter {
        void login();
    }

    interface ILoginModel {
        void login(String username, String pwd, String sourceType, Observer<AccountInfo>
                observer);
    }
}
