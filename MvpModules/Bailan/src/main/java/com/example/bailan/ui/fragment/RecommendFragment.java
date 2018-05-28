package com.example.bailan.ui.fragment;

import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bailan.Const;
import com.example.bailan.R;
import com.example.bailan.base.BaseDaggerSupportListFragment;
import com.example.bailan.bean.MenuItemInfo;
import com.example.bailan.bean.RecommendBean;
import com.example.bailan.mvp.contract.RecommendContract;
import com.example.bailan.mvp.presenter.RecommendPresenter;
import com.example.bailan.widget.GridMenuRecyclerView;
import com.zhiyangstudio.commonlib.glide.GlideApp;
import com.zhiyangstudio.commonlib.utils.LoggerUtils;
import com.zhiyangstudio.commonlib.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by example on 2018/5/25.
 */

public class RecommendFragment extends BaseDaggerSupportListFragment<RecommendPresenter,
        RecommendContract.IListView, RecommendBean.LayoutDataBean> implements RecommendContract
        .IListView {

    @Override
    protected void inject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected boolean hasEnableRereshAndLoadMore() {
        return false;
    }

    @Override
    protected void loadRemoteData() {
        mPresenter.loadListData();
    }

    @Override
    protected void initPageNumb() {

    }

    @Override
    protected BaseQuickAdapter<RecommendBean.LayoutDataBean, BaseViewHolder> getListAdapter() {
        RecommendListAdapter listAdapter = new RecommendListAdapter(mList);
        // TODO: 2018/5/28 使用多条目类型
        return listAdapter;
    }


    private class PagerSnapHelper extends LinearSnapHelper {

        @Override
        public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
            int targetPos = super.findTargetSnapPosition(layoutManager, velocityX, velocityY);
            final View currentView = findSnapView(layoutManager);
            if(targetPos != RecyclerView.NO_POSITION && currentView != null){
                int currentPostion = layoutManager.getPosition(currentView);
                int first = ((LinearLayoutManager)layoutManager).findFirstVisibleItemPosition();
                int last = ((LinearLayoutManager)layoutManager).findLastVisibleItemPosition();
                currentPostion = targetPos < currentPostion ? last : (targetPos > currentPostion ? first : currentPostion);
                targetPos = targetPos < currentPostion ? currentPostion - 1 : (targetPos > currentPostion ? currentPostion + 1 : currentPostion);
            }
            return targetPos;
        }
    }

    public class RecommendListAdapter extends BaseMultiItemQuickAdapter<RecommendBean
            .LayoutDataBean, BaseViewHolder> {

        GradientDrawable defaultDrawable, selectedDrawable;
        int size = 0;

        public RecommendListAdapter(List<RecommendBean.LayoutDataBean> data) {
            super(data);
            LoggerUtils.loge("RecommendListAdapter contr");
        }

        @Override
        public int getItemViewType(int position) {
            LoggerUtils.loge("RecommendListAdapter getItemViewType");
            int itemViewType = super.getItemViewType(position);
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
        protected void convert(BaseViewHolder helper, RecommendBean.LayoutDataBean item) {
            LoggerUtils.loge("RecommendListAdapter convert");
            int itemViewType = getItemViewType(helper.getPosition());
            List<RecommendBean.LayoutDataBean.DataListBean> dataList = item.getDataList();
            switch (itemViewType) {
                // 轮播图
                case Const.LIST_ITEM_COLLECTS.ITEM_RECOMMON_LIST.ITEM_TYPE_BANNER:
                    RecyclerView recyclerView = helper.getView(R.id.rcv_banner);
                    size = UiUtils.dp2px(112);

                    defaultDrawable = new GradientDrawable();
                    defaultDrawable.setSize(size, size);
                    defaultDrawable.setCornerRadius(size);
                    defaultDrawable.setColor(0xffffffff);
                    selectedDrawable = new GradientDrawable();
                    selectedDrawable.setSize(size, size);
                    selectedDrawable.setCornerRadius(size);
                    selectedDrawable.setColor(0xff0094ff);

                    new PagerSnapHelper().attachToRecyclerView(recyclerView);

                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                            LinearLayoutManager.HORIZONTAL, false));

                    recyclerView.setAdapter(new BaseQuickAdapter<RecommendBean.LayoutDataBean
                            .DataListBean, BaseViewHolder>(R.layout.layout_item_recomend_banner,
                            dataList) {
                        @Override
                        protected void convert(BaseViewHolder helper, RecommendBean
                                .LayoutDataBean.DataListBean listBean) {
                            ImageView view = helper.getView(R.id.iv_banner);
                            String iconUrl = listBean.getIcon();
                            LoggerUtils.loge("iconUrl = " + iconUrl);
                            GlideApp.with(getContext()).load(iconUrl).into(view);
                        }
                    });

                    break;
                // 两个图片的广告
                case Const.LIST_ITEM_COLLECTS.ITEM_RECOMMON_LIST.ITEM_TYPE_AD:
                    ImageView imageView1 = helper.getView(R.id.iv1_item_list_recommend);
                    ImageView imageView2 = helper.getView(R.id.iv2_item_list_recommend);
                    List<RecommendBean.LayoutDataBean.DataListBean> list = dataList;
                    for (int i = 0; i < list.size(); i++) {
                        if (i == 0) {
                            String icon = list.get(i).getIcon();
                            GlideApp.with(mContext).load(icon).into(imageView1);
                        } else {
                            String icon = list.get(i).getIcon();
                            GlideApp.with(mContext).load(icon).into(imageView2);
                        }
                    }
                    break;
                // 正常条目
                case Const.LIST_ITEM_COLLECTS.ITEM_RECOMMON_LIST.ITEM_TYPE_COMMON:
                    helper.setText(R.id.tv_title_recomment_item_list, item.getName());
                    GridMenuRecyclerView grv = helper.getView(R.id.grv_recomment_item_list);
                    grv.setSpanCount(5);
                    List<MenuItemInfo> menuItemInfos = new ArrayList<>();
                    MenuItemInfo info = null;
                    for (int i = 0; i < 10; i++) {
                        info = new MenuItemInfo();
                        info.setName("test " + i);
                        menuItemInfos.add(info);
                    }
                    grv.setData(menuItemInfos);
                    break;
            }
        }
    }

}