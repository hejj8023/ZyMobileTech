package com.example.wav.ui.activity;


import com.example.wav.R;
import com.example.wav.base.BaseDaggerSupportActivity;
import com.example.wav.mvp.contract.SplashContract;
import com.example.wav.mvp.presenter.SplashPresenter;
import com.zhiyangstudio.commonlib.utils.IntentUtils;
import com.zhiyangstudio.commonlib.utils.RxTimerUtils;

/**
 * Created by zhiyang on 2018/4/25.
 */

public class SplashActivity extends BaseDaggerSupportActivity<SplashPresenter, SplashContract.ISplashView> implements SplashContract.ISplashView {

    @Override
    public int getContentId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {

    }

    @Override
    public void addListener() {

    }

    @Override
    public void initData() {
        mPresenter.log("initData");
        RxTimerUtils.timer(2200, new RxTimerUtils.IRxNext() {
            @Override
            public void onNext(long number) {
                IntentUtils.forward(MainActivity.class);
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }

    @Override
    public void refreshUi() {

    }

    @Override
    public void release() {

    }

    @Override
    protected PermissionListener getPermissonCallBack() {
        return null;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
}
