package com.example.wanandroid.adapter;

import com.example.wanandroid.R;
import com.example.wanandroid.bean.TreeBean;
import com.example.wanandroid.inter.OnTreeItemClickListener;
import com.zysdk.vulture.clib.adapter.BaseListAdapter;
import com.zysdk.vulture.clib.adapter.lgrcommon.QuickViewHolder;
import com.zysdk.vulture.clib.utils.LoggerUtils;

/**
 * Created by zhiyang on 2018/4/19.
 */

public class TreeAdapter extends BaseListAdapter<TreeBean> {
    private final OnTreeItemClickListener mListener;

    public TreeAdapter(OnTreeItemClickListener listener) {
        this.mListener = listener;
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_tree;
    }

    @Override
    protected void bindDatas(QuickViewHolder holder, TreeBean bean, int itemViewType, int position) {
        LoggerUtils.loge(this, "bindDatas pos = " + position);
        holder.setText(R.id.tv_title, bean.getName());

        StringBuffer sb = new StringBuffer();
        for (TreeBean.SubTreeBean subTreeBean : bean.getChildren()) {
            sb.append(subTreeBean.getName() + "     ");
        }
        holder.setText(R.id.tv_content, sb.toString());
        holder.setOnClickListener(v -> {
            if (mListener != null) {
                mListener.onItemClick(bean);
            }
        });
    }
}
