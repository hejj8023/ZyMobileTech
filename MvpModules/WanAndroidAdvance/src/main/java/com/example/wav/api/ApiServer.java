package com.example.wav.api;

import com.example.wav.Const;
import com.example.wav.bean.AccountCustomerInfo;
import com.example.wav.bean.AccountDeviceInfo;
import com.example.wav.bean.AccountGroupInfo;
import com.example.wav.bean.AccountInfo;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by zhiyang on 2018/4/26.
 */

public interface ApiServer {
    @POST(Const.API_URL_CONFIG.API_APP_LOGIN_URL)
    @FormUrlEncoded
    Observable<ResponseBody> login(@Field("username") String userName, @Field("pwd") String pwd);

    @POST(Const.API_URL_CONFIG.API_APP_LOGIN_URL2)
    @FormUrlEncoded
    Observable<AccountInfo> login2(@Field("account") String account, @Field("pwd") String
            pwd, @Field("SourceType") String sourceType);

    @POST(Const.API_URL_CONFIG.API_DEVICE_REGISTER_URL)
    @FormUrlEncoded
    Observable<ResponseBody> getDeviceReg();

    /*需要登录*/
    @GET(Const.API_URL_CONFIG.API_CUSTOMER_LIST_URL)
    Observable<List<AccountCustomerInfo>> getCustomerList();

    @GET(Const.API_URL_CONFIG.API_CUSTOMER_GROUP_LIST_URL)
    Observable<ResponseBody> getCustomerGroupList(@Query("CustomerID") String customerID);

    @GET(Const.API_URL_CONFIG.API_CUSTOMER_GROUP_LIST_URL)
    Observable<List<AccountGroupInfo>> getCustomerGroupList2(@Query("CustomerID") String customerID);

    /*需要登录*/
    @GET(Const.API_URL_CONFIG.API_DEVICE_LIST_URL)
    Observable<AccountDeviceInfo> getDeviceList(
            @Query("CustomerID") String customerID,
            @Query("GroupID") String GroupID,
            @Query("Status") int status,
            @Query("page") int page,
            @Query("rows") int rows
    );

}
