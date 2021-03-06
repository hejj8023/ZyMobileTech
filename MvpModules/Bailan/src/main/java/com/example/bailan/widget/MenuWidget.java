package com.example.bailan.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.bailan.R;
import com.zysdk.vulture.clib.glide.GlideUtils;
import com.zysdk.vulture.clib.utils.EmptyUtils;
import com.zysdk.vulture.clib.utils.UiUtils;

/**
 * Created by zzg on 2018/5/26.
 */

public class MenuWidget extends RelativeLayout {

    private String mTitle;
    private String mSubTitle;
    private TextView mTvTitle;
    private TextView mTvSubTitle;
    private boolean isShowLeftIcon;
    private ImageView mLeftIcon;
    private ImageView mRightIcon;
    private boolean hideSubTitle;
    private boolean isShowDivider2;
    private View vDivider2;

    public MenuWidget(Context context) {
        super(context);
        initProperties(context, null);
    }

    private void initProperties(Context context, AttributeSet attrs) {
        initContent();
        initAttrs(context, attrs);
        initView();
        preAttr();
    }

    private void initContent() {
        UiUtils.inflateViewY(R.layout.layout_menu_widget, this);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        if (attrs == null)
            return;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MenuWidget);
        mTitle = typedArray.getString(R.styleable.MenuWidget_Title);
        mSubTitle = typedArray.getString(R.styleable.MenuWidget_SubTitle);
        isShowLeftIcon = typedArray.getBoolean(R.styleable.MenuWidget_showLeftIcon, false);
        isShowDivider2 = typedArray.getBoolean(R.styleable.MenuWidget_showDivider2, false);
        hideSubTitle = typedArray.getBoolean(R.styleable.MenuWidget_hideSubTitle, false);
        typedArray.recycle();
    }

    private void initView() {
        mTvTitle = findViewById(R.id.tv_title);
        mTvSubTitle = findViewById(R.id.tv_sub_title);
        mLeftIcon = findViewById(R.id.ic_left);
        mRightIcon = findViewById(R.id.ic_right);
        vDivider2 = findViewById(R.id.v_divider2);
    }

    private void preAttr() {
        if (EmptyUtils.isNotEmpty(mTitle)) {
            mTvTitle.setText(mTitle);
        }
        if (EmptyUtils.isNotEmpty(mSubTitle)) {
            mTvSubTitle.setText(mSubTitle);
        }
        if (isShowLeftIcon) {
            mLeftIcon.setVisibility(View.VISIBLE);
        }
        if (hideSubTitle) {
            mTvSubTitle.setVisibility(GONE);
        }
        if (isShowDivider2) {
            vDivider2.setVisibility(View.VISIBLE);
        }
    }

    public MenuWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        initProperties(context, attrs);
    }

    public void setTitle(String title) {
        mTvTitle.setText(title);
    }

    public void setTitle(int titleResid) {
        mTvTitle.setText(ResourceUtils.getStr(titleResid));
    }

    public void setSubTitle(String title) {
        mTvTitle.setText(title);
    }

    public void setSubTitle(int titleResid) {
        mTvTitle.setText(ResourceUtils.getStr(titleResid));
    }

    public void setLeftIcon(int resId) {
        mLeftIcon.setBackgroundResource(resId);
    }

    public void setLeftIconSrc(int resId) {
        mLeftIcon.setImageResource(resId);
    }

    public void setLeftIconBgColor(int resId) {
        mLeftIcon.setBackgroundColor(resId);
    }

    public void setLeftIconBgColor(Drawable resId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mLeftIcon.setBackground(resId);
        }
    }

    public void setRightIcon(int resId) {
        mRightIcon.setBackgroundResource(resId);
    }

    public void setRightIconSrc(int resId) {
        mRightIcon.setImageResource(resId);
    }

    public void setRightIconBgColor(int resId) {
        mRightIcon.setBackgroundColor(resId);
    }

    public void setRightIconBgColor(Drawable resId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mRightIcon.setBackground(resId);
        }
    }

    public void setLeftIcon(String iconUrl) {
        GlideUtils.loadPic(getContext(), iconUrl, mLeftIcon);
    }

    public void setLeftIconSize(int sW, int sH) {
        if (sW > 0 && sH > 0) {
            RelativeLayout.LayoutParams lP = (RelativeLayout.LayoutParams) mLeftIcon.getLayoutParams();
            lP.width = sW;
            lP.height = sH;
            mLeftIcon.setLayoutParams(lP);

            LinearLayout.LayoutParams rLp = (LinearLayout.LayoutParams) vDivider2.getLayoutParams();
            rLp.leftMargin = sW + UiUtils.dp2px(12);
            vDivider2.setLayoutParams(rLp);
        }
    }

    public void hideDivider2() {
        if (vDivider2 != null && vDivider2.getVisibility() != View.GONE) {
            vDivider2.setVisibility(View.GONE);
        }
    }
}
