package com.example.fta;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.SectionEntity;
import com.example.common.corel.BaseActivity;
import com.example.fta.bean.FtpFileBean;
import com.example.utils.LoggerUtils;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zzg on 2018/4/1.
 */

public class FtpFileListActivity extends BaseFtpActivity {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_ftp_file_list;
    }

    @Override
    protected void initView() {
        setTitle("文件列表");
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext, LinearLayoutManager.VERTICAL));
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

                        BaseQuickAdapter<FtpFileBean, BaseViewHolder> baseQuickAdapter = new BaseQuickAdapter<FtpFileBean, BaseViewHolder>(R.layout.layout_item_file_list, fileBeanList) {
                            @Override
                            protected void convert(BaseViewHolder helper, FtpFileBean item) {
                                helper.setText(R.id.tv_name, item.getName());
                                helper.setText(R.id.tv_size, item.getSize() + "");
                            }
                        };
                        recyclerView.setAdapter(baseQuickAdapter);
                    }
                }
            }
        }
    }

    @Override
    protected PermissionListener getPermissonCallBack() {
        return null;
    }

    @OnClick({R.id.tv_reback, R.id.tv_confirm})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_reback:
                finish();
                break;
            case R.id.tv_confirm:
                // TODO: 2018/4/1 批量下载图片，使用ftp
                break;
        }
    }
}
