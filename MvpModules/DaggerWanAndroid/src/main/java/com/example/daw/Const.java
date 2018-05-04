package com.example.daw;

/**
 * Created by example on 2018/5/4.
 */

public class Const {
    /**
     * 是否允许自动登录
     */
    public static boolean ISENABLE_AUTO_LOGIN = false;

    public class API_URL_CONFIG {
        public static final String BASEURL = "http://www.wanandroid.com/";

        /*登录*/
        public static final String API_LOGIN = "user/login";
    }

    public class KEY_CONFIG {
        public static final String KEY_IS_LOGIN = "key_is_login";
        public static final String KEY_USER_DATA = "key_user_data";
        public static final String KEY_AES_KEY = "key_aes";
    }
}
