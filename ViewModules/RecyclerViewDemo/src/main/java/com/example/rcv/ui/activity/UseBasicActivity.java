package com.example.rcv.ui.activity;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.rcv.R;
import com.example.rcv.adapter.BasicListAdapter;
import com.example.rcv.divider.GridDivider;
import com.example.rcv.divider.LinearDivider;
import com.example.rcv.ui.activity.BaseRecyclerViewActivity;
import com.example.utils.UiUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by example on 2018/3/29.
 */

public class UseBasicActivity extends BaseRecyclerViewActivity {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.ItemDecoration defaultItemDecoration;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_use_basic;
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
        layoutManager = new LinearLayoutManager(mContext);
        // TODO: 2018/3/29 添加布局管理器
        recyclerView.setLayoutManager(layoutManager);
        // TODO: 2018/3/29 添加系统自带的分割线
        defaultItemDecoration = new DividerItemDecoration(mContext, LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(defaultItemDecoration);
        recyclerView.setAdapter(new BasicListAdapter(dataList));
    }

    @OnClick({R.id.btn_b_type_grid, R.id.btn_b_type_line,
            R.id.btn_di_type_line, R.id.btn_di_type_grid})
    public void onViewClick(View v) {
        recyclerView.removeItemDecoration(defaultItemDecoration);
        switch (v.getId()) {
            case R.id.btn_b_type_grid:
            case R.id.btn_di_type_grid:
                defaultItemDecoration = new GridDivider(mContext, 2, R.drawable
                        .custom_divider_grid);
                layoutManager = new GridLayoutManager(mContext, 3);
                break;
            case R.id.btn_b_type_line:
                layoutManager = new LinearLayoutManager(mContext);
                defaultItemDecoration = new DividerItemDecoration(mContext, LinearLayoutManager.VERTICAL);
                // TODO: 2018/3/29 设置自定义的颜色， DividerItemDecoration只对LinearLayoutManager有效，对于其它的两种，均无效
                DividerItemDecoration decoration = (DividerItemDecoration) defaultItemDecoration;
                decoration.setDrawable(UiUtils.getDrawable(mContext, R.drawable.custom_divider_line));
                break;
            case R.id.btn_di_type_line:
                layoutManager = new LinearLayoutManager(mContext);
//                defaultItemDecoration = new LinearDivider(mContext, LinearLayoutManager.VERTICAL,
//                        5, R.drawable.custom_divider_line);
                defaultItemDecoration = new LinearDivider(mContext, LinearLayoutManager.VERTICAL,
                        2, getResources().getColor(R.color.cadetblue));
                break;
        }
        if (defaultItemDecoration != null) {
            recyclerView.addItemDecoration(defaultItemDecoration);
        }
        recyclerView.setLayoutManager(layoutManager);

    }
}
