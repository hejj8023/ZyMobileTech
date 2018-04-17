package com.example.wanandroid.mvp.presenter;

import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.bean.FriendBean;
import com.example.wanandroid.bean.HotwordBean;
import com.example.wanandroid.mvp.contract.SearchContract;
import com.example.wanandroid.mvp.model.SearchModel;
import com.zhiyangstudio.commonlib.mvp.presenter.BasePresenter;
import com.zhiyangstudio.commonlib.net.callback.RxObserver;
import com.zhiyangstudio.commonlib.net.callback.RxPageListObserver;
import com.zhiyangstudio.commonlib.utils.LoggerUtils;

import java.util.List;

/**
 * Created by example on 2018/4/13.
 */

public class SearchPresenter extends BasePresenter<SearchContract.ISearchView> implements SearchContract.ISearchPresenter {

    private final SearchModel mSearchModel;
    private SearchContract.ISearchView mSearchView;

    public SearchPresenter() {
        mSearchModel = new SearchModel();
    }

    @Override
    public void getHotWord() {
        mSearchView = getView();
        mSearchModel.getHotWord(new RxObserver<List<HotwordBean>>(this) {

            @Override
            protected void onSucess(List<HotwordBean> data) {
                if (data != null) {
                    mSearchView.setHotwordData(data);
                }
            }

            @Override
            protected void onFailure(int errorCode, String errorMsg) {
                mSearchView.showFail(errorMsg);
            }
        });
    }

    @Override
    public void getFriend() {
        mSearchView = getView();
        mSearchModel.getFriend(new RxObserver<List<FriendBean>>(this) {

            @Override
            protected void onSucess(List<FriendBean> data) {
                if (data != null) {
                    mSearchView.setFriendData(data);
                }
            }

            @Override
            protected void onFailure(int errorCode, String errorMsg) {
                mSearchView.showFail(errorMsg);
            }
        });
    }

    @Override
    public void search() {
        mSearchView = getView();
        int page = mSearchView.getPage();
        LoggerUtils.loge(this, page + "");
        mSearchModel.searchArticle(page, mSearchView.getKeyword(), new
                RxPageListObserver<ArticleBean>(this) {
                    @Override
                    protected void onSucess(List<ArticleBean> list) {
                        mSearchView.setData(list);
                        if (mSearchView.getData().size() == 0) {
                            mSearchView.showEmpty();
                        } else {
                            mSearchView.showContent();
                        }
                    }

                    @Override
                    protected void onFailure(int errorCode, String errorMsg) {
                        mSearchView.showFail(errorMsg);
                    }
                });
    }
}
