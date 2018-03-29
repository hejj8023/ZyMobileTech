package com.example.rcv.ui.activity;

import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.example.common.corel.BaseActivity;
import com.example.rcv.R;
import com.example.rcv.adapter.SortButtonAdapter;
import com.example.rcv.model.ButtonModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import fj.mtsortbutton.lib.Interface.ViewControl;
import fj.mtsortbutton.lib.SoreButton;

/**
 * Created by zzg on 2018/3/29.
 */

public class CategoryFJMtSortButtonActivity extends BaseActivity implements ViewControl {
    @BindView(R.id.soreButton)
    SoreButton soreButton;
    private List<Integer> viewList;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_category_fjmtsortbutton;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void addListener() {
        soreButton.setViewControl(this);
    }

    @Override
    protected void initData() {
        viewList = new ArrayList<>();
        viewList.add(R.layout.layout_viewpage);
        viewList.add(R.layout.layout_viewpage);
        viewList.add(R.layout.layout_viewpage_page_text);
        soreButton.setView(viewList).init();
    }

    @Override
    protected PermissionListener getPermissonCallBack() {
        return null;
    }

    @Override
    public void setView(View view, int type) {
        switch (type) {
            case 0:
                GridView gridView = view.findViewById(R.id.gridView);
                gridView.setAdapter(new SortButtonAdapter(mContext, setData()));
                break;
            case 1:
                GridView gridView2 = view.findViewById(R.id.gridView);
                gridView2.setAdapter(new SortButtonAdapter(mContext, setData2()));
                break;
            case 2:
                TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
                tvTitle.setText("可高度定制，可设置任意layout,并且在回调中获取该layout内的所有控件");
                tvTitle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showShort("点击了该文字");
                    }
                });
                break;
        }
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
        return data;
    }

    private List<ButtonModel> setData2() {
        List<ButtonModel> data = new ArrayList<>();
        ButtonModel buttonModel = new ButtonModel();
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
}
