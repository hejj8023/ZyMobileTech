package com.example.testapp.fragment;

import android.widget.GridView;

import com.example.testapp.R;

import butterknife.BindView;

/**
 * Created by example on 2018/4/18.
 */

public class SingleTypeItemGridFragmentSingle extends BaseSingleListFragment {
    @BindView(R.id.gridview)
    GridView mGridView;

    @Override
    public int getContentId() {
        return R.layout.fragment_type_grid;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        mGridView.setAdapter(quickAdapter);
    }

}
