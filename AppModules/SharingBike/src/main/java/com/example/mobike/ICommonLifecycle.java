package com.example.mobike;

import com.example.utils.LogListener;

/**
 * Created by zzg on 2018/3/30.
 */

public interface ICommonLifecycle extends LogListener {
    int getContentViewId();

    void initView();

    void addListener();

    void initData();
}
