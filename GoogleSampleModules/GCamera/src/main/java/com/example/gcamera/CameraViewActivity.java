package com.example.gcamera;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;

import com.example.common.corel.BaseActivity;

/**
 * Created by zzg on 2018/3/22.
 */

public abstract class CameraViewActivity extends BaseActivity {

    protected boolean granted = false;
    protected PermissionListener mPerListener = new PermissionListener() {
        @Override
        public void onGrant(int code) {
            if (REQ_CAMERA_PERMISSION == code) {
                granted = true;
            }
        }

        @Override
        public void onDeny(int code) {

        }
    };

    @Override
    protected void preprocess() {
        // 全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //全屏显示
        if (Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        } else {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(option);
        }
    }

    @Override
    protected void initView() {
        checkCameraPermission(mPerListener);
    }

    @Override
    protected PermissionListener getPermissonCallBack() {
        return mPerListener;
    }
}
