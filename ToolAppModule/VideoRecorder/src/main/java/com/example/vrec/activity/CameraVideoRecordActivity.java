package com.example.vrec.activity;

import android.content.Context;
import android.graphics.Point;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
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

public class CameraVideoRecordActivity extends BaseToolbarSupportActivity implements MediaRecorder.OnErrorListener {

    private static final Pattern COMMA_PATTERN = Pattern.compile(",");
    private static int numberOfCameras;
    private static int faceBackCameraId;
    private static int faceBackCameraOrientation;
    private static int faceFrontCameraId;
    private static int faceFrontCameraOrientation;
    private static int currentCameraId;

    @BindView(R.id.texture_view)
    TextureView textureView;

    @BindView(R.id.btn_record)
    TextView textView;

    private Camera mCamera;
    private MediaRecorder mediaRecorder;
    private Surface mSurface;
    private int previewFormat;
    private String previewFormatString;
    private Point screenResolution;
    private Point cameraResolution;
    private int displayRotation;
    private File recDir;

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
        return R.layout.actiivty_camera_video_record;
    }

    @Override
    public void beforeSubContentInit() {
        try {
            mCamera = getCameraInstance();
            // get Camera parameters
            Camera.Parameters params = mCamera.getParameters();
            previewFormat = params.getPreviewFormat();

            previewFormatString = params.get("preview-format");
            LoggerUtils.loge("Default preview format: " + previewFormat + '/' + previewFormatString);
            WindowManager manager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            Display display = manager.getDefaultDisplay();
            screenResolution = new Point(display.getWidth(), display.getHeight());
            LoggerUtils.loge("Screen resolution: " + screenResolution);
            cameraResolution = getCameraResolution(params, screenResolution);
            LoggerUtils.loge("Camera resolution: " + screenResolution);
            params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);
            final Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
            Camera.getCameraInfo(currentCameraId, cameraInfo);
            int cameraRotationOffset = cameraInfo.orientation;

            final int rotation = ((WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getRotation();
            int degrees = 0;
            switch (rotation) {
                case Surface.ROTATION_0:
                    degrees = 0;
                    break; // Natural orientation
                case Surface.ROTATION_90:
                    degrees = 90;
                    break; // Landscape left
                case Surface.ROTATION_180:
                    degrees = 180;
                    break;// Upside down
                case Surface.ROTATION_270:
                    degrees = 270;
                    break;// Landscape right
            }

            //根据前置与后置摄像头的不同，设置预览方向，否则会发生预览图像倒过来的情况。
            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                displayRotation = (cameraRotationOffset + degrees) % 360;
                displayRotation = (360 - displayRotation) % 360; // compensate
            } else {
                displayRotation = (cameraRotationOffset - degrees + 360) % 360;
            }
            mCamera.setDisplayOrientation(displayRotation);

            //设置预览大小
            params.setPreviewSize(cameraResolution.x, cameraResolution.y);
            params.setPictureSize(cameraResolution.x, cameraResolution.y);
            mCamera.setParameters(params);
        } catch (Exception e) {
            e.printStackTrace();
            LoggerUtils.loge("error msg = " + e.getMessage());
        }
    }


    @Override
    public void initView() {
        RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) textureView.getLayoutParams();
        rlp.width = cameraResolution.x;
        rlp.height = cameraResolution.y;
        textureView.setLayoutParams(rlp);
    }

    private static Point getCameraResolution(Camera.Parameters parameters, Point screenResolution) {

        String previewSizeValueString = parameters.get("preview-size-values");
        // saw this on Xperia
        if (previewSizeValueString == null) {
            previewSizeValueString = parameters.get("preview-size-value");
        }

        Point cameraResolution = null;

        if (previewSizeValueString != null) {
            LoggerUtils.loge("preview-size-values parameter: " + previewSizeValueString);
            cameraResolution = findBestPreviewSizeValue(previewSizeValueString, screenResolution);
        }

        if (cameraResolution == null) {
            // Ensure that the camera resolution is a multiple of 8, as the screen may not be.
            cameraResolution = new Point(
                    (screenResolution.x >> 3) << 3,
                    (screenResolution.y >> 3) << 3);
        }

        return cameraResolution;
    }


    private static Point findBestPreviewSizeValue(CharSequence previewSizeValueString, Point screenResolution) {
        int bestX = 0;
        int bestY = 0;
        int diff = Integer.MAX_VALUE;
        for (String previewSize : COMMA_PATTERN.split(previewSizeValueString)) {

            previewSize = previewSize.trim();
            int dimPosition = previewSize.indexOf('x');
            if (dimPosition < 0) {
                LoggerUtils.loge("Bad preview-size: " + previewSize);
                continue;
            }

            int newX;
            int newY;
            try {
                newX = Integer.parseInt(previewSize.substring(0, dimPosition));
                newY = Integer.parseInt(previewSize.substring(dimPosition + 1));
            } catch (NumberFormatException nfe) {
                LoggerUtils.loge("Bad preview-size: " + previewSize);
                continue;
            }

            int newDiff = Math.abs(newX - screenResolution.x) + Math.abs(newY - screenResolution.y);
            if (newDiff == 0) {
                bestX = newX;
                bestY = newY;
                break;
            } else if (newDiff < diff) {
                bestX = newX;
                bestY = newY;
                diff = newDiff;
            }

        }

        if (bestX > 0 && bestY > 0) {
            return new Point(bestX, bestY);
        }
        return null;
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
            mediaRecorder.setVideoSize(screenResolution.x, screenResolution.y);

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
        releaseCamera();              // release the camera immediately on pause event
    }

    private void releaseMediaRecorder() {
        if (mediaRecorder != null) {
            mediaRecorder.reset();   // clear recorder configuration
            mediaRecorder.release(); // release the recorder object
            mediaRecorder = null;
            mCamera.lock();           // lock camera for later use
        }
    }

    private void releaseCamera() {
        if (mCamera != null) {
            mCamera.release();        // release the camera for other applications
            mCamera = null;
        }
    }


    /**
     * A safe way to get an instance of the Camera object.
     */
    public static Camera getCameraInstance() {

        //有多少个摄像头
        numberOfCameras = Camera.getNumberOfCameras();
        Camera c = null;

        for (int i = 0; i < numberOfCameras; ++i) {
            final Camera.CameraInfo cameraInfo = new Camera.CameraInfo();

            Camera.getCameraInfo(i, cameraInfo);
            //后置摄像头
            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                faceBackCameraId = i;
                faceBackCameraOrientation = cameraInfo.orientation;
            }
            //前置摄像头
            else if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                faceFrontCameraId = i;
                faceFrontCameraOrientation = cameraInfo.orientation;
            }
        }

        try {
            if (numberOfCameras > 0) {
                currentCameraId = faceBackCameraId;
                c = Camera.open(faceBackCameraId);
            } else {
                currentCameraId = faceFrontCameraId;
                c = Camera.open(faceFrontCameraId);
            }
        } catch (Exception e) {
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
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
