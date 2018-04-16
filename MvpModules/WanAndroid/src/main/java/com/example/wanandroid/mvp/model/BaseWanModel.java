package com.example.wanandroid.mvp.model;

import com.example.wanandroid.Const;
import com.example.wanandroid.api.ApiServer;
import com.zhiyangstudio.commonlib.mvp.model.BaseModel;

/**
 * Created by example on 2018/4/11.
 */

public class BaseWanModel extends BaseModel {
    public ApiServer getApi() {
        return createApiService(Const.URL_CONFIG.baseUrl, ApiServer.class);
    }


}
