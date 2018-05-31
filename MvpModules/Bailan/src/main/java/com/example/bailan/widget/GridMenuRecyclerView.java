package com.example.bailan.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.blankj.utilcode.util.ScreenUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bailan.R;
import com.example.bailan.bean.MenuItemInfo;
import com.zhiyangstudio.commonlib.glide.GlideUtils;
import com.zhiyangstudio.commonlib.utils.EmptyUtils;
import com.zhiyangstudio.commonlib.utils.LoggerUtils;

import java.util.List;

/**
 * Created by zzg on 2018/5/27.
 * 水平的菜单，自定义一行几个
 */

public class GridMenuRecyclerView extends RecyclerView {
    // 默认支持4个
    private int mSpanCount = 4;
    private OnItemClickListener mListener;
    private int mPaddingLeft;
    private int mPaddingRight;
    private int mScreenWidth;

    public GridMenuRecyclerView(Context context) {
        super(context);
    }

    public GridMenuRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GridMenuRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    public void setSpanCount(int spanCount) {
        this.mSpanCount = spanCount;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public void setData(List<MenuItemInfo> list) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,
                false);
        this.setLayoutManager(layoutManager);
        mPaddingLeft = getPaddingLeft();
        mPaddingRight = getPaddingRight();
        mScreenWidth = ScreenUtils.getScreenWidth();
        LoggerUtils.loge("mScreenWidth = " + mScreenWidth + ",mPaddingLeft = " + mPaddingLeft + ",mPaddingRight= " +
                mPaddingRight);
        setAdapter(new BaseQuickAdapter<MenuItemInfo, BaseViewHolder>(R.layout.layout_item_menu,
                list) {
            @Override
            protected void convert(BaseViewHolder helper, MenuItemInfo item) {
                if (item.getIconId() != 0)
                    helper.setImageResource(R.id.iv_icon_menu, item.getIconId());

                String iconUrl = item.getIconUrl();
                if (EmptyUtils.isNotEmpty(iconUrl)) {
                    ImageView view = helper.getView(R.id.iv_icon_menu);
                    // GlideApp.with(getContext()).load(iconUrl).into(view);
                    GlideUtils.loadPic(getContext(), iconUrl, R.drawable.icon_default_bg, R.drawable.icon_default_bg,
                            view, false);
                }

                RelativeLayout linearLayout = helper.getView(R.id.ll_root_menu);
                //设置每张小图的宽高为屏幕宽度的1/3
                ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
                int fw = (mScreenWidth - mPaddingLeft - mPaddingRight) / mSpanCount;
                LoggerUtils.loge("fw = " + fw);
                layoutParams.width = fw;
                linearLayout.setLayoutParams(layoutParams);
                helper.setText(R.id.tv_name_menu, item.getName());
                helper.setOnClickListener(R.id.ll_root_menu, v -> {
                    if (mListener != null) {
                        mListener.onItemClick(helper.getPosition(), item);
                    }
                });
            }
        });
    }

    public interface OnItemClickListener {
        void onItemClick(int pos, MenuItemInfo itemInfo);
    }
}
