package com.example.wav.ui.fragment.filter;

import android.os.Bundle;
import android.view.View;

import com.example.wav.R;
import com.example.wav.base.BaseDaggerSupportListFragment;
import com.example.wav.bean.AccountDeviceInfo;
import com.example.wav.mvp.contract.FilterDeviceListContract;
import com.example.wav.mvp.presenter.FilteDeviceListPresenter;
import com.zhiyangstudio.commonlib.CommonConst;
import com.zhiyangstudio.commonlib.adapter.BaseListAdapter;
import com.zhiyangstudio.commonlib.adapter.lgrcommon.QuickViewHolder;

import java.util.List;

/**
 * Created by example on 2018/5/2.
 */

public class FilterDeviceListFragment extends
        BaseDaggerSupportListFragment<FilteDeviceListPresenter, FilterDeviceListContract
                .IFilteDeviceListView, AccountDeviceInfo.DeviceDetailInfo> implements FilterDeviceListContract
        .IFilteDeviceListView {
    @Override
    public void addListener() {

    }

    @Override
    public void initData() {

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
    protected void initArguments(Bundle bundle) {
    }

    @Override
    public void loadDatas() {
        mPresenter.getData();
    }

    @Override
    protected boolean isCanLoadMore() {
        return false;
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
    public int getPage() {
        if (state == CommonConst.PAGE_STATE.STATE_REFRESH) {
            return 1;
        }
        return super.getPage();
    }

    @Override
    public int getStatus() {
        return 0;
    }

    @Override
    public int getPageSize() {
        return 20;
    }

    private class DeviceListAdapter extends BaseListAdapter<AccountDeviceInfo.DeviceDetailInfo> {
        @Override
        protected int getLayoutId(int viewType) {
            return R.layout.layout_item_device_list;
        }

        @Override
        protected void bindDatas(QuickViewHolder holder, AccountDeviceInfo.DeviceDetailInfo bean, int itemViewType, int position) {
            holder.setText(R.id.tv_dev_name, bean.getName());
            holder.setText(R.id.tv_dev_group_id, bean.getGroupName());
            holder.setText(R.id.tv_dev_customer_id, bean.getCustomerName());
            holder.setText(R.id.tv_state, bean.getOnlineText());
        }
    }
}
