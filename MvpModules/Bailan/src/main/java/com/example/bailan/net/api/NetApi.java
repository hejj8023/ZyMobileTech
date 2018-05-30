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
    Observable<ResponseBody> getRecommendList();

    /**
     * 分类数据
     *
     * @return
     */
    @GET(Const.NET_API_CONFIG.CATEGORY_LIST)
    Observable<ResponseBody> getCategoryList();

    /**
     * 排行数据
     *
     * @return
     */
    @GET(Const.NET_API_CONFIG.Rank_LIST)
    Observable<ResponseBody> getRankList();
}
