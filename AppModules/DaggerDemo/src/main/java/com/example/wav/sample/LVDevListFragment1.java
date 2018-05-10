package com.example.wav.sample;

import com.example.wav.R;
import com.example.wav.bean.AccountDeviceInfo;
import com.example.wav.mvp.contract.DevListCotract2;
import com.example.wav.mvp.presenter.DevListPresenter2;
import com.zhiyangstudio.commonlib.adapter.BaseRecyclerAdapter;
import com.zhiyangstudio.commonlib.adapter.SmartViewHolder;
import com.zhiyangstudio.commonlib.refreshsupport.smartrefresh.BaseMVPSRLListFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by example on 2018/5/10.
 */

public class LVDevListFragment1 extends BaseMVPSRLListFragment<DevListPresenter2, DevListCotract2
        .IDevListView, AccountDeviceInfo.DeviceDetailInfo> implements DevListCotract2.IDevListView {

    @Override
    protected DevListPresenter2 createPresenter() {
        return new DevListPresenter2();
    }

    @Override
    protected void loadRemoteData() {
        mPresenter.loadList();
    }

    @Override
    protected void initPageNumb() {
        mPage = 1;
    }

    @Override
    protected BaseRecyclerAdapter<AccountDeviceInfo.DeviceDetailInfo> getListAdapter() {
        return new BaseRecyclerAdapter<AccountDeviceInfo.DeviceDetailInfo>(mList, R
                .layout.layout_item_device_list_online) {
            @Override
            protected void onBindViewHolder(SmartViewHolder holder, AccountDeviceInfo
                    .DeviceDetailInfo model, int position) {
                holder.text(R.id.tv_device_title, model.getName());
                holder.text(R.id.tv_device_state, model.getOnlineText());
                SimpleDateFormat sdfDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssss");
                try {
                    Date date = sdfDateFormat.parse(model.getUpdataDate());
                    SimpleDateFormat sdfDateFormat1 = new SimpleDateFormat("HH:mm");
                    String timeStr = sdfDateFormat1.format(date);
                    holder.text(R.id.tv_device_time, timeStr);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        };
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
