package com.example.bailan.mvp.presenter;

import com.example.bailan.mvp.model.RankModel;
import com.zhiyangstudio.commonlib.mvp.presenter.BasePresenter;
import com.zhiyangstudio.commonlib.net.callback.AbsBaseObserver;
import com.zhiyangstudio.commonlib.utils.EmptyUtils;
import com.zhiyangstudio.commonlib.utils.LoggerUtils;

import javax.inject.Inject;

import okhttp3.ResponseBody;

/**
 * Created by zzg on 2018/5/27.
 */

public class RankPrsenter extends BasePresenter<RankContract.IRankView> implements RankContract.IRankPresenter {

    private final RankModel mRankModel;

    @Inject
    public RankPrsenter() {
        mRankModel = new RankModel();
    }

    @Override
    public void loadData() {
        mRankModel.loadData(new AbsBaseObserver<ResponseBody>(this, RankModel.class.getName()) {
            @Override
            public void onNext(ResponseBody responseBody) {
                if (responseBody != null) {
                    String responseStr = responseBody.toString();
                    if (EmptyUtils.isNotEmpty(responseStr)) {
                        LoggerUtils.loge("responseStr = \n" + responseStr);
                    }
                }
            }
        });
    }
}
