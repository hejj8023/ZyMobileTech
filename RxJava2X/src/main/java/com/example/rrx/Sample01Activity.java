package com.example.rrx;

import android.view.View;

import butterknife.OnClick;

/**
 * 资料地址:https://www.jianshu.com/p/8818b98c44e2
 */
public class Sample01Activity extends SampleRxActivity {

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_sample01;
    }


    @OnClick({R.id.btn_crate_observer, R.id.btn_crate_observer2,
            R.id.btn_crate_observer3, R.id.btn_crate_observer4,
            R.id.btn_schedule, R.id.btn_reschedule})
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.btn_crate_observer:
                sampleHelper.create();
                break;
            case R.id.btn_crate_observer2:
                sampleHelper.create2();
                break;
            case R.id.btn_crate_observer3:
                sampleHelper.create3();
                break;
            case R.id.btn_crate_observer4:
                sampleHelper.create4();
                break;
            case R.id.btn_schedule:
                sampleHelper.schedule(false);
                break;
            case R.id.btn_reschedule:
                sampleHelper.schedule(true);
                break;
        }
    }
}
