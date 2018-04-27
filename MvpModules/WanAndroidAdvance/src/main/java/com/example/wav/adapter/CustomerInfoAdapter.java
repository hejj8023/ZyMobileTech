package com.example.wav.adapter;

import android.content.Context;
import android.widget.CheckBox;

import com.example.wav.R;
import com.example.wav.bean.CustomerGroupInfo;
import com.example.wav.bean.CustomerInfo;
import com.zhiyangstudio.commonlib.adapter.lgrcommon.QuickAdapter;
import com.zhiyangstudio.commonlib.adapter.lgrcommon.QuickViewHolder;

import java.util.List;

/**
 * 客户列表
 */
public class CustomerInfoAdapter extends QuickAdapter<CustomerInfo> {
    private List<CustomerGroupInfo> mCustomerGroupInfoList;

    public CustomerInfoAdapter(Context context, List<CustomerInfo> list, List<CustomerGroupInfo> customerGroupInfoList) {
        super(context, list, R.layout.layout_item_customer_list);
        this.mCustomerGroupInfoList = customerGroupInfoList;
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
                    // 修改改变之后的状态
                    groupInfo.setCustomerList(customerList);
                }
            }
        }
    }
}