package com.example.ndebuger;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

public class MyEditText extends EditText {

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyEditText(Context context) {
        super(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent evt) {
        switch (evt.getAction()) {
        case MotionEvent.ACTION_MOVE:
        // 通知其父控件，现在进行的是本控件的操作，不允许拦截    
                  getParent().requestDisallowInterceptTouchEvent(true);
            break;
        }
        return super.onTouchEvent(evt);
    }
}