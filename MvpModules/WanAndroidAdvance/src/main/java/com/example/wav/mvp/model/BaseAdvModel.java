package com.example.wav.mvp.model;

import com.example.wav.Const;
import com.example.wav.api.ApiServer;
import com.zhiyangstudio.commonlib.mvp.model.BaseModel;

/**
 * Created by zhiyang on 2018/4/26.
 */

public class BaseAdvModel extends BaseModel {
    protected ApiServer mApiServer;

    public BaseAdvModel() {
        mApiServer = getApi();
    }

    public ApiServer getApi() {
        return createApiService(Const.API_URL_CONFIG.BASEURL, ApiServer.class);
    }
}
