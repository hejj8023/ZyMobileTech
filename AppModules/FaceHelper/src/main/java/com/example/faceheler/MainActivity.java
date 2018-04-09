package com.example.faceheler;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;

import com.zhiyangstudio.commonlib.corel.BaseActivity;
import com.zhiyangstudio.commonlib.utils.EmptyUtils;

public class MainActivity extends BaseActivity {

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

    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void initData() {
        try {
            Context remoteCtx = RemoteAssetsManager.getRemoteContext(mContext, "com.example.faceapp");
            AssetManager remoteAssetManager = RemoteAssetsManager.getRemoteAssetManager(RemoteAssetsManager.getRemoteResource(remoteCtx));

            Bitmap bitmap = RemoteAssetsManager.getFace(remoteAssetManager, "ee_1.png", "face.json", "face");
            if (bitmap != null) {

            }

            String faceName = RemoteAssetsManager.getFaceName(remoteAssetManager, "ee_1.png", "face.json");
            if (EmptyUtils.isEmpty(faceName)) {

            }
            // 获取指定布局文件
            String packageName = remoteCtx.getPackageName();

            // TODO: 2018/4/9 如下操作会不成功 ，不要尝试使用
//            View mainView = getView(remoteCtx, getResId(remoteRes, "layout", "main", packageName));
//            if (mainView != null) {
//
//            }
            // 获取指定字符串
//            String appExtNameStr = remoteRes.getString(getResId(remoteRes, "string", "app_ext_name", packageName));
//            if (EmptyUtils.isNotEmpty(appExtNameStr)) {
//
//            }

            // 获取指定图片
//            Drawable icon012CoverDrawable = remoteRes.getDrawable(getResId(remoteRes,
//                    "drawable", "icon_012_cover", packageName));
//            if (icon012CoverDrawable != null) {
//
//            }

//            int accent2Color = remoteRes.getColor(getResId(remoteRes, "color", "colorAccent2",
//                    packageName));
//            if (accent2Color != -1) {
//
//            }

            // 获取指定颜色值

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取视图
     *
     * @param ctx
     * @param id
     * @return
     */
    public View getView(Context ctx, int id) {
        return ((LayoutInflater) ctx.getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(id, null);
    }

    /**
     * 获取资源对应的编号
     *
     * @param resources
     * @param name
     * @param type
     * @param pak
     * @return
     */
    public int getResId(Resources resources, String name, String type, String pak) {
        return resources.getIdentifier(name, type, pak);
    }
}
