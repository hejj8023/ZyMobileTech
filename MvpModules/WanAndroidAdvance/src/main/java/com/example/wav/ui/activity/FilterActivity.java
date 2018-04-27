package com.example.wav.ui.activity;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;

import com.blankj.utilcode.util.ToastUtils;
import com.example.wav.Const;
import com.example.wav.R;
import com.example.wav.base.BaseAdvActivity;
import com.example.wav.bean.CustomerGroupInfo;
import com.example.wav.bean.CustomerInfo;
import com.example.wav.bean.DeviceInfo;
import com.example.wav.mvp.contract.FilterContract;
import com.example.wav.mvp.presenter.FilterPresenter;
import com.zhiyangstudio.commonlib.adapter.lgrcommon.QuickAdapter;
import com.zhiyangstudio.commonlib.adapter.lgrcommon.QuickMultiSupport;
import com.zhiyangstudio.commonlib.adapter.lgrcommon.QuickViewHolder;

import java.util.List;

import butterknife.BindView;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.SimpleTabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;

/**
 * Created by zhiyang on 2018/4/26.
 */

public class FilterActivity extends BaseAdvActivity<FilterPresenter, FilterContract.IFilterView> implements FilterContract.IFilterView {
    @BindView(R.id.tablayout)
    VerticalTabLayout mVerticalTabLayout;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    private List<CustomerGroupInfo> mCustomerGroupInfoList;
    private List<CustomerInfo> mCustomerInfoList;
    private QuickAdapter adapter = null;
    private List<DeviceInfo> mDevList;

    @Override
    public void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
    }

    @Override
    public void initData() {
        mPresenter.loadList();
    }

    @Override
    public void refreshUi() {

    }

    @Override
    public void release() {

    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected boolean initToolBar() {
        setTitle("筛选");
        return true;
    }

    @Override
    protected boolean hasShowHome() {
        return true;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_filter;
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
                Const.TMP_DATA.DEV_LIST.clear();
                Const.TMP_DATA.CUSTOMERGROUP_LIST.clear();
                Const.TMP_DATA.CUSTOMER_LIST.clear();
                // TODO: 2018/4/26 这里添加的数据是状态发生变化之后的数据,因为该界面上有对数据处理的操作
                Const.TMP_DATA.DEV_LIST.addAll(mDevList);
                Const.TMP_DATA.CUSTOMERGROUP_LIST.addAll(mCustomerGroupInfoList);
                Const.TMP_DATA.CUSTOMER_LIST.addAll(mCustomerInfoList);
                setResult(Const.UI_ACTION.RESULT_REFRESH_UI);
                finish();
                break;
        }
        return true;
    }

    @Override
    public void setCustomerInfoData(List<CustomerInfo> list) {
        this.mCustomerInfoList = list;
    }

    @Override
    public void setCustomerGroupInfoData(List<CustomerGroupInfo> list) {
        this.mCustomerGroupInfoList = list;
    }

    @Override
    public void setDeviceList(List<DeviceInfo> list) {
        this.mDevList = list;
    }

    @Override
    public void refreshContentUi() {
        mVerticalTabLayout.setTabAdapter(new SimpleTabAdapter() {
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
        });
        mVerticalTabLayout.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {

                switch (position) {
                    case 0:
                        adapter = new CustomerInfoAdapter(mCustomerInfoList);
                        break;
                    case 1:
                        adapter = new CustomerGroupAdapter(mCustomerGroupInfoList);
                        break;
                    case 2:
                        adapter = new DeviceAdapter(mDevList);
                        break;
                }
                if (adapter != null) {
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onTabReselected(TabView tab, int position) {

            }
        });
        adapter = new CustomerInfoAdapter(mCustomerInfoList);
        recyclerView.setAdapter(adapter);
    }

    /**
     * 修改每个客户分组中的客户列表状态
     *
     * @param data
     */
    private void changeGroupSubDataState(CustomerInfo data) {
        if (mCustomerGroupInfoList != null && mCustomerGroupInfoList.size() > 0) {
            for (CustomerGroupInfo groupInfo : mCustomerGroupInfoList) {
                if (groupInfo != null) {
                    if (groupInfo.getType() == 1)
                        // 如果是头就继续下一次循环
                        continue;
                    List<CustomerInfo> customerList = groupInfo.getCustomerList();
                    if (customerList != null && customerList.size() > 0) {
                        for (CustomerInfo customerInfo : customerList) {
                            if (customerInfo.getId() == data.getId()) {
                                customerInfo.setChecked(data.isChecked());
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 是否是全选
     *
     * @return
     */
    private boolean isAllChecked() {
        for (CustomerGroupInfo customerGroupInfo : mCustomerGroupInfoList) {
            if (customerGroupInfo.getType() == 1)
                // 如果是头就继续下一次循环
                continue;
            // 有一个未选中就不是全部选择
            if (!customerGroupInfo.isCheckd()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 取消全选
     */
    private void unSelectAllCustomerGroup() {
        for (CustomerGroupInfo groupInfo : mCustomerGroupInfoList) {
            if (groupInfo.getType() == 1)
                // 如果是头就继续下一次循环
                continue;

            groupInfo.unSelectAll();

            // 操作客户列表
            changeCustomerState(false, groupInfo.getCustomerList());
        }
    }

    /**
     * 修改客户列表状态
     *
     * @param checked
     * @param customerList
     */
    private void changeCustomerState(boolean checked, List<CustomerInfo> customerList) {
        for (CustomerInfo customerInfo : mCustomerInfoList) {
            for (CustomerInfo info : customerList) {
                if (customerInfo.getId() == info.getId()) {
                    customerInfo.setChecked(checked);
                }
            }
        }
    }

    /**
     * 全选
     */
    private void selectAllCustomerGroup() {
        for (CustomerGroupInfo groupInfo : mCustomerGroupInfoList) {
            if (groupInfo.getType() == 1)
                // 如果是头就继续下一次循环
                continue;
            groupInfo.selectAll();
            // 操作客户列表
            changeCustomerState(true, groupInfo.getCustomerList());
        }
    }

    /**
     * 客户列表
     */
    private class CustomerInfoAdapter extends QuickAdapter<CustomerInfo> {
        public CustomerInfoAdapter(List<CustomerInfo> list) {
            super(mContext, list, R.layout.layout_item_customer_list);
        }

        @Override
        protected void convert(QuickViewHolder holder, CustomerInfo data, int position) {
            holder.setText(R.id.tv_title, data.getName());
            CheckBox checkBox = holder.getView(R.id.cb_customer);
            if (data.isChecked()) {
                checkBox.setChecked(true);
            } else {
                checkBox.setChecked(false);
            }
            holder.setOnClickListener(v -> {
                if (checkBox.isChecked()) {
                    checkBox.setChecked(false);
                    data.setChecked(false);
                } else {
                    checkBox.setChecked(true);
                    data.setChecked(true);
                }
                // 考虑到数据联动，第一种方式，在操作的时候操作客户分组数据
                changeGroupSubDataState(data);
                set(position, data);
            });
        }
    }

    /**
     * 客户分组列表
     */
    private class CustomerGroupAdapter extends QuickAdapter<CustomerGroupInfo> {
        public static final int VIEW_TYPE_HEADER = 0;
        public static final int VIEW_TYPE_DATA = 1;

        public CustomerGroupAdapter(List<CustomerGroupInfo> list) {
            super(mContext, list,
                    new QuickMultiSupport<CustomerGroupInfo>() {

                        @Override
                        public int getViewTypeCount() {
                            return 2;
                        }

                        @Override
                        public int getLayoutId(CustomerGroupInfo data) {
                            return R.layout.layout_item_customer_group_list;
                        }

                        @Override
                        public int getItemViewType(CustomerGroupInfo data) {
                            if (data.getType() == 1) {
                                return VIEW_TYPE_HEADER;
                            }
                            return VIEW_TYPE_DATA;
                        }

                        @Override
                        public boolean isSpan(CustomerGroupInfo data) {
                            return false;
                        }
                    });
        }

        @Override
        protected void convert(QuickViewHolder holder, CustomerGroupInfo data, int position) {
            int itemViewType = getItemViewType(position);
            CheckBox checkBox = holder.getView(R.id.cb_customer_group);
            switch (itemViewType) {
                case VIEW_TYPE_DATA:
                    holder.setText(R.id.tv_group_title, data.getGroupName());
                    // 第二种方式的使用
                    if (data.isCheckd()) {
                        checkBox.setChecked(true);
                    } else {
                        checkBox.setChecked(false);
                    }

                    holder.setOnClickListener(v -> {
                        List<CustomerInfo> customerList = data.getCustomerList();
                        // 方式二，在选择和取消选择的时候操作客户列表数据
                        if (data.isCheckd()) {
                            checkBox.setChecked(false);
                            data.unSelectAll();
                            changeCustomerState(false, customerList);
                        } else {
                            checkBox.setChecked(true);
                            data.selectAll();
                            changeCustomerState(true, customerList);
                        }
                        set(position, data);
                    });
                    break;
                case VIEW_TYPE_HEADER:
                    holder.setText(R.id.tv_group_title, data.getGroupName());
                    holder.setTextColor(R.id.tv_dev_name, R.color.red);

                    if (isAllChecked()) {
                        checkBox.setChecked(true);
                    } else {
                        checkBox.setChecked(false);
                    }
                    holder.setOnClickListener(v -> {
                        // 全选,取消全选 所有
                        if (isAllChecked()) {
                            checkBox.setChecked(false);
                            unSelectAllCustomerGroup();
                        } else {
                            checkBox.setChecked(true);
                            selectAllCustomerGroup();
                        }
                        notifyDataSetChanged();
                    });
                    break;
            }
        }
    }

    /**
     * 设备列表
     */
    private class DeviceAdapter extends QuickAdapter<DeviceInfo> {
        public DeviceAdapter(List<DeviceInfo> list) {
            super(mContext, list, R.layout.layout_item_device_list);
        }

        @Override
        protected void convert(QuickViewHolder holder, DeviceInfo data, int position) {
            holder.setText(R.id.tv_dev_name, "设备名称:" + data.getDevName());
            int devId = data.getGroupId();
            CustomerGroupInfo groupInfo = null;
            String devGroupName = "";
            if (mCustomerGroupInfoList != null) {
                for (CustomerGroupInfo customerGroupInfo : mCustomerGroupInfoList) {
                    if (customerGroupInfo.getType() == 1)
                        continue;

                    if (customerGroupInfo.getGroupId() == devId) {
                        groupInfo = customerGroupInfo;
                        break;
                    }
                }
            }
            devGroupName = groupInfo.getGroupName();
            if (groupInfo.isCheckd()) {
                holder.setBackgroundColor(R.color.silver);
            }
            int customerId = data.getCustomerId();
            String cName = "";
            if (mCustomerInfoList != null) {
                for (CustomerInfo customerInfo : mCustomerInfoList) {
                    if (customerInfo.getId() == customerId) {
                        cName = customerInfo.getName();
                        break;
                    }
                }
            }
            holder.setText(R.id.tv_dev_customer_id, "用户名称:" + cName);
            holder.setText(R.id.tv_dev_group_id, "设备所属组:" + devGroupName);
            holder.setText(R.id.tv_state, "设备状态:" + (data.getState() == 0 ? "运行中" : "已停止运行"));
        }
    }
}
