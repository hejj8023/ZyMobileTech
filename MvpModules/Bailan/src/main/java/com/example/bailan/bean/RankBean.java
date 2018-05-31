package com.example.bailan.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.bailan.Const;

import java.util.List;

/**
 * Created by zzg on 2018/5/27.
 */

public class RankBean {
    private int hasNextPage;
    private int count;
    private int marginTop;
    private String name;
    private int isSupSearch;
    private int contentType;
    private String statKey;
    private List<?> sortInfo;
    private int totalPages;
    private String salt;
    private String rspKey;
    private List<RankLayoutData> layoutData;

    public int getHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(int hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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

    public int getIsSupSearch() {
        return isSupSearch;
    }

    public void setIsSupSearch(int isSupSearch) {
        this.isSupSearch = isSupSearch;
    }

    public int getContentType() {
        return contentType;
    }

    public void setContentType(int contentType) {
        this.contentType = contentType;
    }

    public String getStatKey() {
        return statKey;
    }

    public void setStatKey(String statKey) {
        this.statKey = statKey;
    }

    public List<?> getSortInfo() {
        return sortInfo;
    }

    public void setSortInfo(List<?> sortInfo) {
        this.sortInfo = sortInfo;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getRspKey() {
        return rspKey;
    }

    public void setRspKey(String rspKey) {
        this.rspKey = rspKey;
    }

    public List<RankLayoutData> getLayoutData() {
        return layoutData;
    }

    public void setLayoutData(List<RankLayoutData> layoutData) {
        this.layoutData = layoutData;
    }

    public static class RankLayoutData implements MultiItemEntity{
        private int dataList_type;
        private String listId;
        private int isUpdatableFilter;
        private String name;
        private String layoutName;
        private int layoutId;
        private int isInstalledFilter;
        private String detailId;
        private List<RankLayoutDataItem> dataList;

        public int getDataList_type() {
            return dataList_type;
        }

        public void setDataList_type(int dataList_type) {
            this.dataList_type = dataList_type;
        }

        public String getListId() {
            return listId;
        }

        public void setListId(String listId) {
            this.listId = listId;
        }

        public int getIsUpdatableFilter() {
            return isUpdatableFilter;
        }

        public void setIsUpdatableFilter(int isUpdatableFilter) {
            this.isUpdatableFilter = isUpdatableFilter;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLayoutName() {
            return layoutName;
        }

        public void setLayoutName(String layoutName) {
            this.layoutName = layoutName;
        }

        public int getLayoutId() {
            return layoutId;
        }

        public void setLayoutId(int layoutId) {
            this.layoutId = layoutId;
        }

        public int getIsInstalledFilter() {
            return isInstalledFilter;
        }

        public void setIsInstalledFilter(int isInstalledFilter) {
            this.isInstalledFilter = isInstalledFilter;
        }

        public String getDetailId() {
            return detailId;
        }

        public void setDetailId(String detailId) {
            this.detailId = detailId;
        }

        public List<RankLayoutDataItem> getDataList() {
            return dataList;
        }

        public void setDataList(List<RankLayoutDataItem> dataList) {
            this.dataList = dataList;
        }

        @Override
        public int getItemType() {
            int type = -1;
            switch (getDataList_type()) {
                case 1:
                    type = Const.LIST_ITEM_COLLECTS.ITEM_RANK_LIST.ITEM_RANK_TITLE_DIVIDER;
                    break;
                case 2:
                    type = Const.LIST_ITEM_COLLECTS.ITEM_RANK_LIST.ITEM_RANK_MENU;
                    break;
                case 3:
                    type = Const.LIST_ITEM_COLLECTS.ITEM_RANK_LIST.ITEM_RANK_LIST;
                    break;
            }
            return type;
        }
    }


    public static class RankLayoutDataItem {
        private String appVersionName;
        private List<?> dependentedApps;
        private String memo;
        private String operaType;
        private String score;
        private String packageName;
        private String versionCode;
        private String appid;
        private String exIcons;
        private String downCountDesc;
        private int btnDisable;
        private String name;
        private String ID;
        private String downurl;
        private String icon;
        private String trace;
        private int ctype;
        private String sizeDesc;
        private String intro;
        private long size;
        private String describeType;
        private String aliasName;
        private String price;
        private String exIcon;
        private String listId;
        private String stars;
        private int videoFlag;
        private String md5;
        private String comefrom;
        private String detailId;
        private String commendIcon;

        public String getAppVersionName() {
            return appVersionName;
        }

        public void setAppVersionName(String appVersionName) {
            this.appVersionName = appVersionName;
        }

        public List<?> getDependentedApps() {
            return dependentedApps;
        }

        public void setDependentedApps(List<?> dependentedApps) {
            this.dependentedApps = dependentedApps;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public String getOperaType() {
            return operaType;
        }

        public void setOperaType(String operaType) {
            this.operaType = operaType;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public String getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(String versionCode) {
            this.versionCode = versionCode;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getExIcons() {
            return exIcons;
        }

        public void setExIcons(String exIcons) {
            this.exIcons = exIcons;
        }

        public String getDownCountDesc() {
            return downCountDesc;
        }

        public void setDownCountDesc(String downCountDesc) {
            this.downCountDesc = downCountDesc;
        }

        public int getBtnDisable() {
            return btnDisable;
        }

        public void setBtnDisable(int btnDisable) {
            this.btnDisable = btnDisable;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
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

        public String getTrace() {
            return trace;
        }

        public void setTrace(String trace) {
            this.trace = trace;
        }

        public int getCtype() {
            return ctype;
        }

        public void setCtype(int ctype) {
            this.ctype = ctype;
        }

        public String getSizeDesc() {
            return sizeDesc;
        }

        public void setSizeDesc(String sizeDesc) {
            this.sizeDesc = sizeDesc;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public long getSize() {
            return size;
        }

        public void setSize(long size) {
            this.size = size;
        }

        public String getDescribeType() {
            return describeType;
        }

        public void setDescribeType(String describeType) {
            this.describeType = describeType;
        }

        public String getAliasName() {
            return aliasName;
        }

        public void setAliasName(String aliasName) {
            this.aliasName = aliasName;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getExIcon() {
            return exIcon;
        }

        public void setExIcon(String exIcon) {
            this.exIcon = exIcon;
        }

        public String getListId() {
            return listId;
        }

        public void setListId(String listId) {
            this.listId = listId;
        }

        public String getStars() {
            return stars;
        }

        public void setStars(String stars) {
            this.stars = stars;
        }

        public int getVideoFlag() {
            return videoFlag;
        }

        public void setVideoFlag(int videoFlag) {
            this.videoFlag = videoFlag;
        }

        public String getMd5() {
            return md5;
        }

        public void setMd5(String md5) {
            this.md5 = md5;
        }

        public String getComefrom() {
            return comefrom;
        }

        public void setComefrom(String comefrom) {
            this.comefrom = comefrom;
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
    }
}
