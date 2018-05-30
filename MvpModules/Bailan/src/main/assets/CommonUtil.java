package com.example.xiaowusong.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.hardware.Camera;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.example.xiaowusong.R;
import com.example.xiaowusong.ui.activity.MainActivity;

import org.json.JSONObject;

/**
 * Created by Administrator on 2018/3/29.
 */

public class CommonUtil {

    public static boolean isCameraCanUse() {
        boolean canUse = true;
        Camera mCamera = null;
        try {
            mCamera = Camera.open();
        } catch (Exception e) {
            canUse = false;
        }
        if (canUse) {
            if (mCamera != null)
                mCamera.release();
            mCamera = null;
        }
        return canUse;
    }

    public static String optString(JSONObject object, String key) {
        return object.optString(key);
    }

    public static String unicodeToUtf8(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len; ) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed   \\uxxxx   encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }

    /**
     * 通知消息展示
     */
    public static void notifyPushMsg(Context context, String title, String content) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context
                .NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            //获取通知实例
            NotificationCompat.Builder builder1 = new NotificationCompat.Builder(context);
            //为实例设置属性
            builder1.setSmallIcon(R.mipmap.logo_circle);//设置小图标
            // (必须设置，若LargeIcon没设置，默认也是它，而且通知栏没下拉的时候显示也是它)
            builder1.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.logo_circle));
            builder1.setDefaults(Notification.DEFAULT_ALL);
            //设置大图标
            builder1.setContentTitle(title);//设置标题内容
            builder1.setContentText(content);//设置正文内容
            builder1.setTicker(content);//设置滚动内容
            builder1.setAutoCancel(true);//点击后消失
            builder1.setOngoing(true);//是否一直显示（设置了这个方法后，不能通过左右滑动通知让通知栏消失，只有通过点击通知才能让通知消失）
            // builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);//设置优先级，级别越高，显示的位置越高
            //设置通知的点击事件（一般用来跳转到真正要显示的页面内）
            builder1.setContentIntent(PendingIntent.getActivity(context, 1, new Intent(context,
                    MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT));
            //发送通知
            manager.notify(1, builder1.build());
        } else {
            // TODO: 2018/5/30 8.0的通知
            NotificationChannel channel = new NotificationChannel("1",
                    "Channel1", NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableLights(true); //是否在桌面icon右上角展示小红点
            channel.setLightColor(Color.GREEN); //小红点颜色
            channel.setShowBadge(true); //是否在久按桌面图标时显示此渠道的通知
            manager.createNotificationChannel(channel);

            Notification.Builder builder = new Notification.Builder(context, "1");
            //为实例设置属性
            builder.setSmallIcon(R.mipmap.logo_circle);//设置小图标
            // (必须设置，若LargeIcon没设置，默认也是它，而且通知栏没下拉的时候显示也是它)
            builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.logo_circle));
            builder.setDefaults(Notification.DEFAULT_ALL);
            //设置大图标
            builder.setContentTitle(title);//设置标题内容
            builder.setContentText(content);//设置正文内容
            builder.setTicker(content);//设置滚动内容
            builder.setAutoCancel(true);//点击后消失
            builder.setOngoing(true);//是否一直显示（设置了这个方法后，不能通过左右滑动通知让通知栏消失，只有通过点击通知才能让通知消失）
            // builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);//设置优先级，级别越高，显示的位置越高
            //设置通知的点击事件（一般用来跳转到真正要显示的页面内）
            builder.setContentIntent(PendingIntent.getActivity(context, 1, new Intent(context,
                    MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT));
            manager.notify(1, builder.build());
        }
    }
}
