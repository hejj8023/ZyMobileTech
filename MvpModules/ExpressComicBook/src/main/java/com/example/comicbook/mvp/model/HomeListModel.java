package com.example.comicbook.mvp.model;

import android.app.Activity;

import com.example.comicbook.Const;
import com.example.comicbook.base.BaseWSModel;
import com.example.comicbook.bean.HomeBean;
import com.example.comicbook.mvp.contract.HomeListContract;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomeListModel extends BaseWSModel implements HomeListContract.IHomeModel {

    private final RxAppCompatActivity rxAppCompatActivity;

    public HomeListModel(Activity context) {
        rxAppCompatActivity = (RxAppCompatActivity) context;
    }

    @Override
    public void getListData(Observer<List<HomeBean>> observer) {
        Observable<List<HomeBean>> listObservable = Observable.create(new ObservableOnSubscribe<List<HomeBean>>() {
            @Override
            public void subscribe(ObservableEmitter<List<HomeBean>> e) throws Exception {
                e.onNext(getRemoteData());
            }
        });
        Observable<List<HomeBean>> bannerObsever = Observable.create(new ObservableOnSubscribe<List<HomeBean>>() {
            @Override
            public void subscribe(ObservableEmitter<List<HomeBean>> e) throws Exception {

            }
        });
        Observable.merge(listObservable, bannerObsever)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(rxAppCompatActivity.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(observer);
    }

    private List<HomeBean> getRemoteData() {
        List<HomeBean> beanList = new ArrayList<>();
        try {
            Document recomment = Jsoup.connect(Const.API_URL_CONFIG.TENCENT_HOME_PAGE).get();
            Document japan = Jsoup.connect(Const.API_URL_CONFIG.TENCENT_JPAN_HOT).get();
            Document doc = Jsoup.connect(Const.API_URL_CONFIG.TENCENT_TOP_URL).get();

            addHomeBean(recomment, beanList, Const.TYPE_HOT_SERIAL);
            addHomeBean(recomment, beanList, Const.TYPE_BOY_RANK);
            addHomeBean(recomment, beanList, Const.TYPE_GIRL_RANK);
            addHomeBean(recomment, beanList, Const.TYPE_HOT_JAPAN);
            addHomeBean(recomment, beanList, Const.TYPE_RANK_LIST);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return beanList;
    }

    private void addHomeBean(Document document, List<HomeBean> list, int type) {
        HomeBean homeBean;
        switch (type) {
            case Const.TYPE_HOT_SERIAL:
                break;
            case Const.TYPE_BOY_RANK:
                homeBean = new HomeBean();
                break;
            case Const.TYPE_GIRL_RANK:
                break;
            case Const.TYPE_HOT_JAPAN:
                break;
            case Const.TYPE_RANK_LIST:
                break;
        }
    }
}
