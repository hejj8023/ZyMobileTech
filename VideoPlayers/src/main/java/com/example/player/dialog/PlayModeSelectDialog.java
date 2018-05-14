package com.example.player.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.player.R;
import com.zhiyangstudio.commonlib.utils.EmptyUtils;
import com.zhiyangstudio.commonlib.utils.UiUtils;

/**
 * Created by example on 2018/5/14.
 */

public class PlayModeSelectDialog extends Dialog {

    private final TextView mTvTitle;
    private final TextView mTvContent;

    public PlayModeSelectDialog(@NonNull Context context) {
        super(context, R.style.my_dialog_style);

        View dialogView = UiUtils.inflateView(R.layout.layout_select_play_mode_dialog);
        mTvTitle = dialogView.findViewById(R.id.tv_title);
        mTvContent = dialogView.findViewById(R.id.tv_content);
        dialogView.findViewById(R.id.tv_confirm).setOnClickListener(v -> {
            UiUtils.showToastSafe("确定");
        });
        dialogView.findViewById(R.id.tv_cancel).setOnClickListener(v -> {
            UiUtils.showToastSafe("取消");
        });
        setContentView(dialogView);

        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.8); // 高度设置为屏幕的0.6
        lp.height = (int) (d.heightPixels * 0.25); // 高度设置为屏幕的0.6
        dialogWindow.setAttributes(lp);
    }

    public void setTitle(String title) {
        if (EmptyUtils.isNotEmpty(title)) {
            if (mTvTitle == null) {
                return;
            }

            mTvTitle.setText(title);
        }
    }
    public void setMessage(String message) {
        if (EmptyUtils.isNotEmpty(message)) {
            if (mTvContent == null) {
                return;
            }

            mTvContent.setText(message);
        }
    }
}
