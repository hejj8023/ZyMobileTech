package com.example.rrx;

import com.example.rrx.helper.SampleHelper;
import com.zysdk.vulture.clib.sample.activity.BaseSampleToolbarSupportActivity;

public abstract class SampleRxActivity extends BaseSampleToolbarSupportActivity {

    protected SampleHelper sampleHelper;
    protected SampleHelper2 sampleHelper2;

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        sampleHelper = new SampleHelper(mContext);
        sampleHelper2 = new SampleHelper2(mContext);
    }

    @Override
    public void refreshUi() {

    }

    @Override
    public void release() {

    }
}
