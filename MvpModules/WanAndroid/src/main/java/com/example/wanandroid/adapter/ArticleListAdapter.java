package com.example.wanandroid.adapter;

import com.example.wanandroid.R;
import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.inter.OnArticleListItemClickListener;
import com.zhiyangstudio.commonlib.widget.BaseListAdapter;
import com.zhiyangstudio.commonlib.widget.recyclerview.CommonRViewHolder;

/**
 * Created by ubt on 2018/4/11.
 */

public class ArticleListAdapter extends BaseListAdapter<ArticleBean> {
    private final OnArticleListItemClickListener mListener;
    private final int mType;

    public ArticleListAdapter(OnArticleListItemClickListener listener, int type) {
        this.mListener = listener;
        this.mType = type;
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.layout_item_article_list;
    }

    @Override
    protected void bindDatas(CommonRViewHolder holder, ArticleBean bean, int itemViewType, int position) {

    }
}
