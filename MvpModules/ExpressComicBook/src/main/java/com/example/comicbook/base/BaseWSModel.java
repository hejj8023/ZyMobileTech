package com.example.comicbook.base;

import com.example.comicbook.Const;
import com.example.comicbook.api.ApiService;
import com.zysdk.vulture.clib.mvp.model.BaseModel;

/**
 * Created by zzg on 2018/4/21.
 */

public class BaseWSModel extends BaseModel {
    public ApiService getApi() {
        return createApiService(Const.API_URL_CONFIG.BASEURL, ApiService.class);
    }
}
