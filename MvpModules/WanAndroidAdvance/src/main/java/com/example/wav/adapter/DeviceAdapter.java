package com.example.wav.adapter;

import android.content.Context;

import com.example.wav.R;
import com.example.wav.bean.CustomerGroupInfo;
import com.example.wav.bean.CustomerInfo;
import com.example.wav.bean.DeviceInfo;
import com.zhiyangstudio.commonlib.adapter.lgrcommon.QuickAdapter;
import com.zhiyangstudio.commonlib.adapter.lgrcommon.QuickViewHolder;

import java.util.List;

/**
 * 设备列表
 */
public class DeviceAdapter extends QuickAdapter<DeviceInfo> {
    private List<CustomerInfo> mCustomerInfoList;
    private List<CustomerGroupInfo> mCustomerGroupInfoList;

    public DeviceAdapter(Context context, List<DeviceInfo> list, List<CustomerInfo> customerInfoList,
                         List<CustomerGroupInfo> customerGroupInfoList) {
        super(context, list, R.layout.layout_item_device_list);
        this.mCustomerInfoList = customerInfoList;
        this.mCustomerGroupInfoList = customerGroupInfoList;
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
        if (groupInfo.isAllCheckd()) {
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