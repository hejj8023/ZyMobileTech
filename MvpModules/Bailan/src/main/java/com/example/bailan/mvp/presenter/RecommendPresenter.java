package com.example.bailan.mvp.presenter;

import com.example.bailan.bean.RecommonFinalBean;
import com.example.bailan.mvp.contract.RecommendContract;
import com.example.bailan.mvp.model.RecommendModel;
import com.zhiyangstudio.commonlib.mvp.presenter.BasePresenter;
import com.zhiyangstudio.commonlib.net.callback.AbsBaseObserver;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by zzg on 2018/5/27.
 */

public class RecommendPresenter extends BasePresenter<RecommendContract.IListView> implements
        RecommendContract.IListPresenter {

    private final RecommendModel mRecommendModel;
    private RecommendContract.IListView mListView;

    @Inject
    public RecommendPresenter() {
        mRecommendModel = new RecommendModel();
    }

    @Override
    public void loadListData() {
        mListView = getView();
        mRecommendModel.loadData2(new AbsBaseObserver<RecommonFinalBean>(this, RecommendModel.class.getName()) {
            @Override
            public void onNext(RecommonFinalBean recommonFinalBean) {
                List<RecommonFinalBean.FianlLayoutData> fianlLayoutDataList = new ArrayList<>();
                if (recommonFinalBean != null) {
                    fianlLayoutDataList = recommonFinalBean.getLayoutData();
                }
                mListView.setData(fianlLayoutDataList);
            }
        });
    }
}
