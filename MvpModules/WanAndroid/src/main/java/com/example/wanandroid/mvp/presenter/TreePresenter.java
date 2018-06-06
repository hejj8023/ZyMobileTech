package com.example.wanandroid.mvp.presenter;

import com.example.wanandroid.bean.TreeBean;
import com.example.wanandroid.mvp.contract.TreeContract;
import com.example.wanandroid.mvp.model.TreeModel;
import com.zysdk.vulture.clib.mvp.presenter.BasePresenter;
import com.zysdk.vulture.clib.net.callback.RxObserver;

import java.util.List;

/**
 * Created by zhiyang on 2018/4/19.
 */

public class TreePresenter extends BasePresenter<TreeContract.ITreeView> implements TreeContract
        .ITreePresenter {

    private final TreeModel mTreeModel;
    private TreeContract.ITreeView mITreeView;

    public TreePresenter() {
        mTreeModel = new TreeModel();
    }

    @Override
    public void loadTreeList() {
        mITreeView = getView();
        mTreeModel.getTreeData(new RxObserver<List<TreeBean>>(this, TreeModel.class.getName()) {
            @Override
            protected void onSucess(List<TreeBean> data) {
                mITreeView.setData(data);
                if (data == null || data.size() == 0) {
                    mITreeView.showEmpty();
                } else {
                    mITreeView.showContent();
                }
            }

            @Override
            protected void onFailure(int errorCode, String errorMsg) {
                mITreeView.showFail(errorMsg);
            }
        });
    }
}
