package com.example.wav.mvp.contract;

import com.example.wav.bean.AccountDeviceInfo;
import com.zhiyangstudio.commonlib.mvp.inter.IView;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Created by example on 2018/5/9.
 */

public interface DevListCotract {
    public interface IDevListView extends IView {
        int getStatus();

        int getPage();

        int getPageSize();

        void setData(List<AccountDeviceInfo.DeviceDetailInfo> list);

        void showContent();

        void setDataCount(int total);

        void showNoMoreData();
    }

    public interface IDevListPresenter {
        void loadList();
    }

    public interface IDevListModel {
        void loadDeviceList(String id, String groupId, int status, int page,
                            int pageSize, Consumer<AccountDeviceInfo> consumer);
    }
}
