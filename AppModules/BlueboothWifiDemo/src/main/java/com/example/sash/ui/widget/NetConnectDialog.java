package com.example.sash.ui.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.sash.Const;
import com.example.sash.R;
import com.zhiyangstudio.commonlib.utils.EmptyUtils;
import com.zhiyangstudio.commonlib.utils.UiUtils;
import com.zhiyangstudio.commonlib.utils.WifiUtils;
import com.zhiyangstudio.commonlib.widget.dialog.LoadingDialog;

/**
 * Created by example on 2018/5/24.
 */

public class NetConnectDialog extends Dialog {
    private static NetConnectDialog loadingDialog;

    public NetConnectDialog(@NonNull Context context) {
        super(context);
    }

    public NetConnectDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected NetConnectDialog(@NonNull Context context, boolean cancelable, @Nullable
            OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static NetConnectDialog showWifiConnect(Activity context, String title, int wifiType) {
        loadingDialog = new NetConnectDialog(context, R.style.loadingDialog);
        loadingDialog.setContentView(R.layout.layout_wifi_connect);
        loadingDialog.setCancelable(true);
        loadingDialog.setCanceledOnTouchOutside(true);
        loadingDialog.setTitle(title);
        EditText etPwd = loadingDialog.findViewById(R.id.et_wifi_pwd);
        loadingDialog.findViewById(R.id.bt_confirm).setOnClickListener(v -> {

            // TODO: 2018/5/25 怎么判断是否连接上，如果已经连接上了就不连接
            String pwdStr = etPwd.getText().toString
                    ().trim();
            if (EmptyUtils.isEmpty(pwdStr)) {
                ToastUtils.showShort(UiUtils.getStr(R.string.tip_wifi_pwd_error));
            } else {
                Const.TMP_CONNECT_SSID = title;
                LoadingDialog.show(context);
                boolean connectWifi = WifiUtils.connectWifi(context, title, pwdStr,
                        wifiType);
                UiUtils.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        LoadingDialog.hideDialog();
                        if (connectWifi) {
                            ToastUtils.showShort("连接到" + title + "成功");
                            loadingDialog.dismiss();
                        } else {
                            ToastUtils.showShort("连接到" + title + "失败");
                        }
                    }
                }, 150);

            }
        });

        loadingDialog.findViewById(R.id.btn_cancel).setOnClickListener(v -> {
            loadingDialog.dismiss();
        });
        // 设置属性
        Window window = loadingDialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = Gravity.CENTER;
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

    public static NetConnectDialog showWifiApCreate(Activity activity) {
        loadingDialog = new NetConnectDialog(activity, R.style.loadingDialog);
        loadingDialog.setTitle("");
        loadingDialog.setContentView(R.layout.layout_ap_create);
        loadingDialog.setCancelable(true);
        loadingDialog.setCanceledOnTouchOutside(true);
        EditText etApName = loadingDialog.findViewById(R.id.et_ap_name);
        EditText etApPwd = loadingDialog.findViewById(R.id.et_ap_pwd);
        loadingDialog.findViewById(R.id.bt_confirm).setOnClickListener(v -> {
            String nameStr = etApName.getText().toString().trim();
            String pwdStr = etApPwd.getText().toString().trim();
            if (EmptyUtils.isEmpty(nameStr) || EmptyUtils.isEmpty(pwdStr)) {
                ToastUtils.showShort("ap名称或密码为空");
                return;
            }

            LoadingDialog.show(activity);
            boolean hotspot = WifiUtils.createHotspot(nameStr, pwdStr);
            UiUtils.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LoadingDialog.hideDialog();
                    if (hotspot) {
                        ToastUtils.showShort(UiUtils.getStr(R.string.tip_ap_create_sucess));
                    } else {
                        ToastUtils.showShort(UiUtils.getStr(R.string.tip_ap_create_fail));
                    }
                    loadingDialog.dismiss();
                }
            }, 150);
        });

        loadingDialog.findViewById(R.id.btn_cancel).setOnClickListener(v -> {
            loadingDialog.dismiss();
        });
        // 设置属性
        Window window = loadingDialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = Gravity.CENTER;
        window.setAttributes(attributes);
        loadingDialog.show();
        return loadingDialog;
    }
}
