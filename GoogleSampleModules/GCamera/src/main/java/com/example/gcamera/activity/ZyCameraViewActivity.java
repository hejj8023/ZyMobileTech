package com.example.gcamera.activity;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.common.corel.BaseActivity;
import com.example.gcamera.R;
import com.example.gcamera.camera.CameraManager;
import com.example.gcamera.camera.ZyCameraContainer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zzg on 2018/3/23.
 */

public class ZyCameraViewActivity extends BaseActivity {
    @BindView(R.id.camera_container)
    ZyCameraContainer cameraContainer;
    private CameraManager mCameraManager;

    @BindView(R.id.tv_flashlight)
    TextView tvFlashLight;
    @BindView(R.id.tv_camera_direction)
    TextView tvCameradirection;
    @BindView(R.id.ib_recentpic)
    TextView ibRecentpic;

    @Override
    protected int getContentViewId() {
        return R.layout.layout_zy_camera_view;
    }

    @Override
    protected void initView() {
        mCameraManager = CameraManager.getInstance(this);
    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void initData() {
        mCameraManager.bindOptionMenuView(tvFlashLight, tvCameradirection);
        cameraContainer.bindActivity(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (cameraContainer != null) {
            cameraContainer.onStart();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (cameraContainer != null) {
            cameraContainer.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCameraManager.unbinding();
        mCameraManager.releaseActivityCamera();
    }

    @Override
    protected PermissionListener getPermissonCallBack() {
        return null;
    }

    @OnClick({R.id.tv_flashlight, R.id.tv_camera_direction})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_flashlight:
                cameraContainer.switchFlashMode();
                break;
            case R.id.tv_camera_direction:
                break;
        }
    }
}
