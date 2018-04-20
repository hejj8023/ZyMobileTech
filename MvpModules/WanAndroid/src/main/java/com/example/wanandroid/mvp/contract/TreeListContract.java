package com.example.wanandroid.mvp.contract;

import com.example.wanandroid.bean.ArticleBean;
import com.zhiyangstudio.commonlib.mvp.inter.IListDataView;

/**
 * Created by zhiyang on 2018/4/19.
 */

public interface TreeListContract {
    interface ITreeListView extends IListDataView<ArticleBean> {
        int getCid();
    }

    public interface ITreeListPresenter {
        void loadTreeList();
    }
}