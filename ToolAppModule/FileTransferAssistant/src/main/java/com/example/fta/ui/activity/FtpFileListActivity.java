package com.example.fta.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.fta.R;
import com.example.fta.bean.FtpFileBean;
import com.zhiyangstudio.sdklibrary.utils.LoggerUtils;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zzg on 2018/4/1.
 */

public class FtpFileListActivity extends BaseFtpActivity {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private List<FtpFileBean> checkList = new LinkedList<>();

    @Override
    protected int getContentViewId() {
        return R.layout.activity_ftp_file_list;
    }

    @Override
    protected void initView() {
        setTitle("文件列表->批量下载");
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

                        BaseQuickAdapter<FtpFileBean, BaseViewHolder> baseQuickAdapter =
                                new BaseQuickAdapter<FtpFileBean, BaseViewHolder>(R.layout.layout_item_file_list, fileBeanList) {
                                    @Override
                                    protected void convert(BaseViewHolder helper, FtpFileBean item) {
                                        helper.setChecked(R.id.cb_check, item.isChecked());
                                        helper.setText(R.id.tv_name, item.getName());
                                        helper.setText(R.id.tv_size, item.getSize() + "");

                                        helper.setOnClickListener(R.id.ll_item_root, v -> {
                                            item.setChecked(!item.isChecked());
                                            if (item.isChecked()) {
                                                if (!checkList.contains(item)) {
                                                    checkList.add(item);
                                                }
                                            } else {
                                                if (checkList.contains(item)) {
                                                    checkList.remove(item);
                                                }
                                            }
                                            helper.setChecked(R.id.cb_check, item.isChecked());
                                            getData().set(helper.getPosition(), item);
                                        });
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
                ToastUtils.showShort("选择了:" + checkList.size() + "条数据");
                break;
        }
    }
}
