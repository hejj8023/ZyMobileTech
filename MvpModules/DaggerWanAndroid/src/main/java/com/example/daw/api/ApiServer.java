package com.example.daw.api;


import com.example.daw.Const;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by zhiyang on 2018/4/26.
 */

public interface ApiServer {

    @POST(Const.API_URL_CONFIG.API_LOGIN)
    @FormUrlEncoded
    Observable<ResponseBody> login(@Field("username") String userName, @Field("password") String
            pwd);
}
