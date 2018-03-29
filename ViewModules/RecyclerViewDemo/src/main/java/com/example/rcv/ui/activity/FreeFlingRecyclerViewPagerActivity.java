package com.example.rcv.ui.activity;

/**
 * Created by example on 2018/3/29.
 */

public class FreeFlingRecyclerViewPagerActivity extends BasicUseRecyclerViewPagerActivity {
    @Override
    protected void initData() {
        super.initData();
        mRecyclerView.setSinglePageFling(false);
    }
}
