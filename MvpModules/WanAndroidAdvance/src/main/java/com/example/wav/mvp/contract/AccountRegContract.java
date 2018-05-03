package com.example.wav.mvp.contract;

import io.reactivex.Observer;
import okhttp3.ResponseBody;

/**
 * Created by example on 2018/5/3.
 */

public interface AccountRegContract {
    interface IAccountRegView extends LoginContract.ILoginView {
        String getConfrimPwd();
    }

    interface IAccountRegPresenter {
        void login(String userName, String pwd);

        void regAccount();
    }

    interface IAccountRegModel extends LoginContract.ILoginModel {
        void regAccount(String userName, String pwd, Observer<ResponseBody> observer);
    }
}
