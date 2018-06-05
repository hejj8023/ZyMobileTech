package com.example.vrec.activity;

import android.content.Context;
import android.graphics.Point;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.camera2.CameraManager;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.TextureView;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.vrec.CommonUtil;
import com.example.vrec.R;
import com.example.vrec.manager.CameraManagerHelper;
import com.zhiyangstudio.commonlib.corel.BaseActivity;
import com.zhiyangstudio.commonlib.corel.BaseToolbarSupportActivity;
import com.zhiyangstudio.commonlib.utils.CommonUtils;
import com.zhiyangstudio.commonlib.utils.FileUtils;
import com.zhiyangstudio.commonlib.utils.LoggerUtils;
import com.zhiyangstudio.commonlib.utils.UiUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.vrec.CommonUtil.MEDIA_TYPE_VIDEO;

public class TextureViewVideoRecordActivity extends BaseCameraActivity implements MediaRecorder
        .OnErrorListener {

    @BindView(R.id.texture_view)
    TextureView textureView;

    private MediaRecorder mediaRecorder;
    private Surface mSurface;
    private boolean isRecording;

    @Override
    protected int getContentLayoutId() {
        return R.layout.actiivty_camera_video_record;
    }

    @Override
    public void initView() {
    }


    @Override
    public void addListener() {
        textureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                mSurface = new Surface(surface);
                LoggerUtils.loge("onSurfaceTextureAvailable");

                if (mCamera != null) {
                    releaseCamera();
                }

                try {
                    initCamera();

                    if (mCamera == null) {
                        ToastUtils.showShort("未能获取到相机！");
                        return;
                    }

                    mCamera.setPreviewTexture(surface);
                    CameraManagerHelper.setCameraParam(mCamera);
                    mCamera.startPreview();
                } catch (IOException e) {
                    LoggerUtils.loge("Error setting camera preview: " + e.getMessage());
                }
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
                LoggerUtils.loge("onSurfaceTextureSizeChanged");
                if (textureView.getSurfaceTexture() == null) {
                    return;
                }
            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                LoggerUtils.loge("onSurfaceTextureDestroyed");
                releaseCamera();
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseMediaRecorder();       // if you are using MediaRecorder, release it first
    }

    private void releaseMediaRecorder() {
        if (mediaRecorder != null) {
            mediaRecorder.reset();   // clear recorder configuration
            mediaRecorder.release(); // release the recorder object
            mediaRecorder = null;
            mCamera.lock();           // lock camera for later use
        }
    }


    @OnClick({R.id.record_control})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.record_control:
                if (isRecording) {
                    // stop recording and release camera
                    mediaRecorder.stop();  // stop the recording
                    releaseMediaRecorder(); // release the MediaRecorder object
                    mCamera.lock();         // take camera access back from MediaRecorder

                    // inform the user that recording has stopped
                    isRecording = false;
                } else {
                    // initialize video camera
                }
                break;
        }
    }

    @Override
    public void onError(MediaRecorder mr, int what, int extra) {
        LoggerUtils.loge("media error = " + what + " , extra = " + extra);
    }
}
