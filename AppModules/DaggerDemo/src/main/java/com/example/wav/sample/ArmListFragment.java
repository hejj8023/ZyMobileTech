package com.example.wav.sample;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wav.R;
import com.zhiyangstudio.commonlib.mvp.BaseMVPSupportFragment;
import com.zhiyangstudio.commonlib.mvp.presenter.BasePresenter;
import com.zhiyangstudio.commonlib.utils.LoggerUtils;
import com.zhiyangstudio.commonlib.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.fangx.haorefresh.HaoRecyclerView;

/**
 * Created by example on 2018/5/9.
 */

public class ArmListFragment extends BaseMVPSupportFragment {

    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.hao_recycleview)
    HaoRecyclerView mHaoRecyclerView;
    private ArrayList<String> listData = new ArrayList<>();
    private int limit = 10;
    private MyAdapter mAdapter;
    private boolean isFirstLoad = true;
    private int mPage = 1;

    @Override
    public int getContentId() {
        return R.layout.fragmetn_arm_list;
    }

    @Override
    public void initView() {
        mRefreshLayout.setColorSchemeResources(R.color.textBlueDark, R.color.textBlueDark, R
                        .color.textBlueDark,
                R.color.textBlueDark);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mHaoRecyclerView.setLayoutManager(layoutManager);

        mHaoRecyclerView.addItemDecoration(new DividerItemDecoration(mContext,
                DividerItemDecoration.VERTICAL));

        View footerView = UiUtils.inflateView(R.layout.item_root_loading_more);
        mHaoRecyclerView.setFootLoadingView(footerView);

        View noMoreDataView = UiUtils.inflateView(R.layout.item_footer_nomore);
        mHaoRecyclerView.setFootEndView(noMoreDataView);
    }

    @Override
    public void addListener() {
        mRefreshLayout.setOnRefreshListener(() -> {
            UiUtils.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // 重置
                    mPage = 1;
                    initData();
                    //注意此处
                    mHaoRecyclerView.refreshComplete();
                    mRefreshLayout.setRefreshing(false);
                    mAdapter.notifyDataSetChanged();
                }
            }, 500);
        });
        mHaoRecyclerView.setLoadMoreListener(() -> {
            UiUtils.postDelayed(new Runnable() {
                @Override
                public void run() {
                    int size = listData.size();
                    mPage++;
                    LoggerUtils.loge("listData.size() = " + size);
                    if (size >= 35) {
                        mHaoRecyclerView.loadMoreEnd();
                        return;
                    }

                    if (mPage < 4) {
                        for (int i = 0; i < 10; i++) {
                            listData.add((size + i) + " -> , page = 2");
                        }
                    } else {
                        for (int i = 0; i < 5; i++) {
                            listData.add((size + i) + " -> , page = 3");
                        }
                    }
                    mAdapter.notifyDataSetChanged();
                    mHaoRecyclerView.loadMoreComplete();
                }
            }, 800);
        });
    }

    @Override
    public void initData() {
        listData.clear();
        for (int i = 0; i < mPage * limit; i++) {
            listData.add(i + " , page = 1");
        }

        if (isFirstLoad) {
            isFirstLoad = false;
            mAdapter = new MyAdapter(listData);
            mHaoRecyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    public void refreshUi() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initArguments(Bundle bundle) {

    }

    private class MyAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
        public MyAdapter(List<String> data) {
            super(R.layout.layout_item_test_list, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.tag_text, item);
        }
    }
}
