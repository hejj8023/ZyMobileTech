package com.example.sa.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.algorithm.utils.LoggerUtils;
import com.example.common.corel.BaseActivity;
import com.example.recyclerview.SampleItemDecoration;
import com.example.sa.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by example on 2018/3/2.
 */

public class TestSpeechActivity extends BaseActivity {

    private static final String CATEGORY_SAMPLE_CODE = "com.example.sa.intent.category.SAMPLE_CODE";
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private SpeechListAdapter listAdapter;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_test_speech;
    }

    @Override
    protected void initView() {
        setTitle("识别服务");
        recyclerView.addItemDecoration(new SampleItemDecoration());
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void initData() {
        List<Map<String, Object>> mData = getData();
        listAdapter = new SpeechListAdapter(mContext, mData);
        listAdapter.setOnItemClick(intent -> {
            startActivity(intent);
        });

        recyclerView.setAdapter(listAdapter);


    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> myData = new ArrayList<>();
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(CATEGORY_SAMPLE_CODE);

        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, 0);

        if (list == null) {
            return myData;
        }

        int len = list.size();

        ResolveInfo info;
        for (int i = 0; i < len; i++) {
            info = list.get(i);
            if (!getPackageName().equalsIgnoreCase(info.activityInfo.packageName)) {
                continue;
            }

            CharSequence labelSeq = info.loadLabel(packageManager);
            CharSequence desc = null;
            if (info.activityInfo.descriptionRes != 0) {
                desc = packageManager.getText(info.activityInfo.packageName, info.activityInfo
                        .descriptionRes, null);
                LoggerUtils.loge(this, "desc = " + desc);
            }

            String label = labelSeq != null ? labelSeq.toString() : info.activityInfo.name;
            addItem(myData, label, activityIntent(info.activityInfo.applicationInfo.packageName,
                    info.activityInfo.name), desc);
        }

        return myData;
    }

    private void addItem(List<Map<String, Object>> list, String label, Intent intent, CharSequence desc) {
        Map<String, Object> temp = new HashMap<>();
        temp.put("title", label);
        if (desc != null) {
            temp.put("description", desc.toString());
        }
        temp.put("intent", intent);
        list.add(temp);
    }

    private Intent activityIntent(String packageName, String name) {
        Intent intent = new Intent();
        intent.setClassName(packageName, name);
        return intent;
    }

    @Override
    protected PermissionListener getPermissonCallBack() {
        return null;
    }
}
