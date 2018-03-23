package com.example.gcamera.camera.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * @author jerry
 * @date 2016/03/11
 */
public class Utils {

    public static void displayToastCenter(Context context, int strResId) {
        Toast.makeText(context, strResId, Toast.LENGTH_SHORT).show();
    }
}
