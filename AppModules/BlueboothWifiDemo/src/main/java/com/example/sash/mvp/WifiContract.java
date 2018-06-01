package com.example.sash.mvp;

import com.example.sash.bean.WifiInfo;
import com.zhiyangstudio.commonlib.mvp.inter.ISampleRefreshView;

/**
 * Created by example on 2018/5/24.
 */

public interface WifiContract {
    public interface IWifiView extends ISampleRefreshView<WifiInfo> {
    }
}
