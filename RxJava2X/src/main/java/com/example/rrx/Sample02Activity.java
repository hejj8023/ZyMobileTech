package com.example.rrx;

import android.view.View;

import butterknife.OnClick;

/**
 * 资料地址:https://www.jianshu.com/p/aedbca59d39b
 */
public class Sample02Activity extends SampleRxActivity {


    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_sample02;
    }


    @OnClick({R.id.btn_crate_observer, R.id.btn_crate_observer2,
            R.id.btn_crate_observer3, R.id.btn_crate_observer4,
            R.id.btn_schedule, R.id.btn_reschedule})
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.btn_crate_observer:
sampleHelper2.doCreate();
                break;
            case R.id.btn_crate_observer2:
                break;
            case R.id.btn_crate_observer3:
                break;
            case R.id.btn_crate_observer4:
                break;
            case R.id.btn_schedule:
                break;
            case R.id.btn_reschedule:
                break;
        }
    }
}
