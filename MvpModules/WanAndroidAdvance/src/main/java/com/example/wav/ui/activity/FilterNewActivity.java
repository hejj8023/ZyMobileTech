package com.example.wav.ui.activity;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;

import com.blankj.utilcode.util.ToastUtils;
import com.example.wav.Const;
import com.example.wav.DataManager;
import com.example.wav.R;
import com.example.wav.base.BaseAdvActivity;
import com.example.wav.bean.AccountCustomerInfo;
import com.example.wav.bean.AccountDeviceInfo;
import com.example.wav.bean.AccountGroupInfo;
import com.example.wav.mvp.contract.FilterNewContract;
import com.example.wav.mvp.presenter.FilterNewPresenter;
import com.zhiyangstudio.commonlib.adapter.lgrcommon.QuickAdapter;
import com.zhiyangstudio.commonlib.adapter.lgrcommon.QuickViewHolder;
import com.zhiyangstudio.commonlib.widget.LoadingLayout;

import java.util.List;

import butterknife.BindView;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.SimpleTabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;

/**
 * Created by example on 2018/4/28.
 */

public class FilterNewActivity extends BaseAdvActivity<FilterNewPresenter,
        FilterNewContract.IFilterNewView> implements FilterNewContract.IFilterNewView {

    @BindView(R.id.tablayout)
    VerticalTabLayout mVerticalTabLayout;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.loading_view)
    LoadingLayout mLoadingLayout;
    private QuickAdapter customerListAdapter;
    private QuickAdapter customerGroupListAdapter;
    private QuickAdapter deviceListAdapter;

    // 是否有数据在请求中，只对tab切换有效
    private boolean hasReqDataing = false;
    VerticalTabLayout.OnTabSelectedListener onTabSelectedListener = new VerticalTabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabView tab, int position) {
            if (hasReqDataing)
                return;

            mLoadingLayout.showLoding();
            hasReqDataing = true;
            switch (position) {
                case 0:
                    // 点击的时候从网络上获取最新的数据,这里取全部的客户列表
                    mPresenter.getCustomerListData();
                    break;
                case 1:
                    // 点击的时候从网络上获取最新的数据,使用最新的客户id
                    mPresenter.getCustomerGroupListData();
                    break;
                case 2:
                    // 点击的时候从网络上获取最新的数据,最新的客户id+最新的groupId
                    mPresenter.getDevListData();
                    break;
            }
        }

        @Override
        public void onTabReselected(TabView tab, int position) {

        }
    };
    // 是否是第一次加载
    private boolean isFirstLoad = true;
    private SimpleTabAdapter simpleTabAdapter = new SimpleTabAdapter() {
        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public ITabView.TabTitle getTitle(int position) {
            String title = "客户名称";
            switch (position) {
                case 1:
                    title = "客户分组";
                    break;
                case 2:
                    title = "设备状态";
                    break;
            }
            return new ITabView.TabTitle.Builder()
                    .setContent(title)
                    .setTextColor(R.color.red, R.color.greenyellow)
                    .setTextSize(20)
                    .build();
        }
    };


    @Override
    protected boolean initToolBar() {
        setTitle("筛选 ~ New");
        return true;
    }

    @Override
    protected boolean hasShowHome() {
        return true;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_filter2;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_filter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_filter_confirm:
                ToastUtils.showShort("确定");
                // 保存数据
                DataManager.saveDefaultUserId(Const.TMP_DATA.FILTER_CUSTOMER_ID);
                DataManager.saveDefaultGroupId(Const.TMP_DATA.FILTER_CUSTOMER_GROUP_ID);
                break;
        }
        return true;
    }

    @Override
    public void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));

        mVerticalTabLayout.setTabAdapter(simpleTabAdapter);
        mVerticalTabLayout.addOnTabSelectedListener(onTabSelectedListener);
    }

    @Override
    public void initData() {
        mLoadingLayout.showLoding();
        mPresenter.getAllData();
    }

    @Override
    public void refreshUi() {

    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void setCustomerListData(List<AccountCustomerInfo> list) {
        AccountCustomerInfo accountGroupInfo = list.get(0);
        accountGroupInfo.setChecked(true);
        Const.TMP_DATA.FILTER_CUSTOMER_ID = accountGroupInfo.getId() + "";
        list.set(0, accountGroupInfo);
        customerListAdapter = new FilterCustomerListAdapter(list);
        hasReqDataing = false;
        if (!isFirstLoad) {
            recyclerView.setAdapter(customerListAdapter);
        }
        mLoadingLayout.showContent();
    }

    @Override
    public void setCustomerGroupListData(List<AccountGroupInfo> list) {
        AccountGroupInfo accountGroupInfo = list.get(0);
        accountGroupInfo.setChecked(true);
        Const.TMP_DATA.FILTER_CUSTOMER_GROUP_ID = accountGroupInfo.getId();
        list.set(0, accountGroupInfo);
        customerGroupListAdapter = new FilterCustomerGroupListAdapter(list);
        hasReqDataing = false;
        if (!isFirstLoad) {
            recyclerView.setAdapter(customerGroupListAdapter);
        }
        mLoadingLayout.showContent();
    }

    @Override
    public void setDeviceListData(AccountDeviceInfo list) {
        if (list != null) {
            List<AccountDeviceInfo.DeviceDetailInfo> detailInfos = list.getRows();
            deviceListAdapter = new FilterDeviceAdapter(detailInfos);
        }
        hasReqDataing = false;
        if (isFirstLoad) {
            isFirstLoad = false;
            recyclerView.setAdapter(customerListAdapter);
        } else {
            recyclerView.setAdapter(deviceListAdapter);
        }
        mLoadingLayout.showContent();
    }

    @Override
    public int getStatus() {
        return 0;
    }

    @Override
    public int getPage() {
        return 1;
    }

    @Override
    public int getPageSize() {
        return 20;
    }

    private class FilterCustomerListAdapter extends QuickAdapter<AccountCustomerInfo> {
        public FilterCustomerListAdapter(List<AccountCustomerInfo> accountCustomerInfos) {
            super(mContext, accountCustomerInfos, R.layout.layout_item_customer_list);
        }

        @Override
        protected void convert(QuickViewHolder holder, AccountCustomerInfo data, int position) {
            holder.setText(R.id.tv_title, data.getText());
            CheckBox checkBox = holder.getView(R.id.cb_customer);
            if (data.isChecked()) {
                checkBox.setChecked(true);
            } else {
                checkBox.setChecked(false);
            }
            holder.setOnClickListener(v -> {
                // 取消所有的选择
                for (AccountCustomerInfo datum : mData) {
                    if (!datum.equals(data))
                        datum.setChecked(false);
                }
                if (data.isChecked()) {
                    data.setChecked(false);
                    Const.TMP_DATA.FILTER_CUSTOMER_ID = "";
                } else {
                    data.setChecked(true);
                    Const.TMP_DATA.FILTER_CUSTOMER_ID = data.getId() + "";
                }
                checkBox.setChecked(data.isChecked());
                notifyDataSetChanged();
            });
        }
    }

    private class FilterCustomerGroupListAdapter extends QuickAdapter<AccountGroupInfo> {
        public FilterCustomerGroupListAdapter(List<AccountGroupInfo> accountCustomerInfos) {
            super(mContext, accountCustomerInfos, R.layout.layout_item_customer_group_list);
        }

        @Override
        protected void convert(QuickViewHolder holder, AccountGroupInfo data, int position) {
            holder.setText(R.id.tv_group_title, data.getText());
            CheckBox checkBox = holder.getView(R.id.cb_customer_group);
            if (data.isChecked()) {
                checkBox.setChecked(true);
            } else {
                checkBox.setChecked(false);
            }
            holder.setOnClickListener(v -> {
                // 取消所有的选择
                for (AccountGroupInfo datum : mData) {
                    if (!datum.equals(data))
                        datum.setChecked(false);
                }
                if (data.isChecked()) {
                    data.setChecked(false);
                    Const.TMP_DATA.FILTER_CUSTOMER_GROUP_ID = "";
                } else {
                    data.setChecked(true);
                    Const.TMP_DATA.FILTER_CUSTOMER_GROUP_ID = data.getId();
                }
                checkBox.setChecked(data.isChecked());
                notifyDataSetChanged();
            });
        }
    }

    private class FilterDeviceAdapter extends QuickAdapter<AccountDeviceInfo.DeviceDetailInfo> {
        public FilterDeviceAdapter(List<AccountDeviceInfo.DeviceDetailInfo> list) {
            super(mContext, list, R.layout.layout_item_device_list);
        }

        @Override
        protected void convert(QuickViewHolder holder, AccountDeviceInfo.DeviceDetailInfo data, int position) {
            holder.setText(R.id.tv_dev_name, data.getName());
            holder.setText(R.id.tv_dev_group_id, data.getGroupName());
            holder.setText(R.id.tv_dev_customer_id, data.getCustomerName());
            holder.setText(R.id.tv_state, data.getOnlineText());
        }
    }
}
