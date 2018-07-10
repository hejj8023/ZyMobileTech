package com.example.rrx.activity;

import android.view.View;

import com.example.rrx.R;
import com.example.rrx.helper.SampleHelper;

import butterknife.OnClick;

/**
 * 资料地址:https://www.jianshu.com/p/8818b98c44e2
 */
public class Sample01Activity extends SampleRxActivity {

    private SampleHelper baseHelper;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_sample01;
    }

    @Override
    public void initData() {
        baseHelper = new SampleHelper(mContext);
    }

    @OnClick({R.id.btn_crate_observer, R.id.btn_crate_observer2,
            R.id.btn_crate_observer3, R.id.btn_crate_observer4,
            R.id.btn_schedule, R.id.btn_reschedule})
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.btn_crate_observer:
                baseHelper.create();
                break;
            case R.id.btn_crate_observer2:
                baseHelper.create2();
                break;
            case R.id.btn_crate_observer3:
                baseHelper.create3();
                break;
            case R.id.btn_crate_observer4:
                baseHelper.create4();
                break;
            case R.id.btn_schedule:
                baseHelper.schedule(false);
                break;
            case R.id.btn_reschedule:
                baseHelper.schedule(true);
                break;
        }
    }
}
