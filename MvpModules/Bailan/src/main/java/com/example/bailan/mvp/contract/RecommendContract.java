package com.example.bailan.mvp.contract;

import com.example.bailan.bean.RecommonFinalBean;
import com.zhiyangstudio.commonlib.mvp.inter.ISampleRefreshView;

import io.reactivex.Observer;

/**
 * Created by zzg on 2018/5/27.
 */

public class RecommendContract {
    public interface IListView extends ISampleRefreshView<RecommonFinalBean.FianlLayoutData> {
    }

    public interface IListPresenter {
        void loadListData();
    }

    public interface IListModel {
        void loadData2(Observer<RecommonFinalBean> observer);
    }
}
