package com.example.wav.api;

import com.example.wav.Const;
import com.example.wav.bean.AccountCustomerInfo;
import com.example.wav.bean.AccountDeviceInfo;
import com.example.wav.bean.AccountGroupInfo;
import com.example.wav.bean.AccountInfo;
import com.example.wav.bean.DevHistoryBean;

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
    /**
     * app登录
     *
     * @param account
     * @param pwd
     * @param sourceType
     * @return
     */
    @POST(Const.API_URL_CONFIG.API_APP_LOGIN_URL2)
    @FormUrlEncoded
    Observable<AccountInfo> login2(@Field("account") String account, @Field("pwd") String
            pwd, @Field("SourceType") String sourceType);


    /**
     * 用户注册
     * LoginName:登录帐号（6-16位英文字母、数字和下划线，字母开头）
     * Password:客户登录密码,MD5加密
     */
    @POST(Const.API_URL_CONFIG.API_ACCOUNT_REGISTER_URL)
    @FormUrlEncoded
    Observable<ResponseBody> accountReg(@Field("LoginName") String LoginName, @Field("Password")
            String Password);

    /**
     * 设备注册
     * CRCID:设备注册码
     * SimCard:物联网卡号
     * DevName:设备名称
     * GroupName:分组名称
     * CustName:客户名称
     * AppendFlag:0-新建客户；1-已注册客户；
     * LoginName:登录帐号（6-16位英文字母、数字和下划线，字母开头）
     * Password:客户登录密码,MD5加密
     * ParentName:上级客户名称
     */
    @POST(Const.API_URL_CONFIG.API_DEVICE_REGISTER_URL)
    @FormUrlEncoded
    Observable<ResponseBody> deviceReg(@Field("CRCID") String cRCID,
                                       @Field("SimCard") String simCard,
                                       @Field("DevName") String devName,
                                       @Field("GroupName") String groupName,
                                       @Field("CustName") String custName,
                                       @Field("AppendFlag") String appendFlag,
                                       @Field("LoginName") String loginName,
                                       @Field("Password") String password,
                                       @Field("ParentName") String parentName);

    /**
     * 获取客户列表
     *
     * @return
     */
    @GET(Const.API_URL_CONFIG.API_CUSTOMER_LIST_URL)
    Observable<List<AccountCustomerInfo>> getCustomerList();

    @GET(Const.API_URL_CONFIG.API_CUSTOMER_GROUP_LIST_URL)
    Observable<ResponseBody> getCustomerGroupList(@Query("CustomerID") String customerID);

    /**
     * 获取客户分组列表
     *
     * @param customerID
     * @return
     */
    @GET(Const.API_URL_CONFIG.API_CUSTOMER_GROUP_LIST_URL)
    Observable<List<AccountGroupInfo>> getCustomerGroupList2(@Query("CustomerID") String
                                                                     customerID);

    /**
     * 获取设备列表
     *
     * @param customerID
     * @param GroupID
     * @param status
     * @param page
     * @param rows
     * @return
     */
    @GET(Const.API_URL_CONFIG.API_DEVICE_LIST_URL)
    Observable<AccountDeviceInfo> getDeviceList(@Query("CustomerID") String customerID,
                                                @Query("GroupID") String GroupID,
                                                @Query("Status") int status,
                                                @Query("page") int page,
                                                @Query("rows") int rows
    );

    @GET(Const.API_URL_CONFIG.API_DEVICE_HISTORY)
    Observable<DevHistoryBean> getDevHistory(@Query("ID") String devId);
}
