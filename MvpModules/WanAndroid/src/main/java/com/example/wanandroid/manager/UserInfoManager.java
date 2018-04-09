package com.example.wanandroid.manager;

import com.example.wanandroid.Const;
import com.zhiyangstudio.commonlib.utils.PreUtils;

/**
 * Created by example on 2018/4/9.
 */

public class UserInfoManager {
    public static boolean isLogin() {
        return (boolean) PreUtils.get(Const.USERINFO_KEY.IS_LOGIN,false);
    }
}
