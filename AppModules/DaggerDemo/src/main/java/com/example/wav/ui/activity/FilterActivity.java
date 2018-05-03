package com.example.wav.ui.activity;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.blankj.utilcode.util.ToastUtils;
import com.example.wav.Const;
import com.example.wav.R;
import com.example.wav.adapter.CustomerGroupAdapter;
import com.example.wav.adapter.CustomerInfoAdapter;
import com.example.wav.adapter.DeviceAdapter;
import com.example.wav.base.BaseAdvActivity;
import com.example.wav.bean.CustomerGroupInfo;
import com.example.wav.bean.CustomerInfo;
import com.example.wav.bean.DeviceInfo;
import com.example.wav.mvp.contract.FilterContract;
import com.example.wav.mvp.presenter.FilterPresenter;
import com.zhiyangstudio.commonlib.adapter.lgrcommon.QuickAdapter;

import java.util.List;

import butterknife.BindView;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.SimpleTabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;

/**
 * Created by zhiyang on 2018/4/26.
 */

public class FilterActivity extends BaseAdvActivity<FilterPresenter, FilterContract.IFilterView> implements FilterContract.IFilterView {
    @BindView(R.id.tablayout)
    VerticalTabLayout mVerticalTabLayout;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private List<CustomerGroupInfo> mCustomerGroupInfoList;
    private List<CustomerInfo> mCustomerInfoList;
    private QuickAdapter adapter = null;
    private List<DeviceInfo> mDevList;

    VerticalTabLayout.OnTabSelectedListener onTabSelectedListener = new VerticalTabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabView tab, int position) {
            switch (position) {
                case 0:
                    FilterActivity.this.adapter = new CustomerInfoAdapter(mContext, mCustomerInfoList, mCustomerGroupInfoList);
                    break;
                case 1:
                    FilterActivity.this.adapter = new CustomerGroupAdapter(mContext, mCustomerGroupInfoList, mCustomerInfoList);
                    break;
                case 2:
                    FilterActivity.this.adapter = new DeviceAdapter(mContext, mDevList, mCustomerInfoList, mCustomerGroupInfoList);
                    break;
            }
            if (FilterActivity.this.adapter != null) {
                recyclerView.setAdapter(FilterActivity.this.adapter);
            }
        }

        @Override
        public void onTabReselected(TabView tab, int position) {

        }
    };

    private SimpleTabAdapter simpleTabAdapter = new SimpleTabAdapter() {
        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public ITabView.TabTitle getTitle(int position) {
            String title = "客户名称";
            switch (position) {
                case 1:
                    title = "客户分组";
                    break;
                case 2:
                    title = "设备状态";
                    break;
            }
            return new ITabView.TabTitle.Builder()
                    .setContent(title)
                    .setTextColor(R.color.red, R.color.greenyellow)
                    .setTextSize(20)
                    .build();
        }
    };

    @Override
    public void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
    }

    @Override
    public void initData() {
        mPresenter.loadList();
    }

    @Override
    public void refreshUi() {

    }

    @Override
    public void release() {

    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected boolean initToolBar() {
        setTitle("筛选");
        return true;
    }

    @Override
    protected boolean hasShowHome() {
        return true;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_filter;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_filter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_filter_confirm:
                ToastUtils.showShort("确定");
                Const.TMP_DATA.DEV_LIST.clear();
                Const.TMP_DATA.CUSTOMERGROUP_LIST.clear();
                Const.TMP_DATA.CUSTOMER_LIST.clear();
                // TODO: 2018/4/26 这里添加的数据是状态发生变化之后的数据,因为该界面上有对数据处理的操作
                Const.TMP_DATA.DEV_LIST.addAll(mDevList);
                Const.TMP_DATA.CUSTOMERGROUP_LIST.addAll(mCustomerGroupInfoList);
                Const.TMP_DATA.CUSTOMER_LIST.addAll(mCustomerInfoList);
                setResult(Const.UI_ACTION.RESULT_REFRESH_UI);
                finish();
                break;
        }
        return true;
    }

    @Override
    public void setCustomerInfoData(List<CustomerInfo> list) {
        this.mCustomerInfoList = list;
    }

    @Override
    public void setCustomerGroupInfoData(List<CustomerGroupInfo> list) {
        this.mCustomerGroupInfoList = list;
    }

    @Override
    public void setDeviceList(List<DeviceInfo> list) {
        this.mDevList = list;
    }

    @Override
    public void refreshContentUi() {
        mVerticalTabLayout.setTabAdapter(simpleTabAdapter);
        mVerticalTabLayout.addOnTabSelectedListener(onTabSelectedListener);
        this.adapter = new CustomerInfoAdapter(mContext, mCustomerInfoList, mCustomerGroupInfoList);
        recyclerView.setAdapter(this.adapter);
    }
}
