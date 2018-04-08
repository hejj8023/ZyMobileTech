package com.example.bmpa;

import android.view.View;

import com.zhiyangstudio.commonlib.corel.BaseActivity;

import butterknife.OnClick;

/**
 * Created by example on 2018/4/8.
 */

public class HomeActivity extends BaseActivity {
    @Override
    protected int getContentViewId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected PermissionListener getPermissonCallBack() {
        return null;
    }

    @OnClick({R.id.btn_pic_crop_scale, R.id.btn_pic_download, R.id.btn_pic_upload})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_pic_crop_scale:
                forward(PicCropScaleActivity.class);
                break;
            case R.id.btn_pic_download:
                forward(PicDownloadActivity.class);
                break;
            case R.id.btn_pic_upload:
                forward(PicUploadActivity.class);
                break;
        }
    }
}
