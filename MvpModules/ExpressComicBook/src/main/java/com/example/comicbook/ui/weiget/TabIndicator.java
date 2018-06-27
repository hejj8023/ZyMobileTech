package com.example.comicbook.ui.weiget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.comicbook.R;
import com.zysdk.vulture.clib.utils.DisplayUtils;


public class TabIndicator extends RelativeLayout {

    private static final int DEFAULT_ICON_SIZE = 20;

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
        if (imageView == null)
            return;
        if (icon != 0) {
            imageView.setImageResource(icon);
            imageView.setVisibility(View.VISIBLE);

            if (TextUtils.isEmpty(textViewDesc.getText())) {
                textViewDesc.setText("");
            }
        } else {
            imageView.setVisibility(View.GONE);
        }
    }

    public void setIconSize(int size) {
        size = size == 0 ? DEFAULT_ICON_SIZE : size;
        if (imageView != null) {
            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
            int fSize = DisplayUtils.dip2px(size);
            layoutParams.width = fSize;
            layoutParams.height = fSize;
            imageView.setLayoutParams(layoutParams);
        }
    }


    public void setText(String msg) {
        if (textViewDesc == null)
            return;
        if (!TextUtils.isEmpty(msg)) {
            textViewDesc.setText(msg);
            textViewDesc.setVisibility(View.VISIBLE);
        } else {
            textViewDesc.setText("");
            textViewDesc.setVisibility(View.GONE);
        }
    }

}