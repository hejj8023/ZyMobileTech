package com.example.wanandroid.mvp.contract;

import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.bean.BannerBean;
import com.zhiyangstudio.commonlib.mvp.inter.IListDataView;

import java.util.List;

/**
 * Created by zhiyang on 2018/4/10.
 */

public interface HomeListContract {
    public interface IHomeListView extends IListDataView<ArticleBean> {
        void setBannerData(List<BannerBean> list);
    }

    public interface IHomeListPresenter {
        void getHomeList();
    }
}
