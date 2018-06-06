package com.example.vrec.activity;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;

import com.example.vrec.CommonUtil;
import com.example.vrec.R;
import com.zysdk.vulture.clib.corel.BaseActivity;
import com.zysdk.vulture.clib.corel.BaseToolbarSupportActivity;
import com.zysdk.vulture.clib.utils.IntentUtils;

import butterknife.OnClick;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO;

/**
 * Created by example on 2018/6/4.
 */

public class VideoRecordActivity extends BaseToolbarSupportActivity {
    private static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 10;

    @Override
    protected int getContentLayoutId() {
        return R.layout.actiivty_video_record;
    }

    @OnClick({R.id.btn_opt_camera2, R.id.btn_opt_camera, R.id.btn_opt_camera_intent})
    public void onViewClick(View view) {
        Class<? extends BaseActivity> targetCls = null;
        switch (view.getId()) {
            case R.id.btn_opt_camera:
                targetCls = TextureViewVideoRecordActivity.class;
                break;
            case R.id.btn_opt_camera2:
                targetCls = SurfaceViewVideoRecordActivity.class;
                break;
            case R.id.btn_opt_camera_intent:
                //create new Intent
                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

                Uri fileUri = CommonUtil.getOutputMediaFileUri(MEDIA_TYPE_VIDEO);  // create a
                // file to save the video
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);  // set the image file name
                intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1); // set the video image
                // quality to high
                // start the Video Capture Intent
                startActivityForResult(intent, CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE);
                break;
        }
        if (targetCls != null)
            IntentUtils.forward(targetCls);
    }
}
