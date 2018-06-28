package com.example.comicbook.mvp.contract;

import com.example.comicbook.bean.HomeBean;
import com.zysdk.vulture.clib.mvp.inter.ISampleRefreshView;

import java.util.List;

import io.reactivex.Observer;

public interface HomeListContract {
    public interface IHomeView extends ISampleRefreshView<HomeBean> {
    }

    public interface IHomePresenter {
        void getHomeList();
    }

    public interface IHomeModel {
        void getListData(Observer<List<HomeBean>> observer);
    }
}
