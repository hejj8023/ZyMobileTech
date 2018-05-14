package com.example.wav.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.example.wav.R;
import com.example.wav.sample.SmartRefreshLayoutTestActivity;
import com.example.wav.ui.activity.DeviceRegActivity;
import com.example.wav.ui.activity.FilterTestActivity;
import com.example.wav.ui.activity.InformationListActivity;
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
            R.id.btn_device_reg, R.id.btn_mp_android_chart,
            R.id.btn_filter_menu, R.id.btn_smartrefreshlayout,
            R.id.btn_information_list
    })
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_device_reg:
                IntentUtils.forward(DeviceRegActivity.class);
                break;
            case R.id.btn_mp_android_chart:
                IntentUtils.forward(SampleMPAndroidChartActivity.class);
                break;
            case R.id.btn_filter_menu:
                // dialog,popupwindow,(dialog)activity,(dialog)fragment
                IntentUtils.forward(FilterTestActivity.class);
                break;
            case R.id.btn_smartrefreshlayout:
                IntentUtils.forward(SmartRefreshLayoutTestActivity.class);
                break;
            case R.id.btn_information_list:
                IntentUtils.forward(InformationListActivity.class);
                break;
        }
    }
}
