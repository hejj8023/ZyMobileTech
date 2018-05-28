package com.example.bailan.mvp.presenter;

import com.example.bailan.bean.RecommendBean;
import com.example.bailan.mvp.contract.RecommendContract;
import com.example.bailan.mvp.model.RecommendModel;
import com.zhiyangstudio.commonlib.mvp.presenter.BasePresenter;
import com.zhiyangstudio.commonlib.net.callback.AbsBaseObserver;

import javax.inject.Inject;

/**
 * Created by zzg on 2018/5/27.
 */

public class RecommendPresenter extends BasePresenter<RecommendContract.IListView> implements RecommendContract.IListPresenter {

    private final RecommendModel mRecommendModel;
    private RecommendContract.IListView mListView;

    @Inject
    public RecommendPresenter() {
        mRecommendModel = new RecommendModel();
    }

    @Override
    public void loadListData() {
        mListView = getView();
        mRecommendModel.loadData(new AbsBaseObserver<RecommendBean>(this, RecommendModel.class.getName()) {
            @Override
            public void onNext(RecommendBean responseBody) {
                if (responseBody != null) {

                }
            }
        });
    }
}
