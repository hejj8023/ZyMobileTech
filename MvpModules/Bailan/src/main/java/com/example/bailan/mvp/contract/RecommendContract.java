package com.example.bailan.mvp.contract;

import com.example.bailan.bean.RecommonBean;
import com.zysdk.vulture.clib.mvp.inter.ISampleRefreshView;

import io.reactivex.Observer;

/**
 * Created by zzg on 2018/5/27.
 */

public class RecommendContract {
    public interface IListView extends ISampleRefreshView<RecommonBean.RecommendLayoutData> {
    }

    public interface IListPresenter {
        void loadListData();
    }

    public interface IListModel {
        void loadData2(Observer<RecommonBean> observer);
    }
}
