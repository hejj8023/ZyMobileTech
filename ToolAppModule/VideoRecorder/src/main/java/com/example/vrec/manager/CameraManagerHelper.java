package com.example.vrec.manager;

import android.content.Context;
import android.graphics.Point;
import android.hardware.Camera;
import android.view.Display;
import android.view.Surface;
import android.view.WindowManager;

import com.zhiyangstudio.commonlib.utils.LoggerUtils;
import com.zhiyangstudio.commonlib.utils.UiUtils;

import java.util.regex.Pattern;

public class CameraManagerHelper {

    private static final Pattern COMMA_PATTERN = Pattern.compile(",");

    private static int numberOfCameras;
    private static int currentCameraId;
    private static int previewFormat;
    private static String previewFormatString;
    private static Point screenResolution;
    private static Point cameraResolution;
    private static int displayRotation;
    private static int faceBackCameraId;
    private static int faceBackCameraOrientation;
    private static int faceFrontCameraId;
    private static int faceFrontCameraOrientation;

    public static Camera getCamera() {
        return getCameraInstance();
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

    public static void setCameraParam(Camera c) {
        try {
            // get Camera parameters
            Camera.Parameters params = c.getParameters();
            previewFormat = params.getPreviewFormat();

            previewFormatString = params.get("preview-format");
            LoggerUtils.loge("Default preview format: " + previewFormat + '/' + previewFormatString);
            WindowManager manager = (WindowManager) UiUtils.getContext().getSystemService(Context.WINDOW_SERVICE);
            Display display = manager.getDefaultDisplay();
            screenResolution = new Point(display.getWidth(), display.getHeight());
            LoggerUtils.loge("Screen resolution: " + screenResolution);
            cameraResolution = getCameraResolution(params, screenResolution);
            LoggerUtils.loge("Camera resolution: " + screenResolution);

            final Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
            Camera.getCameraInfo(currentCameraId, cameraInfo);
            int cameraRotationOffset = cameraInfo.orientation;

            final int rotation = display.getRotation();
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
            c.setDisplayOrientation(displayRotation);

            //设置预览大小
//            params.setPreviewSize(cameraResolution.x, cameraResolution.y);
//            params.setPictureSize(cameraResolution.x, cameraResolution.y);

            //设置聚焦模式
            params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);
            //缩短Recording启动时间
            params.setRecordingHint(true);
            //影像稳定能力
            if (params.isVideoStabilizationSupported())
                params.setVideoStabilization(true);

            c.setParameters(params);
        } catch (Exception e) {
            e.printStackTrace();
            LoggerUtils.loge("error msg = " + e.getMessage());
        }
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


    public static Point getCameraResolution() {
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

    public static void releaseCam(Camera camera) {
        if (camera != null) {
            camera.release();        // release the camera for other applications
            camera = null;
        }
    }
}
