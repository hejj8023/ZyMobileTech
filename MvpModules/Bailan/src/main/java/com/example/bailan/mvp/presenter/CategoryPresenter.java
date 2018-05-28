package com.example.bailan.mvp.presenter;

import com.example.bailan.mvp.contract.CategoryContract;
import com.zhiyangstudio.commonlib.mvp.presenter.BasePresenter;

import javax.inject.Inject;

/**
 * Created by zzg on 2018/5/27.
 */

public class CategoryPresenter extends BasePresenter<CategoryContract.ICategoryView> implements CategoryContract.ICategoryPresenter {
    @Inject
    public CategoryPresenter() {

    }
}
