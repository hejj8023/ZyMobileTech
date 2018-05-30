package com.example.xiaowusong;

import android.content.Context;
import android.content.Intent;

import com.example.xiaowusong.utils.CommonUtil;
import com.google.gson.reflect.TypeToken;
import com.tencent.android.tpush.XGNotifaction;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.android.tpush.XGPushNotifactionCallback;
import com.tencent.android.tpush.rpc.XGRemoteService;
import com.tencent.android.tpush.service.XGPushServiceV3;
import com.tencent.bugly.crashreport.CrashReport;
import com.zhiyangstudio.commonlib.CommonConst;
import com.zhiyangstudio.commonlib.corel.BaseApp;
import com.zhiyangstudio.commonlib.utils.AesEncryptionUtils;
import com.zhiyangstudio.commonlib.utils.EmptyUtils;
import com.zhiyangstudio.commonlib.utils.GsonUtils;
import com.zhiyangstudio.commonlib.utils.LoggerUtils;
import com.zhiyangstudio.commonlib.utils.OkHttpUtils;
import com.zhiyangstudio.commonlib.utils.PreUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Created by zzg on 2018/4/20.
 */

public class WSApp extends BaseApp {

//    private RefWatcher refWatcher;

//    public static RefWatcher getRefWatcher(Context context) {
//        WSApp application = (WSApp) context.getApplicationContext();
//        return application.refWatcher;
//    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // ui卡顿检测
//        BlockCanary.install(this, new AppContext()).start();
//        // 内存泄露检测
//        refWatcher = LeakCanary.install(this);
//        CrashReport.initCrashReport(this, "07db95c8c0", true);
        CrashReport.initCrashReport(getApplicationContext());

        // 用户加密使用的key
        AesEncryptionUtils.AES_KEY = "aes_xwusong";
        //  日志tag
        CommonConst.LOG_TAG = "wusong";
        CommonConst.NET_CACHE_DIR_NAME = "xiaowusong_cacahe";
        PreUtils.init("xiaowusong_config");

        Const.isEnableAutoLogin = false;

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

        initXgService();
    }

    private void initXgService() {
        LoggerUtils.loge("initXgService");
        startService(new Intent(getApplicationContext(), XGPushServiceV3.class));
        startService(new Intent(getApplicationContext(), XGRemoteService.class));

        //开启信鸽的日志输出，线上版本不建议调用
        XGPushConfig.enableDebug(this, true);
        // 1.获取设备Token
        // 新增收到通知后弹出通知前的回调接口。
        // 为保证弹出通知前一定调用本方法，需要在application的onCreate注册
        // 收到通知时，会调用本回调函数。
        // 相当于这个回调会在信鸽的弹出通知之前被截取
        // 一般上针对需要获取通知内容、标题，设置通知点击的跳转逻辑等等
        XGPushManager.setNotifactionCallback(new XGPushNotifactionCallback() {

            @Override
            public void handleNotify(XGNotifaction xGNotifaction) {
                LoggerUtils.loge("处理信鸽通知：" + xGNotifaction);
//                // 获取标签、内容、自定义内容
                String title = xGNotifaction.getTitle();
                String content = xGNotifaction.getContent();
//                String customContent = xGNotifaction.getCustomContent();
//                // 其它的处理
//                // 如果还要弹出通知，可直接调用以下代码或自己创建Notifaction，否则，本通知将不会弹出在通知栏中。
//                xGNotifaction.doNotify();
                CommonUtil.notifyPushMsg(getApplicationContext(), title, content);
            }
        });
    }

    @Override
    protected String getLogTag() {
        return "wusong";
    }

    @Override
    protected boolean isDebugModel() {
        return BuildConfig.DEBUG;
    }
}
