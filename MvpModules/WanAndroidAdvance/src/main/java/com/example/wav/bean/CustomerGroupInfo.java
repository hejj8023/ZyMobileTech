package com.example.wav.bean;

import java.util.List;

/**
 * Created by zhiyang on 2018/4/26.
 */

public class CustomerGroupInfo {
    private int groupId;
    private String groupName;
    private List<CustomerInfo> customerList;

    public CustomerGroupInfo() {
    }

    public CustomerGroupInfo(int groupId, String groupName, List<CustomerInfo> customerList) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.customerList = customerList;
    }

    /**
     * 是否有条目处于选择状态
     *
     * @return
     */
    public boolean isCheckd() {
        for (CustomerInfo customerInfo : customerList) {
            if (customerInfo.isChecked()) {
                return true;
            }
        }
        return false;
    }

    public void selectAll() {
        for (CustomerInfo customerInfo : customerList) {
            customerInfo.setChecked(true);
        }
    }

    public void unSelectAll() {
        for (CustomerInfo customerInfo : customerList) {
            customerInfo.setChecked(false);
        }
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<CustomerInfo> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<CustomerInfo> customerList) {
        this.customerList = customerList;
    }

    @Override
    public String toString() {
        return "CustomerGroupInfo{" +
                "groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", customerList=" + customerList +
                '}';
    }

    /**
     * 是否处于选择状态
     *
     * @param list
     * @return
     */
    public boolean isChecked(List<CustomerInfo> list) {
        for (CustomerInfo customerInfo : customerList) {
            for (CustomerInfo info : list) {
                if (customerInfo.getId() == info.getId()) {
                    if (info.isChecked()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
