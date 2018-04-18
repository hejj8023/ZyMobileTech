package com.example.testapp.fragment;

import android.widget.ListView;

import com.example.testapp.R;

import butterknife.BindView;

/**
 * Created by example on 2018/4/18.
 */

public class MultiTypeItemListFragment extends BaseMultiListFragment {

    @BindView(R.id.listview)
    ListView mListView;

    @Override
    public int getContentId() {
        return R.layout.fragment_type_list;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        mListView.setAdapter(mQuickAdapter);
    }

}
