package com.example.wav.mvp.contract;

import com.example.wav.bean.AccountDeviceInfo;
import com.zhiyangstudio.commonlib.mvp.inter.IListDataView;

/**
 * Created by example on 2018/4/28.
 */

public interface HomeFragmentContract {
    public interface IHomeFragmentView extends IListDataView<AccountDeviceInfo> {
    }

    public interface IHomeFragmentPresenter {
    }
}
