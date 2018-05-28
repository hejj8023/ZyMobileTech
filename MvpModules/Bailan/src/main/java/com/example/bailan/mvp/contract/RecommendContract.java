package com.example.bailan.mvp.contract;

import com.example.bailan.bean.RecommendBean;
import com.zhiyangstudio.commonlib.mvp.inter.ISampleRefreshView;

import io.reactivex.Observer;

/**
 * Created by zzg on 2018/5/27.
 */

public class RecommendContract {
    public interface IListView extends ISampleRefreshView<RecommendBean.LayoutDataBean> {
    }

    public interface IListPresenter {
        void loadListData();
    }

    public interface IListModel {
        void loadData(Observer<RecommendBean> observer);
    }
}
