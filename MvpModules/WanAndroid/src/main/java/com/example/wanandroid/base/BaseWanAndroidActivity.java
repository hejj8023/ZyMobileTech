package com.example.wanandroid.base;

import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.example.wanandroid.R;
import com.zysdk.vulture.clib.corel.BaseInternalHandler;
import com.zysdk.vulture.clib.mvp.BaseMVPSupportActivivty;
import com.zysdk.vulture.clib.mvp.inter.IView;
import com.zysdk.vulture.clib.mvp.presenter.BasePresenter;

import butterknife.ButterKnife;

/**
 * Created by example on 2018/4/9.
 */

public abstract class BaseWanAndroidActivity<P extends BasePresenter<V>, V extends IView> extends
        BaseMVPSupportActivivty<P, V> {

    protected BaseInternalHandler mH = new BaseInternalHandler(this) {
        @Override
        protected void processMessage(Message pMessage) {

        }
    };
    protected Toolbar toolbar;

    private LinearLayout containerLayout;


    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        // TODO: 2018/4/10 在这里处理注解无法使用的问题
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        containerLayout = (LinearLayout) findViewById(R.id.frameLayout);
        if (initToolBar()) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(v -> {
                onNavigationClick();
            });
        } else {
            toolbar.setVisibility(View.GONE);
        }

        if (getContentLayoutId() != 0) {
            View contentView = LayoutInflater.from(mContext).inflate(getContentLayoutId(), containerLayout,
                    false);
            containerLayout.addView(contentView);
            // TODO: 2018/4/10 重新绑定view,不重新绑定会无法使用
            unbinder = ButterKnife.bind(this);
        }

    }

    protected abstract boolean initToolBar();

    protected void onNavigationClick() {
        finish();
        release();
    }

    protected abstract int getContentLayoutId();

    @Override
    protected void onDestroy() {
        mH.destory();
        super.onDestroy();
    }


    @Override
    public int getContentId() {
        return R.layout.activity_base_wan_android;
    }

    @Override
    public void addListener() {

    }
}
