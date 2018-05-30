package com.example.bailan.mvp.presenter;

import com.example.bailan.bean.CategoryBean;
import com.example.bailan.mvp.contract.CategoryContract;
import com.example.bailan.mvp.model.CategoryModel;
import com.zhiyangstudio.commonlib.mvp.presenter.BasePresenter;
import com.zhiyangstudio.commonlib.net.callback.AbsBaseObserver;

import javax.inject.Inject;

/**
 * Created by zzg on 2018/5/27.
 */

public class CategoryPresenter extends BasePresenter<CategoryContract.ICategoryView> implements CategoryContract
        .ICategoryPresenter {


    private final CategoryModel mCategoryModel;
    private CategoryContract.ICategoryView mCategoryView;

    @Inject
    public CategoryPresenter() {
        mCategoryModel = new CategoryModel();
    }

    @Override
    public void getData() {
        mCategoryView = getView();
        mCategoryModel.getData(new AbsBaseObserver<CategoryBean>(this, CategoryModel.class.getName()) {
            @Override
            public void onNext(CategoryBean categoryBean) {
                if (categoryBean != null) {
                    mCategoryView.setData(categoryBean.getLayoutData());
                }
            }
        });
    }
}
