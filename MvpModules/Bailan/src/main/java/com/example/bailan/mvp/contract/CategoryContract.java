package com.example.bailan.mvp.contract;

import com.example.bailan.bean.CategoryBean;
import com.zhiyangstudio.commonlib.mvp.inter.ISampleRefreshView;

/**
 * Created by zzg on 2018/5/27.
 */

public class CategoryContract {
    public interface ICategoryView extends ISampleRefreshView<CategoryBean> {
    }

    public interface ICategoryPresenter {
    }
}
