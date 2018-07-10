package com.example.comicbook.mvp.contract;

import com.example.comicbook.bean.Comic;
import com.zysdk.vulture.clib.mvp.inter.ISampleRefreshView;

import java.util.List;

import io.reactivex.Observer;

public interface HomeListContract {
    public interface IHomeView extends ISampleRefreshView<Comic> {
    }

    public interface IHomePresenter {
        void getHomeList();
    }

    public interface IHomeModel {
        void getListData(Observer<List<Comic>> observer);
    }
}
