package com.example.wav.sample;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wav.R;
import com.example.wav.bean.AccountDeviceInfo;
import com.example.wav.mvp.contract.DevListCotract;
import com.example.wav.mvp.presenter.DevListPresenter;
import com.example.wav.ui.activity.DeviceDetailActivity;
import com.zhiyangstudio.commonlib.mvp.BaseMVPSupportFragment;
import com.zhiyangstudio.commonlib.utils.IntentUtils;
import com.zhiyangstudio.commonlib.utils.UiUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import me.fangx.haorefresh.HaoRecyclerView;

/**
 * Created by example on 2018/5/9.
 */

public class DevListFragment1 extends BaseMVPSupportFragment<DevListPresenter, DevListCotract
        .IDevListView> implements DevListCotract.IDevListView {

    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.hao_recycleview)
    HaoRecyclerView mHaoRecyclerView;
    private int mPage = 1;
    private boolean hasNoMoreData;
    private List<AccountDeviceInfo.DeviceDetailInfo> mList = new ArrayList<>();
    private boolean hasNoData;
    private DevListAdapter mAdapter;
    private int mTotal;

    @Override
    public int getContentId() {
        return R.layout.fragment_dev_list1;
    }

    @Override
    public void initView() {
        setRefresh();
    }

    @Override
    public void addListener() {

    }

    @Override
    public void initData() {
        mPresenter.loadList();
    }

    @Override
    public void refreshUi() {
        if (hasNoMoreData) {
//            mHaoRecyclerView.loadMoreEnd();
        }

        if (hasNoData) {
            // TODO: 2018/5/9 显示空布局
        }

        mAdapter.setNewData(mList);
        if (mPage == 1) {
            mHaoRecyclerView.refreshComplete();
            mRefreshLayout.setRefreshing(false);
        } else {
            mAdapter.notifyDataSetChanged();
            mHaoRecyclerView.loadMoreComplete();
        }
    }

    private void setRefresh() {
        mRefreshLayout.setColorSchemeResources(R.color.textBlueDark, R.color.textBlueDark, R
                        .color.textBlueDark,
                R.color.textBlueDark);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mHaoRecyclerView.setLayoutManager(layoutManager);

        mHaoRecyclerView.addItemDecoration(new DividerItemDecoration(mContext,
                DividerItemDecoration.VERTICAL));

        View footerView = UiUtils.inflateView(R.layout.item_root_loading_more);
        mHaoRecyclerView.setFootLoadingView(footerView);

        View noMoreDataView = UiUtils.inflateView(R.layout.item_footer_nomore);
        mHaoRecyclerView.setFootEndView(noMoreDataView);
        mRefreshLayout.setOnRefreshListener(() -> {
            UiUtils.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // 重置
                    mPage = 1;
                    initData();
                }
            }, 500);
        });
        mHaoRecyclerView.setLoadMoreListener(() -> {
            UiUtils.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (mList.size() == mTotal) {
                        mHaoRecyclerView.loadMoreEnd();
                        return;
                    }
                    mPage++;
                    initData();
                }
            }, 800);
        });
        mAdapter = new DevListAdapter(mList);
        mHaoRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected DevListPresenter createPresenter() {
        return new DevListPresenter();
    }

    @Override
    protected void initArguments(Bundle bundle) {

    }

    @Override
    public void showLoading(String msg) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showFail(String msg) {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public int getStatus() {
        return 0;
    }

    @Override
    public int getPage() {
        return mPage;
    }

    @Override
    public int getPageSize() {
        return 10;
    }

    @Override
    public void setData(List<AccountDeviceInfo.DeviceDetailInfo> list) {
        if (mPage == 1) {
            mList.clear();
        }
        if (list == null || list.size() == 0) {
            if (mPage == 1) {
                // TODO: 2018/5/9 没有数据
                hasNoData = true;
            } else {
                // TODO: 2018/5/9 没有更多数据
                hasNoMoreData = true;
            }
        }
        mList.addAll(list);
        refreshUi();
    }

    @Override
    public void showContent() {

    }

    @Override
    public void setDataCount(int total) {
        mTotal = total;
    }

    @Override
    public void showNoMoreData() {

    }

    private class DevListAdapter extends BaseQuickAdapter<AccountDeviceInfo.DeviceDetailInfo,
            BaseViewHolder> {

        public DevListAdapter(List<AccountDeviceInfo.DeviceDetailInfo> data) {
            super(R.layout.layout_item_device_list_online, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, AccountDeviceInfo.DeviceDetailInfo bean) {
            helper.setText(R.id.tv_device_title, bean.getName());
            helper.setText(R.id.tv_device_state, bean.getOnlineText());
            SimpleDateFormat sdfDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssss");
            try {
                Date date = sdfDateFormat.parse(bean.getUpdataDate());
                SimpleDateFormat sdfDateFormat1 = new SimpleDateFormat("HH:mm");
                String timeStr = sdfDateFormat1.format(date);
                helper.setText(R.id.tv_device_time, timeStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            helper.setOnClickListener(R.id.rl_root, v -> {
                Bundle bundle = new Bundle();
                bundle.putString("devName", bean.getName());
                bundle.putString("devID", bean.getID());
                IntentUtils.forward(DeviceDetailActivity.class, bundle);
            });
        }
    }
}
