package com.example.rrx.activity;

import android.view.View;

import com.example.rrx.R;
import com.example.rrx.helper.SampleHelper2;

import butterknife.OnClick;

/**
 * 资料地址:https://www.jianshu.com/p/aedbca59d39b
 */
public class Sample02Activity extends SampleRxActivity {


    private SampleHelper2 sampleHelper2;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_sample02;
    }

    @Override
    public void initData() {
        sampleHelper2 = new SampleHelper2(mContext);
    }

    @OnClick({R.id.btn_crate_observer, R.id.btn_crate_observer2,
            R.id.btn_schedule, R.id.btn_flowable_used,
            R.id.btn_con_fi, R.id.btn_test_requestId})
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.btn_crate_observer:
                sampleHelper2.doCreate();
                break;
            case R.id.btn_crate_observer2:
                sampleHelper2.createByConsumer();
                break;
            case R.id.btn_schedule:
                sampleHelper2.schedule();
                break;
            case R.id.btn_flowable_used:
                sampleHelper2.cerateByFlowable();
                break;
            case R.id.btn_con_fi:
                sampleHelper2.useFlowableInterval();
                break;
            case R.id.btn_test_requestId:
                sampleHelper2.testQuestId();
                break;
        }
    }
}
