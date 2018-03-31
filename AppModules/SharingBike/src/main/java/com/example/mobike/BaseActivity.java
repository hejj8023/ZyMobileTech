package com.example.mobike;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.example.utils.LoggerUtils;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by zzg on 2018/3/30.
 */

public abstract class BaseActivity extends SupportActivity implements ICommonLifecycle {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getContentViewId() != 0) {
            preProcess();
            setContentView(getContentViewId());
            LoggerUtils.loge(this, "onCreate");
            initView();
            addListener();
            initData();
        } else {
            throw new IllegalArgumentException("布局id不能为0!");
        }
    }

    protected void preProcess() {

    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        LoggerUtils.loge(this, "setContentView");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LoggerUtils.loge(this, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LoggerUtils.loge(this, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LoggerUtils.loge(this, "onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LoggerUtils.loge(this, "onDestroy");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        LoggerUtils.loge(this, "onSaveInstanceState");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LoggerUtils.loge(this, "onNewIntent");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        LoggerUtils.loge(this, "onRestoreInstanceState");
    }
}
