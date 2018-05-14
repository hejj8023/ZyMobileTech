package com.example.wav;

import android.content.Context;

import com.tencent.android.tpush.XGPushBaseReceiver;
import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushRegisterResult;
import com.tencent.android.tpush.XGPushShowedResult;
import com.tencent.android.tpush.XGPushTextMessage;
import com.zhiyangstudio.commonlib.utils.LoggerUtils;

/**
 * Created by example on 2018/5/14.
 */

public class MessageReceiver extends XGPushBaseReceiver {
    @Override
    public void onRegisterResult(Context context, int i, XGPushRegisterResult
            xgPushRegisterResult) {
        LoggerUtils.loge("MessageReceiver onRegisterResult xgPushRegisterResult = " +
                xgPushRegisterResult);
    }

    @Override
    public void onUnregisterResult(Context context, int i) {
        LoggerUtils.loge("MessageReceiver onUnregisterResult ");
    }

    @Override
    public void onSetTagResult(Context context, int i, String s) {
        LoggerUtils.loge("MessageReceiver onSetTagResult str =  " + s);
    }

    @Override
    public void onDeleteTagResult(Context context, int i, String s) {
        LoggerUtils.loge("MessageReceiver onDeleteTagResult str = " + s);
    }

    @Override
    public void onTextMessage(Context context, XGPushTextMessage xgPushTextMessage) {
        LoggerUtils.loge("MessageReceiver onTextMessage ");
    }

    @Override
    public void onNotifactionClickedResult(Context context, XGPushClickedResult
            xgPushClickedResult) {
        LoggerUtils.loge("MessageReceiver onNotifactionClickedResult = " + xgPushClickedResult);
    }

    @Override
    public void onNotifactionShowedResult(Context context, XGPushShowedResult xgPushShowedResult) {
        LoggerUtils.loge("MessageReceiver onNotifactionShowedResult ");
    }
}
