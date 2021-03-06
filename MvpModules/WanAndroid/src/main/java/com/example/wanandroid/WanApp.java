package com.example.wanandroid;

import com.google.gson.reflect.TypeToken;
import com.zysdk.vulture.clib.CommonConst;
import com.zysdk.vulture.clib.corel.BaseApp;
import com.zysdk.vulture.clib.utils.EmptyUtils;
import com.zysdk.vulture.clib.utils.GsonUtils;
import com.zysdk.vulture.clib.utils.OkHttpUtils;
import com.zysdk.vulture.clib.utils.PreUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Created by example on 2018/4/9.
 */

public class WanApp extends BaseApp {
    @Override
    public void onCreate() {
        super.onCreate();
        CommonConst.NET_CACHE_DIR_NAME = "wanandroid_cache";
        PreUtils.init("wanandorid_userinfo");
        // TODO: 2018/4/19 收藏和取消收藏的时候是需要cookie的，没有cookie会不成功
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
}
