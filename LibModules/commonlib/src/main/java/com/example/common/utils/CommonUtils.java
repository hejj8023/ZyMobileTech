package com.example.common.utils;

import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by example on 2018/2/24.
 */

public class CommonUtils {
    public static int[] getScreenWH(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display defaultDisplay = wm.getDefaultDisplay();
        return new int[]{defaultDisplay.getWidth(), defaultDisplay.getHeight()};
    }
}
