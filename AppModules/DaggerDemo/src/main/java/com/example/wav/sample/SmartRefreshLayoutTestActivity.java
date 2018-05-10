package com.example.wav.sample;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.wav.R;
import com.example.wav.base.BaseAdvActivity;
import com.example.wav.mvp.contract.SRLContract;
import com.example.wav.mvp.presenter.SRLPresenter;
import com.example.wav.sample.refreshlayout.BasicListFragment;
import com.example.wav.sample.refreshlayout.EmptyListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by example on 2018/5/9.
 */

public class SmartRefreshLayoutTestActivity extends BaseAdvActivity<SRLPresenter,
        SRLContract.SRLIView> {

    @BindView(R.id.tablayout)
    TabLayout mTabLayout;

    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setupWithViewPager(mViewPager);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new RVDevListFragment1());
        fragments.add(new LVDevListFragment1());
        fragments.add(new RVDevListFragment());
        fragments.add(new LVDevListFragment());
        fragments.add(new EmptyListFragment());
        fragments.add(new BasicListFragment());
        fragments.add(new DevListFragment1());
        fragments.add(new DevListFragment2());
        fragments.add(new ArmListFragment());
        fragments.add(new ArmListFragment1());
        fragments.add(new StaicsListFragment());
        InternalFListAdapter adapter = new InternalFListAdapter
                (getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(adapter);
    }

    @Override
    public void refreshUi() {

    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected boolean initToolBar() {
        setTitle("refresh_layout新框架示例");
        return false;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_smart_refresh_layout;
    }
}
