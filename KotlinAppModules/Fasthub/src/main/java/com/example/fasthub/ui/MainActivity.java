package com.example.fasthub.ui;

import com.zhiyangstudio.commonlib.corel.BaseToolbarSupportActivity;

/**
 * Created by zzg on 2018/5/21.
 */

public class MainActivity extends BaseToolbarSupportActivity {
    @Override
    protected boolean initToolBar() {
        return false;
    }

    @Override
    protected int getToolbarBgColor() {
        return 0;
    }

    @Override
    protected int getContentLayoutId() {
        return 0;
    }
}
