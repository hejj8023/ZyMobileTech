package com.example.vrec.activity;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;

import com.example.vrec.CommonUtil;
import com.example.vrec.R;
import com.zhiyangstudio.commonlib.corel.BaseToolbarSupportActivity;

import butterknife.OnClick;

import static com.example.vrec.CommonUtil.MEDIA_TYPE_IMAGE;

/**
 * Created by example on 2018/6/4.
 */

public class CaptureActivity extends BaseToolbarSupportActivity {
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 101;

    @Override
    protected int getContentLayoutId() {
        return R.layout.actiivty_capture;
    }

    @OnClick({R.id.btn_opt_camera_cintent})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_opt_camera_cintent:
                // create Intent to take a picture and return control to the calling application
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                Uri fileUri = CommonUtil.getOutputMediaFileUri(CommonUtil.MEDIA_TYPE_IMAGE); //
                // create a file to save the image
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name
                // start the image capture Intent
                startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
                break;
        }
    }
}
