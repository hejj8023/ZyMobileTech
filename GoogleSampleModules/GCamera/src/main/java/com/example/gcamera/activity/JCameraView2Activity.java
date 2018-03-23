package com.example.gcamera.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Environment;
import android.view.WindowManager;

import com.cjt2325.cameralibrary.JCameraView;
import com.cjt2325.cameralibrary.listener.ErrorListener;
import com.cjt2325.cameralibrary.listener.JCameraListener;
import com.example.common.corel.BaseActivity;
import com.example.gcamera.CameraViewActivity;
import com.example.gcamera.R;
import com.example.utils.LoggerUtils;

import java.io.File;

import butterknife.BindView;

/**
 * Created by zzg on 2018/3/22.
 */

public class JCameraView2Activity extends CameraViewActivity {
    @BindView(R.id.jcameraview)
    JCameraView jCameraView;

    @Override
    protected int getContentViewId() {
        return R.layout.layout_jcamera_view2;
    }

    @Override
    protected void initView() {
        super.initView();
        setTitle("JCameraView2");

        //设置视频保存路径
        jCameraView.setSaveVideoPath(Environment.getExternalStorageDirectory().getPath() + File.separator + "JCamera");
        jCameraView.setFeatures(JCameraView.BUTTON_STATE_ONLY_CAPTURE);
        jCameraView.setTip("JCameraView Tip");
        jCameraView.setMediaQuality(JCameraView.MEDIA_QUALITY_MIDDLE);
        jCameraView.setErrorLisenter(new ErrorListener() {
            @Override
            public void onError() {
                LoggerUtils.loge(JCameraView2Activity.this, "onError");
            }

            @Override
            public void AudioPermissionError() {
                LoggerUtils.loge(JCameraView2Activity.this, "AudioPermissionError");
            }
        });
        jCameraView.setJCameraLisenter(new JCameraListener() {
            @Override
            public void captureSuccess(Bitmap bitmap) {
                LoggerUtils.loge(JCameraView2Activity.this, "captureSuccess bm width = " + bitmap.getWidth() + ", bm height = " + bitmap.getHeight());
            }

            @Override
            public void recordSuccess(String url, Bitmap firstFrame) {
                LoggerUtils.loge(JCameraView2Activity.this, "recordSuccess");
            }
        });
        jCameraView.setLeftClickListener(() -> {
            LoggerUtils.loge(JCameraView2Activity.this, "LeftClick");
            JCameraView2Activity.this.finish();
        });
        jCameraView.setRightClickListener(() -> {
            LoggerUtils.loge(JCameraView2Activity.this, "RightClick");
        });
    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (granted)
            jCameraView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        jCameraView.onPause();
    }
}
