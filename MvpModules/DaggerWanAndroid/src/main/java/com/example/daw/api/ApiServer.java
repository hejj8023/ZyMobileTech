package com.example.daw.api;


import com.example.daw.Const;
import com.example.daw.bean.UserBean;
import com.zysdk.vulture.clib.bean.BaseBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by zhiyang on 2018/4/26.
 *   根据数据类型来决定要使用哪种类型，如果类型不确定就使用Observable<ResponseBody>
 */

public interface ApiServer {

    /**
     * // {"data":null,"errorCode":-1,"errorMsg":"账号密码不匹配！"}
     * // {"data":{"collectIds":[2835],"email":"","icon":"","id":4642,"password":"12345678",
     * // "type":0,"username":"xfgczzg"},"errorCode":0,"errorMsg":""}
     * @param userName
     * @param pwd
     * @return
     */
    @POST(Const.API_URL_CONFIG.API_LOGIN)
    @FormUrlEncoded
    Observable<BaseBean<UserBean>> login(@Field("username") String userName, @Field("password")
            String
            pwd);
}
