package com.example.wanandroid.mvp.presenter;

import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.mvp.contract.TreeListContract;
import com.example.wanandroid.mvp.model.TreeListModel;
import com.zhiyangstudio.commonlib.mvp.presenter.BasePresenter;
import com.zhiyangstudio.commonlib.net.callback.RxPageListObserver;

import java.util.List;

/**
 * Created by zhiyang on 2018/4/19.
 */

public class TreeListPresenter extends BasePresenter<TreeListContract.ITreeListView> implements
        TreeListContract.ITreeListPresenter {

    private final TreeListModel mListModel;
    private TreeListContract.ITreeListView mTreeListView;

    public TreeListPresenter() {
        mListModel = new TreeListModel();
    }

    @Override
    public void loadTreeList() {
        mTreeListView = getView();
        mListModel.getTreeList(mTreeListView.getPage(), mTreeListView.getCid(), new
                RxPageListObserver<ArticleBean>(this, TreeListModel.class.getName()) {
                    @Override
                    protected void onSucess(List<ArticleBean> list) {
                        mTreeListView.setData(list);
                        if (list == null || list.size() == 0) {
                            mTreeListView.showEmpty();
                        } else {
                            mTreeListView.showContent();
                        }
                    }

                    @Override
                    protected void onFailure(int errorCode, String errorMsg) {
                        mTreeListView.showFail(errorMsg);
                    }
                });
    }
}
