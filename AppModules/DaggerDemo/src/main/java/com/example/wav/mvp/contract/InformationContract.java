package com.example.wav.mvp.contract;

import com.example.wav.bean.InformationBean;
import com.zhiyangstudio.commonlib.mvp.inter.ISampleRefreshView;

import io.reactivex.Observer;
import okhttp3.ResponseBody;

/**
 * Created by example on 2018/5/14.
 */

public interface InformationContract {
    public interface IInformationView extends ISampleRefreshView<InformationBean> {
    }

    public interface IInformationModel {
        void getList(Observer<ResponseBody> obsrever);
    }

    public interface IInformationPresenter {
        void getInformationList();
    }
}
