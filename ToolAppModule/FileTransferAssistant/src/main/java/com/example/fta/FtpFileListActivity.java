package com.example.fta;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.common.corel.BaseActivity;
import com.example.fta.bean.FtpFileBean;
import com.example.utils.LoggerUtils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zzg on 2018/4/1.
 */

public class FtpFileListActivity extends BaseActivity {
    @Override
    protected int getContentViewId() {
        return R.layout.activity_ftp_file_list;
    }

    @Override
    protected void initView() {
        setTitle("文件列表");
    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                Serializable serializable = extras.getSerializable("list");
                if (serializable != null && serializable instanceof List) {
                    List<FtpFileBean> fileBeanList = (List<FtpFileBean>) serializable;
                    if (fileBeanList != null && fileBeanList.size() > 0) {
                        for (FtpFileBean fileBean : fileBeanList) {
                            LoggerUtils.loge(this, " file -> name = " + fileBean.getName());
                        }
                    }
                }
            }
        }
    }

    @Override
    protected PermissionListener getPermissonCallBack() {
        return null;
    }
}
