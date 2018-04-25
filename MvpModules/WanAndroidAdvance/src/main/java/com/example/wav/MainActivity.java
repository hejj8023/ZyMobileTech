package com.example.wav;

import android.os.Bundle;

import com.gyf.barlibrary.ImmersionBar;
import com.zhiyangstudio.commonlib.corel.BaseActivity;

public class MainActivity extends BaseActivity {

    private ImmersionBar mImmersionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected PermissionListener getPermissonCallBack() {
        return null;
    }

    @Override
    public void beforeSetContentView() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarColor(R.color._0091ea);
        mImmersionBar.fitsSystemWindows(true);
        mImmersionBar.init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //必须调用该方法，防止内存泄漏
        if (mImmersionBar != null) {
            mImmersionBar.destroy();
        }
    }

    @Override
    public int getContentId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

    }

    @Override
    public void addListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void refreshUi() {

    }

    @Override
    public void release() {

    }
}
