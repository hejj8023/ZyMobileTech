package com.example.wav.mvp.model;

import com.example.wav.mvp.contract.DeviceRegContract;
import com.example.wav.utils.MD5Utils;
import com.zhiyangstudio.commonlib.utils.RxUtils;

import io.reactivex.Observer;
import okhttp3.ResponseBody;

/**
 * Created by example on 2018/5/2.
 */

public class DeviceRegModel extends BaseAdvModel implements DeviceRegContract.IDeviceRegModel {
    @Override
    public void reg(String cRCID, String simCard, String devName, String groupName,
                    String custName, String appendFlag, String loginName, String password,
                    String parentName, Observer<ResponseBody> observer) {
        getApi().deviceReg(cRCID, simCard, devName, groupName, custName, appendFlag, loginName,
                MD5Utils.getMd5(password), parentName).compose(RxUtils.io_main()).subscribe
                (observer);
    }
}
