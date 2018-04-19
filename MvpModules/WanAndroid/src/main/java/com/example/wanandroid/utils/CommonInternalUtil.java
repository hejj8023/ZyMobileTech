package com.example.wanandroid.utils;

import android.app.Activity;
import android.content.Intent;

import com.example.wanandroid.Const;
import com.example.wanandroid.ui.activity.WebViewActivity;
import com.zhiyangstudio.commonlib.utils.CommonUtils;
import com.zhiyangstudio.commonlib.utils.UiUtils;

/**
 * Created by zhiyang on 2018/4/19.
 */

public class CommonInternalUtil {
    /**
     * 跳转到webview界面
     *
     * @param title
     * @param url
     */
    public static void goWebView(String title, String url) {
        Intent intent = new Intent(UiUtils.getContext(), WebViewActivity.class);
        intent.putExtra(Const.BUNDLE_KEY.HOME_LIST_ITEM_TITLE, title);
        intent.putExtra(Const.BUNDLE_KEY.HOME_LIST_ITEM_URL, url);
        Activity currentActivity = CommonUtils.getCurrentActivity();
        if (currentActivity != null) {
            currentActivity.startActivity(intent);
        } else {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            UiUtils.getContext().startActivity(intent);
        }
    }
}
