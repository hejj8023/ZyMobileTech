package com.example.vrec.activity;

import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.blankj.utilcode.util.ToastUtils;
import com.example.vrec.manager.CameraManagerHelper;
import com.zysdk.vulture.clib.corel.BaseToolbarSupportActivity;
import com.zysdk.vulture.clib.utils.CommonUtils;
import com.zysdk.vulture.clib.utils.EmptyUtils;
import com.zysdk.vulture.clib.utils.FileUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class BaseCameraActivity extends BaseToolbarSupportActivity {
    protected Camera mCamera;
    protected boolean hasReleaseCam;

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
//        initCamera();
    }

    public void initCamera() {
        mCamera = CameraManagerHelper.getCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    protected void releaseCamera() {

        CameraManagerHelper.releaseCam(mCamera);
        hasReleaseCam = true;
    }

    /**
     * 视频文件路径
     */
    protected String getVideoDir() {
        if (!FileUtils.isSdCardAvailable()) {
            ToastUtils.showShort("SD卡无效请检查SD卡");
            return null;
        }

        String sdCardPath = FileUtils.getSDCardPath();
        if (EmptyUtils.isEmpty(sdCardPath)) {
            ToastUtils.showShort("SD卡无效请检查SD卡-2");
            return null;
        }

        File file = new File(sdCardPath, "TestVideoRec");
        if (!file.exists()) {
            file.mkdirs();
        }

        return file.getAbsolutePath();
    }

    /**
     * 视频文件名称
     */
    protected String getVideoFileName() {
        return "VID_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".mp4";
    }

}
