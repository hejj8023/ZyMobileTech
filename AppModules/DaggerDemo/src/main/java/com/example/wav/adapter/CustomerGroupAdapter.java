package com.example.wav.adapter;

import android.content.Context;

import com.example.wav.R;
import com.example.wav.bean.CustomerGroupInfo;
import com.example.wav.bean.CustomerInfo;
import com.zhiyangstudio.commonlib.adapter.lgrcommon.QuickAdapter;
import com.zhiyangstudio.commonlib.adapter.lgrcommon.QuickMultiSupport;
import com.zhiyangstudio.commonlib.adapter.lgrcommon.QuickViewHolder;

import java.util.List;

/**
 * 客户分组列表
 * 说明：分组列表中的所有的客户都处于选择状态的时候，当前的客户分组才会被选中
 * 当前客户列表处于选中的时候，如果当前组成员有一个取消，当前的状态即修改为取消选择状态
 */
public class CustomerGroupAdapter extends QuickAdapter<CustomerGroupInfo> {
    public static final int VIEW_TYPE_HEADER = 0;
    public static final int VIEW_TYPE_DATA = 1;
    private List<CustomerInfo> mCustomerInfoList;

    public CustomerGroupAdapter(Context context, List<CustomerGroupInfo> list, List<CustomerInfo> customerInfoList) {
        super(context, list,
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
        this.mCustomerInfoList = customerInfoList;
    }

    @Override
    protected void convert(QuickViewHolder holder, CustomerGroupInfo data, int position) {
//        int itemViewType = getItemViewType(position);
//        CheckBox checkBox = holder.getView(R.id.cb_customer_group);
//        switch (itemViewType) {
//            case VIEW_TYPE_DATA:
//                holder.setText(R.id.tv_group_title, data.getGroupName());
//                // 第二种方式的使用
//                if (data.isAllCheckd()) {
//                    checkBox.setChecked(true);
//                } else {
//                    checkBox.setChecked(false);
//                }
//
//                holder.setOnClickListener(v -> {
//                    List<CustomerInfo> customerList = data.getCustomerList();
//                    // 方式二，在选择和取消选择的时候操作客户列表数据
//                    if (data.isAllCheckd()) {
//                        checkBox.setChecked(false);
//                        data.unSelectAll();
//                        changeCustomerState(false, customerList);
//                    } else {
//                        checkBox.setChecked(true);
//                        data.selectAll();
//                        changeCustomerState(true, customerList);
//                    }
//                    set(position, data);
//                });
//                break;
//            case VIEW_TYPE_HEADER:
//                holder.setText(R.id.tv_group_title, data.getGroupName());
//                holder.setTextColor(R.id.tv_dev_name, R.color.red);
//
//                if (isAllChecked()) {
//                    checkBox.setChecked(true);
//                } else {
//                    checkBox.setChecked(false);
//                }
//                holder.setOnClickListener(v -> {
//                    // 全选,取消全选 所有
//                    if (isAllChecked()) {
//                        checkBox.setChecked(false);
//                        unSelectAllCustomerGroup();
//                    } else {
//                        checkBox.setChecked(true);
//                        selectAllCustomerGroup();
//                    }
//                    notifyDataSetChanged();
//                });
//                break;
//        }
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
     * 是否是全选
     *
     * @return
     */
    private boolean isAllChecked() {
        for (CustomerGroupInfo customerGroupInfo : mData) {
            if (customerGroupInfo.getType() == 1)
                // 如果是头就继续下一次循环
                continue;
            // 有一个未选中就不是全部选择
            if (!customerGroupInfo.isAllCheckd()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 取消全选
     */
    private void unSelectAllCustomerGroup() {
        for (CustomerGroupInfo groupInfo : mData) {
            if (groupInfo.getType() == 1)
                // 如果是头就继续下一次循环
                continue;

            groupInfo.unSelectAll();

            // 操作客户列表
            changeCustomerState(false, groupInfo.getCustomerList());
        }
    }

    /**
     * 全选
     */
    private void selectAllCustomerGroup() {
        for (CustomerGroupInfo groupInfo : mData) {
            if (groupInfo.getType() == 1)
                // 如果是头就继续下一次循环
                continue;
            groupInfo.selectAll();
            // 操作客户列表
            changeCustomerState(true, groupInfo.getCustomerList());
        }
    }

}