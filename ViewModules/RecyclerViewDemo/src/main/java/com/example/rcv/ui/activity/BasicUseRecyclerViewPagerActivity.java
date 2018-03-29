package com.example.rcv.ui.activity;

import android.support.v7.widget.LinearLayoutManager;

import com.example.rcv.R;
import com.example.rcv.adapter.BasicListAdapter;
import com.example.rcv.adapter.LayoutAdapter;
import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;

import butterknife.BindView;

/**
 * Created by example on 2018/3/29.
 */

public class BasicUseRecyclerViewPagerActivity extends BaseRecyclerViewActivity {
    @BindView(R.id.viewpager)
    RecyclerViewPager recyclerViewPager;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_basic_use_recycler_viewpager;
    }

    @Override
    protected void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPager.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void initData() {
        super.initData();
        recyclerViewPager.setAdapter(new LayoutAdapter(dataList,recyclerViewPager));
    }

}
