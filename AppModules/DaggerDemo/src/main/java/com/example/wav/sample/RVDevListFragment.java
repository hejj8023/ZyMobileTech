package com.example.wav.sample;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.wav.R;
import com.example.wav.bean.AccountDeviceInfo;
import com.example.wav.mvp.contract.DevListCotract;
import com.example.wav.mvp.presenter.DevListPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhiyangstudio.commonlib.adapter.BaseRecyclerAdapter;
import com.zhiyangstudio.commonlib.adapter.SmartViewHolder;
import com.zhiyangstudio.commonlib.mvp.BaseMVPSupportFragment;
import com.zhiyangstudio.commonlib.utils.UiUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import ezy.ui.layout.LoadingLayout;
import io.reactivex.annotations.NonNull;

/**
 * Created by example on 2018/5/10.
 * SmartRefreshLayout + RecyclerView
 */

public class RVDevListFragment extends BaseMVPSupportFragment<DevListPresenter, DevListCotract
        .IDevListView> implements DevListCotract.IDevListView {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.loading)
    LoadingLayout loadingLayout;

    private int mPage = 1;
    private List<AccountDeviceInfo.DeviceDetailInfo> mList = new ArrayList<>();
    private BaseRecyclerAdapter<AccountDeviceInfo.DeviceDetailInfo> mAdapter;
    private int mDataCount;

    @Override
    public int getContentId() {
        return R.layout.fragment_basic_rv_dev_list;
    }

    @Override
    public void initView() {
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));

//        refreshLayout.setRefreshHeader(new ClassicsHeader(getContext()).setSpinnerStyle
//                (SpinnerStyle.FixedBehind).setPrimaryColorId(R.color.colorPrimary)
//                .setAccentColorId(android.R.color.white));
    }

    @Override
    public void addListener() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPage = 1;
                        loadRemoteData();
                    }
                }, 2000);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mList.size() >= mDataCount) {
                            ToastUtils.showShort("数据全部加载完毕");
                            refreshLayout.finishLoadMoreWithNoMoreData();//将不会再次触发加载更多事件
                        } else {
                            mPage++;
                            loadRemoteData();
                        }
                    }
                }, 2000);
            }
        });
    }

    @Override
    public void initData() {
        mAdapter = new BaseRecyclerAdapter<AccountDeviceInfo.DeviceDetailInfo>(mList, R
                .layout.layout_item_device_list_online) {
            @Override
            protected void onBindViewHolder(SmartViewHolder holder, AccountDeviceInfo
                    .DeviceDetailInfo model, int position) {
                holder.text(R.id.tv_device_title, model.getName());
                holder.text(R.id.tv_device_state, model.getOnlineText());
                SimpleDateFormat sdfDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssss");
                try {
                    Date date = sdfDateFormat.parse(model.getUpdataDate());
                    SimpleDateFormat sdfDateFormat1 = new SimpleDateFormat("HH:mm");
                    String timeStr = sdfDateFormat1.format(date);
                    holder.text(R.id.tv_device_time, timeStr);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        };
        // TODO: 2018/5/10 可用上面的这个也可以用自己写的这个
        mRecyclerView.setAdapter(mAdapter);
        refreshLayout.setEnableAutoLoadMore(false);//开启自动加载功能（非必须）
        //触发自动刷新

        UiUtils.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadRemoteData();
            }
        }, 3000);

    }

    @Override
    public void refreshUi() {

    }

    private void loadRemoteData() {
        mPresenter.loadList();
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
            } else {
                // TODO: 2018/5/9 没有更多数据
            }
        }
        mList.addAll(list);

        if (mPage == 1) {
            mAdapter.refresh(mList);
            refreshLayout.finishRefresh();
            refreshLayout.setNoMoreData(false);

            if (loadingLayout != null) {
                loadingLayout.showContent();
            }
        } else {
            mAdapter.loadMore(mList);
            refreshLayout.finishLoadMore();
        }
    }

    @Override
    public void showContent() {

    }

    @Override
    public void setDataCount(int total) {
        this.mDataCount = total;
    }

    @Override
    public void showNoMoreData() {

    }

    @Override
    protected DevListPresenter createPresenter() {
        return new DevListPresenter();
    }

    @Override
    protected void initArguments(Bundle bundle) {

    }
}
