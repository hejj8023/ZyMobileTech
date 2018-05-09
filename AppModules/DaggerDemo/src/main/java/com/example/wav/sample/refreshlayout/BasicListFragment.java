package com.example.wav.sample.refreshlayout;

import android.os.Bundle;
import android.widget.AbsListView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.wav.R;
import com.example.wav.adapter.BaseRecyclerAdapter;
import com.example.wav.adapter.SmartViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhiyangstudio.commonlib.mvp.BaseMVPSupportFragment;
import com.zhiyangstudio.commonlib.mvp.presenter.BasePresenter;

import java.util.Arrays;
import java.util.Collection;

import butterknife.BindView;
import io.reactivex.annotations.NonNull;

/**
 * Created by example on 2018/5/9.
 */

public class BasicListFragment extends BaseMVPSupportFragment {
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    @BindView(R.id.listView)
    AbsListView mListView;
    private BaseRecyclerAdapter<Void> mAdapter;

    @Override
    public int getContentId() {
        return R.layout.fragmetn_list_basic_refresh_layout;
    }

    @Override
    public void initView() {

        mAdapter = new BaseRecyclerAdapter<Void>(android.R.layout
                .simple_list_item_2) {
            @Override
            protected void onBindViewHolder(SmartViewHolder holder, Void model, int position) {
                holder.text(android.R.id.text1, getString(R.string.item_example_number_title,
                        position));
                holder.text(android.R.id.text2, getString(R.string.item_example_number_abstract,
                        position));
                holder.textColorId(android.R.id.text2, R.color.colorTextAssistant);
            }
        };

        mListView.setAdapter(mAdapter);
        refreshLayout.setEnableAutoLoadMore(true);//开启自动加载功能（非必须）
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.refresh(initData2());
                        refreshLayout.finishRefresh();
                        refreshLayout.setNoMoreData(false);
                    }
                }, 2000);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mAdapter.getItemCount() > 30) {
                            ToastUtils.showShort("数据全部加载完毕");
                            refreshLayout.finishLoadMoreWithNoMoreData();//将不会再次触发加载更多事件
                        } else {
                            mAdapter.loadMore(initData2());
                            refreshLayout.finishLoadMore();
                        }
                    }
                }, 2000);
            }
        });

        //触发自动刷新
        refreshLayout.autoRefresh();
    }

    private Collection<Void> initData2() {
        return Arrays.asList(null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null);
    }

    @Override
    public void addListener() {

    }

    @Override
    public void initData() {

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
}
