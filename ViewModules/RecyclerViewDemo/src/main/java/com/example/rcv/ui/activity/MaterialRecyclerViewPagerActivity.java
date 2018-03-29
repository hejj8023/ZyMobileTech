package com.example.rcv.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;

import com.example.rcv.R;
import com.example.rcv.divider.SpacesItemDecoration;
import com.example.rcv.ui.fragment.CheeseListFragment;
import com.lsjwzh.widget.recyclerviewpager.FragmentStatePagerAdapter;
import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;
import com.lsjwzh.widget.recyclerviewpager.TabLayoutSupport;

import java.util.LinkedHashMap;

import butterknife.BindView;

/**
 * Created by example on 2018/3/29.
 */

public class MaterialRecyclerViewPagerActivity extends BaseRecyclerViewActivity {

    @BindView(R.id.tablaout)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    RecyclerViewPager mRecyclerView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private FragmentsAdapter mAdapter;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_material_rvp;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void initData() {
        super.initData();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new FragmentsAdapter(getSupportFragmentManager());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLongClickable(true);
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(50, mRecyclerView.getAdapter()
                .getItemCount()));
        mRecyclerView.addOnPageChangedListener(new RecyclerViewPager.OnPageChangedListener() {
            @Override
            public void OnPageChanged(int i, int i1) {

            }
        });
        TabLayoutSupport.setupWithViewPager(tabLayout, mRecyclerView, mAdapter);
    }

    class FragmentsAdapter extends FragmentStatePagerAdapter implements TabLayoutSupport.ViewPagerTabLayoutAdapter {

        LinkedHashMap<Integer, Fragment> mFragmentCache = new LinkedHashMap<>();

        public FragmentsAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int i, Fragment.SavedState savedState) {
            Fragment fragment = mFragmentCache.containsKey(i) ? mFragmentCache.get(i) : new
                    CheeseListFragment();
            if (savedState == null || fragment.getArguments() == null) {
                Bundle bundle = new Bundle();
                bundle.putInt("index", i);
                fragment.setArguments(bundle);
            } else if (!mFragmentCache.containsKey(i)) {
                fragment.setInitialSavedState(savedState);
            }
            mFragmentCache.put(i, fragment);
            return fragment;
        }

        @Override
        public void onDestroyItem(int i, Fragment fragment) {
            // 只保留5个项目
            while (mFragmentCache.size() > 5) {
                Object[] keys = mFragmentCache.keySet().toArray();
                mFragmentCache.remove(keys[0]);
            }
        }

        @Override
        public String getPageTitle(int position) {
            return "item->" + position;
        }

        @Override
        public int getItemCount() {
            return 10;
        }
    }
}
