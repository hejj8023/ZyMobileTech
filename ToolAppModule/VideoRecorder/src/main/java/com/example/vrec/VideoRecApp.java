package com.example.vrec;


import android.content.Context;
import android.support.multidex.MultiDex;

import com.tencent.bugly.crashreport.CrashReport;
import com.zhiyangstudio.commonlib.CommonConst;
import com.zhiyangstudio.commonlib.corel.BaseApp;
import com.zhiyangstudio.commonlib.utils.OkHttpUtils;
import com.zhiyangstudio.commonlib.utils.PreUtils;

/**
 * Created by example on 2018/6/1.
 */

public class VideoRecApp extends BaseApp {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(getApplicationContext(), "181fd73244", true);

        CommonConst.NET_CACHE_DIR_NAME = "agora";
        PreUtils.init("agora_config");
        OkHttpUtils.isSupportDataInterceptor(true);
    }

    @Override
    protected String getLogTag() {
        return "agoraapp";
    }
}
