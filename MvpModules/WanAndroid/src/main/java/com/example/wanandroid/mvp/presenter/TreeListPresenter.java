package com.example.wanandroid.mvp.presenter;

import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.mvp.contract.TreeListContract;
import com.example.wanandroid.mvp.model.TreeListModel;
import com.zysdk.vulture.clib.mvp.presenter.BasePresenter;
import com.zysdk.vulture.clib.net.callback.RxObserver;
import com.zysdk.vulture.clib.net.callback.RxPageListObserver;

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

    @Override
    public void unCollectArticle() {
        mTreeListView = getView();
        mListModel.unCollect(mTreeListView.getArticleId(), new RxObserver<String>(this) {
            @Override
            protected void onSucess(String data) {
                mTreeListView.collect(false, "取消收藏成功");
            }

            @Override
            protected void onFailure(int errorCode, String errorMsg) {
                mTreeListView.showFilure(errorMsg);
            }
        });
    }

    @Override
    public void collectArticle() {
        mTreeListView = getView();
        mListModel.collect(mTreeListView.getArticleId(), new RxObserver<String>(this) {
            @Override
            protected void onSucess(String data) {
                mTreeListView.collect(true, "收藏成功");
            }

            @Override
            protected void onFailure(int errorCode, String errorMsg) {
                mTreeListView.showFilure(errorMsg);
            }
        });
    }
}
