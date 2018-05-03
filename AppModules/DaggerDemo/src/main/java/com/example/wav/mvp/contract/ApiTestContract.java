package com.example.wav.mvp.contract;

import com.example.wav.bean.AccountCustomerInfo;
import com.example.wav.bean.AccountDeviceInfo;
import com.example.wav.bean.AccountGroupInfo;
import com.example.wav.bean.AccountInfo;
import com.zhiyangstudio.commonlib.mvp.inter.IView;

import java.util.List;

import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;

/**
 * Created by example on 2018/4/27.
 */

public interface ApiTestContract {
    interface IApiTestView extends IView {
    }

    interface IApiTestPresenter {
        void login();

        void deviceReg();

        void getCustomerList();

        void getCustomerGroupList();

        void getDeviceList();

        void login2();

        void getCustomerGroupList2();
    }

    interface IApiTestModel {
        void login(String userName, String pwd, Consumer<ResponseBody> observer);

        void deviceReg(Consumer<ResponseBody> observer);

        void getCustomerList(Consumer<List<AccountCustomerInfo>> observer);

        void getCustomerGroupList(Consumer<ResponseBody> observer, String customerID);

        void getDeviceList(Consumer<AccountDeviceInfo> observer, String customerID, String groupID, int status, int page, int rows);

        void login2(String uName, String pwd, String sourceType, Consumer<AccountInfo> consumer);

        void getCustomerGroupList2(Consumer<List<AccountGroupInfo>> consumer, String custoemrId);
    }
}
