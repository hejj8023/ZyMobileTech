package com.example.bailan.mvp.model;

import com.example.bailan.base.BaseWLModel;
import com.example.bailan.bean.CategoryBean;
import com.example.bailan.mvp.contract.CategoryContract;
import com.zysdk.vulture.clib.utils.EmptyUtils;
import com.zysdk.vulture.clib.utils.JsonUtil;
import com.zysdk.vulture.clib.utils.LoggerUtils;
import com.zysdk.vulture.clib.utils.RxUtils;

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

public class CategoryModel extends BaseWLModel implements CategoryContract.ICategoryModel {

    @SuppressWarnings("unchecked")
    @Override
    public void getData(Observer<CategoryBean> observer) {
        mNetApi.getCategoryList()
                .flatMap(new Function<ResponseBody, ObservableSource<CategoryBean>>() {
                    @Override
                    public ObservableSource<CategoryBean> apply(ResponseBody responseBody) throws Exception {
                        CategoryBean categoryBean = new CategoryBean();
                        CategoryBean.CategoryLayoutData layoutData = null;
                        List<CategoryBean.CategoryLayoutData> layoutDataList = null;
                        List<CategoryBean.CategoryDataItem> dataItemList = null;
                        CategoryBean.CategoryDataItem dataItem = null;
                        JSONObject ldItemJsonObj = null;
                        JSONObject jsonObject = null;
                        JSONArray dataListJarr = null;
                        JSONObject itemJsonObj = null;
                        if (responseBody != null) {
                            String responseStr = responseBody.string();
                            if (EmptyUtils.isNotEmpty(responseStr)) {
                                LoggerUtils.loge("responseStr = \n" + responseStr);
                                try {
                                    jsonObject = new JSONObject(responseStr);
                                    categoryBean.setContentType(JsonUtil.optInt(jsonObject, "contentType"));
                                    categoryBean.setCount(JsonUtil.optInt(jsonObject, "count"));
                                    categoryBean.setHasNextPage(JsonUtil.optInt(jsonObject, "hasNextPage"));
                                    categoryBean.setIsSupSearch(JsonUtil.optInt(jsonObject, "isSupSearch"));
                                    categoryBean.setMarginTop(JsonUtil.optInt(jsonObject, "marginTop"));
                                    categoryBean.setName(JsonUtil.optStr(jsonObject, "name"));
                                    categoryBean.setRspKey(JsonUtil.optStr(jsonObject, "rspKey"));
                                    categoryBean.setSalt(JsonUtil.optStr(jsonObject, "salt"));
                                    categoryBean.setStatKey(JsonUtil.optStr(jsonObject, "statKey"));
                                    categoryBean.setTotalPages(JsonUtil.optInt(jsonObject, "totalPages"));
                                    JSONArray ldJarr = JsonUtil.optJsonArr(jsonObject, "layoutData");
                                    if (ldJarr != null && ldJarr.length() > 0) {
                                        layoutDataList = new ArrayList<>();
                                        for (int i = 0, j = ldJarr.length(); i < j; i++) {
                                            layoutData = new CategoryBean.CategoryLayoutData();
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
                                            dataListJarr = JsonUtil.optJsonArr(ldItemJsonObj, "dataList");
                                            if (dataListJarr != null && dataListJarr.length() > 0) {
                                                dataItemList = new ArrayList<>();
                                                for (int i1 = 0, j1 = dataListJarr.length(); i1 < j1; i1++) {
                                                    itemJsonObj = dataListJarr.optJSONObject(i1);
                                                    dataItem = new CategoryBean.CategoryDataItem();
                                                    dataItem.setCommendIcon(JsonUtil.optStr(itemJsonObj,
                                                            "commendIcon"));
                                                    dataItem.setDetailId(JsonUtil.optStr(itemJsonObj, "detailId"));
                                                    dataItem.setIcon(JsonUtil.optStr(itemJsonObj, "icon"));
                                                    dataItem.setName(JsonUtil.optStr(itemJsonObj, "name"));
                                                    dataItem.setSize(JsonUtil.optInt(itemJsonObj, "size"));
                                                    dataItem.setTrace(JsonUtil.optStr(itemJsonObj, "trace"));
                                                    dataItemList.add(dataItem);
                                                }
                                                layoutData.setDataList(dataItemList);
                                            }
                                            if (layoutData.getDataList_type() != 0) {
                                                // TODO: 2018/5/30 屏蔽掉无用的条目
                                                layoutDataList.add(layoutData);
                                            }
                                        }
                                        categoryBean.setLayoutData(layoutDataList);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        return RxUtils.createObservableData(categoryBean);
                    }
                })
                .compose(RxUtils.io_main()).subscribe(observer);
    }
}
