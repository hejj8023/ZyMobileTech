package com.example.ndebuger;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;

/**
 * Created by example on 2018/3/20.
 */

public class MyScrollView extends ScrollView {
    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean requestChildRectangleOnScreen(View child, Rect rectangle, boolean immediate) {
        if (child instanceof EditText) {
            return true;
        } else {
            return false;
        }
    }
}

/**
 * ScrollView嵌套 EditText，输入多行（足够多的情况）会使ScrollView整体滚动的问题
 * ScrollView 中嵌套 EditText，在 EditText 中输入多行，会使 ScrollView 整体滚动，如果输入的内容长到
 * 一定程度，如果再次获取焦点则会使EditText向上滚动过多以至于滚出视图范围。研究很久没能解决，后来查看
 * ScrollView的官方文档发现如下方法，大致意思是控制子view是否被固定于一个相对的位置，于是复写看看
 * https://www.2cto.com/kf/201704/622493.html
 */

