package com.example.wav.sample;

import android.os.Bundle;
import android.widget.AbsListView;

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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import io.reactivex.annotations.NonNull;

/**
 * Created by example on 2018/5/10.
 * SmartRefreshLayout + listView
 */

public class LVDevListFragment extends BaseMVPSupportFragment<DevListPresenter, DevListCotract
        .IDevListView> implements DevListCotract.IDevListView {
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.listView)
    AbsListView mListView;
    private int mPage = 1;
    private List<AccountDeviceInfo.DeviceDetailInfo> mList = new ArrayList<>();
    private BaseRecyclerAdapter<AccountDeviceInfo.DeviceDetailInfo> mAdapter;
    private int mDataCount;

    @Override
    public int getContentId() {
        return R.layout.fragment_basic_dev_list;
    }

    @Override
    public void initView() {
        refreshLayout.setEnableAutoLoadMore(true);//开启自动加载功能（非必须）
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
        mListView.setAdapter(mAdapter);
//        mListView.setAdapter(new QuickAdapter<AccountDeviceInfo.DeviceDetailInfo>(mContext,
// mList, R
//                .layout.layout_item_device_list_online) {
//            @Override
//            protected void convert(QuickViewHolder holder, AccountDeviceInfo.DeviceDetailInfo
//                    model, int position) {
//                holder.setText(R.id.tv_device_title, model.getName());
//                holder.setText(R.id.tv_device_state, model.getOnlineText());
//                SimpleDateFormat sdfDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssss");
//                try {
//                    Date date = sdfDateFormat.parse(model.getUpdataDate());
//                    SimpleDateFormat sdfDateFormat1 = new SimpleDateFormat("HH:mm");
//                    String timeStr = sdfDateFormat1.format(date);
//                    holder.setText(R.id.tv_device_time, timeStr);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//            }
//        });

        refreshLayout.setEnableAutoLoadMore(true);//开启自动加载功能（非必须）
        //触发自动刷新
        refreshLayout.autoRefresh();

//        loadRemoteData();
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
