package com.example.daw.manager;

import android.util.Base64;

import com.example.daw.Const;
import com.example.daw.bean.UserBean;
import com.zhiyangstudio.commonlib.utils.AesEncryptionUtils;
import com.zhiyangstudio.commonlib.utils.EmptyUtils;
import com.zhiyangstudio.commonlib.utils.GsonUtils;
import com.zhiyangstudio.commonlib.utils.LoggerUtils;
import com.zhiyangstudio.commonlib.utils.PreUtils;

import javax.crypto.spec.SecretKeySpec;

/**
 * Created by example on 2018/5/4.
 */

public class DataManager {
    /**
     * 是否登录成功过
     *
     * @return
     */
    public static boolean isLogin() {
        Object o = PreUtils.get(Const.KEY_CONFIG.KEY_IS_LOGIN, false);
        return o != null && o instanceof Boolean ? (boolean) o : false;
    }

    /**
     * 保存登录状态
     *
     * @param isLogin
     */
    public static void saveLogin(boolean isLogin) {
        PreUtils.put(Const.KEY_CONFIG.KEY_IS_LOGIN, isLogin);
    }

    /**
     * 保存用户信息
     *
     * @param userBean
     */
    public static void saveUserBean(UserBean userBean) {
        // TODO: 2018/5/4 对象转换成字符串
        String jsonStr = GsonUtils.toJsonStr(userBean);
        LoggerUtils.loge("saveUserBean jsonStr = " + jsonStr);
        // TODO: 2018/5/4 创建加密用的keyspec
        SecretKeySpec keySpec = AesEncryptionUtils.createKey();
        // TODO: 2018/5/4 加密数据
        String encryptStr = AesEncryptionUtils.encrypt(keySpec, jsonStr);
        LoggerUtils.loge("saveUserBean encryptStr = " + encryptStr);
        // TODO: 2018/5/4 保存用户信息
        PreUtils.put(Const.KEY_CONFIG.KEY_USER_DATA, encryptStr);
        // TODO: 2018/5/4 保存密钥
        saveAesKey(keySpec);
    }

    /**
     * 保存aes key
     *
     * @param spec
     */
    public static void saveAesKey(SecretKeySpec spec) {
        String encodeStr = Base64.encodeToString(spec
                .getEncoded(), Base64.DEFAULT);
        LoggerUtils.loge("saveAesKey jsonStr = " + encodeStr);
        PreUtils.put(Const.KEY_CONFIG.KEY_AES_KEY, encodeStr);
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public static UserBean getUserBean() {
        // TODO: 2018/5/4 获取aes key
        SecretKeySpec keySpec = getAesKey();
        if (keySpec == null)
            return null;

        Object o = PreUtils.get(Const.KEY_CONFIG.KEY_USER_DATA, "");
        if (o != null && o instanceof String) {
            // TODO: 2018/5/4 加密的数据
            String dataStr = (String) o;
            LoggerUtils.loge("getUserBean dataStr = " + dataStr);
            // TODO: 2018/5/4 解密之后的数据
            String fStr = AesEncryptionUtils.decrypt(keySpec, dataStr);
            LoggerUtils.loge("getUserBean fStr = " + fStr);
            // TODO: 2018/5/4 创建对象返回
            UserBean userBean = GsonUtils.toObject(fStr, UserBean.class);
            LoggerUtils.loge("getUserBean userBean = " + userBean);
            return userBean;
        }
        return null;
    }

    /**
     * 获取aes key
     *
     * @return
     */
    public static SecretKeySpec getAesKey() {
        Object o = PreUtils.get(Const.KEY_CONFIG.KEY_AES_KEY, "");
        if (o != null && o instanceof String) {
            String keyStr = (String) o;
            if (EmptyUtils.isEmpty(keyStr))
                return null;
            byte[] decode = Base64.decode(keyStr, Base64.DEFAULT);
            return AesEncryptionUtils.getSecretKey(decode);
        }
        return null;
    }
}
