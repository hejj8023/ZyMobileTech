package com.example.vrec;

import android.view.View;

import com.zhiyangstudio.commonlib.corel.BaseActivity;
import com.zhiyangstudio.commonlib.corel.BaseToolbarSupportActivity;

import butterknife.OnClick;

public class MainActivity extends BaseToolbarSupportActivity {

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @OnClick({R.id.btn_action_capture, R.id.btn_action_record})
    public void onViewClick(View view) {
        Class<? extends BaseActivity> targetCls;
        switch (view.getId()) {
            case R.id.btn_action_capture:
                targetCls = CaptureActivity.class;
                break;
            case R.id.btn_action_record:
                targetCls = VideoRecordActivity.class;
                break;
        }
    }
}
