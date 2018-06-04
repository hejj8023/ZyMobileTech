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

public class TextureViewVideoRecordActivity extends BaseCameraActivity implements MediaRecorder.OnErrorListener {

    @BindView(R.id.texture_view)
    TextureView textureView;

    @BindView(R.id.btn_record)
    TextView textView;

    private MediaRecorder mediaRecorder;
    private Surface mSurface;
    private File recDir;
    private Point cameraResolution;

    @Override
    protected int getContentLayoutId() {
        return R.layout.actiivty_camera_video_record;
    }

    @Override
    public void initView() {
        RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) textureView.getLayoutParams();
        cameraResolution = CameraManagerHelper.getCameraResolution();
        rlp.width = cameraResolution.x;
        rlp.height = cameraResolution.y;
        textureView.setLayoutParams(rlp);
    }


    @Override
    public void addListener() {
        textureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                mSurface = new Surface(surface);
                LoggerUtils.loge("onSurfaceTextureAvailable");
                try {
                    mCamera.setPreviewTexture(surface);
                    mCamera.startPreview();
                    mCamera.cancelAutoFocus();
                } catch (IOException e) {
                    LoggerUtils.loge("Error setting camera preview: " + e.getMessage());
                }

            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
                LoggerUtils.loge("onSurfaceTextureSizeChanged");
                // stop preview before making changes
                try {
                    mCamera.stopPreview();
                } catch (Exception e) {
                    // ignore: tried to stop a non-existent preview
                }

                // set preview size and make any resize, rotate or
                // reformatting changes here
                // start preview with new settings
                try {
                    mCamera.setPreviewTexture(surface);
                    mCamera.startPreview();
                } catch (Exception e) {
                    LoggerUtils.loge("Error starting camera preview: " + e.getMessage());
                }
            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                LoggerUtils.loge("onSurfaceTextureDestroyed");
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {
            }
        });
    }

    private boolean isRecording = false;

    private boolean prepareVideoRecorder() {
        try {
            mCamera.unlock();
            mediaRecorder = new MediaRecorder();
            mediaRecorder.reset();
            mediaRecorder.setCamera(mCamera);
            mediaRecorder.setOnErrorListener(this);

            //使用SurfaceView预览
            mediaRecorder.setPreviewDisplay(mSurface);

            //1.设置采集声音
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            //设置采集图像
            mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
            //2.设置视频，音频的输出格式 mp4
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
            //3.设置音频的编码格式
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
            //设置图像的编码格式
            mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
            //设置立体声
//        mediaRecorder.setAudioChannels(2);
            //设置最大录像时间 单位：毫秒
//        mediaRecorder.setMaxDuration(60 * 1000);
            //设置最大录制的大小 单位，字节
//        mediaRecorder.setMaxFileSize(1024 * 1024);
            //音频一秒钟包含多少数据位
            CamcorderProfile mProfile = CamcorderProfile.get(CamcorderProfile.QUALITY_480P);
            mediaRecorder.setAudioEncodingBitRate(44100);
            if (mProfile.videoBitRate > 2 * 1024 * 1024)
                mediaRecorder.setVideoEncodingBitRate(2 * 1024 * 1024);
            else
                mediaRecorder.setVideoEncodingBitRate(1024 * 1024);
            mediaRecorder.setVideoFrameRate(mProfile.videoFrameRate);

            //设置选择角度，顺时针方向，因为默认是逆向90度的，这样图像就是正常显示了,这里设置的是观看保存后的视频的角度
            mediaRecorder.setOrientationHint(90);
            //设置录像的分辨率
            mediaRecorder.setVideoSize(cameraResolution.x, cameraResolution.y);

            boolean available = FileUtils.isSdCardAvailable();
            File recDir = null;
            if (available) {
                recDir = new File(Environment.getExternalStorageDirectory(), "testrec");
                if (!recDir.exists()) {
                    recDir.mkdirs();
                }

                File file = new File(recDir, System.currentTimeMillis() + ".mp4");
                if (!file.exists())
                    file.createNewFile();
                mediaRecorder.setOutputFile(file.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
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


    @OnClick({R.id.btn_record})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_record:
                if (isRecording) {
                    // stop recording and release camera
                    mediaRecorder.stop();  // stop the recording
                    releaseMediaRecorder(); // release the MediaRecorder object
                    mCamera.lock();         // take camera access back from MediaRecorder

                    // inform the user that recording has stopped
                    setCaptureButtonText("Capture");
                    isRecording = false;
                } else {
                    // initialize video camera
                    if (prepareVideoRecorder()) {
                        // Camera is available and unlocked, MediaRecorder is prepared,
                        // now you can start recording
                        mediaRecorder.start();

                        // inform the user that recording has started
                        setCaptureButtonText("Stop");
                        isRecording = true;
                    } else {
                        // prepare didn't work, release the camera
                        releaseMediaRecorder();
                        // inform user
                    }
                }
                break;
        }
    }

    private void setCaptureButtonText(String tst) {
        textView.setText(tst);
    }

    @Override
    public void onError(MediaRecorder mr, int what, int extra) {
        LoggerUtils.loge("media error = " + what + " , extra = " + extra);
    }
}
