package com.example.wav.sample;

import android.os.Bundle;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wav.R;
import com.zhiyangstudio.commonlib.refreshsupport.haorefresh.BaseHRListFragment;

import java.util.List;

/**
 * Created by example on 2018/5/10.
 */

public class ArmListFragment1 extends BaseHRListFragment<String> {
    private boolean isFirstLoad = true;
    private int mPage = 1;
    private int limit = 10;

    @Override
    public void refreshUi() {

    }

    @Override
    public void release() {

    }

    @Override
    protected void initArguments(Bundle bundle) {

    }

    @Override
    protected void loadData() {
        mListData.clear();
        maxDataCount = 35;
        for (int i = 0; i < mPage * limit; i++) {
            mListData.add(i + " , page = 1");
        }

        if (isFirstLoad) {
            isFirstLoad = false;
        } else {
            mHaoRecyclerView.refreshComplete();
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void loadMore() {
        int size = mListData.size();
        if (mPage < 4) {
            for (int i = 0; i < 10; i++) {
                mListData.add((size + i) + " -> , page = 2");
            }
        } else {
            for (int i = 0; i < 5; i++) {
                mListData.add((size + i) + " -> , page = 3");
            }
        }
        mAdapter.setNewData(mListData);
        mHaoRecyclerView.loadMoreComplete();
    }

    @Override
    protected BaseQuickAdapter<String, BaseViewHolder> getListAdapter(List<String> list) {
        return new BaseQuickAdapter<String, BaseViewHolder>(R.layout.layout_item_test_list, list) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.tag_text, item);
            }
        };
    }
}
