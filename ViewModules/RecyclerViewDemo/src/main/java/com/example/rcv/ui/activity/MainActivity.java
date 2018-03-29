package com.example.rcv.ui.activity;

import android.view.View;

import com.example.common.corel.BaseActivity;
import com.example.rcv.R;
import com.example.rcv.ui.FJNestedHoverTabActivity;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected PermissionListener getPermissonCallBack() {
        return null;
    }

    @OnClick({R.id.btn_main_basic, R.id.btn_main_adv, R.id.btn_main_fjnestedhovertab, R.id.btn_main_fjedittextcount})
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.btn_main_adv:
                forward(UseAdvanceActivity.class);
                break;
            case R.id.btn_main_basic:
                forward(UseBasicActivity.class);
                break;
            case R.id.btn_main_fjnestedhovertab:
                forward(FJNestedHoverTabActivity.class);
                break;
            case R.id.btn_main_fjedittextcount:
                forward(FJEditTextCountActivity.class);
                break;
        }
    }
}
