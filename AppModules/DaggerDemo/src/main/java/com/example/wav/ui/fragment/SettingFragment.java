package com.example.wav.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.example.wav.R;
import com.example.wav.ui.activity.DeviceRegActivity;
import com.example.wav.ui.activity.sample.SampleMPAndroidChartActivity;
import com.zhiyangstudio.commonlib.corel.BaseFragment;
import com.zhiyangstudio.commonlib.utils.IntentUtils;

import butterknife.OnClick;

/**
 * Created by example on 2018/4/28.
 */

public class SettingFragment extends BaseFragment {
    @Override
    public int getContentId() {
        return R.layout.fragment_settings;
    }

    @Override
    public void initView() {

    }

    @Override
    public void addListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void refreshUi() {

    }

    @Override
    public void release() {

    }

    @Override
    protected void initArguments(Bundle bundle) {

    }

    @OnClick({
            R.id.btn_device_reg,R.id.btn_mp_android_chart
    })
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_device_reg:
                IntentUtils.forward(DeviceRegActivity.class);
                break;
            case R.id.btn_mp_android_chart:
                IntentUtils.forward(SampleMPAndroidChartActivity.class);
                break;
        }
    }
}
