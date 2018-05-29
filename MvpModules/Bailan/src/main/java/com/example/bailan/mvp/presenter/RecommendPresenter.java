package com.example.bailan.mvp.presenter;

import com.example.bailan.bean.RecommonFinalBean;
import com.example.bailan.mvp.contract.RecommendContract;
import com.example.bailan.mvp.model.RecommendModel;
import com.zhiyangstudio.commonlib.mvp.presenter.BasePresenter;
import com.zhiyangstudio.commonlib.net.callback.AbsBaseObserver;
import com.zhiyangstudio.commonlib.utils.EmptyUtils;
import com.zhiyangstudio.commonlib.utils.JsonUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import okhttp3.ResponseBody;

/**
 * Created by zzg on 2018/5/27.
 */

public class RecommendPresenter extends BasePresenter<RecommendContract.IListView> implements
        RecommendContract.IListPresenter {

    private final RecommendModel mRecommendModel;
    private RecommendContract.IListView mListView;

    @Inject
    public RecommendPresenter() {
        mRecommendModel = new RecommendModel();
    }

    @Override
    public void loadListData2() {
        mListView = getView();
        mRecommendModel.loadData2(new AbsBaseObserver<ResponseBody>(this, RecommendModel.class
                .getName()) {
            @Override
            public void onNext(ResponseBody responseBody) {
                List<RecommonFinalBean.FianlLayoutData> layoutData = new ArrayList<>();
                try {
                    String str = responseBody.string();
                    // gsonformat会造成数据丢失，就自己解析
                    RecommonFinalBean bean = new RecommonFinalBean();
                    if (EmptyUtils.isNotEmpty(str)) {
                        try {
                            JSONObject jsonObject = new JSONObject(str);
                            bean.setContentType(JsonUtil.optInt(jsonObject, "contentType"));
                            bean.setCount(JsonUtil.optInt(jsonObject, "count"));
                            bean.setIsSupSearch(JsonUtil.optInt(jsonObject, "isSupSearch"));
                            bean.setMarginTop(JsonUtil.optInt(jsonObject, "marginTop"));
                            bean.setTotalPages(JsonUtil.optInt(jsonObject, "totalPages"));
                            bean.setName(JsonUtil.optStr(jsonObject, "name"));
                            bean.setRspKey(JsonUtil.optStr(jsonObject, "rspKey"));
                            bean.setSalt(JsonUtil.optStr(jsonObject, "salt"));
                            bean.setStatKey(JsonUtil.optStr(jsonObject, "statKey"));

                            JSONArray jsonArray = JsonUtil.optJsonArr(jsonObject, "layoutData");
                            RecommonFinalBean.FianlLayoutData fianlLayoutData = null;
                            RecommonFinalBean.LayoutItemBean layoutItemBean = null;
                            List<RecommonFinalBean.LayoutItemBean> datList = null;
                            List<RecommonFinalBean.LayoutItemSubBean> subList = null;
                            RecommonFinalBean.LayoutItemSubBean layoutItemSubBean = null;
                            String[] labelUrl = null;
                            if (jsonArray != null && jsonArray.length() > 0) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject layoutDataJobj = jsonArray.optJSONObject(i);
                                    fianlLayoutData = new RecommonFinalBean.FianlLayoutData();
                                    fianlLayoutData.setDataList_type(JsonUtil.optInt(layoutDataJobj, "dataList-type"));
                                    fianlLayoutData.setIsInstalledFilter(JsonUtil.optInt(layoutDataJobj, "isInstalledFilter"));
                                    fianlLayoutData.setIsUpdatableFilter(JsonUtil.optInt(layoutDataJobj, "isUpdatableFilter"));
                                    fianlLayoutData.setLayoutId(JsonUtil.optInt(layoutDataJobj, "layoutId"));

                                    fianlLayoutData.setDetailId(JsonUtil.optStr(layoutDataJobj, "detailId"));
                                    fianlLayoutData.setLayoutName(JsonUtil.optStr(layoutDataJobj, "layoutName"));
                                    fianlLayoutData.setListId(JsonUtil.optStr(layoutDataJobj, "listId"));
                                    fianlLayoutData.setName(JsonUtil.optStr(layoutDataJobj, "name"));

                                    JSONArray dataListJarr = JsonUtil.optJsonArr(layoutDataJobj, "dataList");
                                    if (dataListJarr != null && dataListJarr.length() > 0) {
                                        datList = new ArrayList<>();
                                        for (int i1 = 0; i1 < dataListJarr.length(); i1++) {
                                            JSONObject dataItemJOBj = dataListJarr.optJSONObject(i1);
                                            layoutItemBean = new RecommonFinalBean.LayoutItemBean();
                                            layoutItemBean.setName(JsonUtil.optStr(dataItemJOBj, "name"));
                                            layoutItemBean.setCommendIcon(JsonUtil.optStr(dataItemJOBj, "commendIcon"));
                                            layoutItemBean.setDetailId(JsonUtil.optStr(dataItemJOBj, "detailId"));
                                            layoutItemBean.setIcon(JsonUtil.optStr(dataItemJOBj, "icon"));
                                            layoutItemBean.setTrace(JsonUtil.optStr(dataItemJOBj, "trace"));
                                            JSONArray listArr = JsonUtil.optJsonArr(dataItemJOBj, "list");
                                            if (listArr != null && listArr.length() > 0) {
                                                subList = new ArrayList<>();
                                                for (int i2 = 0; i2 < listArr.length(); i2++) {
                                                    layoutItemSubBean = new RecommonFinalBean.LayoutItemSubBean();
                                                    JSONObject listItemJson = listArr.optJSONObject(i2);
                                                    layoutItemSubBean.setAppid(JsonUtil.optStr(listItemJson, "appid"));
                                                    layoutItemSubBean.setAppVersionName(JsonUtil.optStr(listItemJson, "appVersionName"));
                                                    layoutItemSubBean.setBtnDisable(JsonUtil.optInt(listItemJson, "btnDisable"));
                                                    layoutItemSubBean.setCtype(JsonUtil.optInt(listItemJson, "ctype"));
                                                    layoutItemSubBean.setDescribeType(JsonUtil.optStr(listItemJson, "describeType"));
                                                    layoutItemSubBean.setDetailId(JsonUtil.optStr(listItemJson, "detailId"));
                                                    layoutItemSubBean.setDownCountDesc(JsonUtil.optStr(listItemJson, "downCountDesc"));
                                                    layoutItemSubBean.setDownurl(JsonUtil.optStr(listItemJson, "downurl"));
                                                    layoutItemSubBean.setIcon(JsonUtil.optStr(listItemJson, "icon"));
                                                    layoutItemSubBean.setID(JsonUtil.optStr(listItemJson, "ID"));
                                                    layoutItemSubBean.setIntro(JsonUtil.optStr(listItemJson, "intro"));
                                                    layoutItemSubBean.setListId(JsonUtil.optStr(listItemJson, "listId"));
                                                    layoutItemSubBean.setMd5(JsonUtil.optStr(listItemJson, "md5"));
                                                    layoutItemSubBean.setMemo(JsonUtil.optStr(listItemJson, "memo"));
                                                    layoutItemSubBean.setName(JsonUtil.optStr(listItemJson, "name"));
                                                    layoutItemSubBean.setPackageName(JsonUtil.optStr(listItemJson, "package"));
                                                    layoutItemSubBean.setPrice(JsonUtil.optStr(listItemJson, "price"));
                                                    layoutItemSubBean.setScore(JsonUtil.optStr(listItemJson, "score"));
                                                    layoutItemSubBean.setSize(JsonUtil.optLong(listItemJson, "size"));
                                                    layoutItemSubBean.setTrace(JsonUtil.optStr(listItemJson, "trace"));
                                                    layoutItemSubBean.setStars(JsonUtil.optStr(listItemJson, "stars"));
                                                    layoutItemSubBean.setSizeDesc(JsonUtil.optStr(listItemJson, "sizeDesc"));
                                                    layoutItemSubBean.setVersionCode(JsonUtil.optStr(listItemJson, "versionCode"));
                                                    layoutItemSubBean.setVideoFlag(JsonUtil.optInt(listItemJson, "videoFlag"));

                                                    layoutItemSubBean.setActivityId(JsonUtil.optStr(listItemJson, "activityId"));
                                                    layoutItemSubBean.setActivityInfoCues(JsonUtil.optStr(listItemJson, "activityInfoCues"));
                                                    layoutItemSubBean.setActivityName(JsonUtil.optStr(listItemJson, "activityName"));
                                                    layoutItemSubBean.setIsPrize(JsonUtil.optInt(listItemJson, "isPrize"));
                                                    layoutItemSubBean.setPrizeState(JsonUtil.optInt(listItemJson, "prizeState"));
                                                    JSONArray labelUrlJARR = JsonUtil.optJsonArr(listItemJson, "labelUrl");

                                                    if (labelUrlJARR != null && labelUrlJARR.length() > 0) {
                                                        int len = labelUrlJARR.length();
                                                        labelUrl = new String[len];
                                                        for (int i3 = 0; i3 < len; i3++) {
                                                            labelUrl[i3] = labelUrlJARR.optString(i3);
                                                        }
                                                        layoutItemSubBean.setLabelUrl(labelUrl);
                                                    }
                                                    subList.add(layoutItemSubBean);
                                                }
                                                layoutItemBean.setList(subList);
                                            }
                                            datList.add(layoutItemBean);
                                        }
                                        fianlLayoutData.setDataList(datList);
                                    }
                                    if (fianlLayoutData.getDataList_type() != 0) {
                                        layoutData.add(fianlLayoutData);
                                    }
                                }
                            }
                            bean.setLayoutData(layoutData);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mListView.setData(layoutData);
            }
        });
    }
}
