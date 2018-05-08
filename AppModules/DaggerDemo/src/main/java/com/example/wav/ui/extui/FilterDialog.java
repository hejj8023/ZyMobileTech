package com.example.wav.ui.extui;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.example.wav.R;

/**
 * Created by example on 2018/5/8.
 */

public class FilterDialog extends AlertDialog {
    private final Context mContext;

    public FilterDialog(@NonNull Context context) {
        super(context, R.style.dialog);
        this.mContext = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_home, null);
        setContentView(view);
//        getWindow().setBackgroundDrawable(new ColorDrawable(0x00000000));
//        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams
//                .MATCH_PARENT);
    }
}
