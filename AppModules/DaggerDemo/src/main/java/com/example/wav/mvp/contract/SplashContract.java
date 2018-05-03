package com.example.wav.mvp.contract;

import com.zhiyangstudio.commonlib.mvp.inter.IView;

/**
 * Created by zhiyang on 2018/4/25.
 */

public interface SplashContract {
    interface ISplashView extends IView {
    }

    interface ISplashPresenter {
        void log(String data);
    }
}
