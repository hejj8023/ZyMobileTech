package com.example.sash.ui.activity;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhiyangstudio.commonlib.mvp.presenter.BasePresenter;
import com.zhiyangstudio.commonlib.refreshsupport.extsupport.BaseMVPToolbarSupportSRListActivity;

/**
 * Created by example on 2018/5/23.
 */

public class SampleWifiActivity extends BaseMVPToolbarSupportSRListActivity {
    @Override
    protected String getCurrentTitle() {
        return "Wifi管理";
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void loadRemoteData() {

    }

    @Override
    protected void initPageNumb() {

    }

    @Override
    protected BaseQuickAdapter getListAdapter() {
        return null;
    }
}
