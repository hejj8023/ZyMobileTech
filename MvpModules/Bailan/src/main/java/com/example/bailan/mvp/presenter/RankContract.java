package com.example.bailan.mvp.presenter;

import com.example.bailan.bean.RankBean;
import com.zysdk.vulture.clib.mvp.inter.ISampleRefreshView;

import io.reactivex.Observer;

/**
 * Created by zzg on 2018/5/27.
 */

public interface RankContract {
    public interface IRankView extends ISampleRefreshView<RankBean.RankLayoutData> {
    }

    public interface IRankPresenter {
        void loadData();
    }

    public interface IRankModel {
        void loadData(Observer<RankBean> observer);
    }
}
