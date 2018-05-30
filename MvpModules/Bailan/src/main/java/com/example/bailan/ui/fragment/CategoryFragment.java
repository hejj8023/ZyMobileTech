package com.example.bailan.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bailan.Const;
import com.example.bailan.R;
import com.example.bailan.base.BaseDaggerSupportListFragment;
import com.example.bailan.bean.CategoryBean;
import com.example.bailan.bean.MenuItemInfo;
import com.example.bailan.mvp.contract.CategoryContract;
import com.example.bailan.mvp.presenter.CategoryPresenter;
import com.example.bailan.widget.GridMenuRecyclerView;
import com.example.bailan.widget.MenuWidget;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by example on 2018/5/25.
 */

public class CategoryFragment extends BaseDaggerSupportListFragment<CategoryPresenter, CategoryContract
        .ICategoryView, CategoryBean.CategoryLayoutData> implements CategoryContract.ICategoryView {
    @Override
    protected void inject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected boolean hasSupportItemDivider() {
        return false;
    }

    @Override
    protected boolean hasEnableRereshAndLoadMore() {
        return false;
    }

    @Override
    protected void loadRemoteData() {
        mPresenter.getData();
    }

    @Override
    protected void initPageNumb() {

    }

    @Override
    protected BaseQuickAdapter<CategoryBean.CategoryLayoutData, BaseViewHolder> getListAdapter() {
        return new BaseMultiItemQuickAdapter<CategoryBean.CategoryLayoutData, BaseViewHolder>(mList) {

            @Override
            public int getItemViewType(int position) {
                int itemViewType = super.getItemViewType(position);
                int layoutId = 0;
                switch (itemViewType) {
                    case Const.LIST_ITEM_COLLECTS.ITEM_CATEGORY_LIST.ITEM_CATEGORY_LIST:
                        layoutId = R.layout.layout_item_category_sub_list;
                        break;
                    case Const.LIST_ITEM_COLLECTS.ITEM_CATEGORY_LIST.ITEM_CATEGORY_MENU:
                        layoutId = R.layout.layout_item_category_menu;
                        break;
                    case Const.LIST_ITEM_COLLECTS.ITEM_CATEGORY_LIST.ITEM_CATEGORY_TITLE_DIVIDER:
                        layoutId = R.layout.layout_item_category_divider;
                        break;
                }
                if (itemViewType != 0 && layoutId != 0) {
                    addItemType(itemViewType, layoutId);
                }
                return itemViewType;
            }

            @Override
            protected void convert(BaseViewHolder helper, CategoryBean.CategoryLayoutData item) {
                int position = helper.getPosition();
                int itemViewType = getItemViewType(position);
                List<CategoryBean.CategoryDataItem> dataList = item.getDataList();
                switch (itemViewType) {
                    case Const.LIST_ITEM_COLLECTS.ITEM_CATEGORY_LIST.ITEM_CATEGORY_LIST:
                        RecyclerView recyclerView = helper.getView(R.id.rv_item_category_menu);
                        if (recyclerView != null) {
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                            recyclerView.setAdapter(new BaseQuickAdapter<CategoryBean.CategoryDataItem,
                                    BaseViewHolder>(R
                                    .layout.layout_item_category_list, dataList) {

                                @Override
                                protected void convert(BaseViewHolder helper, CategoryBean.CategoryDataItem dataItem) {
                                    MenuWidget menuWidget = helper.getView(R.id.mw_item_category);
                                    menuWidget.setLeftIcon(dataItem.getIcon());
                                    menuWidget.setTitle(dataItem.getName());
                                }
                            });
                        }

                        break;
                    case Const.LIST_ITEM_COLLECTS.ITEM_CATEGORY_LIST.ITEM_CATEGORY_MENU:
                        if (dataList != null && dataList.size() > 0) {
                            GridMenuRecyclerView gridMenuRecyclerView = helper.getView(R.id.gmrv_item_category_menu);
                            List<MenuItemInfo> menuList = new ArrayList<>();
                            MenuItemInfo menuItemInfo = null;
                            for (CategoryBean.CategoryDataItem dataItem : dataList) {
                                menuItemInfo = new MenuItemInfo();
                                menuItemInfo.setIconUrl(dataItem.getIcon());
                                menuItemInfo.setName(dataItem.getName());
                                menuList.add(menuItemInfo);
                            }
                            gridMenuRecyclerView.setData(menuList);
                        }
                        break;
                    case Const.LIST_ITEM_COLLECTS.ITEM_CATEGORY_LIST.ITEM_CATEGORY_TITLE_DIVIDER:

                        break;
                }
            }
        };
    }


}
