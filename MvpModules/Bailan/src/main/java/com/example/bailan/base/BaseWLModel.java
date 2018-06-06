package com.example.bailan.base;

import com.example.bailan.Const;
import com.example.bailan.net.api.NetApi;
import com.zysdk.vulture.clib.mvp.model.BaseModel;

/**
 * Created by zzg on 2018/5/27.
 */

public class BaseWLModel extends BaseModel {

    protected NetApi mNetApi;

    public BaseWLModel() {
        mNetApi = createApiService(Const.NET_API_CONFIG.BASE_URL, NetApi.class);
    }
}
