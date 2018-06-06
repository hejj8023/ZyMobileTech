package com.example.testapp.fragment;

import android.os.Bundle;

import com.example.testapp.Const;
import com.example.testapp.R;
import com.zysdk.vulture.clib.adapter.lgrcommon.QuickAdapter;
import com.zysdk.vulture.clib.adapter.lgrcommon.QuickViewHolder;
import com.zysdk.vulture.clib.corel.BaseFragment;
import com.zysdk.vulture.clib.utils.UiUtils;

/**
 * Created by example on 2018/4/18.
 */

public abstract class BaseSingleListFragment extends BaseFragment {
    protected QuickAdapter quickAdapter = new QuickAdapter<String>(UiUtils.getContext(),
            Const.DATA.TEST_LIST, R.layout.layout_item_list) {
        @Override
        protected void convert(QuickViewHolder holder, String data, int position) {
            holder.setText(R.id.textview, data);
        }
    };

    @Override
    public void addListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void refreshUi() {

    }

    @Override
    public void release() {

    }

    @Override
    public void initArguments(Bundle bundle) {

    }
}
