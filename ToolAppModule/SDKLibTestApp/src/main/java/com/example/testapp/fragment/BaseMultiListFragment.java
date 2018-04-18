package com.example.testapp.fragment;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.testapp.Const;
import com.example.testapp.HotBean;
import com.example.testapp.R;
import com.zhiyangstudio.commonlib.adapter.lgrcommon.QuickAdapter;
import com.zhiyangstudio.commonlib.adapter.lgrcommon.QuickMultiSupport;
import com.zhiyangstudio.commonlib.adapter.lgrcommon.QuickViewHolder;
import com.zhiyangstudio.commonlib.corel.BaseFragment;
import com.zhiyangstudio.commonlib.glide.GlideApp;
import com.zhiyangstudio.commonlib.utils.UiUtils;

/**
 * Created by example on 2018/4/18.
 */

public abstract class BaseMultiListFragment extends BaseFragment {
    protected static final int COMMON_TEXT = 0;
    protected static final int COMMON_IMG = 1;
    protected static final int COMMON_IMGTEXT = 2;

    protected QuickAdapter mQuickAdapter = new QuickAdapter<HotBean>(UiUtils.getContext(), Const.DATA
            .TEST_HOT_BEAN_LIST, new
            QuickMultiSupport<HotBean>() {
                @Override
                public int getViewTypeCount() {
                    return 3;
                }

                @Override
                public int getLayoutId(HotBean data) {
                    int itemViewType = getItemViewType(data);
                    switch (itemViewType) {
                        case COMMON_TEXT:
                            return R.layout.layout_item_list;
                        case COMMON_IMG:
                            return R.layout.layout_item_img_list;
                        case COMMON_IMGTEXT:
                            return R.layout.layout_item_img_text_list;
                    }
                    return R.layout.layout_item_list;
                }

                @Override
                public int getItemViewType(HotBean data) {
                    switch (data.getType()) {
                        case 0:
                            return COMMON_TEXT;
                        case 1:
                            return COMMON_IMG;
                        case 2:
                            return COMMON_IMGTEXT;
                    }
                    return COMMON_TEXT;
                }

                @Override
                public boolean isSpan(HotBean data) {
                    return false;
                }
            }) {
        @Override
        protected void convert(QuickViewHolder holder, HotBean data, int position) {
            int itemViewType = getItemViewType(position);
            switch (itemViewType) {
                case COMMON_TEXT:
                    holder.setText(R.id.textview, data.getTitle());
                    break;
                case COMMON_IMG:
                    GlideApp.with(UiUtils.getContext()).load(data.getImgUrl()).into((ImageView) holder.getView(R.id.imageview));
                    break;
                case COMMON_IMGTEXT:
                    holder.setText(R.id.textview, data.getTitle());
                    holder.setText(R.id.tv_content, data.getContentTxt());
                    GlideApp.with(UiUtils.getContext()).load(data.getImgUrl()).into((ImageView) holder.getView(R.id.imageview));
                    break;
            }
        }
    };

    @Override
    public void addListener() {

    }

    @Override
    public void refreshUi() {

    }

    @Override
    public void release() {

    }

    @Override
    public void initArguments(Bundle bundle) {

    }
}
