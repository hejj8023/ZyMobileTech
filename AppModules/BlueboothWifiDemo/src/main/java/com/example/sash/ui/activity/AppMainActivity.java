package com.example.sash.ui.activity;

import android.support.design.widget.NavigationView;
import android.view.View;

import com.example.sash.R;
import com.zysdk.vulture.clib.corel.BaseActivity;
import com.zysdk.vulture.clib.corel.BaseToolbarSupportActivity;
import com.zysdk.vulture.clib.utils.CommonUtils;
import com.zysdk.vulture.clib.utils.IntentUtils;
import com.zysdk.vulture.clib.utils.UiUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by example on 2018/5/23.
 */

public class AppMainActivity extends BaseToolbarSupportActivity {

    @BindView(R.id.navi_app_man)
    NavigationView mNavigationView;

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

    @Override
    public void initView() {
        CommonUtils.setNavigationMenuLineStyle(mNavigationView, UiUtils.getColor(R.color.lightgray),
                1);
        CommonUtils.disableNavigationViewScrollbars(mNavigationView);
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
