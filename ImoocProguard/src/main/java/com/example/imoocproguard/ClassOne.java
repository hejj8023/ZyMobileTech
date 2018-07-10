package com.example.imoocproguard;

import android.util.Log;

public class ClassOne {
    public static void functionA() {
        Log.e("test", "functionA");
        LogUtils.e("functionA");
    }
}
