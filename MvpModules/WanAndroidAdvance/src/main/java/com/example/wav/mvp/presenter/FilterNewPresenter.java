package com.example.wav.mvp.presenter;

import com.example.wav.mvp.contract.FilterNewContract;
import com.example.wav.mvp.model.FilterNewModel;
import com.zhiyangstudio.commonlib.mvp.presenter.BasePresenter;

import javax.inject.Inject;

/**
 * Created by example on 2018/4/28.
 */

public class FilterNewPresenter extends BasePresenter<FilterNewContract.IFilterNewView>
        implements FilterNewContract.IFilterNewPresenter {

    private final FilterNewModel mModel;

    @Inject
    public FilterNewPresenter() {
        mModel = new FilterNewModel();
    }
}
