package com.example.wanandroid.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.wanandroid.Const;
import com.example.wanandroid.bean.TreeBean;
import com.example.wanandroid.ui.fragment.TreeListFragment;

import java.util.List;

/**
 * Created by zhiyang on 2018/4/19.
 */

public class TreeFragmentPagerAdapter extends FragmentPagerAdapter {
    private int mAction;
    private List<TreeBean.SubTreeBean> mList;
    private int mChapterId;
    private String mChapterName;

    public TreeFragmentPagerAdapter(FragmentManager manager, int action, List<TreeBean.SubTreeBean> list) {
        super(manager);
        this.mAction = action;
        this.mList = list;
    }

    public TreeFragmentPagerAdapter(FragmentManager supportFragmentManager, int action, int chapterId, String chapterName) {
        super(supportFragmentManager);
        this.mAction = action;
        this.mChapterId = chapterId;
        this.mChapterName = chapterName;
    }

    @Override
    public Fragment getItem(int position) {
        if (Const.BUNDLE_KEY.ACTION_TREE == mAction) {
            return TreeListFragment.instance(mList.get(position).getId());
        } else if (Const.BUNDLE_KEY.ACTION_LIST == mAction) {
            return TreeListFragment.instance(mChapterId);
        }
        return null;
    }

    @Override
    public int getCount() {
        if (Const.BUNDLE_KEY.ACTION_TREE == mAction) {
            return mList != null ? mList.size() : 0;
        }
        return 1;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (Const.BUNDLE_KEY.ACTION_TREE == mAction) {
            return mList.get(position).getName();
        }
        return mChapterName;
    }
}
