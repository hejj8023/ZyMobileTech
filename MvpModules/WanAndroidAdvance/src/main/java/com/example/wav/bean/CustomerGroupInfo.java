package com.example.wav.bean;

import java.util.List;

/**
 * Created by zhiyang on 2018/4/26.
 */

public class CustomerGroupInfo {
    // 默认为0普通条目，1为头部
    private int type = 0;

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
     * 是否要选中这个方法要看需求，如果全选的时候，当前分组中的成员取消一个选择，其它很多成员是选择状态 ，
     * 这里会返回true,此方法不适用于此场景,判断是否是全部选择用下面的方法
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

    /**
     * 判断成员是否是全部选择
     *
     * @return
     */
    public boolean isAllCheckd() {
        for (CustomerInfo customerInfo : customerList) {
            if (!customerInfo.isChecked()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 全选
     */
    public void selectAll() {
        for (CustomerInfo customerInfo : customerList) {
            customerInfo.setChecked(true);
        }
    }

    /**
     * 取消全选
     */
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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
