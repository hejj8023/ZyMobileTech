package com.example.wanandroid.api;

import com.example.wanandroid.Const;
import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.bean.BannerBean;
import com.zhiyangstudio.commonlib.bean.BaseBean;
import com.zhiyangstudio.commonlib.bean.PageListDataBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by ubt on 2018/4/11.
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
}
