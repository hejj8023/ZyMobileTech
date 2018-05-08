package com.example.wav.ui.activity;

import android.view.View;

import com.example.wav.R;
import com.example.wav.base.BaseAdvActivity;
import com.example.wav.ui.extui.FilterDialog;

import butterknife.OnClick;

/**
 * Created by example on 2018/5/8.
 */

public class FilterTestActivity extends BaseAdvActivity {
    @Override
    protected boolean initToolBar() {
        setTitle("筛选测试");
        return true;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_filter_test;
    }

    @OnClick({R.id.btn_dialog, R.id.btn_popupwindow})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_dialog:
                FilterDialog filterDialog = new FilterDialog(this);
                filterDialog.show();


                break;
            case R.id.btn_popupwindow:
//                FilterPopupWindow popupWindow = new FilterPopupWindow(this);
                break;
        }
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void refreshUi() {

    }

    @Override
    protected void initInject() {

    }
}
