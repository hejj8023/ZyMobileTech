package com.example.bailan.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bailan.R;
import com.example.bailan.bean.MenuItemInfo;

import java.util.List;

/**
 * Created by zzg on 2018/5/27.
 * 水平的菜单，自定义一行几个
 */

public class GridMenuRecyclerView extends RecyclerView {
    private int mSpanCount;
    private OnItemClickListener mListener;

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

    public interface OnItemClickListener {
        void onItemClick(int pos, MenuItemInfo itemInfo);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public void setData(List<MenuItemInfo> list) {
        this.setLayoutManager(new GridLayoutManager(getContext(), mSpanCount));
        setAdapter(new BaseQuickAdapter<MenuItemInfo, BaseViewHolder>(R.layout.layout_item_menu, list) {
            @Override
            protected void convert(BaseViewHolder helper, MenuItemInfo item) {
                helper.setImageResource(R.id.iv_icon_menu, item.getIconId());
                helper.setText(R.id.tv_name_menu, item.getName());
                helper.setOnClickListener(R.id.ll_root_menu, v -> {
                    if (mListener != null) {
                        mListener.onItemClick(helper.getPosition(), item);
                    }
                });
            }
        });
    }
}
