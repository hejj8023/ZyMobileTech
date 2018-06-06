package com.example.vrec.activity;

import android.content.Context;
import android.graphics.Point;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.example.vrec.R;
import com.example.vrec.VideoUtils;
import com.example.vrec.manager.CameraManagerHelper;
import com.zysdk.vulture.clib.corel.BaseActivity;
import com.zysdk.vulture.clib.corel.BaseToolbarSupportActivity;
import com.zysdk.vulture.clib.utils.CommonUtils;
import com.zysdk.vulture.clib.utils.IntentUtils;
import com.zysdk.vulture.clib.utils.LoggerUtils;
import com.zysdk.vulture.clib.utils.ThreadUtils;
import com.zysdk.vulture.clib.utils.UiUtils;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;

public class SurfaceViewVideoRecordActivity extends BaseCameraActivity implements SurfaceHolder
        .Callback, MediaRecorder.OnErrorListener {

    private static final int STATE_IDEL = 0;
    private static final int STATE_RECORDING = 1;
    private static final int STATE_PAUSE = 2;

    @BindView(R.id.record_surfaceView)
    SurfaceView surfaceView;

    @BindView(R.id.record_control)
    ImageView ivStart;
    @BindView(R.id.record_pause)
    ImageView ivPause;
    @BindView(R.id.record_time)
    Chronometer chronometer;
    private SurfaceHolder surfaceViewHolder;
    private int mRecordState;
    private String mCurrentVideoFilePath;
    private MediaRecorder mediaRecorder;
    private CamcorderProfile profile;
    // 录制暂停时间间隔
    private long mPauseTime = 0;
    private String saveVideoPath = "";
    private boolean hasFromBtn;
    private boolean hasReleaseCam;
    private int mMaxDuration = 30 * 1000;

    @Override
    protected int getContentLayoutId() {
        return R.layout.actiivty_camera2_video_record;
    }


    @Override
    public void initView() {
        surfaceViewHolder = surfaceView.getHolder();
        // 设置该组件不会让屏幕自动关闭
        surfaceViewHolder.setKeepScreenOn(true);
        surfaceViewHolder.addCallback(this);
        surfaceViewHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }


    @Override
    public void initData() {
        profile = CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH);
    }

    @Override
    public void addListener() {
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                //SystemClock.elapsedRealtime()系统当前时间
                //chronometer.getBase()记录计时器开始时的时间
                if ((SystemClock.elapsedRealtime() - chronometer.getBase()) > mMaxDuration) {
                    // 停止视频，停止记时
                    LoggerUtils.loge("停止视频，停止记时");
                    UiUtils.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // 正在录制的视频，点击后暂停
                            // 取消自动对焦
                            mCamera.autoFocus(new Camera.AutoFocusCallback() {
                                @Override
                                public void onAutoFocus(boolean success, Camera camera) {
                                    if (success) {
                                        mCamera.cancelAutoFocus();
                                    }

                                }
                            });

                            //停止视频录制
                            stopRecord();
                            //先给Camera加锁后再释放相机
//                            mCamera.lock();
//                            releaseCamera();
                            hasFromBtn = true;

                            refreshControlUI();

                            mRecordState = STATE_IDEL;
                        }
                    }, 1000);
                }

            }
        });
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        LoggerUtils.loge("surfaceCreated");

        if (mCamera != null) {
            releaseCamera();
        }

        try {
            initCamera();

            if (mCamera == null) {
                ToastUtils.showShort("未能获取到相机！");
                return;
            }

            mCamera.setPreviewDisplay(surfaceViewHolder);
            CameraManagerHelper.setCameraParam(mCamera);
            mCamera.startPreview();
        } catch (IOException e) {
            LoggerUtils.loge("Error setting camera preview: " + e.getMessage());
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        LoggerUtils.loge("surfaceChanged");
        if (surfaceViewHolder.getSurface() == null) {
            return;
        }
    }


    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        LoggerUtils.loge("surfaceDestroyed");
        if (!hasFromBtn) {
            releaseCamera();
        }
    }


    @OnClick({R.id.record_control, R.id.record_pause})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.record_control:
                if (mRecordState == STATE_IDEL) {
                    doRecOpt();
                    hasFromBtn = false;
                } else if (mRecordState == STATE_RECORDING) {
                    //停止视频录制
                    stopRecord();
                    //先给Camera加锁后再释放相机
                    mCamera.lock();
                    releaseCamera();
                    hasFromBtn = true;

                    refreshControlUI();

                    if ("".equals(saveVideoPath)) {
                        saveVideoPath = mCurrentVideoFilePath;
                    } else {
                        mergeRecordVideoFile();
                    }

                    mRecordState = STATE_IDEL;

                    // TODO: 2018/6/5 延迟1秒转到播放器（确保视频合并完成后跳转)
                    UiUtils.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            forwardPlayer();
                        }

                    }, 1000);

                } else if (mRecordState == STATE_PAUSE) {
                    forwardPlayer();
                }
                break;
            case R.id.record_pause:
                if (mRecordState == STATE_RECORDING) {
                    // 正在录制的视频，点击后暂停
                    // 取消自动对焦
                    mCamera.autoFocus(new Camera.AutoFocusCallback() {
                        @Override
                        public void onAutoFocus(boolean success, Camera camera) {
                            if (success) {
                                mCamera.cancelAutoFocus();
                            }

                        }
                    });

                    stopRecord();
                    refreshPauseUI();

                    if ("".equals(saveVideoPath)) {
                        saveVideoPath = mCurrentVideoFilePath;
                    } else {
                        mergeRecordVideoFile();
                    }
                    mRecordState = STATE_PAUSE;
                } else if (mRecordState == STATE_PAUSE) {
                    doRecOpt();
                    hasFromBtn = false;
                }
                break;
        }
    }

    private void doRecOpt() {
        if (getVideoDir() == null) {
            return;
        }

        // 视频文件保存路径，configMediaRecorder方法中会设置
        mCurrentVideoFilePath = getVideoDir() + "/" + getVideoFileName();

        // 开始录制视频
        if (!startRecord()) {
            return;
        }

        refreshControlUI();

        mRecordState = STATE_RECORDING;
    }

    private void forwardPlayer() {
        Bundle bundle = new Bundle();
        bundle.putString("videoPath", saveVideoPath);
        IntentUtils.forward(VideoPlayerActivity.class, bundle);
        finish();
    }

    /**
     * 合并视频文件
     */
    private void mergeRecordVideoFile() {
        ThreadUtils.executeBySingleThread(new Runnable() {
            @Override
            public void run() {
                try {
                    LoggerUtils.loge("saveVideoPath = " + saveVideoPath);
                    LoggerUtils.loge("mCurrentVideoFilePath = " + mCurrentVideoFilePath);
                    String[] str = {saveVideoPath, mCurrentVideoFilePath};
                    //将2个视频文件合并到 append.mp4文件下
                    VideoUtils.appendVideo(mContext, getVideoDir() + "/append.mp4", str);
                    File reName = new File(saveVideoPath);
                    File f = new File(getVideoDir() + "/append.mp4");
                    //再将合成的append.mp4视频文件 移动到 saveVideoPath 路径下
                    f.renameTo(reName);
                    if (reName.exists()) {
                        f.delete();
                        new File(mCurrentVideoFilePath).delete();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void refreshPauseUI() {
        if (mRecordState == STATE_RECORDING) {
            ivPause.setImageResource(R.drawable.control_play);

            mPauseTime = SystemClock.elapsedRealtime();
            chronometer.stop();
        } else if (mRecordState == STATE_PAUSE) {
            ivPause.setImageResource(R.drawable.control_pause);

            if (mPauseTime == 0) {
                chronometer.setBase(SystemClock.elapsedRealtime());
            } else {
                chronometer.setBase(SystemClock.elapsedRealtime() - (mPauseTime - chronometer
                        .getBase()));
            }
            chronometer.start();
        }
    }

    private void stopRecord() {
        // 设置后不会崩
        mediaRecorder.setOnErrorListener(null);
        mediaRecorder.setPreviewDisplay(null);
        //停止录制
        mediaRecorder.stop();
        mediaRecorder.reset();
        //释放资源
        mediaRecorder.release();
        mediaRecorder = null;
    }

    private void refreshControlUI() {
        if (mRecordState == STATE_IDEL) {
            // 录像时间计时
            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();

            //1s后才能按停止录制按钮
            ivStart.setImageResource(R.drawable.recordvideo_stop);
            ivStart.setEnabled(false);
            UiUtils.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ivStart.setEnabled(true);
                }
            }, 1000);

            ivPause.setVisibility(View.VISIBLE);
            ivPause.setEnabled(true);
        } else if (mRecordState == STATE_RECORDING) {
            mPauseTime = 0;
            chronometer.stop();

            ivStart.setImageResource(R.drawable.recordvideo_start);
            ivPause.setVisibility(View.GONE);
            ivPause.setEnabled(false);
        }
    }

    private boolean startRecord() {
        if (hasReleaseCam) {
            initCamera();
            CameraManagerHelper.setCameraParam(mCamera);
        }

        hasReleaseCam = false;
        // 录制前必须先解锁camera
        // 解锁camera便于MediaRecorder使用相机
        mCamera.unlock();

        configMediaRecorder();

        try {
            mediaRecorder.prepare();
            mediaRecorder.start();
        } catch (IOException e) {
            e.printStackTrace();
            LoggerUtils.loge("IOException err = " + e.getMessage());
            return false;
        }
        return true;
    }

    private void configMediaRecorder() {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.reset();
        mediaRecorder.setCamera(mCamera);
        mediaRecorder.setOnErrorListener(this);

        // 使用SurfaceView预览
        mediaRecorder.setPreviewDisplay(surfaceViewHolder.getSurface());


        // 1. 设置采集声音-音频源
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        // 设置采集图像-设置视频源
        mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);

        // 设置输出格式和编码格式（针对低于API Level 8版本）否则就用setProfile方法
//        if (Build.VERSION.SDK_INT >= 8) {
//            mediaRecorder.setProfile(profile);
//        } else {
//            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);   //设置输出格式
//            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);   //设置音频的编码格式
//            mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);//设置视频的编码格式
        // 2. 设置视频，音频的输出格式 mp4
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        // 3. 设置音频的编码格式
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        // 设置图像的编码格式
        mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
//        }


        // 音频一秒钟包含多少数据位
//        CamcorderProfile camcorderProfile = CamcorderProfile.get(CamcorderProfile.QUALITY_480P);
        mediaRecorder.setAudioEncodingBitRate(44100);

        if (profile.videoBitRate > 2 * 1024 * 1024) {
//            mediaRecorder.setVideoEncodingBitRate(2 * 1024 * 1024);
            mediaRecorder.setVideoEncodingBitRate(5 * 1024 * 1024);
        } else
            mediaRecorder.setVideoEncodingBitRate(1024 * 1024);

        /**
         * 不要使用setProfile这个方法，这个方法不够灵活。我们可以自定义MediaRecorder的一些设置。
         * 高清视频的设置主要是以下几个方法
         * mediaRecorder.setVideoSize(1920, 1080); // 设置视频大小
         mediaRecorder.setVideoEncodingBitRate(5*1024*1024);
         mediaRecorder.setVideoFrameRate(60); // 设置帧率
         */
        //设置视频的分辨率
//        mediaRecorder.setVideoFrameRate(profile.videoFrameRate);

        // TODO: 2018/6/6 配置尽量都写死，有的设备会取不到profile
        mediaRecorder.setVideoFrameRate(60);

        // 设置录像的分辨率
//        mediaRecorder.setVideoSize(profile.videoFrameWidth, profile.videoFrameHeight);
        Point cameraResolution = CameraManagerHelper.getCameraResolution();
        mediaRecorder.setVideoSize(cameraResolution.x, cameraResolution.y);

        // 设置选择角度，顺时针方向，因为默认为逆向90度的，这样图像就可以正常显示了
        int currentCameraId = CameraManagerHelper.getCurrentCameraId();
        if (currentCameraId == Camera.CameraInfo.CAMERA_FACING_BACK) {
            mediaRecorder.setOrientationHint(90);
        } else if (currentCameraId == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            mediaRecorder.setOrientationHint(270);
        }
        mediaRecorder.setMaxDuration(mMaxDuration);  //设置最大的录制时间

        // 设置录像视频输出地址
        mediaRecorder.setOutputFile(mCurrentVideoFilePath);
    }

    @Override
    public void onError(MediaRecorder mr, int what, int extra) {
        LoggerUtils.loge("Record onError = " + what);
        try {
            if (mr != null) {
                mr.reset();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
