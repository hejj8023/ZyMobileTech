package com.example.wav.manager;

import android.support.v4.app.Fragment;

import com.example.wav.Const;
import com.example.wav.bean.MainPagerInfo;
import com.example.wav.bean.CustomerGroupInfo;
import com.example.wav.bean.CustomerInfo;
import com.example.wav.bean.DeviceInfo;
import com.example.wav.ui.fragment.HomeFragment;
import com.example.wav.ui.fragment.SettingFragment;
import com.zhiyangstudio.commonlib.utils.LoggerUtils;
import com.zhiyangstudio.commonlib.utils.PreUtils;

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

    public static void saveDefaultUserId(String userId) {
        PreUtils.put(Const.PRE_KEY_DEFAULT_USER_ID, userId);
    }

    public static String getDefaultUserId() {
        Object o = PreUtils.get(Const.PRE_KEY_DEFAULT_USER_ID, "");
        return o != null && o instanceof String ? (String) o : "";
    }

    public static void saveDefaultGroupId(String groupId) {
        PreUtils.put(Const.PRE_KEY_DEFAULT_GROUP_ID, groupId);
    }

    public static String getDefaultGroupId() {
        Object o = PreUtils.get(Const.PRE_KEY_DEFAULT_GROUP_ID, "");
        return o != null && o instanceof String ? (String) o : "";
    }

    public static List<Fragment> getMainFragments() {
        List<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(new HomeFragment());
        fragments.add(new SettingFragment());
        return fragments;
    }

    public static List<MainPagerInfo> getMainPagerDatas() {
        List<MainPagerInfo> pagerInfoList = new ArrayList<MainPagerInfo>();
        pagerInfoList.add(MainPagerInfo.HOMEPAGE);
        pagerInfoList.add(MainPagerInfo.SETTING);
        return pagerInfoList;
    }

}
