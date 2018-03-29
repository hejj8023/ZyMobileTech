package com.example.rcv.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.rcv.R;
import com.example.rcv.adapter.LayoutAdapter;
import com.example.utils.LoggerUtils;
import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;

import butterknife.BindView;

/**
 * Created by example on 2018/3/29.
 */

public class BasicUseRecyclerViewPagerActivity extends BaseRecyclerViewActivity implements RecyclerViewPager.OnPageChangedListener {
    @BindView(R.id.viewpager)
    RecyclerViewPager mRecyclerView;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_basic_use_recycler_viewpager;
    }

    @Override
    protected void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLongClickable(true);
    }

    @Override
    protected void addListener() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                LoggerUtils.loge(BasicUseRecyclerViewPagerActivity.this, "onScrollStateChanged");
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                LoggerUtils.loge(BasicUseRecyclerViewPagerActivity.this, "onScrolled dx = " +
                        dx + " , dy = " + dy);
                // dx : 100 ---> 8 右-> 左滑动
                // dx : -90 ---> -8 左-> 右滑动
                int childCount = mRecyclerView.getChildCount();
                int width = mRecyclerView.getChildAt(0).getWidth();
                int padding = (mRecyclerView.getWidth() - width) / 2;
                for (int i = 0; i < childCount; i++) {
                    View view = recyclerView.getChildAt(i);
                    // 往左 从padding到-(v.getWidth()-padding)的过程中，由大到小
                    float rate = 0;
                    if (view.getLeft() <= padding) {
                        if (view.getLeft() >= padding - view.getWidth()) {
                            rate = (padding - view.getLeft()) * 1f / view.getWidth();
                        } else {
                            rate = 1;
                        }
                        view.setScaleY(1 - rate * 0.1f);
                        view.setScaleX(1 - rate * 0.1f);
                    } else {
                        // 往右 从padding到recyclerView.getWidth()-padding的过程中，由大到小
                        if (view.getLeft() <= recyclerView.getWidth() - padding) {
                            rate = (recyclerView.getWidth() - padding - view.getLeft()) * 1f / view.getWidth();
                        }
                        view.setScaleY(0.9f + rate * 0.1f);
                        view.setScaleX(0.9f + rate * 0.1f);
                    }
                }
            }
        });

        mRecyclerView.addOnPageChangedListener(this);

        mRecyclerView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                LoggerUtils.loge(BasicUseRecyclerViewPagerActivity.this, "onLayoutChange left = " +
                        left + " , top = " + top + " , right = " + right + " , bottom = " + bottom
                        + " , oldLeft = " + oldLeft + " , oldTop = " + oldTop + " , oldRight = " +
                        oldRight + " , oldBottom = " + oldBottom);
                if (mRecyclerView.getChildCount() < 3) {
                    View v1 = mRecyclerView.getChildAt(1);
                    if (v1 != null) {
                        if (mRecyclerView.getCurrentPosition() == 0) {
                            v1.setScaleY(0.9f);
                            v1.setScaleX(0.9f);
                        } else {
                            View v0 = mRecyclerView.getChildAt(0);
                            v0.setScaleY(0.9f);
                            v0.setScaleX(0.9f);
                        }
                    }
                } else {
                    View v0 = mRecyclerView.getChildAt(0);
                    if (v0 != null) {
                        v0.setScaleY(0.9f);
                        v0.setScaleX(0.9f);
                    }
                    
                    View v2 = mRecyclerView.getChildAt(2);
                    if (v2 != null) {
                        v2.setScaleY(0.9f);
                        v2.setScaleX(0.9f);
                    }
                }
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        mRecyclerView.setAdapter(new LayoutAdapter(dataList, mRecyclerView));
    }

    @Override
    public void OnPageChanged(int oldPosition, int newPosition) {
        LoggerUtils.loge(BasicUseRecyclerViewPagerActivity.this, "OnPageChanged oldPosition = "
                + oldPosition + " , newPosition = " + newPosition);
    }
}
