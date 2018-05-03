package com.example.wav.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wav.R;

public class TabIndicator extends RelativeLayout {

    ImageView imageView;
    private TextView textViewDesc;

    public TabIndicator(Context context) {
        super(context);
        init(context, null);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.tab_indicator, this, true);

        imageView = (ImageView) findViewById(R.id.imageView1);
        imageView.setVisibility(GONE);
        textViewDesc = (TextView) findViewById(R.id.tv_desc);
    }

    public TabIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public void setIcon(int icon) {
        if (icon != 0 && imageView != null) {
            imageView.setImageResource(icon);
        }
    }

    public void setText(String msg) {
        if (!TextUtils.isEmpty(msg) && textViewDesc != null) {
            textViewDesc.setText(msg);
        }
    }

}