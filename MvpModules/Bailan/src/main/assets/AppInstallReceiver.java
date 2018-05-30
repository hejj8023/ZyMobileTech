package com.example.xiaowusong.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;

import com.blankj.utilcode.util.ToastUtils;
import com.tencent.android.tpush.XGPushManager;
import com.zhiyangstudio.commonlib.utils.UiUtils;

public class AppInstallReceiver extends BroadcastReceiver {

    public void unRegister() {
        UiUtils.getContext().unregisterReceiver(this);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        PackageManager manager = context.getPackageManager();
        if (intent.getAction().equals(Intent.ACTION_PACKAGE_ADDED)) {
            String packageName = intent.getData().getSchemeSpecificPart();
            ToastUtils.showShort("安装成功" + packageName);
        }
        if (intent.getAction().equals(Intent.ACTION_PACKAGE_REMOVED)) {
            String packageName = intent.getData().getSchemeSpecificPart();
            ToastUtils.showShort("卸载成功" + packageName);
            // 取消绑定
            XGPushManager.registerPush(context, "*");
        }
        if (intent.getAction().equals(Intent.ACTION_PACKAGE_REPLACED)) {
            String packageName = intent.getData().getSchemeSpecificPart();
            ToastUtils.showShort("替换成功" + packageName);
            // 取消绑定
            XGPushManager.registerPush(context, "*");
        }
    }

    public void register() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_PACKAGE_ADDED);
        intentFilter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        intentFilter.addAction(Intent.ACTION_PACKAGE_REPLACED);
        intentFilter.addDataScheme("package");
        UiUtils.getContext().registerReceiver(this, intentFilter);
    }

}