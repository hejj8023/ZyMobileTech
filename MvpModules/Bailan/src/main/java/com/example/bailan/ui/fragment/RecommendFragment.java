package com.example.bailan.ui.fragment;

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
import com.example.bailan.widget.RecyclerBanner;
import com.zhiyangstudio.commonlib.glide.GlideApp;
import com.zhiyangstudio.commonlib.utils.LoggerUtils;

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

    public class RecommendListAdapter extends BaseMultiItemQuickAdapter<RecommendBean
            .LayoutDataBean, BaseViewHolder> {

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
                    RecyclerBanner recyclerView = helper.getView(R.id.rb_banner);

                    List<RecyclerBanner.BannerEntity> tList = new ArrayList<>();
                    RecyclerBanner.BannerEntity dataListBean = new RecommendBean
                            .LayoutDataBean.DataListBean();
                    dataListBean.setUrl("http://f.hiphotos.baidu" +
                            ".com/image/h%3D300/sign=4a6f284cb88f8c54fcd3c32f0a282dee" +
                            "/c9fcc3cec3fdfc03777b0d1ad83f8794a4c22615.jpg");
                    tList.add(dataListBean);

                    dataListBean = new RecommendBean
                            .LayoutDataBean.DataListBean();
                    dataListBean.setUrl("https://ss0.bdstatic" +
                            ".com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1436948145," +
                            "4270509323&fm=200&gp=0.jpg");
                    tList.add(dataListBean);

                    dataListBean = new RecommendBean
                            .LayoutDataBean.DataListBean();
                    dataListBean.setUrl("http://d.hiphotos.baidu" +
                            ".com/image/h%3D300/sign=181318733ad12f2ed105a8607fc0d5ff" +
                            "/94cad1c8a786c9171cc12b9dc53d70cf3ac75757.jpg");
                    tList.add(dataListBean);

                    dataListBean = new RecommendBean
                            .LayoutDataBean.DataListBean();
                    dataListBean.setUrl("https://ss1.bdstatic" +
                            ".com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=994139724," +
                            "1381627717&fm=200&gp=0.jpg");
                    tList.add(dataListBean);

                    dataListBean = new RecommendBean
                            .LayoutDataBean.DataListBean();
                    dataListBean.setUrl("https://ss2.bdstatic" +
                            ".com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2546717774," +
                            "3910079056&fm=200&gp=0.jpg");
                    tList.add(dataListBean);

                    dataListBean = new RecommendBean
                            .LayoutDataBean.DataListBean();
                    dataListBean.setUrl("https://ss0.bdstatic" +
                            ".com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3491324078," +
                            "3983383560&fm=200&gp=0.jpg");
                    tList.add(dataListBean);
                    recyclerView.setDatas(tList);

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