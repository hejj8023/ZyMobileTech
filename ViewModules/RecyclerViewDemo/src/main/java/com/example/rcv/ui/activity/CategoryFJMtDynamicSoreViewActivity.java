package com.example.rcv.ui.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.example.common.corel.BaseActivity;
import com.example.rcv.R;
import com.example.rcv.adapter.SortButtonAdapter;
import com.example.rcv.model.ButtonModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import fj.mtsortbutton.lib.DynamicSoreView;
import fj.mtsortbutton.lib.Interface.IDynamicSore;

/**
 * Created by zzg on 2018/3/29.
 */

public class CategoryFJMtDynamicSoreViewActivity extends BaseActivity implements IDynamicSore {
    @BindView(R.id.dynamicSoreView)
    DynamicSoreView soreView;
    private List<ButtonModel> list;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_category_fjmtdynamicsoreview;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void initData() {
        loadData();
    }

    private void loadData() {
        list = setData();
        soreView.setiDynamicSore(this);
        soreView.setGridView(R.layout.layout_viewpage).init(list);
    }

    @Override
    protected PermissionListener getPermissonCallBack() {
        return null;
    }

    private List<ButtonModel> setData() {
        List<ButtonModel> data = new ArrayList<>();
        ButtonModel buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_1);
        buttonModel.setName("美食");
        data.add(buttonModel);

        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_2);
        buttonModel.setName("电影");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_3);
        buttonModel.setName("酒店");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_4);
        buttonModel.setName("休闲娱乐");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_5);
        buttonModel.setName("外卖");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_6);
        buttonModel.setName("机票/火车票");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_7);
        buttonModel.setName("KTV");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_8);
        buttonModel.setName("周边游");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_9);
        buttonModel.setName("丽人");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_10);
        buttonModel.setName("旅游出行");
        data.add(buttonModel);

        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_11);
        buttonModel.setName("品质酒店");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_12);
        buttonModel.setName("生活服务");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_13);
        buttonModel.setName("足疗按摩");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_14);
        buttonModel.setName("母婴亲子");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_15);
        buttonModel.setName("结婚");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_16);
        buttonModel.setName("景点");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_17);
        buttonModel.setName("温泉");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_18);
        buttonModel.setName("学习培训");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_19);
        buttonModel.setName("洗浴/汗蒸");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_20);
        buttonModel.setName("全部分类");
        data.add(buttonModel);
        return data;
    }

    @Override
    public void setGridView(View view, final int type, List list) {
        List<ButtonModel> buttonModels = list;
        GridView gridView = (GridView) view.findViewById(R.id.gridView);
        soreView.setNumColumns(gridView);
        SortButtonAdapter adapter = new SortButtonAdapter(this, buttonModels);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtils.showShort("第" + type + "页" + position);
            }
        });
    }

    @OnClick(R.id.btn_refresh)
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_refresh:
                loadData();
                break;
        }
    }
}
