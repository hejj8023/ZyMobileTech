package com.example.daw.base;

import com.example.daw.Const;
import com.example.daw.api.ApiServer;
import com.zysdk.vulture.clib.mvp.model.BaseModel;

/**
 * Created by zhiyang on 2018/4/26.
 */

public class BaseDawModel extends BaseModel {
    protected ApiServer mApiServer;

    public BaseDawModel() {
        mApiServer = getApi();
    }

    public ApiServer getApi() {
        return createApiService(Const.API_URL_CONFIG.BASEURL, ApiServer.class);
    }

}
