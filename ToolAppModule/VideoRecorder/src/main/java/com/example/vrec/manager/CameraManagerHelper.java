package com.example.vrec.manager;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.ImageFormat;
import android.graphics.Point;
import android.hardware.Camera;
import android.view.Display;
import android.view.Surface;
import android.view.WindowManager;

import com.zysdk.vulture.clib.utils.LoggerUtils;
import com.zysdk.vulture.clib.utils.UiUtils;

import java.io.IOException;
import java.util.List;
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

    public static int getCurrentCameraId() {
        return currentCameraId;
    }

    public static void setCameraParam(Camera c) {
        try {
            // get Camera parameters
            Camera.Parameters params = c.getParameters();
            previewFormat = params.getPreviewFormat();

            previewFormatString = params.get("preview-format");
            LoggerUtils.loge("Default preview format: " + previewFormat + '/' +
                    previewFormatString);
            WindowManager manager = (WindowManager) UiUtils.getContext().getSystemService(Context
                    .WINDOW_SERVICE);
            Display display = manager.getDefaultDisplay();
            screenResolution = new Point(display.getWidth(), display.getHeight());
            LoggerUtils.loge("Screen resolution: " + screenResolution);
            cameraResolution = getCameraResolution(params, screenResolution);
            LoggerUtils.loge("Camera resolution: " + cameraResolution);

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

            Camera.Size preViewSize = getOptimalSize(params.getSupportedPreviewSizes(),
                    screenResolution.x, screenResolution.y);
            int px = preViewSize.width;
            int py = preViewSize.height;
            LoggerUtils.loge("preViewSize w = : " + px + " , h = " + py);
            if (null != preViewSize) {
                // TODO: 2018/6/6 设置预览大小
                params.setPreviewSize(px, py);
            }

            Camera.Size pictureSize = getOptimalSize(params.getSupportedPictureSizes(),
                    screenResolution.x, screenResolution.y);
            LoggerUtils.loge("pictureSize w = : " + pictureSize.width + " , h = " + pictureSize
                    .height);

            screenResolution = new Point(px, py);
            cameraResolution = screenResolution;
            if (null != pictureSize) {
                // TODO: 2018/6/6 设置图片大小
                params.setPictureSize(px, py);
            }

            List<String> modes = params.getSupportedFocusModes();
            LoggerUtils.loge("modes = " + modes);

            if (modes.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO)) {
                //设置聚焦模式
                LoggerUtils.loge("FocusMode FOCUS_MODE_CONTINUOUS_VIDEO");
                params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);
            } else if (modes.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
                //设置聚焦模式
                LoggerUtils.loge("FocusMode FOCUS_MODE_CONTINUOUS_PICTURE");
                params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
            } else if (modes.contains(Camera.Parameters.FOCUS_MODE_AUTO)) {
                //支持自动聚焦模式
                LoggerUtils.loge("FocusMode FOCUS_MODE_AUTO");
                params.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
            }

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

    private static Point findBestPreviewSizeValue(CharSequence previewSizeValueString, Point
            screenResolution) {
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

    /**
     * 不要多次释放，否则会出现java.lang.RuntimeException: Method called after release()
     */
    public static void releaseCam(Camera camera) {
        LoggerUtils.loge("releaseCam");
        if (camera != null) {
            camera.setPreviewCallback(null);
            camera.cancelAutoFocus();
            camera.stopPreview();
            try {
                camera.setPreviewDisplay(null);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            camera.release();
            camera = null;
        }
    }

    public static void configCamera(Camera camera) {
        Camera.Parameters params = camera.getParameters();
        //设置相机的横竖屏(竖屏需要旋转90°)
        if (UiUtils.getResources(UiUtils.getContext()).getConfiguration().orientation !=
                Configuration.ORIENTATION_LANDSCAPE) {
            params.set("orientation", "portrait");
            camera.setDisplayOrientation(90);
        } else {
            params.set("orientation", "landscape");
            camera.setDisplayOrientation(0);
        }

        Camera.Size preViewSize = getOptimalSize(params.getSupportedPreviewSizes(), 1920, 1080);
        if (null != preViewSize) {
            params.setPreviewSize(preViewSize.width, preViewSize.height);
        }

        Camera.Size pictureSize = getOptimalSize(params.getSupportedPictureSizes(), 1920, 1080);
        if (null != pictureSize) {
            params.setPictureSize(preViewSize.width, preViewSize.height);
        }
        //设置图片格式
        params.setPictureFormat(ImageFormat.JPEG);
        params.setJpegQuality(100);
        params.setJpegThumbnailQuality(100);

        List<String> modes = params.getSupportedFocusModes();
        if (modes.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO)) {
            //设置聚焦模式
            LoggerUtils.loge("FocusMode FOCUS_MODE_CONTINUOUS_VIDEO");
            params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);
        } else if (modes.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
            //设置聚焦模式
            LoggerUtils.loge("FocusMode FOCUS_MODE_CONTINUOUS_PICTURE");
            params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        } else if (modes.contains(Camera.Parameters.FOCUS_MODE_AUTO)) {
            //支持自动聚焦模式
            LoggerUtils.loge("FocusMode FOCUS_MODE_AUTO");
            params.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
        }

        //缩短Recording启动时间
        params.setRecordingHint(true);
        //影像稳定能力
        if (params.isVideoStabilizationSupported()) {
            params.setVideoStabilization(true);
        }
        try {
            camera.setParameters(params);
        } catch (Exception e) {
            e.printStackTrace();
            LoggerUtils.loge("error = " + e.getMessage());
        }
    }

    /**
     * 获取最优尺寸
     */
    public static Camera.Size getOptimalSize(List<Camera.Size> sizes, int w, int h) {
        final double ASPECT_TOLERANCE = 0.1;
        double targetRatio = (double) h / w;
        if (w > h)
            targetRatio = (double) w / h;
        if (sizes == null)
            return null;
        Camera.Size optimalSize = null;
        double minDiff = Double.MAX_VALUE;
        int targetHeight = h;
        for (Camera.Size size : sizes) {
            double ratio = (double) size.width / size.height;
            if (size.height >= size.width)
                ratio = (float) size.height / size.width;
            if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE)
                continue;
            if (Math.abs(size.height - targetHeight) < minDiff) {
                optimalSize = size;
                minDiff = Math.abs(size.height - targetHeight);
            }
        }
        if (optimalSize == null) {
            minDiff = Double.MAX_VALUE;
            for (Camera.Size size : sizes) {
                if (Math.abs(size.height - targetHeight) < minDiff) {
                    optimalSize = size;
                    minDiff = Math.abs(size.height - targetHeight);
                }
            }
        }

        return optimalSize;
    }
}
