package com.example.wav.sample.refreshlayout;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.wav.R;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhiyangstudio.commonlib.adapter.BaseRecyclerAdapter;
import com.zhiyangstudio.commonlib.adapter.SmartViewHolder;
import com.zhiyangstudio.commonlib.mvp.BaseMVPSupportFragment;
import com.zhiyangstudio.commonlib.mvp.presenter.BasePresenter;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import ezy.ui.layout.LoadingLayout;

/**
 * Created by example on 2018/5/10.
 */

public class EmptyListFragment extends BaseMVPSupportFragment implements OnRefreshListener {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.loading)
    LoadingLayout loadingLayout;

    @Override
    public int getContentId() {
        return R.layout.fragment_empty_list;
    }

    @Override
    public void initView() {
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));

        refreshLayout.setRefreshHeader(new ClassicsHeader(getContext()).setSpinnerStyle
                (SpinnerStyle.FixedBehind).setPrimaryColorId(R.color.colorPrimary)
                .setAccentColorId(android.R.color.white));
        refreshLayout.setOnRefreshListener(this);

//        loadingLayout.showEmpty();
    }

    @Override
    public void addListener() {
        refreshLayout.getLayout().postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.autoRefresh();
            }
        }, 3000);
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

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        String[] data = {"集成第三方控件", "内部嵌套", "外部嵌套"};
        List<String> tList = Arrays.asList(data);
        TmpAdapter tmpAdapter = new TmpAdapter(tList);
        mRecyclerView.setAdapter(tmpAdapter);
        refreshLayout.finishRefresh();
        loadingLayout.showContent();
    }

    private class TmpAdapter extends BaseRecyclerAdapter<String> {
        public TmpAdapter(List<String> tList) {
            super(tList, R.layout.layout_item_test_list);
        }

        @Override
        protected void onBindViewHolder(SmartViewHolder holder, String model, int position) {
            holder.text(R.id.tag_text, model);
        }
    }
}
