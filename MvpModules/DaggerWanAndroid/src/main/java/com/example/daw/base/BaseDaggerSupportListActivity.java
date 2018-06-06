package com.example.daw.base;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.example.daw.DawApp;
import com.example.daw.R;
import com.example.daw.di.component.ActivityComponent;
import com.example.daw.di.component.DaggerActivityComponent;
import com.example.daw.di.module.ActivityModule;
import com.gyf.barlibrary.ImmersionBar;
import com.zysdk.vulture.clib.mvp.BaseAbsListActivity;
import com.zysdk.vulture.clib.mvp.inter.IView;
import com.zysdk.vulture.clib.mvp.presenter.BasePresenter;
import com.zysdk.vulture.clib.utils.UiUtils;

import javax.inject.Inject;

/**
 * Created by zzg on 2018/4/21.
 * 自定义的列表activity,支持toolbar和dagger注解
 */

public abstract class BaseDaggerSupportListActivity<P extends BasePresenter<V>, V extends IView, T> extends BaseAbsListActivity<P, V, T> {

    protected Toolbar toolbar;
    protected LinearLayout containerLayout;
    @Inject
    protected P mT;
    private ImmersionBar mImmersionBar;

    @Override
    public int getContentId() {
        return R.layout.activity_base_wan_android;
    }

    @Override
    public void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        containerLayout = (LinearLayout) findViewById(R.id.frameLayout);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getCurrentTitle());
        // TODO: 2018/4/17 toolbar返回键点击时的操作
        toolbar.setNavigationOnClickListener(v -> {
            finish();
        });

        View listView = UiUtils.inflateView(R.layout.layout_base_recycler_list, containerLayout);
        containerLayout.addView(listView);
        super.initView();
    }

    protected abstract String getCurrentTitle();

    protected ActivityComponent getActivityComponent() {
        ActivityComponent mActivityComponent = DaggerActivityComponent.builder().appComponent(DawApp.getAppComponent())
                .activityModule(new ActivityModule(this))
                .build();
        return mActivityComponent;
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

    @Override
    public void beforeSetContentView() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarColor(R.color._0091ea);
        mImmersionBar.init();
    }

    @Override
    public void beforeSubContentInit() {
        initInject();
        mPresenter = mT;
        attachView();
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
    protected P createPresenter() {
        return null;
    }

    protected abstract void initInject();
}
