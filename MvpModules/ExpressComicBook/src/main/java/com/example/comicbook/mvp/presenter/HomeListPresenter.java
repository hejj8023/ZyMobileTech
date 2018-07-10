package com.example.comicbook.mvp.presenter;

import android.app.Activity;

import com.example.comicbook.bean.Comic;
import com.example.comicbook.mvp.contract.HomeListContract;
import com.example.comicbook.mvp.model.HomeListModel;
import com.zysdk.vulture.clib.mvp.presenter.BasePresenter;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class HomeListPresenter extends BasePresenter<HomeListContract.IHomeView> implements
        HomeListContract.IHomePresenter {


    private final HomeListModel model;

    public HomeListPresenter(Activity activity) {
        model = new HomeListModel(activity);
    }

    @Override
    public void getHomeList() {
        model.getListData(new DisposableObserver<List<Comic>>() {
            @Override
            public void onNext(List<Comic> homeBeans) {
                if (homeBeans != null) {

                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
