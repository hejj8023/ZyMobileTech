package com.example.wav.mvp.contract;

import com.zhiyangstudio.commonlib.mvp.inter.IView;

import io.reactivex.Observer;
import okhttp3.ResponseBody;

/**
 * Created by example on 2018/5/2.
 */

public interface DeviceRegContract {
    interface IDeviceRegView extends IView {
    }

    interface IDeviceRegPresenter {
        void regDevice();
    }

    interface IDeviceRegModel {
        void reg(String devCode,
                 String appendFlag,
                 String custName,
                 String loginName,
                 String password,
                 String provinceID,
                 String cityID,
                 String townID,
                 String address,
                 String parentName,
                 String groupName,
                 String devName,
                 Observer<ResponseBody> observer);
    }
}
