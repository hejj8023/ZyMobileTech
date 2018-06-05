package com.example.vrec.activity;

import android.Manifest;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.vrec.R;
import com.zhiyangstudio.commonlib.CommonConst;
import com.zhiyangstudio.commonlib.corel.BaseActivity;
import com.zhiyangstudio.commonlib.corel.BaseToolbarSupportActivity;
import com.zhiyangstudio.commonlib.utils.CommonUtils;
import com.zhiyangstudio.commonlib.utils.IntentUtils;
import com.zhiyangstudio.commonlib.utils.LoggerUtils;

import butterknife.OnClick;

public class MainActivity extends BaseToolbarSupportActivity {

    private boolean hasCameraPermisson;

    public static final int REQ_RECORD_AUDIO = 1012;

    private PermissionListener pListener = new PermissionListener() {
        @Override
        public void onGrant(int code) {
            if (code == CommonConst.PERMISSION.REQ_SDCARD_PERMISSION) {
                checkCameraPermission(pListener);
            } else if (code == CommonConst.PERMISSION.REQ_CAMERA_PERMISSION) {
                checkPermission(Manifest.permission.RECORD_AUDIO, "录音权限", pListener,
                        REQ_RECORD_AUDIO);
                hasCameraPermisson = true;
            } else if (code == REQ_RECORD_AUDIO) {
                LoggerUtils.loge("录音权限申请成功，可以录音了");
            }
        }

        @Override
        public void onDeny(int code) {

        }
    };

    @Override
    public void beforeSubContentInit() {
        if (CommonUtils.hasNeedCheckPermission()) {
            checkSDCardPermission(pListener);
        } else {
            hasCameraPermisson = true;
        }
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @OnClick({R.id.btn_action_capture, R.id.btn_action_record, R.id.btn_action_play})
    public void onViewClick(View view) {
        Class<? extends BaseActivity> targetCls = null;
        switch (view.getId()) {
            case R.id.btn_action_capture:
                targetCls = CaptureActivity.class;
                break;
            case R.id.btn_action_record:
                targetCls = VideoRecordActivity.class;
                break;
            case R.id.btn_action_play:
                targetCls = VideoPlayerActivity.class;
                break;
        }
        if (targetCls != null && hasCameraPermisson)
            IntentUtils.forward(targetCls);
    }

}
