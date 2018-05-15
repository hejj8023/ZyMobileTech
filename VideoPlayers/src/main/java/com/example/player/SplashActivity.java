package com.example.player;

import android.view.View;
import android.view.animation.AlphaAnimation;

import com.zhiyangstudio.commonlib.corel.BaseActivity;

import butterknife.BindView;

/**
 * Created by example on 2018/5/15.
 */

public class SplashActivity extends BaseActivity {

    @BindView(R.id.rl_root)
    View view;

    @Override
    public int getContentId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.5f, 0.8f);
        alphaAnimation.setDuration(3000);
        alphaAnimation.setFillAfter(true);
        view.startAnimation(alphaAnimation);

//        AlphaAnimation alphaAnimation = (AlphaAnimation) AnimationUtils.loadAnimation(this, R
//                .anim.alpha);
//        view.startAnimation(alphaAnimation);
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

    @Override
    protected PermissionListener getPermissonCallBack() {
        return null;
    }
}
