package com.example.bailan;

import com.zhiyangstudio.commonlib.utils.PreUtils;

/**
 * Created by example on 2018/5/25.
 * 用户信息管理
 */

public class UserInfoManager {
    public static boolean isFirstLogin() {
        Object o = PreUtils.get(Const.SP_KEY_CONFIG.KEY_LOGIN_STATE, true);
        if (o != null && o instanceof Boolean) {
            return (boolean) o;
        }
        return false;
    }

    public static void saveIsFirstLogin(boolean loginState) {
        PreUtils.put(Const.SP_KEY_CONFIG.KEY_LOGIN_STATE, loginState);
    }
}
