package com.example.wav.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.example.wav.R;
import com.example.wav.base.BaseDaggerSupportListFragment;
import com.example.wav.bean.AccountDeviceInfo;
import com.example.wav.mvp.contract.HomeFragmentContract;
import com.example.wav.mvp.presenter.HomeFragmentPresenter;
import com.example.wav.ui.activity.DeviceDetailActivity;
import com.zhiyangstudio.commonlib.CommonConst;
import com.zhiyangstudio.commonlib.adapter.BaseListAdapter;
import com.zhiyangstudio.commonlib.adapter.lgrcommon.QuickViewHolder;
import com.zhiyangstudio.commonlib.utils.IntentUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by example on 2018/4/28.
 */

public class HomeFragment extends BaseDaggerSupportListFragment<HomeFragmentPresenter,
        HomeFragmentContract.IHomeFragmentView, AccountDeviceInfo.DeviceDetailInfo> implements
        HomeFragmentContract.IHomeFragmentView {

    private int mDataCount;

    @Override
    public void onResume() {
        super.onResume();
        state = CommonConst.PAGE_STATE.STATE_REFRESH;
        loadDatas();
    }

    @Override
    public void loadDatas() {
        if (state == CommonConst.PAGE_STATE.STATE_REFRESH) {
            isAutoLoadMore = true;
            page = 1;
        } else {
            page++;
            if (mListAdapter.getItemCount() >= mDataCount) {
                ToastUtils.showShort("没有更多数据了...");
                recyclerView.showNoMoreData();
                isAutoLoadMore = false;
                return;
            }
        }

        mPresenter.loadDeviceList();
    }

    @Override
    protected boolean isCanLoadMore() {
        return true;
    }

    @Override
    protected BaseListAdapter getListAdapter() {
        return new DeviceListAdapter();
    }

    @Override
    protected View initHeaderView() {
        return null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initArguments(Bundle bundle) {
    }

    @Override
    public void addListener() {

    }

    @Override
    public void initData() {
        isAutoLoadMore = true;
    }

    @Override
    public void refreshUi() {

    }

    @Override
    public void setData(List<AccountDeviceInfo.DeviceDetailInfo> data) {
        if (state == CommonConst.PAGE_STATE.STATE_REFRESH) {
            mListData.clear();
        }
        mListData.addAll(data);
    }

    @Override
    protected void inject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public int getStatus() {
        return 0;
    }

    @Override
    public int getPageSize() {
        return 10;
    }

    @Override
    public void setDataCount(int total) {
        mDataCount = total;
    }

    @Override
    public void showNoMoreData() {
        isAutoLoadMore = false;
    }

    private class DeviceListAdapter extends BaseListAdapter<AccountDeviceInfo.DeviceDetailInfo> {
        @Override
        protected int getLayoutId(int viewType) {
            return R.layout.layout_item_device_list_online;
        }

        @Override
        protected void bindDatas(QuickViewHolder holder, AccountDeviceInfo.DeviceDetailInfo bean,
                                 int itemViewType, int position) {
            holder.setText(R.id.tv_device_title, bean.getName());
            holder.setText(R.id.tv_device_state, bean.getOnlineText());
            SimpleDateFormat sdfDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssss");
            try {
                Date date = sdfDateFormat.parse(bean.getUpdataDate());
                SimpleDateFormat sdfDateFormat1 = new SimpleDateFormat("HH:mm");
                String timeStr = sdfDateFormat1.format(date);
                holder.setText(R.id.tv_device_time, timeStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            holder.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putString("devName", bean.getName());
                bundle.putString("devID", bean.getID());
                IntentUtils.forward(DeviceDetailActivity.class, bundle);
            });
        }
    }
}
