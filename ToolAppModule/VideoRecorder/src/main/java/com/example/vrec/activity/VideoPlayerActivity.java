package com.example.vrec.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.example.vrec.R;
import com.example.vrec.widget.MyMediaController;
import com.example.vrec.widget.MyVideoView;
import com.zysdk.vulture.clib.corel.BaseToolbarSupportActivity;
import com.zysdk.vulture.clib.utils.CommonUtils;
import com.zysdk.vulture.clib.utils.EmptyUtils;

import java.io.File;

import butterknife.BindView;

/**
 * 视频播放器
 */
public class VideoPlayerActivity extends BaseToolbarSupportActivity implements MediaPlayer
        .OnPreparedListener, MyMediaController.MediaPlayerControl {

    @BindView(R.id.video_view)
    MyVideoView videoView;
    private MyMediaController controller;
    private File file;
    private int vWidth;
    private int vHeight;

    @Override
    protected boolean initToolBar() {
        return false;
    }

    @Override
    public void beforeCreate() {
        CommonUtils.setFullScreen(this);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_video_player;
    }

    @Override
    public void initView() {
        videoView.setOnPreparedListener(this);
        controller = new MyMediaController(this);
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

            Intent intent = getIntent();
            Bundle extras = intent.getExtras();

            String filePath = "";
            if (extras != null) {
                filePath = extras.getString("videoPath");
            }
            if (EmptyUtils.isEmpty(filePath)) {
                filePath = "/mnt/sdcard/test.mp4";
            }
            file = new File(filePath);
            if (file.exists()) {
                videoView.setVideoURI(Uri.fromFile(file));
            }
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        // TODO: 2018/6/5 修正视频拉伸的问题
        //首先取得video的宽和高
        vWidth = mp.getVideoWidth();
        vHeight = mp.getVideoHeight();

        if (vWidth > screenWidth || vHeight > realScreenHeight) {
            //如果video的宽或者高超出了当前屏幕的大小，则要进行缩放
            float wRatio = (float) vWidth / (float) screenWidth;
            float hRatio = (float) vHeight / (float) screenWidth;

            //选择大的一个进行缩放
            float ratio = Math.max(wRatio, hRatio);

            vWidth = (int) Math.ceil((float) vWidth / ratio);
            vHeight = (int) Math.ceil((float) vHeight / ratio);

            //设置surfaceView的布局参数
            videoView.setLayoutParams(new FrameLayout.LayoutParams(vWidth, vHeight, Gravity
                    .CENTER));
        }
        controller.setMediaPlayer(this);
        controller.setAnchorView((ViewGroup) findViewById(R.id.fl_root_player));
        controller.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        videoView.stopPlayback();
        controller.updatePausePlay();
    }

    @Override
    public void start() {
        if (file.exists()) {
            videoView.start();
        } else {
            ToastUtils.showShort("要播放的视频文件不存在");
        }
    }

    @Override
    public void pause() {
        if (videoView.isPlaying()) {
            videoView.pause();
        }
    }

    @Override
    public int getDuration() {
        return videoView.getDuration();
    }

    @Override
    public int getCurrentPosition() {
        return videoView != null ? videoView.getCurrentPosition() : 0;
    }

    @Override
    public void seekTo(int pos) {
        if (videoView != null)
            videoView.seekTo(pos);
    }

    @Override
    public boolean isPlaying() {
        return videoView.isPlaying();
    }

    @Override
    public int getBufferPercentage() {
        return 0;
    }

    @Override
    public boolean canPause() {
        return videoView.canPause();
    }

    @Override
    public boolean canSeekBackward() {
        return videoView.canSeekBackward();
    }

    @Override
    public boolean canSeekForward() {
        return videoView.canSeekForward();
    }

    @Override
    public boolean isFullScreen() {
        return false;
    }

    @Override
    public void toggleFullScreen() {

    }
}
