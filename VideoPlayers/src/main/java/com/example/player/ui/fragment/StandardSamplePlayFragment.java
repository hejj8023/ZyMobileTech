package com.example.player.ui.fragment;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.player.R;
import com.example.player.bean.SwitchVideoModel;
import com.example.player.player.SampleVideo;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.zhiyangstudio.commonlib.corel.BaseFragment;
import com.zhiyangstudio.commonlib.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by example on 2018/5/14.
 */

public class StandardSamplePlayFragment extends BaseFragment {
    @BindView(R.id.video_player)
    SampleVideo sampleVideo;
    private OrientationUtils mOrientationUtils;
    private boolean isTransition;

    @Override
    public int getContentId() {
        return R.layout.fragment_standard_sample_play;
    }

    @Override
    public void initView() {
    }

    @Override
    public void addListener() {

    }

    @Override
    public void initData() {
        List<SwitchVideoModel> models = new ArrayList<>();
        String source1 = "http://9890.vod.myqcloud" +
                ".com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4";
        SwitchVideoModel model = new SwitchVideoModel("普通", source1);
        models.add(model);
        source1 = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f30.mp4";
        model = new SwitchVideoModel("普通", source1);
        models.add(model);
        sampleVideo.setUp(models, true, "测试视频");

        //增加封面
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.mipmap.xxx1);
        sampleVideo.setThumbImageView(imageView);

        //增加title
        sampleVideo.getTitleTextView().setVisibility(View.GONE);
        // sampleVideo.setShowPauseCover(true);

        //设置播放速度
        // sampleVideo.setSpeed(2f);

        // 设置返回键
        sampleVideo.getBackButton().setVisibility(View.VISIBLE);

        // 设置旋转
        mOrientationUtils = new OrientationUtils(getActivity(), sampleVideo);
        sampleVideo.getFullscreenButton().setOnClickListener(v -> {
            // TODO: 2018/5/14 如何全屏播放
            // sampleVideo.setIfCurrentIsFullscreen(true);
            mOrientationUtils.resolveByClick();
        });

        sampleVideo.setBottomProgressBarDrawable(getResources().getDrawable(R.drawable
                .video_new_progress));
        sampleVideo.setDialogVolumeProgressBar(getResources().getDrawable(R.drawable
                .video_new_volume_progress_bg));
        sampleVideo.setDialogProgressBar(getResources().getDrawable(R.drawable.video_new_progress));
        sampleVideo.setBottomShowProgressBarDrawable(getResources().getDrawable(R.drawable
                        .video_new_seekbar_progress),
                getResources().getDrawable(R.drawable.video_new_seekbar_thumb));
        sampleVideo.setDialogProgressColor(getResources().getColor(R.color.colorAccent), -11);

        // 是否可以滑动调整
        sampleVideo.setIsTouchWiget(true);
        sampleVideo.getBackButton().setOnClickListener(v -> {
            onBack();
        });
    }

    private void onBack() {
        // 先返回正常状态
        if (mOrientationUtils.getScreenType() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            sampleVideo.getFullscreenButton().performClick();
            return;
        }

        // 释放所有
        sampleVideo.setVideoAllCallBack(null);
        GSYVideoManager.releaseAllVideos();
        if (isTransition && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mActivity.onBackPressed();
        } else {
            UiUtils.postDelayed(new Runnable() {
                @Override
                public void run() {
                        mActivity.finish();
                        mActivity.overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                }
            }, 500);
        }
    }

    @Override
    public void refreshUi() {

    }

    @Override
    public void release() {

    }

    @Override
    public void onPause() {
        super.onPause();
        sampleVideo.onVideoPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        sampleVideo.onVideoResume();
    }

    @Override
    protected void initArguments(Bundle bundle) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mOrientationUtils != null) {
            mOrientationUtils.releaseListener();
        }
    }
}
