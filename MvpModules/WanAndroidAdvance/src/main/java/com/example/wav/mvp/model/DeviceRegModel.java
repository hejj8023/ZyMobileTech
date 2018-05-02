package com.example.wav.mvp.model;

import com.example.wav.mvp.contract.DeviceRegContract;
import com.zhiyangstudio.commonlib.utils.RxUtils;

import io.reactivex.Observer;
import okhttp3.ResponseBody;

/**
 * Created by example on 2018/5/2.
 */

public class DeviceRegModel extends BaseAdvModel implements DeviceRegContract.IDeviceRegModel {
    @Override
    public void reg(String devCode, String appendFlag, String custName, String loginName,
                    String password, String provinceID, String cityID, String townID,
                    String address, String parentName, String groupName, String devName,
                    Observer<ResponseBody> observer) {
        getApi().regDevice(devCode, appendFlag, custName, loginName, password, provinceID, cityID, townID, address,
                parentName, groupName, devName).compose(RxUtils.io_main()).subscribe(observer);
    }
}
