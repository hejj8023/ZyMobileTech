package com.example.wanandroid.adapter;

import android.os.Build;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wanandroid.Const;
import com.example.wanandroid.R;
import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.inter.OnArticleListItemClickListener;
import com.zhiyangstudio.commonlib.adapter.BaseListAdapter;
import com.zhiyangstudio.commonlib.adapter.lgrcommon.QuickViewHolder;
import com.zhiyangstudio.commonlib.utils.DateUtils;
import com.zhiyangstudio.commonlib.utils.UiUtils;

/**
 * Created by example on 2018/4/11.
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
    protected void bindDatas(QuickViewHolder holder, ArticleBean bean, int itemViewType, int position) {
        TextView tv_author = holder.getView(R.id.tv_author);
        final TextView tv_title = holder.getView(R.id.tv_title);
        TextView tv_time = holder.getView(R.id.tv_time);
        TextView tv_type = holder.getView(R.id.tv_type);
        ImageView img_collect = holder.getView(R.id.img_collect);

        tv_author.setText("");
        tv_author.append("作者: ");
        tv_author.append(getSpanText(bean.getAuthor()));
        tv_time.setText(DateUtils.parseTime(bean.getPublishTime()));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tv_title.setText(Html.fromHtml(bean.getTitle(), Html.FROM_HTML_MODE_LEGACY));
        } else {
            tv_title.setText(Html.fromHtml(bean.getTitle()));
        }
        tv_type.setText("");
        tv_type.append("分类: ");
        tv_type.append(getSpanText(bean.getChapterName()));

        switch (mType) {
            case Const.LIST_TYPE.TREE:
                tv_type.setVisibility(View.GONE);
            case Const.LIST_TYPE.HOME:
            case Const.LIST_TYPE.SEARCH:
                img_collect.setImageResource(bean.isCollect() ? R.drawable
                        .ic_favorite_light_24dp : R.drawable.ic_favorite_gray_24dp);
                img_collect.setOnClickListener(v -> {
                    if (mListener != null) {
                        // 处理收藏和取消收藏的操作
                        mListener.onCollectClick(position, bean.getId());
                    }
                });
                break;
            case Const.LIST_TYPE.COLLECT:
                img_collect.setImageResource(R.drawable.ic_favorite_light_24dp);
                img_collect.setOnClickListener(v -> {
                    if (mListener != null) {
                        mListener.onCollectClick(position, bean.getId(), bean.getOriginId());
                    }
                });
                break;
        }

        tv_type.setOnClickListener(v -> {
            if (mListener != null) {
                mListener.onTreeClick(bean.getChapterId(), bean.getChapterName());
            }
        });

        holder.getItemView().setOnClickListener(v -> {
            if (mListener != null) {
                mListener.onItemClick(tv_title.getText().toString(), bean.getLink());
            }
        });
    }

    private CharSequence getSpanText(String str) {
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new ForegroundColorSpan(UiUtils.getColor(R.color._0091ea)), 0, str
                .length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        return spannableString;
    }
}
