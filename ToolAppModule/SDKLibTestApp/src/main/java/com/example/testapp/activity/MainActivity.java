package com.example.testapp.activity;

import android.view.View;

import com.example.testapp.R;
import com.zysdk.vulture.clib.corel.BaseActivity;
import com.zysdk.vulture.clib.utils.IntentUtils;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Override
    protected PermissionListener getPermissonCallBack() {
        return null;
    }

    @Override
    public int getContentId() {
        return R.layout.activity_main;
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

    @OnClick(R.id.btn_android_common_adapter)
    public void onViewClick(View view) {
        IntentUtils.forward(AndroidCommonAdapterActivity.class);

    }
}
