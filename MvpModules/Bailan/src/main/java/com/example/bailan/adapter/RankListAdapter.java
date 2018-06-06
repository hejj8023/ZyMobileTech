package com.example.bailan.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bailan.Const;
import com.example.bailan.R;
import com.example.bailan.bean.MenuItemInfo;
import com.example.bailan.bean.RankBean;
import com.example.bailan.widget.GridMenuRecyclerView;
import com.example.bailan.widget.MenuWidget;
import com.zysdk.vulture.clib.utils.LoggerUtils;
import com.zysdk.vulture.clib.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by example on 2018/5/31.
 */

public class RankListAdapter extends BaseMultiItemQuickAdapter<RankBean.RankLayoutData, BaseViewHolder> {
    public RankListAdapter(List<RankBean.RankLayoutData> list) {
        super(list);
    }

    @Override
    public int getItemViewType(int position) {
        int itemViewType = super.getItemViewType(position);
        int layoutId = 0;
        switch (itemViewType) {
            case Const.LIST_ITEM_COLLECTS.ITEM_RANK_LIST.ITEM_RANK_LIST:
                layoutId = R.layout.layout_item_category_sub_list;
                break;
            case Const.LIST_ITEM_COLLECTS.ITEM_RANK_LIST.ITEM_RANK_MENU:
                layoutId = R.layout.layout_item_category_menu;
                break;
            case Const.LIST_ITEM_COLLECTS.ITEM_RANK_LIST.ITEM_RANK_TITLE_DIVIDER:
                layoutId = R.layout.layout_item_category_divider;
                break;
        }
        if (itemViewType != 0 && layoutId != 0) {
            addItemType(itemViewType, layoutId);
        }
        return itemViewType;
    }

    @Override
    protected void convert(BaseViewHolder helper, RankBean.RankLayoutData item) {
        int position = helper.getPosition();
        int itemViewType = getItemViewType(position);
        List<RankBean.RankLayoutDataItem> dataList = item.getDataList();
        switch (itemViewType) {
            case Const.LIST_ITEM_COLLECTS.ITEM_RANK_LIST.ITEM_RANK_LIST:
                RecyclerView recyclerView = helper.getView(R.id.rv_item_category_menu);
                if (recyclerView != null) {
                    LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
                    layoutManager.setAutoMeasureEnabled(true);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(new BaseQuickAdapter<RankBean.RankLayoutDataItem,
                            BaseViewHolder>(R.layout.layout_item_category_list, dataList) {

                        @Override
                        protected void convert(BaseViewHolder helper, RankBean.RankLayoutDataItem dataItem) {
                            LoggerUtils.loge("sub lise convert position = " + helper.getPosition() + " , dataList " +
                                    "size = " + dataList.size());
                            MenuWidget menuWidget = helper.getView(R.id.mw_item_category);
                            menuWidget.setLeftIcon(dataItem.getIcon());
                            menuWidget.setTitle(dataItem.getName());
                            menuWidget.setLeftIconSize(UiUtils.dp2px(48), UiUtils.dp2px(48));

                            // TODO: 2018/5/31 最后一条去除分割线
                            if (helper.getPosition() == dataList.size() - 1) {
                                menuWidget.hideDivider2();
                            }
                        }
                    });
                }
                break;
            case Const.LIST_ITEM_COLLECTS.ITEM_RANK_LIST.ITEM_RANK_MENU:
                if (dataList != null && dataList.size() > 0) {
                    GridMenuRecyclerView gridMenuRecyclerView = helper.getView(R.id.gmrv_item_category_menu);
                    List<MenuItemInfo> menuList = new ArrayList<>();
                    MenuItemInfo menuItemInfo = null;
                    for (RankBean.RankLayoutDataItem dataItem : dataList) {
                        menuItemInfo = new MenuItemInfo();
                        menuItemInfo.setIconUrl(dataItem.getIcon());
                        menuItemInfo.setName(dataItem.getName());
                        menuList.add(menuItemInfo);
                    }
                    gridMenuRecyclerView.setData(menuList);
                }
                break;
            case Const.LIST_ITEM_COLLECTS.ITEM_RANK_LIST.ITEM_RANK_TITLE_DIVIDER:
                if (dataList != null && dataList.size() > 0) {
                    RankBean.RankLayoutDataItem layoutDataItem = dataList.get(0);
                    helper.setText(R.id.tv_divider_name, layoutDataItem.getName());
                }
                break;
        }
    }
}
