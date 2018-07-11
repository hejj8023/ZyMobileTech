package com.example.comicbook.mvp.model;

import android.app.Activity;

import com.example.comicbook.Const;
import com.example.comicbook.R;
import com.example.comicbook.base.BaseWSModel;
import com.example.comicbook.bean.Comic;
import com.example.comicbook.bean.HomeTitle;
import com.example.comicbook.mvp.contract.HomeListContract;
import com.example.comicbook.util.DataConvertEngine;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.zysdk.vulture.clib.utils.ResourceUtils;

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
    public void getListData(Observer<List<Comic>> observer) {
        Observable<List<Comic>> listObservable = Observable.create(new ObservableOnSubscribe<List<Comic>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Comic>> e) throws Exception {
                e.onNext(getRemoteData());
            }
        });
        Observable<List<Comic>> bannerObsever = Observable.create(new ObservableOnSubscribe<List<Comic>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Comic>> e) throws Exception {

            }
        });
        Observable.merge(listObservable, bannerObsever)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(rxAppCompatActivity.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(observer);
    }

    private List<Comic> getRemoteData() {
        List<Comic> beanList = new ArrayList<>();
        try {
            Document recomment = Jsoup.connect(Const.API_URL_CONFIG.TENCENT_HOME_PAGE).get();
            Document japan = Jsoup.connect(Const.API_URL_CONFIG.TENCENT_JPAN_HOT).get();
            Document doc = Jsoup.connect(Const.API_URL_CONFIG.TENCENT_TOP_URL).get();

            addComic(recomment, beanList, Const.TYPE_HOT_SERIAL);
            addComic(recomment, beanList, Const.TYPE_BOY_RANK);
            addComic(recomment, beanList, Const.TYPE_GIRL_RANK);
            addComic(recomment, beanList, Const.TYPE_HOT_JAPAN);
            addComic(recomment, beanList, Const.TYPE_RANK_LIST);

            HomeTitle homeTitle = new HomeTitle();
            homeTitle.setItemTitle("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return beanList;
    }

    private void addComic(Document document, List<Comic> list, int type) {
        HomeTitle homeTitle = null;
        switch (type) {
            case Const.TYPE_RECOMMEND:
                homeTitle = new HomeTitle();
                homeTitle.setItemTitle(ResourceUtils.getStr(R.string.hc));
                homeTitle.setTitleType(Const.TYPE_RECOMMEND);
                list.add(homeTitle);
                list.addAll(DataConvertEngine.convertRecommendData(document));
                break;
            case Const.TYPE_HOT_SERIAL:
                homeTitle = new HomeTitle();
                homeTitle.setItemTitle("热门连载");
                homeTitle.setTitleType(Const.TYPE_HOT_SERIAL);
                list.add(homeTitle);
                list.addAll(DataConvertEngine.convertSerialData(document));
                break;
            case Const.TYPE_BOY_RANK:
                homeTitle = new HomeTitle();
                homeTitle.setItemTitle("少年漫画");
                homeTitle.setTitleType(Const.TYPE_BOY_RANK);
                list.add(homeTitle);
                list.addAll(DataConvertEngine.convertBRankData(document));
                break;
            case Const.TYPE_GIRL_RANK:
                homeTitle = new HomeTitle();
                homeTitle.setItemTitle("少女漫画");
                homeTitle.setTitleType(Const.TYPE_GIRL_RANK);
                list.add(homeTitle);
                list.addAll(DataConvertEngine.convertGRankData(document));
                break;
            case Const.TYPE_HOT_JAPAN:
                homeTitle = new HomeTitle();
                homeTitle.setItemTitle("日漫馆");
                homeTitle.setTitleType(Const.TYPE_HOT_JAPAN);
                list.add(homeTitle);
                list.addAll(DataConvertEngine.convertJapanData(document));
                break;
            case Const.TYPE_RANK_LIST:
                homeTitle = new HomeTitle();
                homeTitle.setItemTitle("排行榜");
                homeTitle.setTitleType(Const.TYPE_RANK_LIST);
                list.add(homeTitle);
                list.addAll(DataConvertEngine.convertRankListData(document));
                break;
        }
    }
}
