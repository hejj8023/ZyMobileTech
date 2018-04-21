package com.example.wanandroid.mvp.model.inter;

import com.example.wanandroid.bean.TreeBean;
import com.zhiyangstudio.commonlib.net.callback.RxObserver;

import java.util.List;

/**
 * Created by zhiyang on 2018/4/19.
 */

public interface ITreeModel {
    void getTreeData(RxObserver<List<TreeBean>> rxObserver);
}
