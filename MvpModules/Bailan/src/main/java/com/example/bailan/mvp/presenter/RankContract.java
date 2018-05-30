package com.example.bailan.mvp.presenter;

import com.example.bailan.bean.RankBean;
import com.zhiyangstudio.commonlib.mvp.inter.ISampleRefreshView;

import io.reactivex.Observer;
import okhttp3.ResponseBody;

/**
 * Created by zzg on 2018/5/27.
 */

public interface RankContract {
    public interface IRankView extends ISampleRefreshView<RankBean> {
    }

    public interface IRankPresenter {
        void loadData();
    }

    public interface IRankModel {
        void loadData(Observer<ResponseBody> observer);
    }
}
