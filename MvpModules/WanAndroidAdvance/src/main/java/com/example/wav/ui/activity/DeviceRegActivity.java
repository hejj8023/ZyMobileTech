package com.example.wav.ui.activity;

import android.support.v7.widget.AppCompatEditText;
import android.view.View;

import com.example.wav.R;
import com.example.wav.base.BaseAdvActivity;
import com.example.wav.mvp.contract.DeviceRegContract;
import com.example.wav.mvp.presenter.DeviceRegPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by example on 2018/5/2.
 */

public class DeviceRegActivity extends BaseAdvActivity<DeviceRegPresenter, DeviceRegContract
        .IDeviceRegView> implements DeviceRegContract
        .IDeviceRegView {

    @BindView(R.id.et_device_code)
    AppCompatEditText etDeviceCode;

    @BindView(R.id.et_device_wlw_card_num)
    AppCompatEditText etDevicewlwCarDnum;

    @BindView(R.id.et_device_name)
    AppCompatEditText etDeviceName;

    @BindView(R.id.et_device_gropu_name)
    AppCompatEditText etDeviceGropuName;

    @BindView(R.id.et_device_account)
    AppCompatEditText etDeviceAccount;

    @BindView(R.id.et_device_u_customer_name)
    AppCompatEditText etDeviceuCustomerName;

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void refreshUi() {

    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected boolean initToolBar() {
        return false;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_device_reg;
    }

    @OnClick(R.id.btn_device_register)
    public void onViewClick(View view) {
        mPresenter.regDevice();
    }
}
