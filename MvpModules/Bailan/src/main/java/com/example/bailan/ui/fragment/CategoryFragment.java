package com.example.bailan.ui.fragment;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bailan.Const;
import com.example.bailan.R;
import com.example.bailan.base.BaseDaggerSupportListFragment;
import com.example.bailan.bean.CategoryBean;
import com.example.bailan.mvp.contract.CategoryContract;
import com.example.bailan.mvp.presenter.CategoryPresenter;

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
                        layoutId = R.layout.layout_item_category_list;
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

            }
        };
    }

}
