package com.example.wav.manager;

import android.app.Notification;
import android.content.Context;
import android.media.RingtoneManager;
import android.net.Uri;

import com.example.wav.R;
import com.tencent.android.tpush.XGCustomPushNotificationBuilder;
import com.zhiyangstudio.commonlib.utils.UiUtils;

/**
 * Created by example on 2018/5/15.
 */

public class XGNotificationManager {
    public static XGCustomPushNotificationBuilder createBuilder(Context context) {
        XGCustomPushNotificationBuilder build = new XGCustomPushNotificationBuilder();
        build.setSound(
                RingtoneManager.getActualDefaultRingtoneUri(
                        context, RingtoneManager.TYPE_ALARM)) // 设置声音
                // setSound(
                // Uri.parse("android.resource://" + getPackageName()
                // + "/" + R.raw.wind)) 设定Raw下指定声音文件
                .setDefaults(Notification.DEFAULT_VIBRATE) // 振动
                .setFlags(Notification.FLAG_NO_CLEAR); // 是否可清除
        // 设置自定义通知layout,通知背景等可以在layout里设置
        build.setLayoutId(R.layout.custom_notification);
        // 设置自定义通知内容id
        build.setLayoutTextId(R.id.tv_content);
        // 设置自定义通知标题id
        build.setLayoutTitleId(R.id.tv_title);
        // 设置自定义通知图片id
        build.setLayoutIconId(R.id.iv_notifi);
        // 设置自定义通知图片资源
        build.setLayoutIconDrawableId(R.drawable.ic_notification_app_logo);
        // 设置状态栏的通知小图标
        build.setIcon(R.drawable.ic_notification_app_logo);
        // 设置时间id
//        build.setLayoutTimeId(R.id.time);
        // 若不设定以上自定义layout，又想简单指定通知栏图片资源
//        build.setNotificationLargeIcon(R.drawable.ic_notification_app_logo);
        return build;
    }


    public static XGCustomPushNotificationBuilder createCommonBuilder() {
        XGCustomPushNotificationBuilder build = new XGCustomPushNotificationBuilder();
        Context context = UiUtils.getContext();
        int id = context.getResources().getIdentifier(
                "tixin", "raw", context.getPackageName());
        String uri = "android.resource://"
                + context.getPackageName() + "/" + id;
        build.setSound(Uri.parse(uri));
//        // 设置自定义通知layout,通知背景等可以在layout里设置
//        build.setLayoutId(R.layout.notification);
//        // 设置自定义通知内容id
//        build.setLayoutTextId(R.id.content);
//        // 设置自定义通知标题id
//        build.setLayoutTitleId(R.id.title);
//        // 设置自定义通知图片id
//        build.setLayoutIconId(R.id.icon);
//        // 设置自定义通知图片资源
//        build.setLayoutIconDrawableId(R.mipmap.ic_launcher);
//        // 设置状态栏的通知小图标
//        //build.setbigContentView()
        build.setIcon(R.mipmap.ic_launcher);
//        // 设置时间id
//        build.setLayoutTimeId(R.id.time);

        // 若不设定以上自定义layout，又想简单指定通知栏图片资源
        build.setNotificationLargeIcon(R.mipmap.ic_launcher);
        return build;
    }
}
