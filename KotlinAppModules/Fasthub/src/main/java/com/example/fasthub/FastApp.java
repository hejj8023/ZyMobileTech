package com.example.fasthub;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.tencent.bugly.crashreport.CrashReport;
import com.zhiyangstudio.commonlib.CommonConst;
import com.zhiyangstudio.commonlib.corel.BaseApp;
import com.zhiyangstudio.commonlib.utils.AesEncryptionUtils;
import com.zhiyangstudio.commonlib.utils.EmptyUtils;
import com.zhiyangstudio.commonlib.utils.GsonUtils;
import com.zhiyangstudio.commonlib.utils.OkHttpUtils;
import com.zhiyangstudio.commonlib.utils.PreUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Created by zzg on 2018/5/21.
 */

public class FastApp extends BaseApp {


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // ui卡顿检测
//        BlockCanary.install(this, new AppContext()).start();
//        // 内存泄露检测
        CrashReport.initCrashReport(this);

        // 用户加密使用的key
        AesEncryptionUtils.AES_KEY = "aes_fasthub";
        //  日志tag
        CommonConst.LOG_TAG = "fasthub";
        CommonConst.NET_CACHE_DIR_NAME = "fasthub_cacahe";
        PreUtils.init("fasthub_config");

        // 设置是否支持数据拦截器
        OkHttpUtils.isSupportDataInterceptor(true);

        // TODO: 2018/4/19 收藏和取消收藏的时候是需要cookie的，没有cookie会不成功
        OkHttpUtils.getCookieJar(new CookieJar() {
            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                String cookieStr = (String) PreUtils.get(url.host(), "");
                if (EmptyUtils.isEmpty(cookieStr)) {
                    // 保存cookie
                    cookieStr = GsonUtils.toJsonStr(cookies);
                    // 持久化
                    PreUtils.put(url.host(), cookieStr);
                }
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                // 获取cookie
                String cookieStr = (String) PreUtils.get(url.host(), "");
                List<Cookie> cookies = GsonUtils.toObjectList(cookieStr, new
                        TypeToken<List<Cookie>>() {
                        });
                return cookies != null ? cookies : new ArrayList<Cookie>();
            }
        });
    }

    @Override
    protected String getLogTag() {
        return "fasthub";
    }

    @Override
    protected boolean isDebugModel() {
        return BuildConfig.DEBUG;
    }
}