package com.example.faceheler;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.zhiyangstudio.commonlib.utils.EmptyUtils;
import com.zhiyangstudio.commonlib.utils.IoUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by example 2018/4/9.
 */

public class RemoteAssetsManager {
    /**
     * 获取指定包名的Context
     */
    public static Context getRemoteContext(Context context, String pak) throws PackageManager
            .NameNotFoundException {
        return context.createPackageContext(pak, Context.CONTEXT_IGNORE_SECURITY |
                Context.CONTEXT_INCLUDE_CODE);
    }

    public static Resources getRemoteResource(Context remoteCtx) {
        Resources remoteRes = remoteCtx.getResources();
        return remoteRes;
    }

    public static AssetManager getRemoteAssetManager(Resources remoteRes) {
        return remoteRes.getAssets();
    }

    /**
     * 获取表情名称
     *
     * @param assetManager
     * @param faceFullName
     * @param jsonFileName
     * @return
     */
    public static String getFaceName(AssetManager assetManager, String faceFullName,
                                     String jsonFileName) {
        // 从json文件中进行比对
        String remoteJson = getRemoteJson(assetManager, jsonFileName);
        String faceName = "";
        try {
            JSONArray jsonArray = new JSONArray(remoteJson);
            if (jsonArray != null && jsonArray.length() > 0) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.optJSONObject(i);
                    if (jsonObject != null) {
                        String facename = jsonObject.getString("facename");
                        String resname = jsonObject.getString("resname");
                        if (EmptyUtils.isNotEmpty(resname) && resname.equalsIgnoreCase(faceFullName)) {
                            if (EmptyUtils.isNotEmpty(facename)) {
                                faceName = facename;
                            }
                        }
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return faceName;
    }

    /**
     * 加载远程assets中的json文件,根目录
     *
     * @param assetManager
     * @param jsonFile
     * @return
     */
    public static String getRemoteJson(AssetManager assetManager, String jsonFile) {
        StringBuilder stringBuilder = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader br = null;
        InputStreamReader inputStreamReader = null;
        try {
            inputStream = assetManager.open(jsonFile);
            if (inputStream != null) {
                inputStreamReader = new InputStreamReader(inputStream);
                br = new BufferedReader(inputStreamReader);
                String line = null;
                while ((line = br.readLine()) != null) {
                    stringBuilder.append(line).append("\r\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IoUtils.close(inputStream);
            IoUtils.close(inputStreamReader);
            IoUtils.close(br);
        }
        return stringBuilder.toString();
    }

    /**
     * 获取表情的bitmap
     *
     * @param assetManager
     * @param faceFullName
     * @param faceDir
     * @return
     */
    public static Bitmap getFace(AssetManager assetManager, String faceFullName, String
            jsonFileName, String faceDir) {
        Bitmap bitmap = Bitmap.createBitmap(5, 5, Bitmap.Config.ARGB_8888);
        InputStream inputStream = null;
        try {
            String[] faces = assetManager.list(faceDir);
            String remoteJson = getRemoteJson(assetManager, jsonFileName);
            JSONArray jsonArray = new JSONArray(remoteJson);
            boolean hasExsitJsonList = false;
            // 校验是否是json列表中
            if (jsonArray != null && jsonArray.length() > 0) {
                List<FaceInfo> faceInfos = new ArrayList<>();
                FaceInfo faceInfo = null;
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.optJSONObject(i);
                    faceInfo = new FaceInfo();
                    if (jsonObject != null) {
                        String facename = jsonObject.getString("facename");
                        faceInfo.setFaceName(facename);
                        String resname = jsonObject.getString("resname");
                        if (EmptyUtils.isNotEmpty(resname) && resname.equalsIgnoreCase(faceFullName)) {
                            hasExsitJsonList = true;
                            faceInfo.setFaceResName(resname);
                        }
                    }
                    faceInfos.add(faceInfo);
                }
            }
            // 校验是否存在真实文件
            boolean hasFileExsit = false;
            for (String faceName : faces) {
                if (EmptyUtils.isNotEmpty(faceName)) {
                    if (faceName.equalsIgnoreCase(faceFullName)) {
                        hasFileExsit = true;
                    }
                }
            }
            // 都存在则返回图片
            if (hasExsitJsonList && hasFileExsit) {
                inputStream = assetManager.open(faceDir + File.separator + faceFullName);
                if (inputStream != null) {
                    bitmap = BitmapFactory.decodeStream(inputStream);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            IoUtils.close(inputStream);
        }
        return bitmap;
    }
}
