package com.example.rcv;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.example.common.corel.BaseActivity;
import com.example.rcv.adapter.BasicListAdapter;
import com.example.utils.UiUtils;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by example on 2018/3/29.
 */

public class UseBasicActivity extends BaseActivity {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private DividerItemDecoration defaultItemDecoration;

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
        String[] strs = UiUtils.getStrArrs(mContext, R.array.baic_data_list);
        List<String> dataList = Arrays.asList(strs);
        layoutManager = new LinearLayoutManager(mContext);
        // TODO: 2018/3/29 添加布局管理器
        recyclerView.setLayoutManager(layoutManager);
        // TODO: 2018/3/29 添加系统自带的分割线
        defaultItemDecoration = new DividerItemDecoration(mContext, LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(defaultItemDecoration);
        recyclerView.setAdapter(new BasicListAdapter(dataList));
    }

    @Override
    protected PermissionListener getPermissonCallBack() {
        return null;
    }

    @OnClick({R.id.btn_b_type_grid, R.id.btn_b_type_line, R.id.btn_b_type_stage,
            R.id.btn_di_type_line, R.id.btn_di_type_grid, R.id.btn_di_type_stage})
    public void onViewClick(View v) {
        int orientation = LinearLayoutManager.VERTICAL;
        int dividerResId = R.drawable.custom_divider_line;
        switch (v.getId()) {
            case R.id.btn_b_type_grid:
            case R.id.btn_di_type_grid:
                layoutManager = new GridLayoutManager(mContext, 3);
                orientation = GridLayoutManager.VERTICAL;
                dividerResId = R.drawable.custom_divider_grid;
                break;
            case R.id.btn_b_type_line:
            case R.id.btn_di_type_line:
                layoutManager = new LinearLayoutManager(mContext);
                orientation = LinearLayoutManager.VERTICAL;
                dividerResId = R.drawable.custom_divider_line;
                break;
            case R.id.btn_b_type_stage:
            case R.id.btn_di_type_stage:
                layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
                orientation = StaggeredGridLayoutManager.VERTICAL;
                dividerResId = R.drawable.custom_divider_stagger;
                break;
        }
        recyclerView.setLayoutManager(layoutManager);
        defaultItemDecoration = new DividerItemDecoration(mContext, orientation);
        // TODO: 2018/3/29 设置自定义的颜色， DividerItemDecoration只对LinearLayoutManager有效，对于其它的两种，均无效
        defaultItemDecoration.setDrawable(UiUtils.getDrawable(mContext, dividerResId));
        recyclerView.addItemDecoration(defaultItemDecoration);
    }
}
