package com.example.wav.ui.activity;

import android.view.Menu;
import android.view.MenuItem;

import com.blankj.utilcode.util.ToastUtils;
import com.example.wav.R;
import com.example.wav.base.BaseAdvActivity;
import com.example.wav.mvp.contract.FilterNewContract;
import com.example.wav.mvp.presenter.FilterNewPresenter;

/**
 * Created by example on 2018/4/28.
 */

public class FilterNewActivity extends BaseAdvActivity<FilterNewPresenter,
        FilterNewContract.IFilterNewView> implements FilterNewContract.IFilterNewView {
    @Override
    protected boolean initToolBar() {
        setTitle("筛选");
        return true;
    }

    @Override
    protected boolean hasShowHome() {
        return true;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_filter;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_filter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_filter_confirm:
                ToastUtils.showShort("确定");
                break;
        }
        return true;
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
        getActivityComponent().inject(this);
    }

}
