package com.example.wanandroid.mvp.contract;

import com.example.wanandroid.bean.ArticleBean;
import com.zhiyangstudio.commonlib.mvp.inter.IListDataView;

/**
 * Created by zhiyang on 2018/4/10.
 */

public interface HomeListContract {
    public interface IHomeListView extends IListDataView<ArticleBean> {
    }

    public interface IHomeListPresenter {
        void getHomeList();
    }
}
