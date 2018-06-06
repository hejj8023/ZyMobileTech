package com.example.bailan.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.bailan.Const;
import com.zysdk.vulture.clib.utils.LoggerUtils;

import java.util.List;

/**
 * Created by example on 2018/5/29.
 */

public class RecommonBean {
    private int contentType;
    private int count;
    private int hasNextPage;
    private int isSupSearch;
    private int marginTop;
    private String name;
    private String rspKey;
    private String salt;
    private List<?> sortInfo;
    private String statKey;
    private int totalPages;
    private List<LayoutBean> layout;
    private List<RecommendLayoutData> layoutData;

    public int getContentType() {
        return contentType;
    }

    public void setContentType(int contentType) {
        this.contentType = contentType;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(int hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public int getIsSupSearch() {
        return isSupSearch;
    }

    public void setIsSupSearch(int isSupSearch) {
        this.isSupSearch = isSupSearch;
    }

    public int getMarginTop() {
        return marginTop;
    }

    public void setMarginTop(int marginTop) {
        this.marginTop = marginTop;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRspKey() {
        return rspKey;
    }

    public void setRspKey(String rspKey) {
        this.rspKey = rspKey;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public List<?> getSortInfo() {
        return sortInfo;
    }

    public void setSortInfo(List<?> sortInfo) {
        this.sortInfo = sortInfo;
    }

    public String getStatKey() {
        return statKey;
    }

    public void setStatKey(String statKey) {
        this.statKey = statKey;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<LayoutBean> getLayout() {
        return layout;
    }

    public void setLayout(List<LayoutBean> layout) {
        this.layout = layout;
    }

    public List<RecommendLayoutData> getLayoutData() {
        return layoutData;
    }

    public void setLayoutData(List<RecommendLayoutData> layoutData) {
        this.layoutData = layoutData;
    }

    public static class LayoutBean {
    }

    public static class RecommendLayoutData implements MultiItemEntity {
        private int dataList_type;
        private String detailId;
        private int isInstalledFilter;
        private int isUpdatableFilter;
        private long layoutId;
        private String layoutName;
        private String listId;
        private String name;
        private List<RecommendLayoutDataItem> dataList;

        public List<RecommendLayoutDataItem> getDataList() {
            return dataList;
        }

        public void setDataList(List<RecommendLayoutDataItem> dataList) {
            this.dataList = dataList;
        }

        public int getDataList_type() {
            return dataList_type;
        }

        public void setDataList_type(int dataList_type) {
            this.dataList_type = dataList_type;
        }

        public String getDetailId() {
            return detailId;
        }

        public void setDetailId(String detailId) {
            this.detailId = detailId;
        }

        public int getIsInstalledFilter() {
            return isInstalledFilter;
        }

        public void setIsInstalledFilter(int isInstalledFilter) {
            this.isInstalledFilter = isInstalledFilter;
        }

        public int getIsUpdatableFilter() {
            return isUpdatableFilter;
        }

        public void setIsUpdatableFilter(int isUpdatableFilter) {
            this.isUpdatableFilter = isUpdatableFilter;
        }

        public long getLayoutId() {
            return layoutId;
        }

        public void setLayoutId(long layoutId) {
            this.layoutId = layoutId;
        }

        public String getLayoutName() {
            return layoutName;
        }

        public void setLayoutName(String layoutName) {
            this.layoutName = layoutName;
        }

        public String getListId() {
            return listId;
        }

        public void setListId(String listId) {
            this.listId = listId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public int getItemType() {
            int itemType = -1;
            LoggerUtils.loge("dataList_type = " + dataList_type);
            switch (dataList_type) {
                case 3:
                case 34:
                    itemType = Const.LIST_ITEM_COLLECTS.ITEM_RECOMMON_LIST.ITEM_TYPE_COMMON;
                    break;
                case 2:
                    if (dataList.size() == 2)
                        itemType = Const.LIST_ITEM_COLLECTS.ITEM_RECOMMON_LIST.ITEM_TYPE_AD;
                    else if (dataList.size() == 6)
                        itemType = Const.LIST_ITEM_COLLECTS.ITEM_RECOMMON_LIST.ITEM_TYPE_BANNER;
                    break;
            }
            return itemType;
        }
    }

    public static class RecommendLayoutDataItem {
        private String name;
        private String trace;
        private String detailId;
        private String commendIcon;
        private String icon;
        private List<LayoutItemSubBean> list;

        public List<LayoutItemSubBean> getList() {
            return list;
        }

        public void setList(List<LayoutItemSubBean> list) {
            this.list = list;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTrace() {
            return trace;
        }

        public void setTrace(String trace) {
            this.trace = trace;
        }

        public String getDetailId() {
            return detailId;
        }

        public void setDetailId(String detailId) {
            this.detailId = detailId;
        }

        public String getCommendIcon() {
            return commendIcon;
        }

        public void setCommendIcon(String commendIcon) {
            this.commendIcon = commendIcon;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }

    public static class LayoutItemSubBean {
        private String ID;
        private String appVersionName;
        private String appid;
        private int btnDisable;
        private int ctype;
        private String[] dependentedApps;
        private String describeType;
        private String detailId;
        private String downCountDesc;
        private String downurl;
        private String[] exIcons;
        private String icon;
        private String intro;
        private String listId;
        private String md5;
        private String memo;
        private String name;
        private String packageName;
        private String price;
        private String score;
        private long size;
        private String sizeDesc;
        private String stars;
        private String trace;
        private String versionCode;
        private int videoFlag;
        private String activityId;
        private String activityInfoCues;
        private String activityName;
        private int isPrize;
        private String[] labelUrl;
        private int prizeState;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getAppVersionName() {
            return appVersionName;
        }

        public void setAppVersionName(String appVersionName) {
            this.appVersionName = appVersionName;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public int getBtnDisable() {
            return btnDisable;
        }

        public void setBtnDisable(int btnDisable) {
            this.btnDisable = btnDisable;
        }

        public int getCtype() {
            return ctype;
        }

        public void setCtype(int ctype) {
            this.ctype = ctype;
        }

        public String getDescribeType() {
            return describeType;
        }

        public void setDescribeType(String describeType) {
            this.describeType = describeType;
        }

        public String getDetailId() {
            return detailId;
        }

        public void setDetailId(String detailId) {
            this.detailId = detailId;
        }

        public String getDownCountDesc() {
            return downCountDesc;
        }

        public void setDownCountDesc(String downCountDesc) {
            this.downCountDesc = downCountDesc;
        }

        public String getDownurl() {
            return downurl;
        }

        public void setDownurl(String downurl) {
            this.downurl = downurl;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getListId() {
            return listId;
        }

        public void setListId(String listId) {
            this.listId = listId;
        }

        public String getMd5() {
            return md5;
        }

        public void setMd5(String md5) {
            this.md5 = md5;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public long getSize() {
            return size;
        }

        public void setSize(long size) {
            this.size = size;
        }

        public String getSizeDesc() {
            return sizeDesc;
        }

        public void setSizeDesc(String sizeDesc) {
            this.sizeDesc = sizeDesc;
        }

        public String getStars() {
            return stars;
        }

        public void setStars(String stars) {
            this.stars = stars;
        }

        public String getTrace() {
            return trace;
        }

        public void setTrace(String trace) {
            this.trace = trace;
        }

        public String getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(String versionCode) {
            this.versionCode = versionCode;
        }

        public int getVideoFlag() {
            return videoFlag;
        }

        public void setVideoFlag(int videoFlag) {
            this.videoFlag = videoFlag;
        }

        public String[] getDependentedApps() {
            return dependentedApps;
        }

        public void setDependentedApps(String[] dependentedApps) {
            this.dependentedApps = dependentedApps;
        }

        public String[] getExIcons() {
            return exIcons;
        }

        public void setExIcons(String[] exIcons) {
            this.exIcons = exIcons;
        }

        public String getActivityId() {
            return activityId;
        }

        public void setActivityId(String activityId) {
            this.activityId = activityId;
        }

        public String getActivityInfoCues() {
            return activityInfoCues;
        }

        public void setActivityInfoCues(String activityInfoCues) {
            this.activityInfoCues = activityInfoCues;
        }

        public String getActivityName() {
            return activityName;
        }

        public void setActivityName(String activityName) {
            this.activityName = activityName;
        }

        public int getIsPrize() {
            return isPrize;
        }

        public void setIsPrize(int isPrize) {
            this.isPrize = isPrize;
        }

        public String[] getLabelUrl() {
            return labelUrl;
        }

        public void setLabelUrl(String[] labelUrl) {
            this.labelUrl = labelUrl;
        }

        public int getPrizeState() {
            return prizeState;
        }

        public void setPrizeState(int prizeState) {
            this.prizeState = prizeState;
        }
    }
}
