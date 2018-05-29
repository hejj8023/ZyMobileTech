package com.example.bailan.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.bailan.Const;

import java.util.List;

/**
 * Created by zzg on 2018/5/27.
 */

public class RecommendBean {

    private int hasNextPage;
    private int count;
    private int marginTop;
    private String name;
    private int isSupSearch;
    private int contentType;
    private String statKey;
    private int totalPages;
    private String salt;
    private String rspKey;
    private List<LayoutBean> layout;
    private List<?> sortInfo;
    private List<LayoutDataBean> layoutData;

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

    public List<LayoutBean> getLayout() {
        return layout;
    }

    public void setLayout(List<LayoutBean> layout) {
        this.layout = layout;
    }

    public List<?> getSortInfo() {
        return sortInfo;
    }

    public void setSortInfo(List<?> sortInfo) {
        this.sortInfo = sortInfo;
    }

    public List<LayoutDataBean> getLayoutData() {
        return layoutData;
    }

    public void setLayoutData(List<LayoutDataBean> layoutData) {
        this.layoutData = layoutData;
    }

    public static class LayoutBean {
        private String layoutName;
        private int maxRows;
        private int layoutId;

        public String getLayoutName() {
            return layoutName;
        }

        public void setLayoutName(String layoutName) {
            this.layoutName = layoutName;
        }

        public int getMaxRows() {
            return maxRows;
        }

        public void setMaxRows(int maxRows) {
            this.maxRows = maxRows;
        }

        public int getLayoutId() {
            return layoutId;
        }

        public void setLayoutId(int layoutId) {
            this.layoutId = layoutId;
        }
    }

    public static class LayoutDataBean implements MultiItemEntity {
        private int dataListtype;
        private String listId;
        private int isUpdatableFilter;
        private String layoutName;
        private int layoutId;
        private int isInstalledFilter;
        private String name;
        private String detailId;
        private List<DataListBean> dataList;

        public int getDataListtype() {
            return dataListtype;
        }

        public void setDataListtype(int dataListtype) {
            this.dataListtype = dataListtype;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDetailId() {
            return detailId;
        }

        public void setDetailId(String detailId) {
            this.detailId = detailId;
        }

        public List<DataListBean> getDataList() {
            return dataList;
        }

        public void setDataList(List<DataListBean> dataList) {
            this.dataList = dataList;
        }

        @Override
        public int getItemType() {
            int itemType = -1;
            switch (dataListtype) {
                case 3:
                case 34:
                    itemType = Const.LIST_ITEM_COLLECTS.ITEM_RECOMMON_LIST.ITEM_TYPE_COMMON;
                    break;
                case 2:
                    itemType = Const.LIST_ITEM_COLLECTS.ITEM_RECOMMON_LIST.ITEM_TYPE_AD;
                    break;
                case 0:
                    itemType = Const.LIST_ITEM_COLLECTS.ITEM_RECOMMON_LIST.ITEM_TYPE_BANNER;
                    break;
            }
            return itemType;
        }

        public static class DataListBean {
            private String icon;
            private String trace;
            private String name;
            private String commendIcon;
            private String detailId;
            private int size;
            private List<DataSubBean> list;

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }

            public List<DataSubBean> getList() {
                return list;
            }

            public void setList(List<DataSubBean> list) {
                this.list = list;
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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCommendIcon() {
                return commendIcon;
            }

            public void setCommendIcon(String commendIcon) {
                this.commendIcon = commendIcon;
            }

            public String getDetailId() {
                return detailId;
            }

            public void setDetailId(String detailId) {
                this.detailId = detailId;
            }

            private class DataSubBean {
                private String ID;
                private String appVersionName;
                private String appid;
                private int btnDisable;
                private int ctype;
                private List<?> dependentedApps;
                private String describeType;
                private String detailId;
                private String downCountDesc;
                private String downurl;
                private String exIcons;
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

                public List<?> getDependentedApps() {
                    return dependentedApps;
                }

                public void setDependentedApps(List<?> dependentedApps) {
                    this.dependentedApps = dependentedApps;
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

                public String getExIcons() {
                    return exIcons;
                }

                public void setExIcons(String exIcons) {
                    this.exIcons = exIcons;
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
            }
        }
    }


}
