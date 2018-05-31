package com.example.bailan.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bailan.Const;
import com.example.bailan.R;
import com.example.bailan.bean.MenuItemInfo;
import com.example.bailan.bean.RecommonBean;
import com.example.bailan.widget.GridMenuRecyclerView;
import com.example.bailan.widget.RecyclerBanner;
import com.zhiyangstudio.commonlib.glide.GlideUtils;
import com.zhiyangstudio.commonlib.utils.LoggerUtils;

import java.util.ArrayList;
import java.util.List;

public class RecommendListAdapter extends BaseMultiItemQuickAdapter<RecommonBean.RecommendLayoutData, BaseViewHolder> {

    public RecommendListAdapter(List<RecommonBean.RecommendLayoutData> data) {
        super(data);
    }

    @Override
    public int getItemViewType(int position) {
        int itemViewType = super.getItemViewType(position);
        RecommonBean.RecommendLayoutData data = mData.get(position);
        int layoutId = -1;

        switch (itemViewType) {
            // 轮播图
            case Const.LIST_ITEM_COLLECTS.ITEM_RECOMMON_LIST.ITEM_TYPE_BANNER:
                layoutId = R.layout.layout_item_recommend_bannder;
                break;
            // 两个图片的广告
            case Const.LIST_ITEM_COLLECTS.ITEM_RECOMMON_LIST.ITEM_TYPE_AD:
                layoutId = R.layout.layout_item_recommend_ad;
                break;
            // 正常条目
            case Const.LIST_ITEM_COLLECTS.ITEM_RECOMMON_LIST.ITEM_TYPE_COMMON:
                layoutId = R.layout.layout_item_recommend_common;
                break;
        }
        if (itemViewType != -1 && layoutId != -1) {
            addItemType(itemViewType, layoutId);
        }
        return itemViewType;
    }

    @Override
    protected void convert(BaseViewHolder helper, RecommonBean.RecommendLayoutData item) {
        LoggerUtils.loge("RecommendListAdapter convert");
        int itemViewType = getItemViewType(helper.getPosition());
        List<RecommonBean.RecommendLayoutDataItem> dataList = item.getDataList();
        switch (itemViewType) {
            // 轮播图
            case Const.LIST_ITEM_COLLECTS.ITEM_RECOMMON_LIST.ITEM_TYPE_BANNER:
                RecyclerBanner recyclerView = helper.getView(R.id.rb_banner);
                List<RecyclerBanner.BannerEntity> tList = new ArrayList<>();
                RecyclerBanner.BannerEntity entity = null;
                for (RecommonBean.RecommendLayoutDataItem recommendLayoutDataItem : dataList) {
                    entity = new RecyclerBanner.BannerEntity();
                    entity.setUrl(recommendLayoutDataItem.getIcon());
                    tList.add(entity);
                }
                recyclerView.setDatas(tList);
                break;
            // 两个图片的广告
            case Const.LIST_ITEM_COLLECTS.ITEM_RECOMMON_LIST.ITEM_TYPE_AD:
                ImageView imageView1 = helper.getView(R.id.iv1_item_list_recommend);
                ImageView imageView2 = helper.getView(R.id.iv2_item_list_recommend);
                List<RecommonBean.RecommendLayoutDataItem> list = dataList;
                for (int i = 0; i < list.size(); i++) {
                    String icon = list.get(i).getIcon();
                    if (i == 0) {
                        GlideUtils.loadPic(mContext, icon, imageView1);
                    } else {
                        GlideUtils.loadPic(mContext, icon, imageView2);
                    }
                }
                break;
            // 普通条目
            case Const.LIST_ITEM_COLLECTS.ITEM_RECOMMON_LIST.ITEM_TYPE_COMMON:
                helper.setText(R.id.tv_title_recomment_item_list, item.getName());
                GridMenuRecyclerView grv = helper.getView(R.id.grv_recomment_item_list);
                grv.setSpanCount(5);

                RecommonBean.RecommendLayoutDataItem recommendLayoutDataItem = dataList.get(0);
                if (recommendLayoutDataItem != null) {
                    List<RecommonBean.LayoutItemSubBean> itemSubBeans = recommendLayoutDataItem.getList();
                    if (itemSubBeans != null && itemSubBeans.size() > 0) {
                        List<MenuItemInfo> menuItemInfos = new ArrayList<>();
                        MenuItemInfo info = null;
                        for (int i = 0; i < itemSubBeans.size(); i++) {
                            RecommonBean.LayoutItemSubBean layoutItemSubBean = itemSubBeans.get(i);
                            info = new MenuItemInfo();
                            info.setName(layoutItemSubBean.getName());
                            info.setIconUrl(layoutItemSubBean.getIcon());
                            menuItemInfos.add(info);
                        }
                        grv.setData(menuItemInfos);
                    }
                }
                break;
        }
    }
}