package com.example.wav.ui.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

import com.example.wav.Const;
import com.example.wav.R;
import com.example.wav.base.BaseAdvActivity;
import com.example.wav.bean.AccountCustomerInfo;
import com.example.wav.bean.AccountDeviceInfo;
import com.example.wav.bean.AccountGroupInfo;
import com.example.wav.mvp.contract.FilterNewContract;
import com.example.wav.mvp.presenter.FilterNewPresenter;
import com.example.wav.ui.fragment.filter.FilterCustomerGroupListFragment;
import com.example.wav.ui.fragment.filter.FilterCustomerListFragment;
import com.example.wav.ui.fragment.filter.FilterDeviceListFragment;
import com.zhiyangstudio.commonlib.CommonConst;
import com.zhiyangstudio.commonlib.mvp.BaseAbsListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.SimpleTabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;

/**
 * Created by example on 2018/4/28.
 */

public class NewFilterActivity extends BaseAdvActivity<FilterNewPresenter,
        FilterNewContract.IFilterNewView> implements FilterNewContract.IFilterNewView {

    @BindView(R.id.tablayout)
    VerticalTabLayout mVerticalTabLayout;
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
    private List<BaseAbsListFragment> mFragmentList = new ArrayList<>();
    // 当前的条目
    private int currentPos;
    VerticalTabLayout.OnTabSelectedListener onTabSelectedListener = new VerticalTabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabView tab, int position) {
            int index = -1;
            switch (position) {
                case 0:
                    index = 0;
                    break;
                case 1:
                    index = 1;
                    break;
                case 2:
                    index = 2;
                    break;
            }
            if (currentPos != index) {
                BaseAbsListFragment fragment = mFragmentList.get(index);
                fragment.state = CommonConst.PAGE_STATE.STATE_REFRESH;
                // 重新请求第一次的数据
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                // 隐藏上一个
                transaction.hide(mFragmentList.get(currentPos));
                transaction.replace(R.id.fl_filter_root, fragment);
                // 显示当前的
                transaction.show(fragment).commitAllowingStateLoss();
                currentPos = index;
            }
        }

        @Override
        public void onTabReselected(TabView tab, int position) {

        }
    };

    @Override
    protected boolean initToolBar() {
        setTitle("筛选 ~ New");
        return true;
    }

    @Override
    protected boolean hasShowHome() {
        return true;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_filter2;
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
                // 方案修改了这里就不需要再存储数据了
                setResult(Const.UI_ACTION.RESULT_REFRESH_UI);
                finish();
                break;
        }
        return true;
    }

    @Override
    public void initView() {
        mVerticalTabLayout.setTabAdapter(simpleTabAdapter);
        mVerticalTabLayout.addOnTabSelectedListener(onTabSelectedListener);
    }

    @Override
    public void initData() {
        initFragments();
    }

    private void initFragments() {
        FilterCustomerListFragment customerListFragment = new FilterCustomerListFragment();
        FilterCustomerGroupListFragment groupListFragment = new FilterCustomerGroupListFragment();
        FilterDeviceListFragment deviceListFragment = new FilterDeviceListFragment();
        mFragmentList.add(customerListFragment);
        mFragmentList.add(groupListFragment);
        mFragmentList.add(deviceListFragment);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fl_filter_root, mFragmentList.get(0))
                .show(mFragmentList.get(0))
                .commitAllowingStateLoss();
    }

    @Override
    public void refreshUi() {

    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void setCustomerListData(List<AccountCustomerInfo> list) {
        AccountCustomerInfo accountGroupInfo = list.get(0);
        accountGroupInfo.setChecked(true);
        Const.TMP_DATA.FILTER_CUSTOMER_ID = accountGroupInfo.getId() + "";
        list.set(0, accountGroupInfo);

    }

    @Override
    public void setCustomerGroupListData(List<AccountGroupInfo> list) {
        AccountGroupInfo accountGroupInfo = list.get(0);
        accountGroupInfo.setChecked(true);
        Const.TMP_DATA.FILTER_CUSTOMER_GROUP_ID = accountGroupInfo.getId();
        list.set(0, accountGroupInfo);
    }

    @Override
    public void setDeviceListData(AccountDeviceInfo list) {
        if (list != null) {
            List<AccountDeviceInfo.DeviceDetailInfo> detailInfos = list.getRows();
        }
    }

    @Override
    public int getStatus() {
        return 0;
    }

    @Override
    public int getPage() {
        return 1;
    }

    @Override
    public int getPageSize() {
        return 20;
    }
}
