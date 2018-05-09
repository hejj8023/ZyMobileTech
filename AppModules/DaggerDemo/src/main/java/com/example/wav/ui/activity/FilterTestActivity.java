package com.example.wav.ui.activity;

import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.example.wav.R;
import com.example.wav.base.BaseAdvActivity;
import com.example.wav.sample.MyShouDialogFramgment;
import com.example.wav.sample.MyShouDialogFramgment2;
import com.example.wav.widget.FilterDialogFragment;

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

    @OnClick({R.id.btn_dialog, R.id.btn_popupwindow, R.id.btn_dialog_fragment1, R.id
            .btn_dialog_fragment2})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_dialog:
                FilterDialogFragment mdfa = new FilterDialogFragment();
                FragmentTransaction fta = getSupportFragmentManager().beginTransaction();
                fta.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                mdfa.show(fta, "df");
                break;
            case R.id.btn_popupwindow:
//                FilterPopupWindow popupWindow = new FilterPopupWindow(this);
                break;
            case R.id.btn_dialog_fragment1:
                MyShouDialogFramgment mdf = new MyShouDialogFramgment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                mdf.show(ft, "df");
                break;
            case R.id.btn_dialog_fragment2:
                MyShouDialogFramgment2 mdf2 = new MyShouDialogFramgment2();
                FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
                ft2.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                mdf2.show(ft2, "df");
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
