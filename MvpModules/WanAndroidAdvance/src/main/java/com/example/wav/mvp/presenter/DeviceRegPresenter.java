package com.example.wav.mvp.presenter;

import com.example.wav.mvp.contract.DeviceRegContract;
import com.example.wav.mvp.model.DeviceRegModel;
import com.zhiyangstudio.commonlib.mvp.presenter.BasePresenter;
import com.zhiyangstudio.commonlib.net.callback.AbsBaseObserver;
import com.zhiyangstudio.commonlib.utils.LoggerUtils;

import javax.inject.Inject;

import okhttp3.ResponseBody;

/**
 * Created by example on 2018/5/2.
 */

public class DeviceRegPresenter extends BasePresenter<DeviceRegContract
        .IDeviceRegView> implements DeviceRegContract.IDeviceRegPresenter {

    private final DeviceRegModel mModel;
    private DeviceRegContract.IDeviceRegView mRegView;

    @Inject
    public DeviceRegPresenter() {
        mModel = new DeviceRegModel();
    }

    @Override
    public void regDevice() {
        mRegView = getView();
        mModel.reg(
                "6970714140146",
                "0",
                "kefu02",
                "kefu02",
                "123456",
                "440000",
                "440300",
                "0",
                "广东省深圳市罗湖小学",
                "美的",
                "测试",
                "智能电灯",
                new AbsBaseObserver<ResponseBody>(this, DeviceRegModel.class.getName()) {
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        String s = responseBody.toString();
                        LoggerUtils.loge(s);
                    }
                }
        );
    }
}
