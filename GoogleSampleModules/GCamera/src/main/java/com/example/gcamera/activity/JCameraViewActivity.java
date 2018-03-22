package com.example.gcamera.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Toast;

import com.cjt2325.cameralibrary.JCameraView;
import com.cjt2325.cameralibrary.listener.JCameraListener;
import com.example.common.corel.BaseActivity;
import com.example.gcamera.CameraViewActivity;
import com.example.gcamera.MainActivity;
import com.example.gcamera.R;
import com.example.utils.LoggerUtils;

import java.io.File;

import butterknife.BindView;

/**
 * Created by zzg on 2018/3/22.
 */

public class JCameraViewActivity extends CameraViewActivity {
    private final int GET_PERMISSION_REQUEST = 100;

    @BindView(R.id.jcameraview)
    JCameraView jCameraView;

    @Override
    protected int getContentViewId() {
        return R.layout.layout_jcamera_view;
    }

    @Override
    protected void initView() {
        super.initView();
        setTitle("JCameraView");
        // 设置视频保存路径
        jCameraView.setSaveVideoPath(Environment.getExternalStorageDirectory().getPath() + File.separator + "GJCmera");

        jCameraView.setJCameraLisenter(new JCameraListener() {
            @Override
            public void captureSuccess(Bitmap bitmap) {
                LoggerUtils.loge(JCameraViewActivity.this, "captureSuccess bitmap = " + bitmap.getWidth());
            }

            @Override
            public void recordSuccess(String url, Bitmap firstFrame) {
                LoggerUtils.loge(JCameraViewActivity.this, "recordSuccess bitmap = " + firstFrame.getWidth());
            }
        });

        //6.0动态权限获取
        // getPermissions();
    }



    @Override
    protected void onResume() {
        super.onResume();
        if (granted) {
            jCameraView.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        jCameraView.onPause();
    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected PermissionListener getPermissonCallBack() {
        return mPerListener;
    }

    /**
     * 获取权限
     */
    private void getPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                //具有权限
                granted = true;
            } else {
                //不具有获取权限，需要进行权限申请
                ActivityCompat.requestPermissions(JCameraViewActivity.this, new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.CAMERA}, GET_PERMISSION_REQUEST);
                granted = false;
            }
        }
    }

    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == GET_PERMISSION_REQUEST) {
            int size = 0;
            if (grantResults.length >= 1) {
                int writeResult = grantResults[0];
                //读写内存权限
                boolean writeGranted = writeResult == PackageManager.PERMISSION_GRANTED;//读写内存权限
                if (!writeGranted) {
                    size++;
                }
                //录音权限
                int recordPermissionResult = grantResults[1];
                boolean recordPermissionGranted = recordPermissionResult == PackageManager.PERMISSION_GRANTED;
                if (!recordPermissionGranted) {
                    size++;
                }
                //相机权限
                int cameraPermissionResult = grantResults[2];
                boolean cameraPermissionGranted = cameraPermissionResult == PackageManager.PERMISSION_GRANTED;
                if (!cameraPermissionGranted) {
                    size++;
                }
                if (size == 0) {
                    granted = true;
                    jCameraView.onResume();
                } else {
                    Toast.makeText(this, "请到设置-权限管理中开启", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }
}
