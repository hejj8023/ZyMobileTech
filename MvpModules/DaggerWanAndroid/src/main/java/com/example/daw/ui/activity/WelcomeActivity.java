package com.example.daw.ui.activity;

import com.airbnb.lottie.LottieAnimationView;
import com.blankj.utilcode.util.ToastUtils;
import com.example.daw.Const;
import com.example.daw.R;
import com.example.daw.manager.DataManager;
import com.zysdk.vulture.clib.CommonConst;
import com.zysdk.vulture.clib.corel.BaseActivity;
import com.zysdk.vulture.clib.utils.CommonUtils;
import com.zysdk.vulture.clib.utils.IntentUtils;
import com.zysdk.vulture.clib.utils.RxTimerUtils;
import com.zysdk.vulture.clib.utils.UiUtils;

import butterknife.BindView;

/**
 * Created by example on 2018/5/3.
 */

public class WelcomeActivity extends BaseActivity {

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

    private PermissionListener mPermissionListener = new PermissionListener() {
        @Override
        public void onGrant(int code) {
            if (code == CommonConst.PERMISSION.REQ_SDCARD_PERMISSION) {
                goMain();
            }
        }

        @Override
        public void onDeny(int code) {
//            if (code == CommonConst.PERMISSION.REQ_SDCARD_PERMISSION) {
//                showPermissionDenyDialog("SD卡权限");
//            }
        }
    };

    @Override
    public int getContentId() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initView() {

    }

    @Override
    public void addListener() {

    }

    @Override
    public void initData() {
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
                // TODO: 2018/5/4 低版本直接进进入登录界面
                if (!CommonUtils.hasNeedCheckPermission()) {
                    goMain();
                    return;
                }

                // TODO: 2018/5/4 高版本需要先进行权限检查
                checkSDCardPermission(mPermissionListener);
            }
        });
    }

    private void goMain() {
        if (Const.ISENABLE_AUTO_LOGIN &&
                DataManager.getUserBean() != null &&
                DataManager.isLogin()) {
            ToastUtils.showShort(ResourceUtils.getStr(R.string.tip_auto_login));
            IntentUtils.forward(MainActivity.class);
        } else {
            IntentUtils.forward(LoginActivity.class);
        }
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public void refreshUi() {

    }

    @Override
    public void release() {

    }

    @Override
    protected PermissionListener getPermissonCallBack() {
        return mPermissionListener;
    }
}
