package com.example.wanandroid.base;

import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.example.wanandroid.R;
import com.example.wanandroid.mvp.contract.LoginContract;
import com.zhiyangstudio.commonlib.corel.BaseInternalHandler;
import com.zhiyangstudio.commonlib.mvp.BasePresenterActivivty;
import com.zhiyangstudio.commonlib.mvp.inter.IView;
import com.zhiyangstudio.commonlib.mvp.presenter.BasePresenter;

/**
 * Created by example on 2018/4/9.
 */

public abstract class BaseWanAndroidActivity<P extends BasePresenter, V extends IView> extends BasePresenterActivivty {

    protected BaseInternalHandler mH = new BaseInternalHandler(this) {
        @Override
        protected void processMessage(Message pMessage) {

        }
    };
    private Toolbar toolbar;
    private FrameLayout containerLayout;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_base_wan_android;
    }

    @Override
    protected void initView() {
        toolbar = findViewById(R.id.toolbar);
        containerLayout = findViewById(R.id.frameLayout);
        if (hasShowToolbar()) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener((v) -> {
                onNavigationClick();
            });
        } else {
            toolbar.setVisibility(View.GONE);
        }
        initContent(getContentLayoutId());
    }

    protected abstract boolean hasShowToolbar();

    protected abstract void onNavigationClick();

    private void initContent(int layoutId) {
        if (layoutId != 0) {
            View contentView = LayoutInflater.from(mContext).inflate(layoutId, containerLayout,
                    false);
            containerLayout.addView(contentView);
            initContentView();
        }
    }

    protected abstract int getContentLayoutId();

    protected abstract void initContentView();

    @Override
    protected void onDestroy() {
        mH.destory();
        super.onDestroy();
    }

}
