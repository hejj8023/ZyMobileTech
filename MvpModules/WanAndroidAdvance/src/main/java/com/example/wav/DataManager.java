package com.example.wav;

import com.example.wav.bean.CustomerGroupInfo;
import com.example.wav.bean.CustomerInfo;
import com.example.wav.bean.DeviceInfo;
import com.zhiyangstudio.commonlib.utils.LoggerUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhiyang on 2018/4/26.
 */

public class DataManager {
    /**
     * 客户分组列表
     *
     * @return
     */
    public static List<CustomerGroupInfo> getCustGroupList() {
        List<CustomerGroupInfo> list = new ArrayList<>();
        List<CustomerInfo> infoList = getCustomerList();
        int count = infoList.size() / 10;
        CustomerGroupInfo groupInfo = null;
        for (int i = 0; i < count; i++) {
            groupInfo = new CustomerGroupInfo();
            // 0 , 9
            // 10 , 19
            // 20 , 30
            int startPos = i * 10;
            int endPos = (i + 1) * 10 - 1;
            LoggerUtils.loge("start = " + startPos + " , endPos = " + endPos);
            if (endPos < infoList.size()) {
                endPos += 1;
            }
            ArrayList<CustomerInfo> customerList = new ArrayList<>(infoList
                    .subList(startPos, endPos));
            groupInfo.setCustomerList(customerList);
            groupInfo.setGroupName("group-" + (i + 1));
            groupInfo.setGroupId((10 + i));
            list.add(groupInfo);
        }

        // 在最开始的位置添加头
        CustomerGroupInfo customerGroupInfo = new CustomerGroupInfo();
        customerGroupInfo.setType(1);
        customerGroupInfo.setGroupName("全部分组");
        customerGroupInfo.setGroupId(0);
        // 设置一个空的防止空指针
        customerGroupInfo.setCustomerList(new ArrayList<>());
        list.add(0, customerGroupInfo);
        return list;
    }

    /**
     * 客户列表
     *
     * @return
     */
    public static List<CustomerInfo> getCustomerList() {
        List<CustomerInfo> customerInfos = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            customerInfos.add(new CustomerInfo("test-" + (101 + i), (101 + i)));
        }
        return customerInfos;
    }

    /**
     * 获取设备列表
     *
     * @return
     */
    public static List<DeviceInfo> getDeviceList() {
        List<DeviceInfo> deviceInfos = new ArrayList<>();
        DeviceInfo deviceInfo = new DeviceInfo(1001, "洗衣机", 10, 101, 1);
        deviceInfos.add(deviceInfo);
        deviceInfo = new DeviceInfo(1002, "电冰箱", 11, 102, 0);
        deviceInfos.add(deviceInfo);
        deviceInfo = new DeviceInfo(1003, "空调", 12, 103, 0);
        deviceInfos.add(deviceInfo);
        deviceInfo = new DeviceInfo(1004, "电风扇", 10, 101, 0);
        deviceInfos.add(deviceInfo);
        return deviceInfos;
    }
}
