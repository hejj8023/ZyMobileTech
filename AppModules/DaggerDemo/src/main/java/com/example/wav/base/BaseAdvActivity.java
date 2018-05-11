package com.example.wav.base;

import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.example.wav.R;
import com.gyf.barlibrary.ImmersionBar;
import com.zhiyangstudio.commonlib.corel.BaseInternalHandler;
import com.zhiyangstudio.commonlib.mvp.inter.IView;
import com.zhiyangstudio.commonlib.mvp.presenter.BasePresenter;

import butterknife.ButterKnife;

/**
 * Created by zhiyang on 2018/4/25.
 */

public abstract class BaseAdvActivity<P extends BasePresenter<V>, V extends IView> extends
        BaseDaggerSupportActivity<P, V> {

    protected BaseInternalHandler mH = new BaseInternalHandler(this) {
        @Override
        protected void processMessage(Message pMessage) {

        }
    };

    protected Toolbar toolbar;
    private ImmersionBar mImmersionBar;
    private LinearLayout containerLayout;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        // TODO: 2018/4/10 在这里处理注解无法使用的问题
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        containerLayout = (LinearLayout) findViewById(R.id.frameLayout);
        if (initToolBar()) {
            setSupportActionBar(toolbar);
            if (hasShowHome()) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setHomeButtonEnabled(true);
            }
        } else {
            toolbar.setVisibility(View.GONE);
        }

        if (getContentLayoutId() != 0) {
            View contentView = LayoutInflater.from(mContext).inflate(getContentLayoutId(),
                    containerLayout,
                    false);
            containerLayout.addView(contentView);
            // TODO: 2018/4/10 重新绑定view,不重新绑定会无法使用
            unbinder = ButterKnife.bind(this);
        }

    }

    protected abstract boolean initToolBar();

    protected boolean hasShowHome() {
        return false;
    }

    protected void onNavigationClick() {
        finish();
        release();
    }

    protected abstract int getContentLayoutId();

    @Override
    public int getContentId() {
        return R.layout.activity_base_wan_android;
    }

    @Override
    public void addListener() {

    }

    @Override
    public void beforeSetContentView() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarColor(R.color._0091ea);
        mImmersionBar.init();
    }

    @Override
    protected void onDestroy() {
        mH.destory();
        super.onDestroy();

        //必须调用该方法，防止内存泄漏
        if (mImmersionBar != null) {
            mImmersionBar.destroy();
        }
    }
}
