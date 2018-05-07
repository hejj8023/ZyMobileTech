package com.example.wav.mvp.model;

import com.example.wav.Const;
import com.example.wav.manager.DataManager;
import com.example.wav.bean.AccountCustomerInfo;
import com.example.wav.bean.AccountGroupInfo;
import com.example.wav.bean.AccountInfo;
import com.example.wav.mvp.contract.LoginContract;
import com.example.wav.utils.MD5Utils;
import com.zhiyangstudio.commonlib.utils.LoggerUtils;
import com.zhiyangstudio.commonlib.utils.RxUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by example on 2018/4/28.
 */

public class LoginModel extends BaseAdvModel implements LoginContract.ILoginModel {
    @Override
    public void login(String username, String pwd, String sourceType, Observer<AccountInfo>
            observer) {
        newReq(username, pwd, sourceType, observer);
    }

    private void newReq(String username, String pwd, String sourceType, Observer<AccountInfo> observer) {
        getApi().login2(username, MD5Utils.getMd5(pwd), sourceType)
                .compose(RxUtils.io_main())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<AccountInfo>() {
                    @Override
                    public void accept(AccountInfo accountInfo) throws Exception {
                        if (accountInfo != null) {
                            Const.TMP_DATA.ACCOUNT_INFO = accountInfo;
                        } else {
                            LoggerUtils.loge("登录接口调用出现异常");
                        }
                    }
                })
                .flatMap(new Function<AccountInfo, Observable<List<AccountCustomerInfo>>>() {
                    @Override
                    public Observable<List<AccountCustomerInfo>> apply(AccountInfo accountInfo) throws Exception {
                        if (accountInfo != null && accountInfo.getFlag() == 1) {
                            return getApi().getCustomerList();
                        }
                        return RxUtils.createObservableData(new ArrayList<>());
                    }
                })
                .doOnNext(new Consumer<List<AccountCustomerInfo>>() {
                    @Override
                    public void accept(List<AccountCustomerInfo> accountCustomerInfos) throws Exception {
                        // TODO: 2018/4/28 缓存数据
                        if (accountCustomerInfos != null && accountCustomerInfos.size() > 0) {
                            DataManager.saveDefaultUserId(accountCustomerInfos.get(0).getId() + "");
                        } else {
                            LoggerUtils.loge("用户列表接口调用出现异常");
                        }
                    }
                })
                .flatMap(new Function<List<AccountCustomerInfo>, Observable<List<AccountGroupInfo>>>() {
                    @Override
                    public Observable<List<AccountGroupInfo>> apply(List<AccountCustomerInfo> accountCustomerInfos) throws Exception {
                        if (accountCustomerInfos != null && accountCustomerInfos.size() > 0) {
                            return getApi().getCustomerGroupList2(accountCustomerInfos.get(0)
                                    .getId() + "");
                        }
                        return RxUtils.createObservableData(new ArrayList<>());
                    }
                })
                .doOnNext(new Consumer<List<AccountGroupInfo>>() {
                    @Override
                    public void accept(List<AccountGroupInfo> accountGroupInfos) throws Exception {
                        if (accountGroupInfos != null && accountGroupInfos.size() > 0) {
                            DataManager.saveDefaultGroupId(accountGroupInfos.get(0).getId());
                        } else {
                            LoggerUtils.loge("用户分组接口调用出现异常");
                        }
                    }
                })
                .flatMap(new Function<List<AccountGroupInfo>, Observable<AccountInfo>>() {
                    @Override
                    public Observable<AccountInfo> apply(List<AccountGroupInfo> accountGroupInfos) throws Exception {
                        return RxUtils.createObservableData(Const.TMP_DATA.ACCOUNT_INFO);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
