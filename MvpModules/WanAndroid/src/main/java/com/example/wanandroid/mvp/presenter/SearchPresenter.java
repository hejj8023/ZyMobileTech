package com.example.wanandroid.mvp.presenter;

import com.example.wanandroid.WanApp;
import com.example.wanandroid.bean.SearchBean;
import com.example.wanandroid.mvp.contract.SearchContract;
import com.zhiyangstudio.commonlib.mvp.presenter.BasePresenter;

import java.util.ArrayList;

/**
 * Created by example on 2018/4/13.
 */

public class SearchPresenter extends BasePresenter<SearchContract.ISearchView> implements SearchContract.ISearchPresenter {
    public void loadList() {
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ArrayList<SearchBean> objects = new ArrayList<>();
                SearchBean searchBean = null;
                for (int i = 0; i < 20; i++) {
                    searchBean = new SearchBean();
                    searchBean.setName("index " + (i + 1));
                    objects.add(searchBean);
                }
                WanApp.getAppHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        getView().setData(objects);
                        getView().showContent();
                    }
                });
            }
        }.start();
    }
}
