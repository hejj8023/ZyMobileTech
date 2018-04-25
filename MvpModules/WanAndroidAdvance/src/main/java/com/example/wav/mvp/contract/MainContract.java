package com.example.wav.mvp.contract;

import com.zhiyangstudio.commonlib.mvp.inter.IView;

/**
 * Created by zhiyang on 2018/4/25.
 */

public interface MainContract {
    public interface IMainView extends IView {
    }

    public interface IMainPresenter {
        void log(String s);
    }
}
