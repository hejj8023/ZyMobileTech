package com.example.wav.sample;

import android.os.Bundle;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wav.R;
import com.example.wav.bean.AccountDeviceInfo;
import com.example.wav.mvp.contract.DevListCotract;
import com.example.wav.mvp.presenter.DevListPresenter;
import com.example.wav.ui.activity.DeviceDetailActivity;
import com.zhiyangstudio.commonlib.refreshsupport.haorefresh.BaseAbsHRMvpListFragment;
import com.zhiyangstudio.commonlib.utils.IntentUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by example on 2018/5/9.
 */

public class DevListFragment2 extends BaseAbsHRMvpListFragment<DevListPresenter,
        DevListCotract.IDevListView, AccountDeviceInfo.DeviceDetailInfo> implements
        DevListCotract.IDevListView {

    @Override
    public void refreshUi() {

    }

    @Override
    public void showFail(String msg) {

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
            mListData.clear();
        }
        mListData.addAll(list);
        mAdapter.setNewData(mListData);
        if (mPage > 1) {
            mHaoRecyclerView.loadMoreComplete();
        } else {
            refreshLayout.setRefreshing(false);
            mHaoRecyclerView.refreshComplete();
        }
    }

    @Override
    public void showContent() {

    }

    @Override
    public void setDataCount(int total) {
        maxDataCount = total;
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

    @Override
    protected void loadData() {
        mPage = 1;
        mPresenter.loadList();
    }

    @Override
    protected void loadMore() {
        // TODO: 2018/5/10 delay请求
        mPresenter.loadList();
    }

    @Override
    protected BaseQuickAdapter getListAdapter(List list) {
        return new DevListAdapter(list);
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
