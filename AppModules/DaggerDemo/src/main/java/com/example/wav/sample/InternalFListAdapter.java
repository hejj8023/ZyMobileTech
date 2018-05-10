package com.example.wav.sample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by example on 2018/5/9.
 */

public class InternalFListAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mList;

    private String[] mTitles = new String[]{
            "RV-DevList-(Swipe+Hao->1)", "LV-DevList-(Swipe+Hao->1)", "RV-DevList-(Swipe+Hao)",
            "LV-DevList-(Swipe+Hao)", "EmptyList", "BasicList",
            "DevList-(Swipe+Hao)", "DevList-(Hao->Lib)", "ArmList",
            "ArmList-框架", "StaicsList-n"};

    public InternalFListAdapter(FragmentManager supportFragmentManager, List<Fragment> list) {
        super(supportFragmentManager);
        this.mList = list;
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
