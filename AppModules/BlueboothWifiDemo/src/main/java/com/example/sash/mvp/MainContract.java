package com.example.sash.mvp;

import android.bluetooth.BluetoothAdapter;

import com.example.sash.bean.BluetoothBean;
import com.zhiyangstudio.commonlib.mvp.inter.ISampleRefreshView;

import java.util.List;

import io.reactivex.Observer;


/**
 * Created by example on 2018/5/22.
 */

public interface MainContract {
    public interface IMainView extends ISampleRefreshView<BluetoothBean> {
        BluetoothAdapter getBtAdapter();
    }

    public interface IMainPresenter {
        void startScan();

        void stopScan();
    }

    public interface IMainModel {
        void startScan(BluetoothAdapter btAdapter, Observer<List<BluetoothBean>> observer);
    }
}
