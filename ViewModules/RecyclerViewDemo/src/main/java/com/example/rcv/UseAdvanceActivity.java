package com.example.rcv;

import android.view.View;

import com.example.common.corel.BaseActivity;
import com.example.rcv.ui.activity.BaseRecyclerViewActivity;
import com.example.rcv.ui.activity.BasicUseRecyclerViewPagerActivity;

import butterknife.OnClick;

/**
 * Created by example on 2018/3/29.
 */

public class UseAdvanceActivity extends BaseRecyclerViewActivity {
    @Override
    protected int getContentViewId() {
        return R.layout.activity_use_advance;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void initData() {
        super.initData();
    }

    @OnClick({R.id.btn_rec_vp, R.id.btn_rec_vp_basic_usage})
    public void onViewClick(View view) {
        Class<? extends BaseActivity> tarCls = null;
        switch (view.getId()) {
            case R.id.btn_rec_vp:
                break;
            case R.id.btn_rec_vp_basic_usage:
                tarCls = BasicUseRecyclerViewPagerActivity.class;
                break;
        }
        if (tarCls != null) {
            forward(tarCls);
        }
    }
}
