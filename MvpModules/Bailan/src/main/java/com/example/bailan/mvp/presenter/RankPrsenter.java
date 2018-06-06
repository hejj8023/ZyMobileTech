package com.example.bailan.mvp.presenter;

import com.example.bailan.bean.RankBean;
import com.example.bailan.mvp.model.RankModel;
import com.zysdk.vulture.clib.mvp.presenter.BasePresenter;
import com.zysdk.vulture.clib.net.callback.AbsBaseObserver;

import javax.inject.Inject;

/**
 * Created by zzg on 2018/5/27.
 */

public class RankPrsenter extends BasePresenter<RankContract.IRankView> implements RankContract.IRankPresenter {

    private final RankModel mRankModel;
    private RankContract.IRankView mRankView;

    @Inject
    public RankPrsenter() {
        mRankModel = new RankModel();
    }

    @Override
    public void loadData() {
        mRankView = getView();
        mRankModel.loadData(new AbsBaseObserver<RankBean>(this, RankModel.class.getName()) {
            @Override
            public void onNext(RankBean rankBean) {
                if (rankBean != null) {
                        mRankView.setData(rankBean.getLayoutData());
                }
            }
        });
    }
}
