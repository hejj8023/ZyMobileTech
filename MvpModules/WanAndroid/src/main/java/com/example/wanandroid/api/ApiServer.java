package com.example.wanandroid.api;

import com.example.wanandroid.Const;
import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.bean.BannerBean;
import com.example.wanandroid.bean.FriendBean;
import com.example.wanandroid.bean.HotwordBean;
import com.zhiyangstudio.commonlib.bean.BaseBean;
import com.zhiyangstudio.commonlib.bean.PageListDataBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by example on 2018/4/11.
 */

public interface ApiServer {

    /**
     * 广告
     *
     * @return
     */
    @GET(Const.URL_CONFIG.MAIN_BANNER)
    Observable<BaseBean<List<BannerBean>>> getBannerList();

    /**
     * 首页文章列表
     *
     * @param page
     * @return
     */
    @GET(Const.URL_CONFIG.HOME_LIST)
    Observable<BaseBean<PageListDataBean<ArticleBean>>> getArticleList(@Path("page") int page);

    /**
     * 搜索热词
     *
     * @return
     */
    @GET(Const.URL_CONFIG.HOT_KEYWORD)
    Observable<BaseBean<List<HotwordBean>>> getHotKeyword();

    /**
     * 常用网站
     *
     * @return
     */
    @GET(Const.URL_CONFIG.FRIEND)
    Observable<BaseBean<List<FriendBean>>> getFriend();

    /**
     * 搜索文章
     *
     * @param page
     * @param keyword
     * @return
     */
    @FormUrlEncoded
    @POST(Const.URL_CONFIG.SEARCH)
    Observable<BaseBean<PageListDataBean<ArticleBean>>> searchArticle(@Path("page") int page, @Field("k")
            String keyword);
}
