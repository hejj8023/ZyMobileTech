package com.example.sash;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.sash.bean.BluetoothBean;
import com.example.sash.mvp.MainContract;
import com.example.sash.mvp.presenter.MainPrsenter;
import com.zhiyangstudio.commonlib.refreshsupport.smartrefresh.BaseMVPSRRListActivity;
import com.zhiyangstudio.commonlib.utils.LoggerUtils;
import com.zhiyangstudio.commonlib.utils.UiUtils;

import java.util.List;

public class MainActivity extends BaseMVPSRRListActivity<MainPrsenter, MainContract.IMainView,
        BluetoothBean> implements MainContract.IMainView {

    private TextView mTextView;
    private BluetoothAdapter mBluetoothAdapter;

    @Override
    protected MainPrsenter createPresenter() {
        return new MainPrsenter();
    }

    @Override
    protected boolean hasEnableRereshAndLoadMore() {
        return false;
    }


    @Override
    protected void initOtherProperty() {
        mExtRoot.setVisibility(View.VISIBLE);
        mTextView = new TextView(mContext);
        mTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
        mTextView.setTextColor(UiUtils.getColor(R.color.cadetblue));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        int left = UiUtils.dp2px(5);
        layoutParams.setMargins(left, left, left, left);
        mTextView.setLayoutParams(layoutParams);
        mExtRoot.addView(mTextView);
    }

    @Override
    protected void loadRemoteData() {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    @Override
    protected void initPageNumb() {

    }

    @Override
    protected BaseQuickAdapter<BluetoothBean, BaseViewHolder> getListAdapter() {
        return new BaseQuickAdapter<BluetoothBean, BaseViewHolder>(R.layout.layout_item_list,
                mList) {
            @Override
            protected void convert(BaseViewHolder helper, BluetoothBean item) {

            }
        };
    }

    @Override
    public void setData(List data) {

    }

    @Override
    protected void onResume() {
        super.onResume();

        checkBtStaus();
    }

    private void checkBtStaus() {
        if (mBluetoothAdapter == null) {
            mTextView.append("当前设备不支持蓝牙\r\n");
        } else {
            mTextView.setText("");
            mTextView.append("当前设备支持蓝牙\r\n");
            if (mBluetoothAdapter.isEnabled()) {
                mTextView.append("蓝牙已开启\r\n");
            } else {
                mTextView.append("蓝牙未开启\r\n");
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_open_bluebooth:
                // TODO: 2018/5/22 判断当前设备是否支持蓝牙操作
                if (mBluetoothAdapter == null) {
                    // 设备不支持蓝牙
                    showTipsDialogWithTitle(
                            "警告",
                            "当前设备不支持蓝牙操作",
                            (dialog, whitch) -> {
                                dialog.dismiss();
                            },
                            (dialog, whitch) -> {
                                dialog.dismiss();
                            });
                    return true;
                }

                // TODO: 2018/5/22 打开蓝牙
                if (!mBluetoothAdapter.isEnabled()) {
                    Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(intent, Const.REQ_ENABLE_BT);
                }
                break;
            case R.id.action_start_scan:
                mPresenter.startScan();
                break;
            case R.id.action_stop_scan:
                mPresenter.stopScan();
                break;
            case R.id.action_close_bluebooth:
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Const.REQ_ENABLE_BT) {
            LoggerUtils.loge("蓝牙开启成功");
        }
    }
}
