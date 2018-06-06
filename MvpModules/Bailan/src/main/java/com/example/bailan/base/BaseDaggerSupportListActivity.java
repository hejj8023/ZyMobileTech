package com.example.bailan.base;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.example.bailan.BLApp;
import com.example.bailan.R;
import com.example.bailan.di.component.ActivityComponent;
import com.example.bailan.di.component.DaggerActivityComponent;
import com.example.bailan.di.module.ActivityModule;
import com.gyf.barlibrary.ImmersionBar;
import com.zysdk.vulture.clib.mvp.inter.IView;
import com.zysdk.vulture.clib.mvp.presenter.BasePresenter;
import com.zysdk.vulture.clib.refreshsupport.lmr.BaseAbsListActivity;
import com.zysdk.vulture.clib.utils.UiUtils;

import javax.inject.Inject;

/**
 * Created by zzg on 2018/4/21.
 * 自定义的列表activity,支持toolbar和dagger注解
 */

public abstract class BaseDaggerSupportListActivity<P extends BasePresenter<V>, V extends IView,
        T> extends BaseAbsListActivity<P, V, T> {

    protected Toolbar toolbar;
    protected LinearLayout containerLayout;
    @Inject
    protected P mT;
    private ImmersionBar mImmersionBar;

    @Override
    public int getContentId() {
        return R.layout.layout_toolbar_support_root;
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
        ActivityComponent mActivityComponent = DaggerActivityComponent.builder().appComponent
                (BLApp.getAppComponent())
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
    public void beforeSetContentView() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarColor(R.color.sr_color_primary);
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
    public void beforeSubContentInit() {
        initInject();
        mPresenter = mT;
        attachView();
    }

    @Override
    protected P createPresenter() {
        return null;
    }

    @Override
    public void release() {

    }

    protected abstract void initInject();
}
