package com.example.faceapp;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhiyangstudio.commonlib.corel.BaseActivity;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private BaseQuickAdapter<String, BaseViewHolder> faceAdapter;
    private List<String> faceList;
    private Resources appResource;
    private String packageName;
    private AssetManager assetManager;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected PermissionListener getPermissonCallBack() {
        return null;
    }

    @Override
    protected void initView() {
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext, LinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void initData() {
        try {
            appResource = getResources();
            packageName = getPackageName();
            assetManager = appResource.getAssets();
            String[] faces = assetManager.list("face");
            if (faces != null && faces.length > 0) {
                faceList = Arrays.asList(faces);
                faceAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_face_list, faceList) {
                    @Override
                    protected void convert(BaseViewHolder helper, String item) {
                        String picName = item.substring(0, item.lastIndexOf("."));
                        try {
//                            mContext.getClass().getClassLoader().getResourceAsStream("face/" + item);
                            InputStream inputStream = assetManager.open("face/" + item);
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            if (bitmap != null) {
                                helper.setImageBitmap(R.id.iv_face_icon, bitmap);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        helper.setText(R.id.tv_item_name, picName);
                    }
                };
                faceAdapter.setOnItemClickListener((adapter, view, position) -> {
                    ToastUtils.showShort("点击了" + faceList.get(position) + "表情");
                });
                recyclerView.setAdapter(faceAdapter);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
