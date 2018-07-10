package com.example.comicbook;

public class Const {
    /**
     * 表示主页标题的种类
     * 0  热门推荐 1 排行榜
     */
    public static final int TYPE_RECOMMEND = 0;
    public static final int TYPE_RANK_LIST = 1;
    public static final int TYPE_HOT_SERIAL = 2;
    public static final int TYPE_HOT_JAPAN = 3;
    public static final int TYPE_BOY_RANK = 4;
    public static final int TYPE_GIRL_RANK = 5;

    public class API_URL_CONFIG {
        public static final String BASEURL = "";

        /* --------------   jsoup 爬虫的数据   -------------- */
        public static final String TENCENT_HOME_PAGE = "http://ac.qq.com/";

        public static final String TENCENT_JPAN_HOT = "http://ac.qq" +
                ".com/Comic/all/state/pink/nation/4/search/hot/page/1";

        public static final String TENCENT_TOP_URL = "http://ac.qq" +
                ".com/Comic/all/state/pink/search/hot/page/";


        /* --------------   jsoup 爬虫的数据   -------------- */
    }
}
