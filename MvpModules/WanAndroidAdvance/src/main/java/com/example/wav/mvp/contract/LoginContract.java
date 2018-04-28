package com.example.wav.mvp.contract;

import com.example.wav.bean.AccountInfo;
import com.zhiyangstudio.commonlib.mvp.inter.IView;

import io.reactivex.functions.Consumer;

/**
 * Created by example on 2018/4/28.
 */

public interface LoginContract {
    public interface ILoginView extends IView {
        String getUserName();

        String getPwd();

        void changeState(int flag);
    }

    public interface ILoginPresenter {
        void login();
    }

    public interface ILoginModel {
        void login(String username, String pwd, String sourceType, Consumer<AccountInfo>
                observer);
    }
}
