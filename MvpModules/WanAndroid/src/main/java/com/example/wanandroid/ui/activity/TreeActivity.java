package com.example.wanandroid.ui.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.example.wanandroid.Const;
import com.example.wanandroid.R;
import com.example.wanandroid.adapter.TreeFragmentPagerAdapter;
import com.example.wanandroid.base.BaseWanAndroidActivity;
import com.example.wanandroid.bean.TreeBean;
import com.zysdk.vulture.clib.mvp.presenter.BasePresenter;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zhiyang on 2018/4/19.
 */

public class TreeActivity extends BaseWanAndroidActivity {

    @BindView(R.id.tablayout)
    TabLayout mTableLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    private TreeBean mTreebean;
    private int mAction;
    private String mTitle;
    private List<TreeBean.SubTreeBean> mTreeBeanList;
    private TreeFragmentPagerAdapter mPagerAdapter;
    private String mChapterName;
    private int mChapterId;

    @Override
    public void beforeSubContentInit() {
        super.beforeSubContentInit();

        Intent intent = getIntent();
        mAction = intent.getIntExtra(Const.BUNDLE_KEY.ACTION_TYPE, -1);
        if (mAction == Const.BUNDLE_KEY.ACTION_TREE) {
            Serializable s = intent.getSerializableExtra(Const.BUNDLE_KEY.OBJ);
            if (s != null && s instanceof TreeBean) {
                mTreebean = (TreeBean) s;
                mTitle = mTreebean.getName();
                mTreeBeanList = mTreebean.getChildren();
            }
        } else {
            mChapterName = intent.getStringExtra(Const.BUNDLE_KEY.CHAPTER_NAME);
            mTitle = mChapterName;
            mChapterId = intent.getIntExtra(Const.BUNDLE_KEY.CHAPTER_ID, 0);
        }
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void initView() {
        setTitle(mTitle);
    }

    @Override
    public void initData() {
        if (mAction == Const.BUNDLE_KEY.ACTION_TREE) {
            mPagerAdapter = new TreeFragmentPagerAdapter(getSupportFragmentManager(), mAction, mTreeBeanList);
        } else {
            mPagerAdapter = new TreeFragmentPagerAdapter(getSupportFragmentManager(), mAction,
                    mChapterId, mChapterName);
        }
        mTableLayout.setupWithViewPager(mViewPager);
        mViewPager.setAdapter(mPagerAdapter);
    }

    @Override
    public void refreshUi() {

    }

    @Override
    public void release() {

    }

    @Override
    protected boolean initToolBar() {
        return true;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_tree;
    }

    @Override
    protected int getStatusbarColor() {
        return R.color._0091ea;
    }

    @Override
    protected boolean hasSupportTransStatusBar() {
        return true;
    }
}
