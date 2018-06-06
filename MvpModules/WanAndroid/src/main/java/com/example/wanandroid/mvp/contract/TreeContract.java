package com.example.wanandroid.mvp.contract;

import com.example.wanandroid.bean.TreeBean;
import com.zysdk.vulture.clib.mvp.inter.IListDataView;

/**
 * Created by zhiyang on 2018/4/19.
 */

public interface TreeContract {
    interface ITreeView extends IListDataView<TreeBean> {
    }

    interface ITreePresenter {
        void loadTreeList();
    }
}
