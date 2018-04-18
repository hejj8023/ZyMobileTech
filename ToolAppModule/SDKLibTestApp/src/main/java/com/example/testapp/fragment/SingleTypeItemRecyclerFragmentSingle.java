package com.example.testapp.fragment;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.testapp.R;

import butterknife.BindView;

/**
 * Created by example on 2018/4/18.
 */

public class SingleTypeItemRecyclerFragmentSingle extends BaseSingleListFragment {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @Override
    public int getContentId() {
        return R.layout.fragment_type_recycler;
    }

    @Override
    public void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
    }

    @Override
    public void initData() {
        recyclerView.setAdapter(quickAdapter);
    }

    @Override
    public void addListener() {

    }

}
