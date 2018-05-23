package com.example.sash.ui.activity;

import android.view.View;

import com.example.sash.R;
import com.zhiyangstudio.commonlib.corel.BaseActivity;
import com.zhiyangstudio.commonlib.corel.BaseToolbarSupportActivity;
import com.zhiyangstudio.commonlib.utils.IntentUtils;

import butterknife.OnClick;

/**
 * Created by example on 2018/5/23.
 */

public class AppMainActivity extends BaseToolbarSupportActivity {
    @Override
    protected boolean initToolBar() {
        return true;
    }

    @Override
    protected int getToolbarBgColor() {
        return R.color.sr_color_primary;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_app_main;
    }


    @OnClick({R.id.btn_manage_bt, R.id.btn_manage_wifi})
    public void onViewClick(View view) {
        Class<? extends BaseActivity> tCls = null;
        switch (view.getId()) {
            case R.id.btn_manage_bt:
                tCls = SampleBluetoothActivity.class;
                break;
            case R.id.btn_manage_wifi:
                tCls = SampleWifiActivity.class;
                break;
        }
        if (tCls != null) {
            IntentUtils.forward(tCls);
        }
    }
}
