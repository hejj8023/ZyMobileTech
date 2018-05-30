package com.example.bailan;

/**
 * Created by example on 2018/5/25.
 */

public class Const {
    /**
     * sp配置使用字段
     */
    public class SP_KEY_CONFIG {
        public static final String KEY_LOGIN_STATE = "key_login_state";
    }

    public class NET_API_CONFIG {
        public static final String BASE_URL = "http://112.124.22.238:8081/";

        /**
         * 推荐数据
         */
        public static final String RECOMMEND_LIST = "appstore/recommend";

        /**
         * 分类数据
         */
        public static final String CATEGORY_LIST = "appstore/category";

        /**
         * 排行数据
         */
        public static final String Rank_LIST = "appstore/top";
    }

    public class LIST_ITEM_COLLECTS {
        public class ITEM_CATEGORY_LIST {
            public static final int ITEM_CATEGORY_TITLE_DIVIDER = 1;
            public static final int ITEM_CATEGORY_MENU = 2;
            public static final int ITEM_CATEGORY_LIST = 3;
        }

        public class ITEM_RECOMMON_LIST {
            // 轮播图
            public static final int ITEM_TYPE_BANNER = 1;
            // 两个图片的广告
            public static final int ITEM_TYPE_AD = 2;
            // 正常条目
            public static final int ITEM_TYPE_COMMON = 3;
        }
    }


}
