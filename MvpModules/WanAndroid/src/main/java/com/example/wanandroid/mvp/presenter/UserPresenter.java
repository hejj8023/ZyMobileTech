package com.example.wanandroid.mvp.presenter;

import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.mvp.contract.UserContract;
import com.example.wanandroid.mvp.model.UserModel;
import com.zhiyangstudio.commonlib.mvp.presenter.BasePresenter;
import com.zhiyangstudio.commonlib.net.callback.RxObserver;
import com.zhiyangstudio.commonlib.net.callback.RxPageListObserver;

import java.util.List;

/**
 * Created by zzg on 2018/4/21.
 */

public class UserPresenter extends BasePresenter<UserContract.IUserView> implements UserContract.IPresenter {

    private final UserModel userModel;
    private UserContract.IUserView iUserView;

    public UserPresenter() {
        userModel = new UserModel();
    }

    @Override
    public void loadDataList() {
        iUserView = getView();
        userModel.getCollectArticleList(iUserView.getPage(), new RxPageListObserver<ArticleBean>(this, UserModel.class.getName()) {
            @Override
            protected void onSucess(List<ArticleBean> list) {
                iUserView.setData(list);
                if (list.size() == 0) {
                    iUserView.showEmpty();
                } else {
                    iUserView.showContent();
                }
            }

            @Override
            protected void onFailure(int errorCode, String errorMsg) {
                iUserView.showFail(errorMsg);
            }
        });
    }

    @Override
    public void deleteCollectArticle() {
        iUserView = getView();
        userModel.deleteCollectArticle(iUserView.getArticleId(),iUserView.getOriginId(), new RxObserver<String>(this, UserModel.class.getName()) {
            @Override
            protected void onSucess(String data) {
                iUserView.onDeleteCollectAtricleSucess();
            }

            @Override
            protected void onFailure(int errorCode, String errorMsg) {

            }
        });
    }
}
