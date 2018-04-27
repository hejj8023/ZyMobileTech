package com.example.wanandroid.api;

import com.example.wanandroid.Const;
import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.bean.BannerBean;
import com.example.wanandroid.bean.FriendBean;
import com.example.wanandroid.bean.HotwordBean;
import com.example.wanandroid.bean.TreeBean;
import com.example.wanandroid.bean.UserBean;
import com.zhiyangstudio.commonlib.bean.BaseBean;
import com.zhiyangstudio.commonlib.bean.PageListDataBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

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

    /**
     * 知识体系分类
     *
     * @return
     */
    @GET(Const.URL_CONFIG.TREE)
    Observable<BaseBean<List<TreeBean>>> getTree();

    /**
     * 知识体系列表
     *
     * @param page
     * @param cid
     * @return
     */
    @GET(Const.URL_CONFIG.TREE_LIST)
    Observable<BaseBean<PageListDataBean<ArticleBean>>> getTreeList(@Path("page") int page,
                                                                    @Query("cid") int cid);

    /**
     * 登录
     *
     * @param userName
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST(Const.URL_CONFIG.LOGIN)
    Observable<BaseBean<UserBean>> login(@Field("username") String userName, @Field("password")
            String password);

    /**
     * 注册
     *
     * @param userName
     * @param password   密码
     * @param repassword 重复密码
     * @return
     */
    @FormUrlEncoded
    @POST(Const.URL_CONFIG.REGISTER)
    Observable<BaseBean<String>> register(@Field("username") String userName, @Field("password")
            String password, @Field("repassword") String repassword);

    /**
     * 收藏文章
     *
     * @param articleId
     * @return
     */
    @POST(Const.URL_CONFIG.COLLECT_ARTICLE)
    Observable<BaseBean<String>> collect(@Path("id") int articleId);

    /**
     * 取消收藏
     *
     * @param articleId
     * @return
     */
    @POST(Const.URL_CONFIG.UNCOLLECT_ARTICLE)
    Observable<BaseBean<String>> unCollect(@Path("id") int articleId);

    /**
     * 收藏的文章列表
     *
     * @param page
     * @return
     */
    @GET(Const.URL_CONFIG.COLLECT_ARTICLE_LIST)
    Observable<BaseBean<PageListDataBean<ArticleBean>>> collectArticleList(@Path("page") int page);

    /**
     * 删除收藏的文章
     *
     * @param articleId
     * @param originId
     * @return
     */
    @FormUrlEncoded
    @POST(Const.URL_CONFIG.DELETE_COLLECT_ARTICLE)
    Observable<BaseBean<String>> deleteCollectArticle(@Path("id") int articleId, @Field("originId") int originId);
}
