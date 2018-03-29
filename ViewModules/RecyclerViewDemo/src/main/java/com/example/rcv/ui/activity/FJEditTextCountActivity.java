package com.example.rcv.ui.activity;

import android.view.View;
import android.widget.TextView;

import com.example.common.corel.BaseActivity;
import com.example.rcv.R;
import com.example.utils.LoggerUtils;

import butterknife.BindView;
import butterknife.OnClick;
import fj.edittextcount.lib.FJEditTextCount;

/**
 * Created by zzg on 2018/3/29.
 */

public class FJEditTextCountActivity extends BaseActivity {

    @BindView(R.id.fjEdit)
    FJEditTextCount fjEdit;
    @BindView(R.id.tvText)
    TextView tvText;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_fjedittextcount;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void initData() {
        LoggerUtils.loge(this, "initdata");
    }

    @Override
    protected PermissionListener getPermissonCallBack() {
        return null;
    }

    @OnClick(R.id.btnGetText)
    public void onViewClick(View view) {
        tvText.setText(fjEdit.getText());
    }
}
