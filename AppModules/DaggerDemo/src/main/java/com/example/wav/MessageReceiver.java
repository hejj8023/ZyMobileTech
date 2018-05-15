package com.example.wav;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;

import com.blankj.utilcode.util.ToastUtils;
import com.example.wav.manager.XGNotificationManager;
import com.tencent.android.tpush.XGLocalMessage;
import com.tencent.android.tpush.XGPushBaseReceiver;
import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.android.tpush.XGPushRegisterResult;
import com.tencent.android.tpush.XGPushShowedResult;
import com.tencent.android.tpush.XGPushTextMessage;
import com.zhiyangstudio.commonlib.utils.LoggerUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by example on 2018/5/14.
 */

public class MessageReceiver extends XGPushBaseReceiver {

    private Intent intent = new Intent("com.example.wav.activity.UPDATE_LISTVIEW");

    /**
     * //注册的回调
     *
     * @param context
     */
    @Override
    public void onRegisterResult(Context context, int errorCode, XGPushRegisterResult
            message) {
        LoggerUtils.loge("MessageReceiver onRegisterResult xgPushRegisterResult = " +
                message);

        if (context == null || message == null) {
            return;
        }
        String text = "";
        if (errorCode == XGPushBaseReceiver.SUCCESS) {
            text = message + "注册成功";
            // 在这里拿token
            String token = message.getToken();
        } else {
            text = message + "注册失败错误码：" + errorCode;
        }
        LoggerUtils.loge(text);
        show(text);
    }

    /**
     * 反注册的回调
     *
     * @param context
     * @param errorCode
     */
    @Override
    public void onUnregisterResult(Context context, int errorCode) {
        LoggerUtils.loge("MessageReceiver onUnregisterResult ");

        if (context == null) {
            return;
        }
        String text = "";
        if (errorCode == XGPushBaseReceiver.SUCCESS) {
            text = "反注册成功";
        } else {
            text = "反注册失败" + errorCode;
        }
        LoggerUtils.loge(text);
        show(text);
    }

    /**
     * 设置tag的回调
     *
     * @param context
     */
    @Override
    public void onSetTagResult(Context context, int errorCode, String tagName) {
        LoggerUtils.loge("MessageReceiver onSetTagResult str =  " + tagName);
        if (context == null) {
            return;
        }
        String text = "";
        if (errorCode == XGPushBaseReceiver.SUCCESS) {
            text = "\"" + tagName + "\"设置成功";
        } else {
            text = "\"" + tagName + "\"设置失败,错误码：" + errorCode;
        }
        LoggerUtils.loge(text);
        show(text);
    }

    /**
     * 删除tag的回调
     *
     * @param context
     */
    @Override
    public void onDeleteTagResult(Context context, int errorCode, String tagName) {
        LoggerUtils.loge("MessageReceiver onDeleteTagResult str = " + tagName);
        if (context == null) {
            return;
        }
        String text = "";
        if (errorCode == XGPushBaseReceiver.SUCCESS) {
            text = "\"" + tagName + "\"删除成功";
        } else {
            text = "\"" + tagName + "\"删除失败,错误码：" + errorCode;
        }
        LoggerUtils.loge(text);
        show(text);
    }

    // 消息透传的回调
    @Override
    public void onTextMessage(Context context, XGPushTextMessage message) {
        // TODO Auto-generated method stub
        String text = "收到消息:" + message.toString();
        // 获取自定义key-value
        String customContent = message.getCustomContent();
        if (customContent != null && customContent.length() != 0) {
            try {
                JSONObject obj = new JSONObject(customContent);
                // key1为前台配置的key
                if (!obj.isNull("key")) {
                    String value = obj.getString("key");
                    LoggerUtils.loge("get custom value:" + value);
                }
                // ...
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        LoggerUtils.loge("++++++++++++++++透传消息");
        // APP自主处理消息的过程...

        // TODO: 2018/5/15 有些问题，app处于后台 未测试
        XGNotification notific = new XGNotification();
        notific.setTitle(message.getTitle());
        notific.setContent(message.getContent());
        // notificationActionType==1为Activity，2为url，3为intent
        notific.setNotificationActionType(1);
        //Activity,url,intent都可以通过getActivity()获得
        notific.setUpdate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(Calendar.getInstance().getTime()));
        context.sendBroadcast(intent);

//        showNofity(message.getTitle(), message.getContent());

//        showCustomerNotify(context, message.getContent());
        LoggerUtils.loge(text);
        show(text);
    }

    /**
     * 通知点击回调 actionType=1为该消息被清除，actionType=0为该消息被点击。此处不能做点击消息跳转，详细方法请参照官网的Android常见问题文档
     *
     * @param context
     * @param message
     */
    @Override
    public void onNotifactionClickedResult(Context context, XGPushClickedResult
            message) {
        LoggerUtils.loge("MessageReceiver onNotifactionClickedResult = " + message);

        LoggerUtils.loge("+++++++++++++++ 通知被点击 跳转到指定页面。");
        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
        if (context == null || message == null) {
            return;
        }
        String text = "";
        if (message.getActionType() == XGPushClickedResult.NOTIFACTION_CLICKED_TYPE) {
            // 通知在通知栏被点击啦。。。。。
            // APP自己处理点击的相关动作
            // 这个动作可以在activity的onResume也能监听，请看第3点相关内容
            text = "通知被打开 :" + message;
        } else if (message.getActionType() == XGPushClickedResult.NOTIFACTION_DELETED_TYPE) {
            // 通知被清除啦。。。。
            // APP自己处理通知被清除后的相关动作
            text = "通知被清除 :" + message;
        }
        show("广播接收到通知被点击:" + message.toString());
        // 获取自定义key-value
        String customContent = message.getCustomContent();
        if (customContent != null && customContent.length() != 0) {
            try {
                JSONObject obj = new JSONObject(customContent);
                // key1为前台配置的key
                if (!obj.isNull("key")) {
                    String value = obj.getString("key");
                    LoggerUtils.loge("get custom value:" + value);
                }
                // ...
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        // APP自主处理的过程。。。
//        showNofity(message.getTitle(), message.getContent());

        showCustomerNotify(context, message.getContent());
        LoggerUtils.loge(text);
        show(text);
    }

    /**
     * 通知展示
     *
     * @param context
     */
    @Override
    public void onNotifactionShowedResult(Context context, XGPushShowedResult notifiShowedRlt) {
        LoggerUtils.loge("MessageReceiver onNotifactionShowedResult ");

        if (context == null || notifiShowedRlt == null) {
            return;
        }
        XGNotification notific = new XGNotification();
        notific.setMsg_id(notifiShowedRlt.getMsgId());
        notific.setTitle(notifiShowedRlt.getTitle());
        notific.setContent(notifiShowedRlt.getContent());
        // notificationActionType==1为Activity，2为url，3为intent
        notific.setNotificationActionType(notifiShowedRlt
                .getNotificationActionType());
        //Activity,url,intent都可以通过getActivity()获得
        notific.setActivity(notifiShowedRlt.getActivity());
        notific.setUpdate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(Calendar.getInstance().getTime()));
//        NotificationService.getInstance(context).save(notific);
        context.sendBroadcast(intent);
//        showCustomerNotify(context, notifiShowedRlt.getContent());
        LoggerUtils.loge("发送消息");
        show("您有1条新消息, " + "通知被展示 ， " + notifiShowedRlt.toString());
    }

    public void showCustomerNotify(Context context, String content) {
        // APP自主处理消息的过程...
        XGLocalMessage localMessage = new XGLocalMessage();
        localMessage.setTitle("小米咖");
        localMessage.setContent(content);
        // 客户端保存build_id
        XGPushManager.setDefaultNotificationBuilder(context, XGNotificationManager
                .createCommonBuilder());

        XGPushManager.addLocalNotification(context, localMessage);
    }

    private void show(String s) {
        ToastUtils.showShort(s);
    }

    private void showNofity(Context context, String titleStr, String content) {
        XGLocalMessage localMessage = new XGLocalMessage();
        localMessage.setTitle(titleStr);
        localMessage.setContent(content);
        XGPushManager.addLocalNotification(context, localMessage);
    }
}
