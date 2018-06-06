package com.example.wanandroid.adapter;

import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wanandroid.Const;
import com.example.wanandroid.R;
import com.example.wanandroid.bean.BannerBean;
import com.example.wanandroid.manager.GlideLoaderManager;
import com.example.wanandroid.utils.CommonInternalUtil;
import com.zysdk.vulture.clib.utils.UiUtils;

import java.util.List;

/**
 * Created by example on 2018/4/11.
 */

public class BannerAdapter extends PagerAdapter {

    private SparseArray<View> mViews;
    private List<BannerBean> mList;

    public BannerAdapter(List<BannerBean> list) {
        this.mList = list;
        mViews = new SparseArray<>();
    }

    @Override
    public int getCount() {
        if (mList == null)
            return 0;
        return mList.size() <= 1 ? mList.size() : Integer.MAX_VALUE;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mViews.get(position);
        if (view == null) {
            position %= mList.size();
            BannerBean bannerBean = mList.get(position);
            view = UiUtils.inflateView(R.layout.layout_item_banner, container);
            ImageView imageView = view.findViewById(R.id.img);
            GlideLoaderManager.loadImage(imageView, bannerBean.getImagePath(), Const.IMAGE_LOADER
                    .NOMAL_IMG);
            TextView textView = view.findViewById(R.id.title);
            textView.setText(bannerBean.getTitle());
            view.setOnClickListener(v -> {
                CommonInternalUtil.goWebView(bannerBean.getTitle(),bannerBean.getUrl());
            });
            mViews.put(position, view);
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public void notifyDatas(List<BannerBean> list) {
        this.mList = list;
        notifyDataSetChanged();
    }
}
