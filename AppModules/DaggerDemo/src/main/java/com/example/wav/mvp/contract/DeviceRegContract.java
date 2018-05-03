package com.example.wav.mvp.contract;

import com.zhiyangstudio.commonlib.mvp.inter.IView;

import io.reactivex.Observer;
import okhttp3.ResponseBody;

/**
 * Created by example on 2018/5/2.
 */

public interface DeviceRegContract {
    interface IDeviceRegView extends IView {
        String getCRCID();

        String getSimCard();

        String getDevName();

        String getGroupName();

        String getCustName();

        String getAppendFlag();

        String getLoginName();

        String getPassword();

        String getParentName();

        void onDevciceRegSucess();
    }

    interface IDeviceRegPresenter {
        void regDevice();
    }

    interface IDeviceRegModel {
        void reg(String cRCID, String simCard, String devName, String groupName,
                 String custName, String appendFlag, String loginName, String password,
                 String parentName, Observer<ResponseBody> observer);
    }
}
