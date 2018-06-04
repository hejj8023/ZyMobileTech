package com.example.vrec.activity;

import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.vrec.manager.CameraManagerHelper;
import com.zhiyangstudio.commonlib.corel.BaseToolbarSupportActivity;
import com.zhiyangstudio.commonlib.utils.CommonUtils;

public abstract class BaseCameraActivity extends BaseToolbarSupportActivity {
    protected Camera mCamera;

    @Override
    protected boolean initToolBar() {
        return false;
    }

    @Override
    public void beforeCreate() {
        CommonUtils.setFullScreen(this);
    }

    @Override
    public void beforeSubContentInit() {
        initCamera();
    }

    private void initCamera() {
        mCamera = CameraManagerHelper.getCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseCamera();
    }

    private void releaseCamera() {
        CameraManagerHelper.releaseCam(mCamera);
    }

}
