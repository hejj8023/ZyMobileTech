package com.example.wav.ui.activity;

import android.view.View;

import com.example.wav.R;
import com.example.wav.base.BaseAdvActivity;
import com.example.wav.mvp.contract.ApiTestContract;
import com.example.wav.mvp.presenter.ApiTestPresenter;

import butterknife.OnClick;

/**
 * Created by example on 2018/4/27.
 */

public class ApiTestActivity extends BaseAdvActivity<ApiTestPresenter, ApiTestContract
        .IApiTestView> implements ApiTestContract.IApiTestView {
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
    public void release() {

    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected boolean initToolBar() {
        setTitle("Api测试");
        return true;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_api_test;
    }

    @OnClick({R.id.btn_api_login, R.id.btn_api_login2, R.id.btn_api_dev_reg,
            R.id.btn_api_customer_list, R.id.btn_api_customer_group_list,
            R.id.btn_api_customer_group_list2, R.id.btn_api_device_list})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_api_login:
                mPresenter.login();
                break;
            case R.id.btn_api_login2:
                mPresenter.login2();
                break;
            case R.id.btn_api_dev_reg:
                mPresenter.deviceReg();
                break;
            case R.id.btn_api_customer_list:
                mPresenter.getCustomerList();
                break;
            case R.id.btn_api_customer_group_list:
                mPresenter.getCustomerGroupList();
                break;
            case R.id.btn_api_customer_group_list2:
                mPresenter.getCustomerGroupList2();
                break;
            case R.id.btn_api_device_list:
                mPresenter.getDeviceList();
                break;
        }
    }
}
