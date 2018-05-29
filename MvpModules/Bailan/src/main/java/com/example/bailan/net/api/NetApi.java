package com.example.bailan.net.api;

import com.example.bailan.Const;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

/**
 * Created by zzg on 2018/5/27.
 */

public interface NetApi {
    /**
     * 推荐数据
     *
     * @return
     */
    @GET(Const.NET_API_CONFIG.RECOMMEND_LIST)
    Observable<ResponseBody> getRecommendList2();
}
