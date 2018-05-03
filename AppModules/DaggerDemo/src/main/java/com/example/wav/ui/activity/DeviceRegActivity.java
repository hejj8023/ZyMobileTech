package com.example.wav.ui.activity;

import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.RadioGroup;

import com.blankj.utilcode.util.ToastUtils;
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
        .IDeviceRegView> implements DeviceRegContract.IDeviceRegView {

    @BindView(R.id.et_device_code)
    AppCompatEditText etDeviceCode;
    @BindView(R.id.et_device_wlw_card_num)
    AppCompatEditText etDevicewlwCardNum;
    @BindView(R.id.et_device_name)
    AppCompatEditText etDeviceName;
    @BindView(R.id.et_device_gropu_name)
    AppCompatEditText etDeviceGropuName;
    @BindView(R.id.et_device_customer_name)
    AppCompatEditText etDeviceCustomerName;
    @BindView(R.id.et_device_account)
    AppCompatEditText etDeviceAccount;
    @BindView(R.id.et_device_account_pwd)
    AppCompatEditText etDeviceAccountPwd;
    @BindView(R.id.et_device_parent_name)
    AppCompatEditText etDeviceParentName;
    @BindView(R.id.rg_app_flag)
    RadioGroup rgAppFlag;
    private int appFlag;

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

    @Override
    public void addListener() {
        rgAppFlag.setOnCheckedChangeListener(((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rb_app_flag_newuser:
                    appFlag = 0;
                    break;
                case R.id.rb_app_flag_exsit_user:
                    appFlag = 1;
                    break;
            }
        }));
    }

    @OnClick(R.id.btn_device_register)
    public void onViewClick(View view) {
        mPresenter.regDevice();
    }

    @Override
    public String getCRCID() {
        return etDeviceCode.getText().toString().trim();
    }

    @Override
    public String getSimCard() {
        return etDevicewlwCardNum.getText().toString().trim();
    }

    @Override
    public String getDevName() {
        return etDeviceName.getText().toString().trim();
    }

    @Override
    public String getGroupName() {
        return etDeviceGropuName.getText().toString().trim();
    }

    @Override
    public String getCustName() {
        return etDeviceCustomerName.getText().toString().trim();
    }

    @Override
    public String getAppendFlag() {
        return appFlag + "";
    }

    @Override
    public String getLoginName() {
        return etDeviceAccount.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return etDeviceAccountPwd.getText().toString().trim();
    }

    @Override
    public String getParentName() {
        return etDeviceParentName.getText().toString().trim();
    }

    @Override
    public void onDevciceRegSucess() {
        ToastUtils.showShort("设备注册成功");
        finish();
    }

}
