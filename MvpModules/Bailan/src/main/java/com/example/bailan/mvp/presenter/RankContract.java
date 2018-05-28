package com.example.bailan.mvp.presenter;

import com.example.bailan.bean.RankBean;
import com.zhiyangstudio.commonlib.mvp.inter.ISampleRefreshView;

/**
 * Created by zzg on 2018/5/27.
 */

public interface RankContract {
    public interface IRankView extends ISampleRefreshView<RankBean> {
    }

    public interface IRankPresenter {
    }
}
