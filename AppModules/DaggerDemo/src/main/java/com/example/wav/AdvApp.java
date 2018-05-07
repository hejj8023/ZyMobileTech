package com.example.wav;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.example.wav.di.component.AppComponent;
import com.example.wav.di.component.DaggerAppComponent;
import com.example.wav.di.module.AppModule;
import com.google.gson.reflect.TypeToken;
import com.zhiyangstudio.commonlib.CommonConst;
import com.zhiyangstudio.commonlib.corel.BaseApp;
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
 * Created by zhiyang on 2018/4/25.
 */

public class AdvApp extends BaseApp {

    private static AppComponent mAppComponent;

    public static AppComponent getAppComponent() {
        return mAppComponent;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initComponent();

        CommonConst.NET_CACHE_DIR_NAME = "wanandroid_cache2";
        PreUtils.init("wanandorid_userinfo2");
        OkHttpUtils.isSupportDataInterceptor(true);
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

    private void initComponent() {
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this))
                .build();
    }

    @Override
    protected String getLogTag() {
        return "wanandroidadv";
    }
}
