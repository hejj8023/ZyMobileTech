package com.example.sash.mvp.model;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import com.example.sash.bean.BluetoothBean;
import com.example.sash.mvp.MainContract;
import com.zhiyangstudio.commonlib.utils.RxUtils;

import java.util.ArrayList;
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
            Set<BluetoothDevice> bondedDevices = btAdapter.getBondedDevices();
            if (bondedDevices != null && bondedDevices.size() > 0) {
                BluetoothBean bluetoothBean = null;
                for (BluetoothDevice device : bondedDevices) {
                    bluetoothBean = new BluetoothBean();
                    bluetoothBean.setName(device.getName());
                    bluetoothBean.setName(device.getAddress());
                    bluetoothBean.setTupe(device.getType());
                    bluetoothBean.setUuid(device.getUuids());
                }
            }
        }
        RxUtils.createObservableData(bluetoothBeans).compose(RxUtils.io_main()).subscribe(observer);
    }
}
