package com.example.wav.ui.activity;


import com.airbnb.lottie.LottieAnimationView;
import com.example.wav.R;
import com.example.wav.base.BaseDaggerSupportActivity;
import com.example.wav.mvp.contract.SplashContract;
import com.example.wav.mvp.presenter.SplashPresenter;
import com.zhiyangstudio.commonlib.utils.IntentUtils;
import com.zhiyangstudio.commonlib.utils.RxTimerUtils;

import butterknife.BindView;

/**
 * Created by zhiyang on 2018/4/25.
 */

public class SplashActivity extends BaseDaggerSupportActivity<SplashPresenter, SplashContract.ISplashView> implements SplashContract.ISplashView {

    @BindView(R.id.one_animation)
    LottieAnimationView mOneAnimationView;

    @BindView(R.id.two_animation)
    LottieAnimationView mTwoAnimationView;

    @BindView(R.id.three_animation)
    LottieAnimationView mThreeAnimationView;

    @BindView(R.id.four_animation)
    LottieAnimationView mFourAnimationView;

    @BindView(R.id.five_animation)
    LottieAnimationView mFiveAnimationView;

    @BindView(R.id.six_animation)
    LottieAnimationView mSixAnimationView;

    @BindView(R.id.seven_animation)
    LottieAnimationView mSevenAnimationView;

    @BindView(R.id.eight_animation)
    LottieAnimationView mEightAnimationView;

    @BindView(R.id.nine_animation)
    LottieAnimationView mNineAnimationView;

    @BindView(R.id.ten_animation)
    LottieAnimationView mTenAnimationView;

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

        // TODO: 2018/4/27 逻辑需要修改成登录完成了就自动登录
        mOneAnimationView.setAnimation("W.json");
        mOneAnimationView.playAnimation();
        mTwoAnimationView.setAnimation("A.json");
        mTwoAnimationView.playAnimation();
        mThreeAnimationView.setAnimation("N.json");
        mThreeAnimationView.playAnimation();
        mFourAnimationView.setAnimation("A.json");
        mFourAnimationView.playAnimation();
        mFiveAnimationView.setAnimation("N.json");
        mFiveAnimationView.playAnimation();
        mSixAnimationView.setAnimation("D.json");
        mSixAnimationView.playAnimation();
        mSevenAnimationView.setAnimation("R.json");
        mSevenAnimationView.playAnimation();
        mEightAnimationView.setAnimation("I.json");
        mEightAnimationView.playAnimation();
        mNineAnimationView.setAnimation("O.json");
        mNineAnimationView.playAnimation();
        mTenAnimationView.setAnimation("D.json");
        mTenAnimationView.playAnimation();

        RxTimerUtils.timer(2200, new RxTimerUtils.IRxNext() {
            @Override
            public void onNext(long number) {
                IntentUtils.forward(LoginActivity.class);
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }

    @Override
    public void refreshUi() {

    }

    @Override
    protected PermissionListener getPermissonCallBack() {
        return null;
    }

    @Override
    public void release() {
        super.release();

        if (mOneAnimationView != null) {
            mOneAnimationView.cancelAnimation();
        }

        if (mTwoAnimationView != null) {
            mTwoAnimationView.cancelAnimation();
        }

        if (mThreeAnimationView != null) {
            mThreeAnimationView.cancelAnimation();
        }

        if (mFourAnimationView != null) {
            mFourAnimationView.cancelAnimation();
        }

        if (mFiveAnimationView != null) {
            mFiveAnimationView.cancelAnimation();
        }

        if (mSixAnimationView != null) {
            mSixAnimationView.cancelAnimation();
        }

        if (mSevenAnimationView != null) {
            mSevenAnimationView.cancelAnimation();
        }

        if (mEightAnimationView != null) {
            mEightAnimationView.cancelAnimation();
        }

        if (mNineAnimationView != null) {
            mNineAnimationView.cancelAnimation();
        }

        if (mTenAnimationView != null) {
            mTenAnimationView.cancelAnimation();
        }
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
}
