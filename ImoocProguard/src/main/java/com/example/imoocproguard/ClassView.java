package com.example.imoocproguard;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class ClassView extends View {
    public ClassView(Context context) {
        super(context);
    }

    public ClassView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ClassView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setTitle(String title) {

    }

    public String getTitle() {
        Log.e("test", "getTitle");
        LogUtils.e("getTitle");
        return "hello";
    }

}
