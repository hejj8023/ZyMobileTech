package com.example.gcamera.camera;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Camera;
import android.hardware.SensorManager;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.OrientationEventListener;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.FrameLayout;

import com.example.common.corel.BaseApp;
import com.example.utils.LogListener;
import com.example.utils.LoggerUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzg on 2018/3/23.
 */

public class ZYCameraView extends SurfaceView implements SurfaceHolder.Callback, LogListener, ICameraOperation {
    private final Context mContext;
    private CameraManager cameraManager;
    // 0 后置 1 前置
    private CameraManager.CameraDirection mCameraId;
    private CameraOrientationListener orientationListener;
    private Camera mCamera;
    private SensorControler sensorControler;
    private Camera.Parameters parameters;
    private BaseApp app;
    private Activity mActivity;

    private int mDisplayOrientation;
    private int mLayoutOrientation;

    private OnCameraPrepareListener onCameraPrepareListener;
    private SwitchCameraCallBack mSwitchCameraCallBack;
    private Camera.PictureCallback callback;

    public ZYCameraView(Context context) {
        this(context, null);
    }

    public ZYCameraView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ZYCameraView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        cameraManager = CameraManager.getInstance(context);
        mCameraId = cameraManager.getCameraDirection();
        app = BaseApp.getAppInstance();
        setFocusable(true);

        getHolder().addCallback(this);

        sensorControler = SensorControler.getInstance();

        orientationListener = new CameraOrientationListener(mContext);
        orientationListener.enable();
    }

    public void onStart() {
        orientationListener.enable();
    }

    public void onStop() {
        orientationListener.disable();
    }

    public int getPicRotation() {
        return (mDisplayOrientation
                + orientationListener.getRememberedNormalOrientation()
                + mLayoutOrientation
        ) % 360;
    }

    @Override
    public void switchCamera() {

    }

    public void switchFlashMode() {
        turnLight(cameraManager.getLightStatus().next());
    }

    @Override
    public boolean takePicture() {
        return false;
    }

    /**
     * 手动聚焦
     *
     * @param point 触屏坐标
     */
    protected boolean onFocus(Point point, Camera.AutoFocusCallback callback) {
        if (mCamera == null) {
            return false;
        }

        Camera.Parameters parameters = null;
        try {
            parameters = mCamera.getParameters();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        //不支持设置自定义聚焦，则使用自动聚焦，返回

        if (Build.VERSION.SDK_INT >= 14) {

            if (parameters.getMaxNumFocusAreas() <= 0) {
                return focus(callback);
            }

            LoggerUtils.loge(this, "onCameraFocus:" + point.x + "," + point.y);

            List<Camera.Area> areas = new ArrayList<Camera.Area>();
            int left = point.x - 300;
            int top = point.y - 300;
            int right = point.x + 300;
            int bottom = point.y + 300;
            left = left < -1000 ? -1000 : left;
            top = top < -1000 ? -1000 : top;
            right = right > 1000 ? 1000 : right;
            bottom = bottom > 1000 ? 1000 : bottom;
            areas.add(new Camera.Area(new Rect(left, top, right, bottom), 100));
            parameters.setFocusAreas(areas);
            try {
                //本人使用的小米手机在设置聚焦区域的时候经常会出异常，看日志发现是框架层的字符串转int的时候出错了，
                //目测是小米修改了框架层代码导致，在此try掉，对实际聚焦效果没影响
                mCamera.setParameters(parameters);
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                return false;
            }
        }


        return focus(callback);
    }

    private boolean focus(Camera.AutoFocusCallback callback) {
        try {
            mCamera.autoFocus(callback);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void startPreview() {
        if (mCamera != null) {
            try {
                mCamera.startPreview();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void stopPreview() {
        if (mCamera != null) {
            try {
                mCamera.stopPreview();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setPictureCallback(Camera.PictureCallback callback) {
        this.callback = callback;
    }

    /**
     * 当前缩放
     */
    private int mZoom;

    public void setZoom(int zoom) {
        if (mCamera == null) return;
        Camera.Parameters parameters;
        //注意此处为录像模式下的setZoom方式。在Camera.unlock之后，调用getParameters方法会引起android框架底层的异常
        //stackoverflow上看到的解释是由于多线程同时访问Camera导致的冲突，所以在此使用录像前保存的mParameters。
        parameters = mCamera.getParameters();

        if (!parameters.isZoomSupported()) return;
        parameters.setZoom(zoom);
        mCamera.setParameters(parameters);
        mZoom = zoom;
    }

    @Override
    public int getZoom() {
        return 0;
    }

    public boolean isBackCamera() {
        return mCameraId == CameraManager.CameraDirection.CAMERA_BACK;
    }

    public int getMaxZoom() {
        return 0;
    }

    private class CameraOrientationListener extends OrientationEventListener {

        private int mCurrentNormalizedOrientation;
        private int mRememberedNormalOrientation;

        public CameraOrientationListener(Context context) {
            super(context, SensorManager.SENSOR_DELAY_NORMAL);
        }

        @Override
        public void onOrientationChanged(int orientation) {
            if (orientation == ORIENTATION_UNKNOWN) {
                mCurrentNormalizedOrientation = normalize(orientation);
            }
        }

        private int normalize(int degrees) {
            if (degrees > 315 || degrees <= 45) {
                return 0;
            }

            if (degrees > 45 && degrees <= 135) {
                return 90;
            }

            if (degrees > 135 && degrees <= 225) {
                return 180;
            }

            if (degrees > 225 && degrees <= 315) {
                return 270;
            }
            throw new RuntimeException("The physics as we know them are no more. Watch out for anomalies.");
        }

        public void rememberOrientation() {
            mRememberedNormalOrientation = mCurrentNormalizedOrientation;
        }

        public int getRememberedNormalOrientation() {
            return mRememberedNormalOrientation;
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        LoggerUtils.loge(this, "surfaceCreated");
        cameraManager.releaseStartTakePhotoCamera();
        if (null == mCamera) {
            setUpCamera(mCameraId, false);
        }
    }

    /**
     * 设置当前的Camera 并进行参数设置
     */
    private void setUpCamera(CameraManager.CameraDirection cameraId, boolean isSwitchFromFront) {
        int facing = mCameraId.ordinal();
        try {
            mCamera = cameraManager.openCameraFacing(facing);
            // 重置对焦计数
            sensorControler.restFoucs();
        } catch (Exception e) {
            LoggerUtils.loge(this, "相机被禁用 请修改应用权限");
            e.printStackTrace();
        }

        if (mCamera != null) {
            try {
                mCamera.setPreviewDisplay(getHolder());
                initCamera();
            } catch (IOException e) {
                e.printStackTrace();
                LoggerUtils.loge(this, "初始化相机失败,msg = " + e.getMessage());
            }

        }
    }

    /**
     * 初始化相机
     */
    private void initCamera() {
        parameters = mCamera.getParameters();
        parameters.setPictureFormat(PixelFormat.JPEG);

        List<String> focusModes = parameters.getSupportedFocusModes();

        //设置对焦模式
        if (focusModes.contains(Camera.Parameters.FOCUS_MODE_AUTO))
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);

        try {
            mCamera.setParameters(parameters);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //修正surfaceView的宽高
        cameraManager.setFitPreSize(mCamera);

        Camera.Size adapterSize = mCamera.getParameters().getPreviewSize();
        LoggerUtils.loge(this, "camera preview size w = " + adapterSize.width + " , h =" + adapterSize.height);

        // 设置图片的宽高比和预览的一样
        cameraManager.setFitPicSize(mCamera, (float) adapterSize.width / adapterSize.height);
        LoggerUtils.loge(this, "adpterSize Preview-->width:" + adapterSize.width + "  height:" + adapterSize.height);

        adapterSize = mCamera.getParameters().getPictureSize();
        LoggerUtils.loge(this, "adpterSize Picture-->width:" + adapterSize.width + "  height:" + adapterSize.height);

        // 调整控件的布局，防止预览被拉伸
        adjustView(adapterSize);
        determineDisplayOrientation();
        mCamera.startPreview();
        turnLight(cameraManager.getLightStatus());  //设置闪光灯
        cameraManager.setActivityCamera(mCamera);
    }

    public void bindActivity(Activity activity) {
        this.mActivity = activity;
    }

    private void determineDisplayOrientation() {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(mCameraId.ordinal(), cameraInfo);

        int rotation = mActivity.getWindowManager().getDefaultDisplay().getRotation();
        int degrees = 0;

        switch (rotation) {
            case Surface.ROTATION_0: {
                degrees = 0;
                break;
            }
            case Surface.ROTATION_90: {
                degrees = 90;
                break;
            }
            case Surface.ROTATION_180: {
                degrees = 180;
                break;
            }
            case Surface.ROTATION_270: {
                degrees = 270;
                break;
            }
        }

        int displayOrientation;

        // Camera direction
        if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            // Orientation is angle of rotation when facing the camera for
            // the camera image to match the natural orientation of the device
            displayOrientation = (cameraInfo.orientation + degrees) % 360;
            displayOrientation = (360 - displayOrientation) % 360;
        } else {
            displayOrientation = (cameraInfo.orientation - degrees + 360) % 360;
        }

        mDisplayOrientation = (cameraInfo.orientation - degrees + 360) % 360;
        mLayoutOrientation = degrees;

        mCamera.setDisplayOrientation(displayOrientation);
        LoggerUtils.loge(this, "displayOrientation:" + displayOrientation);
    }

    /**
     * 调整SurfaceView的宽高
     */
    private void adjustView(Camera.Size adapterSize) {
        int width = app.getScreenWidth();
        int height = width * adapterSize.width / adapterSize.height;

        //让surfaceView的中心和FrameLayout的中心对齐
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) getLayoutParams();
        params.topMargin = -(height - width) / 2;
        params.width = width;
        params.height = height;
        setLayoutParams(params);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        LoggerUtils.loge(this, "surfaceChanged");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        LoggerUtils.loge(this, "surfaceDestroyed");

        try {
            releaseCamera();

            // 释放相机
            if (holder != null) {
                if (Build.VERSION.SDK_INT >= 14) {
                    holder.getSurface().release();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            LoggerUtils.loge(this, "surfaceDestroyed e msg = " + e.getMessage());
        }


    }

    private void turnLight(CameraManager.FlashLigthStatus ligthStatus) {
        if (CameraManager.mFlashLightNotSupport.contains(ligthStatus)) {
            turnLight(ligthStatus.next());
            return;
        }

        if (mCamera == null || mCamera.getParameters() == null
                || mCamera.getParameters().getSupportedFlashModes() == null) {
            return;
        }
        Camera.Parameters parameters = mCamera.getParameters();
        List<String> supportedModes = mCamera.getParameters().getSupportedFlashModes();

        switch (ligthStatus) {
            case LIGHT_AUTO:
                if (supportedModes.contains(Camera.Parameters.FLASH_MODE_AUTO)) {
                    parameters.setFlashMode(Camera.Parameters.FLASH_MODE_AUTO);
                }
                break;
            case LIGTH_OFF:
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                break;
            case LIGHT_ON:
                if (supportedModes.contains(Camera.Parameters.FLASH_MODE_ON)) {
                    parameters.setFlashMode(Camera.Parameters.FLASH_MODE_ON);
                } else if (supportedModes.contains(Camera.Parameters.FLASH_MODE_AUTO)) {
                    parameters.setFlashMode(Camera.Parameters.FLASH_MODE_AUTO);
                } else if (supportedModes.contains(Camera.Parameters.FLASH_MODE_OFF)) {
                    parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                }
                break;
        }
        mCamera.setParameters(parameters);
        cameraManager.setLightStatus(ligthStatus);
    }

    @Override
    public void releaseCamera() {
        cameraManager.releaseCamera(mCamera);
        mCamera = null;
    }

    public void setOnCameraPrepareListener(OnCameraPrepareListener onCameraPrepareListener) {
        this.onCameraPrepareListener = onCameraPrepareListener;
    }

    public void setSwitchCameraCallBack(SwitchCameraCallBack mSwitchCameraCallBack) {
        this.mSwitchCameraCallBack = mSwitchCameraCallBack;
    }

    public interface OnCameraPrepareListener {
        void onPrepare(CameraManager.CameraDirection cameraDirection);
    }

    public interface SwitchCameraCallBack {
        public void switchCamera(boolean isSwitchFromFront);
    }

}
