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
import com.zhiyangstudio.commonlib.helper.ViewHolderHelper;
import com.zhiyangstudio.commonlib.utils.DateUtils;
import com.zhiyangstudio.commonlib.utils.UiUtils;
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
        ViewHolderHelper holderHelper = holder.getHolderHelper();
        TextView tv_author = holderHelper.findView(R.id.tv_author);
        final TextView tv_title = holderHelper.findView(R.id.tv_title);
        TextView tv_time = holderHelper.findView(R.id.tv_time);
        TextView tv_type = holderHelper.findView(R.id.tv_type);
        ImageView img_collect = holderHelper.findView(R.id.img_collect);

        tv_author.setText("");
        tv_author.append("作者: ");
        tv_author.append(getSpanText(bean.getAuthor()));
        tv_time.setText(DateUtils.parseTime(bean.getPublishTime()));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tv_title.setText(Html.fromHtml(bean.getTitle(), Html.FROM_HTML_MODE_LEGACY));
        } else {
            tv_title.setText(bean.getTitle());
        }
        tv_type.setText("");
        tv_type.append("分类: ");
        tv_type.append(getSpanText(bean.getChapterName()));

        switch (mType) {
            case Const.LIST_TYPE.TREE:
                tv_type.setVisibility(View.GONE);
                break;
            case Const.LIST_TYPE.HOME:
            case Const.LIST_TYPE.SEARCH:
                img_collect.setImageResource(bean.isCollect() ? R.drawable
                        .ic_view_quilt_light_24dp : R.drawable.ic_favorite_gray_24dp);
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

        holderHelper.getItemView().setOnClickListener(v -> {
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
