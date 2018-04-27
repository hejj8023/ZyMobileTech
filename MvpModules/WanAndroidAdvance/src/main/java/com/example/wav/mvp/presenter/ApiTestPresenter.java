package com.example.wav.mvp.presenter;

import com.example.wav.bean.AccountCustomerInfo;
import com.example.wav.bean.AccountDeviceInfo;
import com.example.wav.bean.AccountGroupInfo;
import com.example.wav.bean.AccountInfo;
import com.example.wav.mvp.contract.ApiTestContract;
import com.example.wav.mvp.model.ApiTestModel;
import com.google.gson.reflect.TypeToken;
import com.zhiyangstudio.commonlib.mvp.presenter.BasePresenter;
import com.zhiyangstudio.commonlib.utils.GsonUtils;
import com.zhiyangstudio.commonlib.utils.LoggerUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;

/**
 * Created by example on 2018/4/27.
 */

public class ApiTestPresenter extends BasePresenter<ApiTestContract
        .IApiTestView> implements ApiTestContract.IApiTestPresenter {

    private final ApiTestModel mModel;

    @Inject
    public ApiTestPresenter() {
        mModel = new ApiTestModel();
    }

    @Override
    public void login() {
        mModel.login("kefu02", "123456", new Consumer<ResponseBody>() {
            @Override
            public void accept(ResponseBody responseBody) throws Exception {
                String result = responseBody.string();
                LoggerUtils.loge("login 请求结果:" + result);
            }
        });

    }

    @Override
    public void deviceReg() {

    }

    @Override
    public void getCustomerList() {
        mModel.getCustomerList(new Consumer<List<AccountCustomerInfo>>() {
            @Override
            public void accept(List<AccountCustomerInfo> accountCustomerInfos) throws Exception {
//                String result = responseBody.string();
                // [{"id":"11723","text":"\u5ba2\u6237\u6d4b\u8bd5"}]
                LoggerUtils.loge("getCustomerList 请求结果:" + accountCustomerInfos);
            }
        });
    }

    @Override
    public void getCustomerGroupList() {
        mModel.getCustomerGroupList(new Consumer<ResponseBody>() {
            @Override
            public void accept(ResponseBody responseBody) throws Exception {
                String result = responseBody.string();
                LoggerUtils.loge("getCustomerGroupList 请求结果:" + result);
                List<AccountGroupInfo> groupInfos = GsonUtils.toObjectList(result, new
                        TypeToken<List<AccountGroupInfo>>() {
                        });
                if (groupInfos != null) {
                    LoggerUtils.loge("getCustomerGroupList 请求结果:" + groupInfos);
                }
            }
        }, "kefu02");
    }

    @Override
    public void getDeviceList() {
        mModel.getDeviceList(new Consumer<AccountDeviceInfo>() {
            @Override
            public void accept(AccountDeviceInfo responseBody) throws Exception {
//                String result = responseBody.string();
                LoggerUtils.loge("getDeviceList 请求结果:" + responseBody.toString());
            }
        }, "11723", "0", 0, 1, 10);
    }

    @Override
    public void login2() {
        mModel.login2("kefu02", "123456", "03", new Consumer<AccountInfo>() {
            @Override
            public void accept(AccountInfo result) throws Exception {
                LoggerUtils.loge("login2 请求结果:" + result.toString());
            }
        });
    }

    @Override
    public void getCustomerGroupList2() {
        mModel.getCustomerGroupList2(new Consumer<List<AccountGroupInfo>>() {
            @Override
            public void accept(List<AccountGroupInfo> accountGroupInfos) throws Exception {
                if (accountGroupInfos != null) {
                    LoggerUtils.loge("getCustomerGroupList2 请求结果:" + accountGroupInfos);
                }
            }
        }, "kefu02");
    }
}
