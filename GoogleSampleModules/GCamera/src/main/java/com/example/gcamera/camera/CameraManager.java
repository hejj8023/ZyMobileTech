package com.example.gcamera.camera;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.gcamera.R;
import com.example.gcamera.camera.utils.SPConfigUtil;
import com.example.utils.LogListener;
import com.example.utils.LoggerUtils;

import java.util.ArrayList;
import java.util.List;

import static android.os.Build.VERSION_CODES.GINGERBREAD;

/**
 * Created by zzg on 2018/3/23.
 */

public class CameraManager implements ICameraHelper, LogListener {
    private static CameraManager mInstance;
    private final Context mContext;
    private FlashLigthStatus mLightStatus;
    private CameraDirection mFlashDirection;

    private final ICameraHelper mCameraHelper;

    private Camera mStartTakePhotoCamera;
    private Camera mActivityCamera;

    public static final String SP_LIGHT_STATUE = "SP_LIGHT_STATUE";
    public static final String SP_CAMERA_DIRECTION = "SP_CAMERA_DIRECTION";

    public static final int ALLOW_PIC_LEN = 2000;       //最大允许的照片尺寸的长度   宽或者高

    public static List<FlashLigthStatus> mFlashLightNotSupport = new ArrayList<FlashLigthStatus>();

    private TextView m_tvFlashLight, m_tvCameraDireation;  //绑定的闪光灯和前后置镜头切换控件

    public static final int[] RES_DRAWABLE_FLASHLIGHT = {R.drawable.selector_btn_flashlight_auto, R.drawable.selector_btn_flashlight_on, R.drawable.selector_btn_flashlight_off};
    public static final int[] RES_DRAWABLE_CAMERA_DIRECTION = {R.drawable.selector_btn_camera_back, R.drawable.selector_btn_camera_front};

    public static final int[] RES_STRING_FLASHLIGHT = {R.string.topic_camera_flashlight_auto, R.string.topic_camera_flashlight_on, R.string.topic_camera_flashlight_off};
    public static final int[] RES_STRING_CAMERA_DIRECTION = {R.string.topic_camera_back, R.string.topic_camera_front};

    public static final int LEN_PIC = 64;   //图片的边长   px

    private CameraManager(Context context) {
        this.mContext = context;
        if (Build.VERSION.SDK_INT >= GINGERBREAD) {
            mCameraHelper = new CameraHelperGBImpl();
        } else {
            mCameraHelper = new CameraHelperBaseImpl();
        }

        mLightStatus = FlashLigthStatus.valueOf(SPConfigUtil.loadInt(SP_LIGHT_STATUE, FlashLigthStatus.LIGHT_AUTO.ordinal())); //默认 自动
        mFlashDirection = CameraDirection.valueOf(SPConfigUtil.loadInt(SP_CAMERA_DIRECTION, CameraDirection.CAMERA_BACK.ordinal())); //默认后置摄像头
    }

    public static CameraManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (CameraManager.class) {
                if (mInstance == null) {
                    mInstance = new CameraManager(context);
                }
            }
        }

        return mInstance;
    }

    public CameraDirection getCameraDirection() {
        return mFlashDirection;
    }

    @Override
    public int getNumberOfCameras() {
        return 0;
    }

    // 打开指定的相机
    @Override
    public Camera openCameraFacing(int facing) throws Exception {
        Camera camera = mCameraHelper.openCameraFacing(facing);
        mFlashLightNotSupport.clear();
        if (camera != null) {
            List<String> supportFlashModes = camera.getParameters().getSupportedFlashModes();
            if (facing == 0) {
                //某些supportFlashModes  null  不支持
                if (supportFlashModes != null) {
                    if (!supportFlashModes.contains(Camera.Parameters.FLASH_MODE_AUTO)) {
                        mFlashLightNotSupport.add(FlashLigthStatus.LIGHT_AUTO);
                    }
                    if (!supportFlashModes.contains(Camera.Parameters.FLASH_MODE_ON)) {
                        mFlashLightNotSupport.add(FlashLigthStatus.LIGHT_ON);
                    }
                }
            }
        }
        return camera;
    }

    @Override
    public boolean hasCamera(int facing) {
        return false;
    }

    public void setStartTakePhotoCamera(Camera mStartTakePhotoCamera) {
        this.mStartTakePhotoCamera = mStartTakePhotoCamera;
    }

    public void setActivityCamera(Camera mActivityCamera) {
        this.mActivityCamera = mActivityCamera;
    }

    @Override
    public void getCameraInfo(int cameraId, Camera.CameraInfo cameraInfo) {

    }

    /**
     * 绑定闪光灯、摄像头设置控件
     */
    public void bindOptionMenuView(TextView tvFlashLight, TextView tvCameraDireation) {
        m_tvFlashLight = tvFlashLight;
        m_tvCameraDireation = tvCameraDireation;

        //刷新视图
        setLightStatus(getLightStatus());
        setCameraDirection(getCameraDirection());

        //设置监听
        if (m_tvFlashLight != null) {
            m_tvFlashLight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setLightStatus(getLightStatus().next());
                }
            });
        }
    }

    public void setCameraDirection(CameraDirection mFlashDirection) {
        this.mFlashDirection = mFlashDirection;

        // 保留用户设置
        if (m_tvCameraDireation != null) {
            m_tvCameraDireation.setText(RES_STRING_CAMERA_DIRECTION[mFlashDirection.ordinal()]);

            Drawable drawable = mContext.getResources().getDrawable(RES_DRAWABLE_CAMERA_DIRECTION[mFlashDirection.ordinal()]);
            drawable.setBounds(0, 0, LEN_PIC, LEN_PIC);
            m_tvCameraDireation.setCompoundDrawables(drawable, null, null, null);
            //不再
            // 记录相机方向  会导致部分相机 前置摄像头
            SPConfigUtil.save(SP_CAMERA_DIRECTION, mFlashDirection.ordinal() + "");

            m_tvFlashLight.setVisibility(mFlashDirection == CameraDirection.CAMERA_BACK ? View.VISIBLE : View.GONE);
        }
    }

    public void releaseStartTakePhotoCamera() {
        if (mStartTakePhotoCamera != null) {
            try {
                mStartTakePhotoCamera.stopPreview();
                mStartTakePhotoCamera.setPreviewCallback(null);
                mStartTakePhotoCamera.setPreviewCallbackWithBuffer(null);
                mStartTakePhotoCamera.release();
                mStartTakePhotoCamera = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void releaseCamera(Camera camera) {
        if (camera != null) {
            try {
                camera.release();
                camera.stopPreview();
                camera.setPreviewCallback(null);
                camera.setPreviewCallbackWithBuffer(null);
                camera = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 设置合适的预览尺寸
     */
    public void setFitPreSize(Camera camera) {
        Camera.Parameters parameters = camera.getParameters();
        try {
            Camera.Size adapterSize = findFitPreResolution(camera);
            LoggerUtils.loge(this, "PictureSize w = " + adapterSize.width + " , h = " + adapterSize.height);
            parameters.setPictureSize(adapterSize.width, adapterSize.height);
            camera.setParameters(parameters);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回合适的照片尺寸参数
     */
    private Camera.Size findFitPicResolution(Camera camera, float bl) throws Exception {
        Camera.Parameters cameraParameters = camera.getParameters();
        List<Camera.Size> supportedPicResolutions = cameraParameters.getSupportedPictureSizes();

        Camera.Size resultSize = null;
        for (Camera.Size size : supportedPicResolutions) {
            if ((float) size.width / size.height == bl && size.width <= ALLOW_PIC_LEN && size.height <= ALLOW_PIC_LEN) {
                if (resultSize == null) {
                    resultSize = size;
                } else if (size.width > resultSize.width) {
                    resultSize = size;
                }
            }
        }
        if (resultSize == null) {
            return supportedPicResolutions.get(0);
        }
        return resultSize;
    }

    private Camera.Size findFitPreResolution(Camera camera) throws Exception {
        Camera.Parameters cameraParameters = camera.getParameters();
        List<Camera.Size> supportedPicResolutions = cameraParameters.getSupportedPreviewSizes();

        Camera.Size resultSize = null;
        for (Camera.Size size : supportedPicResolutions) {
            if (size.width <= ALLOW_PIC_LEN) {
                if (resultSize == null) {
                    resultSize = size;
                } else if (size.width > resultSize.width) {
                    resultSize = size;
                }
            }
        }
        if (resultSize == null) {
            return supportedPicResolutions.get(0);
        }
        return resultSize;
    }

    public void setLightStatus(FlashLigthStatus mLightStatus) {
        this.mLightStatus = mLightStatus;

        // 保留用户设置
        if (m_tvFlashLight != null) {
            m_tvFlashLight.setText(RES_STRING_FLASHLIGHT[mLightStatus.ordinal()]);

            Drawable drawable = mContext.getResources().getDrawable(RES_DRAWABLE_FLASHLIGHT[mLightStatus.ordinal()]);
            drawable.setBounds(0, 0, LEN_PIC, LEN_PIC);
            m_tvFlashLight.setCompoundDrawables(drawable, null, null, null);

            SPConfigUtil.save(SP_LIGHT_STATUE, mLightStatus.ordinal() + "");
        }
    }

    public FlashLigthStatus getLightStatus() {
        return mLightStatus;
    }

    public void setFitPicSize(Camera camera, float bl) {
        Camera.Parameters parameters = camera.getParameters();

        try {
            Camera.Size adapterSize = findFitPicResolution(camera, bl);
            parameters.setPictureSize(adapterSize.width, adapterSize.height);
            camera.setParameters(parameters);

            LoggerUtils.loge(this, "setFitPicSize:" + adapterSize.width + "*" + adapterSize.height);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void unbinding() {
        m_tvCameraDireation = null;
        m_tvFlashLight = null;
    }

    public void releaseActivityCamera() {
        if (mActivityCamera != null) {
            try {
                mActivityCamera.stopPreview();
                mActivityCamera.setPreviewCallback(null);
                mActivityCamera.setPreviewCallbackWithBuffer(null);
                mActivityCamera.release();
                mActivityCamera = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 前置还是后置摄像头
     */
    public enum CameraDirection {
        CAMERA_BACK, CAMERA_FRONT;

        //不断循环的枚举
        public CameraDirection next() {
            int index = ordinal();
            int len = CameraDirection.values().length;
            return CameraDirection.values()[(index + 1) % len];
        }

        public static CameraDirection valueOf(int index) {
            return CameraDirection.values()[index];
        }
    }

    /**
     * 闪光灯状态
     */
    public enum FlashLigthStatus {
        LIGHT_AUTO, LIGHT_ON, LIGTH_OFF;

        //不断循环的枚举
        public FlashLigthStatus next() {
            int index = ordinal();
            int len = FlashLigthStatus.values().length;
            FlashLigthStatus status = FlashLigthStatus.values()[(index + 1) % len];
            if (!mFlashLightNotSupport.contains(status.name())) {
                return status;
            } else {
                return next();
            }
        }

        public static FlashLigthStatus valueOf(int index) {
            return FlashLigthStatus.values()[index];
        }
    }
}
