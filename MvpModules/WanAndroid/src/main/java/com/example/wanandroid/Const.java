package com.example.wanandroid;

/**
 * Created by example on 2018/4/9.
 */

public class Const {
    public static class USERINFO_KEY {
        // 登录状态
        public static final String IS_LOGIN = "mIsLogin";
    }

    public static class URL_CONFIG {
        public static final String baseUrl = "http://www.wanandroid.com/";
        /**
         * 登录
         */
        public static final String LOGIN = "user/login";

        /**
         * 注册
         */
        public static final String REGISTER = "user/register";

        /**
         * 首页文章列表
         */
        public static final String HOME_LIST = "article/list/{page}/json";

        /**
         * 首页广告
         */
        public static final String MAIN_BANNER = "banner/json";

        /**
         * 收藏文章
         */
        public static final String COLLECT_ARTICLE = "lg/collect/{id}/json";

        /**
         * 收藏站内文章
         */
        public static final String COLLECT_INSIDE_ARTICLE = "lg/collect/{id}/json";

        /**
         * 取消收藏的文章
         */
        public static final String UNCOLLECT_ARTICLE = "lg/uncollect_originId/{id}/json";

        /**
         * 删除收藏的文章
         */
        public static final String DELETE_COLLECT_ARTICLE = "lg/uncollect/{id}/json";


        /**
         * 知识体系
         */
        public static final String TREE = "tree/json";

        /**
         * 知识体系文章列表
         */
        public static final String TREE_LIST = "article/list/{page}/json?";
        /**
         * 收藏的文章列表
         */
        public static final String COLLECT_ARTICLE_LIST = "lg/collect/list/{page}/json";

        /**
         * 搜索
         */
        public static final String SEARCH = "article/query/{page}/json";

        /**
         * 搜索热词
         */
        public static final String HOT_KEYWORD = "/hotkey/json";

        /**
         * 常用网站
         */
        public static final String FRIEND = "friend/json";
    }

    //列表Type
    public static class LIST_TYPE {
        public static final int HOME = 0;
        public static final int TREE = 1;
        public static final int COLLECT = 2;
        public static final int SEARCH = 3;
    }

    //图片加载
    public static class IMAGE_LOADER {
        public static final int HEAD_IMG = 0;
        public static final int NOMAL_IMG = 1;
    }

}
