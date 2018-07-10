package com.example.rrx;

import android.app.Activity;
import android.view.View;

import com.zysdk.vulture.clib.sample.activity.BaseSampleToolbarSupportActivity;
import com.zysdk.vulture.clib.utils.IntentUtils;

import butterknife.OnClick;

public class MainActivity extends BaseSampleToolbarSupportActivity {

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

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

    @OnClick({
            R.id.btn_action_0, R.id.btn_action_1

    })
    public void onViewClick(View v) {
        Class<? extends Activity> targetCls = null;
        switch (v.getId()) {
            case R.id.btn_action_0:
                targetCls = Sample01Activity.class;
                break;
            case R.id.btn_action_1:
                targetCls = Sample02Activity.class;
                break;
        }
        IntentUtils.forward(targetCls);
    }
}
