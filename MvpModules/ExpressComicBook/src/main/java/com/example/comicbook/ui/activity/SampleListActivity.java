package com.example.comicbook.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.comicbook.R;
import com.zysdk.vulture.clib.sample.activity.BaseSampleToolbarSupportActivity;
import com.zysdk.vulture.clib.widget.recyclerview.divider.LinearDivider;

import java.util.ArrayList;

import butterknife.BindView;

public class SampleListActivity extends BaseSampleToolbarSupportActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_sample_list;
    }

    @Override
    public void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        recyclerView.addItemDecoration(new LinearDivider(mContext, LinearLayoutManager.VERTICAL,
                10, R.color.red));
    }

    @Override
    public void initData() {

        ArrayList<String> list = new ArrayList<>();
        list.add("sample-01");
        list.add("sample-01");
        list.add("sample-01");
        list.add("sample-01");
        list.add("sample-01");
        list.add("sample-01");
        list.add("sample-01");
        list.add("sample-01");
        list.add("sample-01");
        list.add("sample-01");
        list.add("sample-01");
        recyclerView.setAdapter(new BaseQuickAdapter<String, BaseViewHolder>(R.layout
                .item_list_sample, list) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.textview, item);
            }
        });
    }

    @Override
    public void refreshUi() {

    }

    @Override
    public void release() {

    }
}
