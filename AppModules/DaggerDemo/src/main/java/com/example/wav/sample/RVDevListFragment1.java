package com.example.wav.sample;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wav.R;
import com.example.wav.bean.AccountDeviceInfo;
import com.example.wav.mvp.contract.DevListCotract2;
import com.example.wav.mvp.presenter.DevListPresenter2;
import com.zhiyangstudio.commonlib.refreshsupport.smartrefresh.BaseMVPSRRListFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by example on 2018/5/10.
 */

public class RVDevListFragment1 extends BaseMVPSRRListFragment<DevListPresenter2, DevListCotract2
        .IDevListView, AccountDeviceInfo.DeviceDetailInfo> implements DevListCotract2.IDevListView {

    @Override
    protected void loadRemoteData() {
        mPresenter.loadList();
    }

    @Override
    protected void initPageNumb() {
        mPage = 1;
    }

    @Override
    protected BaseQuickAdapter<AccountDeviceInfo.DeviceDetailInfo, BaseViewHolder> getListAdapter
            () {
        return new BaseQuickAdapter<AccountDeviceInfo.DeviceDetailInfo, BaseViewHolder>(R.layout
                .layout_item_device_list_online, mList) {
            @Override
            protected void convert(BaseViewHolder helper, AccountDeviceInfo.DeviceDetailInfo item) {
                helper.setText(R.id.tv_device_title, item.getName());
                helper.setText(R.id.tv_device_state, item.getOnlineText());
                SimpleDateFormat sdfDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssss");
                try {
                    Date date = sdfDateFormat.parse(item.getUpdataDate());
                    SimpleDateFormat sdfDateFormat1 = new SimpleDateFormat("HH:mm");
                    String timeStr = sdfDateFormat1.format(date);
                    helper.setText(R.id.tv_device_time, timeStr);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    @Override
    protected DevListPresenter2 createPresenter() {
        return new DevListPresenter2();
    }

    @Override
    public int getStatus() {
        return 0;
    }

    @Override
    public int getPageSize() {
        return 10;
    }

}
