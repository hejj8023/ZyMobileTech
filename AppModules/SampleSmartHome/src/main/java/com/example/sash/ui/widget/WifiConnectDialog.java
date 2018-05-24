package com.example.sash.ui.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.sash.R;

/**
 * Created by example on 2018/5/24.
 */

public class WifiConnectDialog extends Dialog {
    private static WifiConnectDialog loadingDialog;

    public WifiConnectDialog(@NonNull Context context) {
        super(context);
    }

    public WifiConnectDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected WifiConnectDialog(@NonNull Context context, boolean cancelable, @Nullable
            OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static WifiConnectDialog show(Activity context, String title, OnCancelListener
            cancelListener) {
        loadingDialog = new WifiConnectDialog(context, R.style.loadingDialog);
        loadingDialog.setTitle("");
        loadingDialog.setContentView(R.layout.layout_wifi_connect);
        loadingDialog.setCancelable(true);
        loadingDialog.setCanceledOnTouchOutside(true);
        loadingDialog.setTitle(title);

        loadingDialog.findViewById(R.id.bt_confirm).setOnClickListener(v -> {
            // 获取密码，连接wifi,无论成功或失败都关闭对话框
            loadingDialog.dismiss();
        });
        loadingDialog.findViewById(R.id.btn_cancel).setOnClickListener(v -> {
            loadingDialog.dismiss();
        });
        if (cancelListener != null)
            loadingDialog.setOnCancelListener(cancelListener);
        // 设置属性
        Window window = loadingDialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = Gravity.CENTER;
        // 设置背景层透明效果
//        attributes.dimAmount = 0.2f;
        window.setAttributes(attributes);
        loadingDialog.show();
        return loadingDialog;
    }

    @Override
    public void setTitle(@Nullable CharSequence title) {
        TextView textView = findViewById(R.id.tv_ssid_name);
        if (textView != null) {
            textView.setText(title);
        }
    }

}
