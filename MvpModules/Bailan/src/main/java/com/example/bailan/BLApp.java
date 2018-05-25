package com.example.bailan;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.example.bailan.di.component.AppComponent;
import com.example.bailan.di.component.DaggerAppComponent;
import com.example.bailan.di.module.AppModule;
import com.google.gson.reflect.TypeToken;
import com.scwang.smartrefresh.header.DeliveryHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.zhiyangstudio.commonlib.CommonConst;
import com.zhiyangstudio.commonlib.corel.BaseApp;
import com.zhiyangstudio.commonlib.utils.EmptyUtils;
import com.zhiyangstudio.commonlib.utils.GsonUtils;
import com.zhiyangstudio.commonlib.utils.OkHttpUtils;
import com.zhiyangstudio.commonlib.utils.PreUtils;
import com.zhiyangstudio.commonlib.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Created by example on 2018/5/25.
 */

public class BLApp extends BaseApp {
    private static AppComponent mAppComponent;

    // static 代码可以防止内存泄露，全局设置刷新头部及尾部，优先级最低
    static {
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(((context, layout) -> {
            // TODO: 2018/5/9 全局设置主题 颜色
            layout.setPrimaryColorsId(R.color.sr_color_primary, android.R.color.white);
            return new DeliveryHeader(context);
        }));
        SmartRefreshLayout.setDefaultRefreshFooterCreater(((context, layout) -> {
            // TODO: 2018/5/9 全局设置主题 颜色
            return new BallPulseFooter(context).setAnimatingColor(UiUtils.getColor(R.color
                    .sr_color_primary));
        }));
    }

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

        CommonConst.NET_CACHE_DIR_NAME = "bail";
        PreUtils.init("bail_config");
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
        return "bailan";
    }
}
