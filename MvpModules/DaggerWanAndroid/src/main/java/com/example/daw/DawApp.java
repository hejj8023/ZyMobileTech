package com.example.daw;

import com.example.daw.di.component.AppComponent;
import com.example.daw.di.component.DaggerAppComponent;
import com.example.daw.di.module.AppModule;
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
 * Created by example on 2018/5/3.
 */

public class DawApp extends BaseApp {

    private static AppComponent mAppComponent;

    public static AppComponent getAppComponent() {
        return mAppComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initComponent();

        CommonConst.NET_CACHE_DIR_NAME = "dagger_wanandroid_cache";
        PreUtils.init("dagger_wanandorid_userinfo");
        Const.ISENABLE_AUTO_LOGIN = true;

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
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }
}
