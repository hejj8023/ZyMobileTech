package com.example.sash.mvp.presenter;

import com.example.sash.bean.BluetoothBean;
import com.example.sash.mvp.MainContract;
import com.example.sash.mvp.model.MainModel;
import com.zhiyangstudio.commonlib.mvp.presenter.BasePresenter;
import com.zhiyangstudio.commonlib.net.callback.AbsBaseObserver;

import java.util.List;

/**
 * Created by example on 2018/5/22.
 */

public class MainPrsenter extends BasePresenter<MainContract.IMainView> implements MainContract
        .IMainPresenter {

    private final MainModel mMainModel;
    private MainContract.IMainView mIMainView;

    public MainPrsenter() {
        mMainModel = new MainModel();
    }

    @Override
    public void startScan() {
        mIMainView = getView();
        mMainModel.startScan(mIMainView.getBtAdapter(), new AbsBaseObserver<List<BluetoothBean>>
                (this, MainModel.class.getName()) {
            @Override
            public void onNext(List<BluetoothBean> bluetoothBeans) {

            }
        });
    }

    @Override
    public void stopScan() {

    }
}
