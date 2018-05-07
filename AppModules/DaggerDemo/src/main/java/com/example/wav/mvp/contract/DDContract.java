package com.example.wav.mvp.contract;

import com.example.wav.bean.DevHistoryBean;
import com.zhiyangstudio.commonlib.mvp.inter.IView;

import io.reactivex.Observer;

/**
 * Created by example on 2018/5/7.
 */

public interface DDContract {
    interface IDDView extends IView {
        String getDevId();

        void setData(DevHistoryBean historyBean);
    }

    interface IDDPresenter {
        void getDevHistory();
    }

    interface IDDModel {
        void getDevHistory(String devId, Observer<DevHistoryBean> observer);
    }
}
