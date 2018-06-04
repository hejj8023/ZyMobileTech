package com.example.sash.mvp.model;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.ParcelUuid;

import com.example.sash.bean.BluetoothBean;
import com.example.sash.mvp.MainContract;
import com.zhiyangstudio.commonlib.utils.LoggerUtils;
import com.zhiyangstudio.commonlib.utils.RxUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import io.reactivex.Observer;

/**
 * Created by example on 2018/5/22.
 */

public class MainModel implements MainContract.IMainModel {
    @Override
    public void startScan(BluetoothAdapter btAdapter, Observer<List<BluetoothBean>> observer) {
        List<BluetoothBean> bluetoothBeans = new ArrayList<>();
        if (btAdapter != null) {
            // getBondedDevices 获取与本机蓝牙所有绑定的远程蓝牙信息
            Set<BluetoothDevice> devices = btAdapter.getBondedDevices();
            if (devices.size() > 0) {
                BluetoothBean bluetoothBean = null;
                for (Iterator<BluetoothDevice> iterator = devices.iterator(); iterator.hasNext();
                        ) {
                    BluetoothDevice bluetoothDevice = (BluetoothDevice) iterator.next();
                    String name = bluetoothDevice.getName();
                    LoggerUtils.loge("设备：" + name + " " + bluetoothDevice
                            .getAddress());

                    bluetoothBean = new BluetoothBean();
                    bluetoothBean.setName(name);
                    bluetoothBean.setAddress(bluetoothDevice.getAddress());
                    bluetoothBean.setTupe(bluetoothDevice.getType());
                    ParcelUuid[] uuids = bluetoothDevice.getUuids();
                    if (uuids != null && uuids.length > 0) {
                        for (ParcelUuid uuid : uuids) {
                            LoggerUtils.loge("uuid = " + uuid.getUuid());
                        }
                    }
                    bluetoothBean.setUuid(uuids);
                    bluetoothBeans.add(bluetoothBean);
                }
            }

        }
        RxUtils.createObservableData(bluetoothBeans).compose(RxUtils.io_main()).subscribe(observer);
    }
}
