package com.example.bailan.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.bailan.R;
import com.example.bailan.base.BaseBLActivity;
import com.example.bailan.ui.fragment.CategoryListFragment;
import com.example.bailan.ui.fragment.ManagerFragment;
import com.example.bailan.ui.fragment.MimeFragment;
import com.example.bailan.ui.fragment.RankListFragment;
import com.example.bailan.ui.fragment.RecommendListFragment;
import com.zhiyangstudio.commonlib.corel.BaseFragment;
import com.zhiyangstudio.commonlib.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseBLActivity {

    @BindView(R.id.tl_main)
    TabLayout mTabLayout;

    @BindView(R.id.vp_main)
    ViewPager mViewPager;

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        String[] titles = UiUtils.getStrArrs(R.array.main_top_list);
        List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(new RecommendListFragment());
        fragments.add(new CategoryListFragment());
        fragments.add(new RankListFragment());
        fragments.add(new ManagerFragment());
        fragments.add(new MimeFragment());
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setupWithViewPager(mViewPager);
        MainPagerAdapter mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(),
                fragments, titles);
        mViewPager.setAdapter(mainPagerAdapter);
    }

    @Override
    public void refreshUi() {

    }

    @Override
    protected void initInject() {

    }

    @Override
    protected boolean initToolBar() {
        return false;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    class MainPagerAdapter extends FragmentPagerAdapter {

        private String[] mTitles;
        private List<BaseFragment> mFragmentList;

        public MainPagerAdapter(FragmentManager fm, List<BaseFragment> fragments, String[] titles) {
            super(fm);
            this.mFragmentList = fragments;
            this.mTitles = titles;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }
    }
}
