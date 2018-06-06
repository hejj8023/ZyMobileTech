package com.example.wanandroid.manager;

import android.util.Base64;

import com.example.wanandroid.Const;
import com.example.wanandroid.bean.UserBean;
import com.example.wanandroid.utils.AesEncryptionUtils;
import com.zysdk.vulture.clib.utils.EmptyUtils;
import com.zysdk.vulture.clib.utils.GsonUtils;
import com.zysdk.vulture.clib.utils.LoggerUtils;
import com.zysdk.vulture.clib.utils.PreUtils;

import javax.crypto.spec.SecretKeySpec;

/**
 * Created by example on 2018/4/9.
 */

public class UserInfoManager {
    public static boolean isLogin() {
        Object o = PreUtils.get(Const.USERINFO_KEY.IS_LOGIN, false);
        return (o != null && o instanceof Boolean) ? ((Boolean) o) : false;
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public static UserBean getUserInfo() {
        UserBean userBean = null;
        SecretKeySpec keySpec = getAesKey();
        LoggerUtils.loge(null, "keySpec = " + keySpec);
        String userInfo = AesEncryptionUtils.decrypt(keySpec, (String) PreUtils.get(Const.USERINFO_KEY.USER_INFO, ""));
        LoggerUtils.loge(null, "userInfo = " + userInfo);
        if (EmptyUtils.isEmpty(userInfo))
            return null;
        userBean = GsonUtils.toObject(userInfo, UserBean.class);
        return userBean;
    }

    private static SecretKeySpec getAesKey() {
        String keyStr = (String) PreUtils.get(Const.USERINFO_KEY.AES, "");
        return AesEncryptionUtils.getSecretKey(Base64.decode(keyStr, Base64.CRLF));
    }

    public static void saveUserInfo(UserBean data) {
        String userInfoStr = GsonUtils.toJsonStr(data);
        LoggerUtils.loge(null, "userInfoStr = " + userInfoStr);
        SecretKeySpec key = AesEncryptionUtils.createKey();
        String aesContent = AesEncryptionUtils.encrypt(key, userInfoStr);
        LoggerUtils.loge(null, "aesContent = " + aesContent);
        // 保存用户信息
        PreUtils.put(Const.USERINFO_KEY.USER_INFO, aesContent);
        // 保存密钥
        saveAesKey(key);
    }

    private static void saveAesKey(SecretKeySpec key) {
        PreUtils.put(Const.USERINFO_KEY.AES, Base64.encodeToString(key.getEncoded(), Base64.DEFAULT));
    }


    public static void saveIsLogin(boolean isLogin) {
        PreUtils.put(Const.USERINFO_KEY.IS_LOGIN, isLogin);
    }
}
