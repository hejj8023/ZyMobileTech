package com.example.wanandroid.mvp.contract;

import com.example.wanandroid.bean.SearchBean;
import com.zhiyangstudio.commonlib.mvp.inter.IListDataView;

/**
 * Created by example on 2018/4/13.
 */

public interface SearchContract {
    public interface ISearchPresenter {
    }

    public interface ISearchView extends IListDataView<SearchBean> {
    }
}
