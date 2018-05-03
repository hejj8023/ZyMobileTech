package com.example.wav.mvp.presenter;

import com.example.wav.mvp.contract.DeviceRegContract;
import com.example.wav.mvp.model.DeviceRegModel;
import com.zhiyangstudio.commonlib.mvp.presenter.BasePresenter;
import com.zhiyangstudio.commonlib.net.callback.AbsBaseObserver;
import com.zhiyangstudio.commonlib.utils.EmptyUtils;
import com.zhiyangstudio.commonlib.utils.LoggerUtils;

import java.io.IOException;

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
        //    "000020750628393",
        // "898602B8191650753583",
        mModel.reg(
                mRegView.getCRCID(),
                mRegView.getSimCard(),
                mRegView.getDevName(),
                mRegView.getGroupName(),
                mRegView.getCustName(),
                mRegView.getAppendFlag(),
                mRegView.getLoginName(),
                mRegView.getPassword(),
                mRegView.getParentName(),
                new AbsBaseObserver<ResponseBody>(this, DeviceRegModel.class.getName()) {
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String s = responseBody.string();
                            LoggerUtils.loge(s);
                            if (EmptyUtils.isNotEmpty(s)) {
                                int result = Integer.parseInt(s);
                                if (result == 1) {
                                    mRegView.onDevciceRegSucess();
                                } else {
                                    mRegView.showFail("设备注册失败");
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
        );
    }
}
