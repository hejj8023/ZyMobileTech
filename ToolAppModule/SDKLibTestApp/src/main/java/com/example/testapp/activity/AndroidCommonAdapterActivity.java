package com.example.testapp.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.example.testapp.R;
import com.example.testapp.fragment.MultiTypeItemGridFragment;
import com.example.testapp.fragment.MultiTypeItemListFragment;
import com.example.testapp.fragment.MultiTypeItemRecyclerFragment;
import com.example.testapp.fragment.SingleTypeItemGridFragmentSingle;
import com.example.testapp.fragment.SingleTypeItemRecyclerFragmentSingle;
import com.example.testapp.fragment.SingleTypeItemSingleListFragment;
import com.zhiyangstudio.commonlib.corel.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by example on 2018/4/18.
 */

public class AndroidCommonAdapterActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    @Override
    public int getContentId() {
        return R.layout.activity_android_common_adapter;
    }

    @Override
    public void initView() {
        mToolbar.setTitle("Android通用Adapter");
        setSupportActionBar(mToolbar);
    }

    @Override
    public void addListener() {
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setupWithViewPager(mViewPager);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new SingleTypeItemSingleListFragment());
        fragments.add(new SingleTypeItemGridFragmentSingle());
        fragments.add(new SingleTypeItemRecyclerFragmentSingle());
        fragments.add(new MultiTypeItemListFragment());
        fragments.add(new MultiTypeItemGridFragment());
        fragments.add(new MultiTypeItemRecyclerFragment());
        String[] titles = {
                "S-list", "S-grid", "S-recycler",
                "M-list", "M-grid", "M-recycler"
        };
        ListPageAdapter listPageAdapter = new ListPageAdapter(getSupportFragmentManager(),
                fragments, titles);
        mViewPager.setAdapter(listPageAdapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public void refreshUi() {

    }

    @Override
    public void release() {

    }

    @Override
    protected PermissionListener getPermissonCallBack() {
        return null;
    }

    private class ListPageAdapter extends FragmentPagerAdapter {
        private List<Fragment> mList;
        private String[] mTitles;

        public ListPageAdapter(FragmentManager manager, List<Fragment> list, String[] titles) {
            super(manager);
            this.mList = list;
            this.mTitles = titles;
        }

        @Override
        public Fragment getItem(int position) {
            return mList.get(position);
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }
    }
}
