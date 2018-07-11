package com.example.sash.adapter;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.sash.Const;
import com.example.sash.R;
import com.example.sash.bean.BluetoothBean;
import com.example.sash.ui.activity.SampleBluetoothActivity;
import com.zysdk.vulture.clib.utils.BluetoothUtils;
import com.zysdk.vulture.clib.utils.EmptyUtils;
import com.zysdk.vulture.clib.utils.LoggerUtils;
import com.zysdk.vulture.clib.utils.ThreadUtils;
import com.zysdk.vulture.clib.utils.UiUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by example on 2018/5/23.
 */

public class BtAdapter extends BaseQuickAdapter<BluetoothBean, BaseViewHolder> {
    private final BluetoothAdapter btAdapter;
    private final RecyclerView mRecyclerView;

    private boolean hasOpen = false;
    private OutputStream mOutputStream;
    private BluetoothSocket socket = null;

    public BtAdapter(List<BluetoothBean> data, BluetoothAdapter btAdapter, RecyclerView
            recyclerView) {
        super(R.layout.layout_item_list, data);
        this.btAdapter = btAdapter;
        this.mRecyclerView = recyclerView;
    }

    @Override
    protected void convert(BaseViewHolder helper, BluetoothBean item) {
        View extOptView = helper.getView(R.id.ll_ext_opt_menu);
        extOptView.setVisibility(View.GONE);
        View pairView = helper.getView(R.id.tv_pair);
        View removePairView = helper.getView(R.id.tv_remove_pair);
        View connectView = helper.getView(R.id.tv_connect);
        View testView = helper.getView(R.id.tv_send_test_data);
        View vd01 = helper.getView(R.id.v_d_01);
        View vd02 = helper.getView(R.id.v_d_02);
        View vd03 = helper.getView(R.id.v_d_03);

        String itemName = item.getName();
        helper.setText(R.id.tv_devname, ResourceUtils.getStr(R.string.tip_dev_name) + itemName);
        String itemAddress = item.getAddress();
        helper.setText(R.id.tv_devmac, ResourceUtils.getStr(R.string.tip_mac_addr) +
                itemAddress);

        BluetoothDevice remoteDev = btAdapter.getRemoteDevice(itemAddress);
        if (remoteDev != null) {
            int bondState = remoteDev.getBondState();
            switch (bondState) {
                case BluetoothDevice.BOND_NONE:
                    // 未配对,设备配对
                    helper.setText(R.id.tv_devtype, ResourceUtils.getStr(R.string.tip_un_pair));
                    if (pairView.getVisibility() != View.VISIBLE)
                        pairView.setVisibility(View.VISIBLE);
                    break;
                case BluetoothDevice.BOND_BONDED:
                    // 已配对,进行连接
                    helper.setText(R.id.tv_devtype, ResourceUtils.getStr(R.string.tip_paired));
                    if (pairView.getVisibility() != View.GONE)
                        pairView.setVisibility(View.GONE);
                    if (vd01.getVisibility() != View.GONE)
                        vd01.setVisibility(View.GONE);
                    break;
            }
        }


        helper.setOnClickListener(R.id.tv_ope_menu, v -> {
            // TODO: 2018/5/23 隐藏上一次打开的菜单,列表中一次只有一个是打开的
            if (SampleBluetoothActivity.lastSelectIndex > -1) {
                RecyclerView.LayoutManager layoutManager = mRecyclerView.getLayoutManager();
                // TODO: 2018/5/23 只能先通过layoutmanager取到指定位置 的view，再找到具体的view再对其进行操作
                View tView = layoutManager.findViewByPosition(SampleBluetoothActivity
                        .lastSelectIndex);
                if (tView != null) {
                    View lastMenuView = tView.findViewById(R.id.ll_ext_opt_menu);
                    if (lastMenuView != null && lastMenuView.getVisibility() != View.GONE) {
                        lastMenuView.setVisibility(View.GONE);
                    }
                }
            }

            if (hasOpen) {
                if (extOptView.getVisibility() != View.GONE) {
                    extOptView.setVisibility(View.GONE);
                }
                hasOpen = false;
                return;
            }
            hasOpen = true;
            extOptView.setVisibility(View.VISIBLE);
            SampleBluetoothActivity.lastSelectIndex = helper.getPosition();
        });

        pairView.setOnClickListener(v -> {
            // 配对
            // 未配对,设备配对
            Boolean result = null;
            try {
                result = BluetoothUtils.createBond(BluetoothDevice.class, btAdapter
                        .getRemoteDevice(itemAddress));
            } catch (Exception e) {
                e.printStackTrace();
                LoggerUtils.loge(e.getMessage());
            }

            // 从发起配对到弹出提示是需要时间的，需要耐心等待
            LoggerUtils.loge("配对 result = " + result);
            if (result) {
                // 发起配对成功，并不代表配对成功,因为可能被拒绝
                return;
            }
        });
        removePairView.setOnClickListener(v -> {
            // 取消配对
            Boolean result = null;
            try {
                result = BluetoothUtils.removeBond(BluetoothDevice.class, btAdapter
                        .getRemoteDevice(itemAddress));
            } catch (Exception e) {
                e.printStackTrace();
                LoggerUtils.loge(e.getMessage());
            }

            LoggerUtils.loge("取消配对 result = " + result);
            if (result) {
                // 发起配对成功，并不代表配对成功,因为可能被拒绝
                return;
            }
        });
        connectView.setOnClickListener(v -> {
            // 连接
            connect(remoteDev);
        });
        testView.setOnClickListener(v -> {
            // 发送测试数据
            try {
                if (mOutputStream != null) {
                    mOutputStream.write("test sample data\r\n".getBytes());
                    mOutputStream.flush();
                } else {
                    ToastUtils.showShort("输出流为空不能发送数据");
                }
            } catch (IOException e) {
                e.printStackTrace();
                LoggerUtils.loge("bt send data error msg = " + e.getMessage());
            }
        });
    }

    /**
     * 蓝牙设备连接
     *
     * @param device
     */
    private void connect(BluetoothDevice device) {
        // 固定uuid

        BluetoothSocket tmp = null;
        try {
            tmp = device.createRfcommSocketToServiceRecord(Const.UUID_DATA);
        } catch (IOException e) {
            e.printStackTrace();
            LoggerUtils.loge("bt createRfcommSocketToServiceRecord error msg = " + e.getMessage());
        }

        socket = tmp;

        // 连接需要放到子线程中
        ThreadUtils.executeBySingleThread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket.connect();
                    mOutputStream = socket.getOutputStream();
                    if (mOutputStream != null) {
                        UiUtils.showToastSafe(ResourceUtils.getStr(R.string.tip_connect_sucess));
                    }

                    InputStream inputStream = socket.getInputStream();
                    if (inputStream != null) {
                        BufferedReader br = new BufferedReader(new
                                InputStreamReader(inputStream));
                        String line = "";
                        // TODO: 2018/5/24 连接未断开是收不到服务器发送过来的数据的。
                        while ((line = br.readLine()) != null) {
                            String text = ResourceUtils.getStr(R.string.tip_rec_from_server_msg) + line;
                            LoggerUtils.loge(text);
                            ToastUtils.showShort(text);
                        }

                        if (EmptyUtils.isEmpty(line)) {
                            LoggerUtils.loge("与服务器判断了连接...");
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    LoggerUtils.loge("bt connect error msg = " + e.getMessage());
                }
            }
        });
    }

}
