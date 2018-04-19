package com.example.wanandroid.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.wanandroid.Const;
import com.example.wanandroid.adapter.TreeAdapter;
import com.example.wanandroid.bean.TreeBean;
import com.example.wanandroid.inter.OnTreeItemClickListener;
import com.example.wanandroid.mvp.contract.TreeContract;
import com.example.wanandroid.mvp.presenter.TreePresenter;
import com.example.wanandroid.ui.activity.TreeActivity;
import com.zhiyangstudio.commonlib.adapter.BaseListAdapter;
import com.zhiyangstudio.commonlib.mvp.BaseAbsListFragment;
import com.zhiyangstudio.commonlib.utils.IntentUtils;

import java.util.List;

/**
 * Created by zhiyang on 2018/4/10.
 */

public class TreeFragment extends BaseAbsListFragment<TreePresenter, TreeContract.ITreeView,
        TreeBean> implements TreeContract.ITreeView, OnTreeItemClickListener {

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
    public void setData(List<TreeBean> data) {
        mListData.addAll(data);
    }

    @Override
    protected TreePresenter createPresenter() {
        return new TreePresenter();
    }

    @Override
    protected void initArguments(Bundle bundle) {

    }

    @Override
    protected void loadDatas() {
        mPresenter.loadTreeList();
    }

    @Override
    protected boolean isCanLoadMore() {
        return true;
    }

    @Override
    protected BaseListAdapter getListAdapter() {
        return new TreeAdapter(this);
    }

    @Override
    protected View initHeaderView() {
        return null;
    }

    @Override
    public void onItemClick(TreeBean bean) {
        Intent intent = new Intent(mContext,TreeActivity.class);
        intent.putExtra(Const.BUNDLE_KEY.ACTION_TYPE, Const.BUNDLE_KEY.ACTION_TREE);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Const.BUNDLE_KEY.OBJ,bean);
        intent.putExtras(bundle);
        IntentUtils.forward(intent);
    }
}
