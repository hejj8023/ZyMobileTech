package com.example.bailan.mvp.model;

import com.example.bailan.base.BaseWLModel;
import com.example.bailan.bean.RankBean;
import com.example.bailan.mvp.presenter.RankContract;
import com.zhiyangstudio.commonlib.utils.EmptyUtils;
import com.zhiyangstudio.commonlib.utils.JsonUtil;
import com.zhiyangstudio.commonlib.utils.LoggerUtils;
import com.zhiyangstudio.commonlib.utils.RxUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;

/**
 * Created by example on 2018/5/30.
 */

public class RankModel extends BaseWLModel implements RankContract.IRankModel {

    @SuppressWarnings("unchecked")
    @Override
    public void loadData(Observer<RankBean> observer) {
        mNetApi.getRankList()
                .flatMap(new Function<ResponseBody, ObservableSource<RankBean>>() {
                    @Override
                    public ObservableSource<RankBean> apply(ResponseBody responseBody) throws Exception {
                        RankBean rankBean = new RankBean();
                        if (responseBody != null) {

                            JSONObject jsonObject = null;
                            JSONArray ldJarr = null;
                            List<RankBean.RankLayoutData> layoutDataList = null;
                            RankBean.RankLayoutData layoutData = null;
                            JSONObject ldItemJsonObj = null;
                            List<RankBean.RankLayoutDataItem> dataItemList = null;
                            RankBean.RankLayoutDataItem dataItem = null;
                            JSONObject itemJsonObj = null;

                            String responseStr = responseBody.string();
                            if (EmptyUtils.isNotEmpty(responseStr)) {
                                LoggerUtils.loge("responseStr = \n" + responseStr);
                                try {
                                    jsonObject = new JSONObject(responseStr);
                                    rankBean.setContentType(JsonUtil.optInt(jsonObject, "contentType"));
                                    rankBean.setCount(JsonUtil.optInt(jsonObject, "count"));
                                    rankBean.setHasNextPage(JsonUtil.optInt(jsonObject, "hasNextPage"));
                                    rankBean.setIsSupSearch(JsonUtil.optInt(jsonObject, "isSupSearch"));
                                    rankBean.setMarginTop(JsonUtil.optInt(jsonObject, "marginTop"));
                                    rankBean.setName(JsonUtil.optStr(jsonObject, "name"));
                                    rankBean.setRspKey(JsonUtil.optStr(jsonObject, "rspKey"));
                                    rankBean.setSalt(JsonUtil.optStr(jsonObject, "salt"));
                                    rankBean.setStatKey(JsonUtil.optStr(jsonObject, "statKey"));
                                    rankBean.setTotalPages(JsonUtil.optInt(jsonObject, "totalPages"));

                                    ldJarr = JsonUtil.optJsonArr(jsonObject, "layoutData");
                                    if (ldJarr != null && ldJarr.length() > 0) {
                                        layoutDataList = new ArrayList<>();

                                        for (int i = 0, j = ldJarr.length(); i < j; i++) {
                                            layoutData = new RankBean.RankLayoutData();
                                            ldItemJsonObj = ldJarr.optJSONObject(i);
                                            layoutData.setDataList_type(JsonUtil.optInt(ldItemJsonObj,
                                                    "dataList-type"));
                                            layoutData.setIsInstalledFilter(JsonUtil.optInt(ldItemJsonObj,
                                                    "isInstalledFilter"));
                                            layoutData.setIsUpdatableFilter(JsonUtil.optInt(ldItemJsonObj,
                                                    "isUpdatableFilter"));
                                            layoutData.setLayoutId(JsonUtil.optInt(ldItemJsonObj, "layoutId"));
                                            layoutData.setLayoutName(JsonUtil.optStr(ldItemJsonObj, "layoutName"));
                                            layoutData.setListId(JsonUtil.optStr(ldItemJsonObj, "listId"));
                                            layoutData.setDetailId(JsonUtil.optStr(ldItemJsonObj, "detailId"));
                                            JSONArray dataListJarr = JsonUtil.optJsonArr(ldItemJsonObj, "dataList");
                                            if (dataListJarr != null && dataListJarr.length() > 0) {
                                                dataItemList = new ArrayList<>();
                                                for (int i1 = 0, j1 = dataListJarr.length(); i1 < j1; i1++) {
                                                    itemJsonObj = dataListJarr.optJSONObject(i1);
                                                    dataItem = new RankBean.RankLayoutDataItem();
                                                    dataItem.setCommendIcon(JsonUtil.optStr(itemJsonObj,
                                                            "commendIcon"));
                                                    dataItem.setDetailId(JsonUtil.optStr(itemJsonObj, "detailId"));
                                                    dataItem.setIcon(JsonUtil.optStr(itemJsonObj, "icon"));
                                                    dataItem.setName(JsonUtil.optStr(itemJsonObj, "name"));
                                                    dataItem.setSize(JsonUtil.optInt(itemJsonObj, "size"));
                                                    dataItem.setTrace(JsonUtil.optStr(itemJsonObj, "trace"));
                                                    dataItem.setAppVersionName(JsonUtil.optStr(itemJsonObj,
                                                            "appVersionName"));
                                                    dataItem.setMemo(JsonUtil.optStr(itemJsonObj,
                                                            "memo"));
                                                    dataItem.setOperaType(JsonUtil.optStr(itemJsonObj,
                                                            "operaType"));
                                                    dataItem.setScore(JsonUtil.optStr(itemJsonObj,
                                                            "score"));
                                                    dataItem.setPackageName(JsonUtil.optStr(itemJsonObj,
                                                            "package"));
                                                    dataItem.setVersionCode(JsonUtil.optStr(itemJsonObj,
                                                            "versionCode"));
                                                    dataItem.setAppid(JsonUtil.optStr(itemJsonObj,
                                                            "appid"));
                                                    dataItem.setExIcons(JsonUtil.optStr(itemJsonObj,
                                                            "exIcons"));
                                                    dataItem.setDownCountDesc(JsonUtil.optStr(itemJsonObj,
                                                            "downCountDesc"));
                                                    dataItem.setID(JsonUtil.optStr(itemJsonObj,
                                                            "ID"));
                                                    dataItem.setSizeDesc(JsonUtil.optStr(itemJsonObj,
                                                            "sizeDesc"));
                                                    dataItem.setDownurl(JsonUtil.optStr(itemJsonObj,
                                                            "downurl"));
                                                    dataItem.setIntro(JsonUtil.optStr(itemJsonObj,
                                                            "intro"));
                                                    dataItem.setDescribeType(JsonUtil.optStr(itemJsonObj,
                                                            "describeType"));
                                                    dataItem.setAliasName(JsonUtil.optStr(itemJsonObj,
                                                            "aliasName"));
                                                    dataItem.setPrice(JsonUtil.optStr(itemJsonObj,
                                                            "price"));
                                                    dataItem.setExIcon(JsonUtil.optStr(itemJsonObj,
                                                            "exIcon"));
                                                    dataItem.setListId(JsonUtil.optStr(itemJsonObj,
                                                            "listId"));
                                                    dataItem.setStars(JsonUtil.optStr(itemJsonObj,
                                                            "stars"));
                                                    dataItem.setMd5(JsonUtil.optStr(itemJsonObj,
                                                            "md5"));
                                                    dataItem.setComefrom(JsonUtil.optStr(itemJsonObj,
                                                            "comefrom"));
                                                    dataItem.setBtnDisable(JsonUtil.optInt(itemJsonObj,
                                                            "btnDisable"));
                                                    dataItem.setCtype(JsonUtil.optInt(itemJsonObj,
                                                            "ctype"));
                                                    dataItem.setVideoFlag(JsonUtil.optInt(itemJsonObj,
                                                            "videoFlag"));
                                                    dataItem.setSize(JsonUtil.optLong(itemJsonObj,
                                                            "size"));
                                                    dataItemList.add(dataItem);
                                                }
                                                layoutData.setDataList(dataItemList);
                                            }
                                            if (layoutData.getDataList_type() != 0) {
                                                // TODO: 2018/5/30 屏蔽掉无用的条目
                                                layoutDataList.add(layoutData);
                                            }
                                        }
                                        rankBean.setLayoutData(layoutDataList);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        return RxUtils.createObservableData(rankBean);
                    }
                })
                .compose(RxUtils.io_main()).subscribe(observer);
    }
}
