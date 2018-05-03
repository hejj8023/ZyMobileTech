package com.example.wav.mvp.presenter;

import com.example.wav.Const;
import com.example.wav.DataManager;
import com.example.wav.bean.AccountInfo;
import com.example.wav.mvp.contract.AccountRegContract;
import com.example.wav.mvp.model.AccountRegModel;
import com.example.wav.mvp.model.LoginModel;
import com.zhiyangstudio.commonlib.mvp.presenter.BasePresenter;
import com.zhiyangstudio.commonlib.net.callback.AbsBaseObserver;
import com.zhiyangstudio.commonlib.utils.EmptyUtils;
import com.zhiyangstudio.commonlib.utils.LoggerUtils;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.ResponseBody;

/**
 * Created by example on 2018/5/3.
 */

public class AccountRegPresenter extends BasePresenter<AccountRegContract.IAccountRegView>
        implements AccountRegContract.IAccountRegPresenter {

    private final AccountRegModel mModel;
    private AccountRegContract.IAccountRegView mAccountRegView;
    private String mUserName;
    private String mPassword;

    @Inject
    public AccountRegPresenter() {
        mModel = new AccountRegModel();
    }

    private boolean verifyAccount() {
        mAccountRegView = getView();
        mUserName = mAccountRegView.getUserName();
        mPassword = mAccountRegView.getPwd();
        if (EmptyUtils.isEmpty(mUserName)) {
            mAccountRegView.showFail("用户名不能为空");
            return false;
        }
        if (EmptyUtils.isEmpty(mPassword)) {
            mAccountRegView.showFail("密码不能为空");
            return false;
        }
        return true;
    }

    @Override
    public void login(String userName, String pwd) {
        mModel.login(mAccountRegView.getUserName(), mAccountRegView.getPwd(), "03",
                new AbsBaseObserver<AccountInfo>(this, LoginModel.class.getName()) {
                    @Override
                    public void onNext(AccountInfo accountInfo) {
                        LoggerUtils.loge("accountInfo = " + accountInfo);
                        printExtInfo();
                        mAccountRegView.changeState(accountInfo.getFlag());
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideLoading();
                        // TODO: 2018/5/2 这里说明登录接口请求成功的
                        AccountInfo accountInfo = Const.TMP_DATA.ACCOUNT_INFO;
                        printExtInfo();
                        if (accountInfo != null) {
                            mAccountRegView.changeState(accountInfo.getFlag());
                            return;
                        }
                        // 只单独处理登录
                        mAccountRegView.changeState(0);
                    }
                });
    }

    private void printExtInfo() {
        String defaultUserId = DataManager.getDefaultUserId();
        String defaultGroupId = DataManager.getDefaultGroupId();
        LoggerUtils.loge("defaultUserId = " + defaultUserId + " , defaultGroupId = " +
                defaultGroupId);
    }

    @Override
    public void regAccount() {
        mAccountRegView = getView();
        if (!verifyAccount())
            return;
        mAccountRegView.showLoading("用户注册中,请稍候...");
        mModel.regAccount(mUserName, mPassword,
                new AbsBaseObserver<ResponseBody>(this, AccountRegModel.class.getName()) {
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String resStr = responseBody.string();
                            if (resStr.contains("\n"))
                                resStr = resStr.replace("\n", "");
                            int resCode = Integer.parseInt(resStr);
                            if (resCode == 1) {
                                mAccountRegView.showFail("用户注册成功,准备自动登录");
                                login(mUserName, mPassword);
                            } else {
                                mAccountRegView.showFail("用户注册失败");
                                hideLoading();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });
    }


}
