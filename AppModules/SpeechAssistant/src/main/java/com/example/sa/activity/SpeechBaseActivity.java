package com.example.sa.activity;

import com.example.common.corel.BaseActivity;
import com.example.sa.R;

/**
 * Created by example on 2018/3/2.
 */

public class SpeechBaseActivity extends BaseActivity{
    @Override
    protected int getContentViewId() {
        return R.layout.activity_speech_comm;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected PermissionListener getPermissonCallBack() {
        return null;
    }
}
