package com.example.wanandroid;

import com.zhiyangstudio.commonlib.corel.BaseApp;
import com.zhiyangstudio.commonlib.utils.PreUtils;

/**
 * Created by example on 2018/4/9.
 */

public class WanApp extends BaseApp {
    @Override
    public void onCreate() {
        super.onCreate();
        PreUtils.init("wanandorid_userinfo");
    }
}
