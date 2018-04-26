package com.example.wav.ui.activity;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.example.wav.Const;
import com.example.wav.R;
import com.example.wav.base.BaseWanAdvListActivity;
import com.example.wav.bean.CustomerGroupInfo;
import com.example.wav.bean.CustomerInfo;
import com.example.wav.bean.DeviceInfo;
import com.example.wav.mvp.contract.DeviceListContract;
import com.example.wav.mvp.presenter.DeviceListPresenter;
import com.zhiyangstudio.commonlib.adapter.BaseListAdapter;
import com.zhiyangstudio.commonlib.adapter.lgrcommon.QuickViewHolder;
import com.zhiyangstudio.commonlib.utils.IntentUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhiyang on 2018/4/26.
 * 默认加载的是所有的设备列表数据,只有筛选返回的时候才会是筛选之后的数据
 */

public class DeviceListActivity extends BaseWanAdvListActivity<DeviceListPresenter,
        DeviceListContract.IDeviceView, DeviceInfo> implements DeviceListContract
        .IDeviceView {

    @Override
    public void setData(List<DeviceInfo> data) {
        mListData.clear();
        mListData.addAll(data);
    }

    @Override
    protected String getCurrentTitle() {
        return "设备列表";
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected boolean isCanLoadMore() {
        return false;
    }

    @Override
    protected BaseListAdapter getListAdapter() {
        return new BaseListAdapter<DeviceInfo>() {
            @Override
            protected int getLayoutId(int viewType) {
                return R.layout.layout_item_device_list;
            }

            @Override
            protected void bindDatas(QuickViewHolder holder, DeviceInfo data, int itemViewType, int position) {
                holder.setText(R.id.tv_dev_name, "设备名称:" + data.getDevName());
                int devId = data.getGroupId();
                CustomerGroupInfo groupInfo = null;
                String devGroupName = "";
                if (Const.TMP_DATA.CUSTOMERGROUP_LIST != null) {
                    for (CustomerGroupInfo customerGroupInfo : Const.TMP_DATA.CUSTOMERGROUP_LIST) {
                        if (customerGroupInfo.getGroupId() == devId) {
                            groupInfo = customerGroupInfo;
                            break;
                        }
                    }
                }
                if (groupInfo != null) {
                    devGroupName = groupInfo.getGroupName();
                    if (groupInfo.isCheckd()) {
                        holder.setBackgroundColor(R.color.silver);
                    }
                }
                int customerId = data.getCustomerId();
                String cName = "";
                if (Const.TMP_DATA.CUSTOMER_LIST != null) {
                    for (CustomerInfo customerInfo : Const.TMP_DATA.CUSTOMER_LIST) {
                        if (customerInfo.getId() == customerId) {
                            cName = customerInfo.getName();
                            break;
                        }
                    }
                }
                holder.setText(R.id.tv_dev_customer_id, "用户名称:" + cName);
                holder.setText(R.id.tv_dev_group_id, "设备所属组:" + devGroupName);
                holder.setText(R.id.tv_state, "设备状态:" + (data.getState() == 0 ? "运行中" : "已停止运行"));
            }
        };
    }

    @Override
    protected View initHeaderView() {
        return null;
    }

    @Override
    protected void loadDatas() {
        mPresenter.getDeviceList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_device, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_filter:
                ToastUtils.showShort("筛选");
                IntentUtils.forwardForResult(FilterActivity.class, Const.UI_ACTION.REQ_DEVICE_LIST);
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Const.UI_ACTION.RESULT_REFRESH_UI) {
            // 对比是否需要展示设备列表
            List<CustomerGroupInfo> list = Const.TMP_DATA.CUSTOMERGROUP_LIST;
            List<DeviceInfo> nList = new ArrayList<>();
            // 清除掉不需要展示的数据
            // 第一步处理
            for (CustomerGroupInfo groupInfo : list) {
                for (DeviceInfo dInfo : Const.TMP_DATA.DEV_LIST) {
                    if (dInfo.getGroupId() == groupInfo.getGroupId()) {
                        if (groupInfo.isCheckd()) {
                            nList.add(dInfo);
                        }
                    }
                }
            }

            mListData.clear();
            mListData.addAll(nList);
            setEnableRefresh(false);
            if (nList.size() > 0) {
                showContent();
            } else {
                showEmpty();
            }
        }
    }
}
