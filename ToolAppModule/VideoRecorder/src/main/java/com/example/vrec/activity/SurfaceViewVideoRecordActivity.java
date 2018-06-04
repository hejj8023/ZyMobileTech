package com.example.vrec.activity;

import android.content.Context;
import android.graphics.Point;
import android.hardware.Camera;
import android.view.Display;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.Chronometer;
import android.widget.ImageView;

import com.example.vrec.R;
import com.example.vrec.manager.CameraManagerHelper;
import com.zhiyangstudio.commonlib.corel.BaseActivity;
import com.zhiyangstudio.commonlib.corel.BaseToolbarSupportActivity;
import com.zhiyangstudio.commonlib.utils.LoggerUtils;

import java.io.IOException;

import butterknife.BindView;

public class SurfaceViewVideoRecordActivity extends BaseCameraActivity implements SurfaceHolder.Callback {

    @BindView(R.id.record_surfaceView)
    SurfaceView surfaceView;

    @BindView(R.id.record_control)
    ImageView ivStart;
    @BindView(R.id.record_pause)
    ImageView ivPause;
    @BindView(R.id.record_time)
    Chronometer chronometer;
    private SurfaceHolder surfaceViewHolder;

    @Override
    protected int getContentLayoutId() {
        return R.layout.actiivty_camera2_video_record;
    }


    @Override
    public void initView() {
        surfaceViewHolder = surfaceView.getHolder();
        surfaceViewHolder.addCallback(this);
        surfaceViewHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    @Override
    public void addListener() {
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            mCamera.setPreviewDisplay(surfaceViewHolder);
            mCamera.startPreview();
        } catch (IOException e) {
            LoggerUtils.loge("Error setting camera preview: " + e.getMessage());
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        try {
            mCamera.stopPreview();
        } catch (Exception e) {
            // ignore: tried to stop a non-existent preview
        }

        try {
            mCamera.setPreviewDisplay(holder);
            mCamera.startPreview();
        } catch (IOException e) {
            LoggerUtils.loge("Error setting camera preview: " + e.getMessage());
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        releaseMediaRecorder();       // if you are using MediaRecorder, release it first
    }

    private void releaseMediaRecorder() {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

}
