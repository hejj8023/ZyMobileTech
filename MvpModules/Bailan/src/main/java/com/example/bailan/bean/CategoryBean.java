package com.example.bailan.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.bailan.Const;

import java.util.List;

/**
 * Created by zzg on 2018/5/27.
 */

public class CategoryBean {

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
    private List<CategoryLayoutData> layoutData;

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

    public List<CategoryLayoutData> getLayoutData() {
        return layoutData;
    }

    public void setLayoutData(List<CategoryLayoutData> layoutData) {
        this.layoutData = layoutData;
    }

    public static class CategoryLayoutData implements MultiItemEntity {
        private int dataList_type;
        private String listId;
        private int isUpdatableFilter;
        private String layoutName;
        private int layoutId;
        private int isInstalledFilter;
        private List<CategoryDataItem> dataList;

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

        public List<CategoryDataItem> getDataList() {
            return dataList;
        }

        public void setDataList(List<CategoryDataItem> dataList) {
            this.dataList = dataList;
        }

        @Override
        public int getItemType() {
            int type = -1;
            switch (getDataList_type()) {
                case 1:
                    type = Const.LIST_ITEM_COLLECTS.ITEM_CATEGORY_LIST.ITEM_CATEGORY_TITLE_DIVIDER;
                    break;
                case 2:
                    type = Const.LIST_ITEM_COLLECTS.ITEM_CATEGORY_LIST.ITEM_CATEGORY_MENU;
                    break;
                case 39:
                    type = Const.LIST_ITEM_COLLECTS.ITEM_CATEGORY_LIST.ITEM_CATEGORY_LIST;
                    break;
            }
            return type;
        }

        public int getDataList_type() {
            return dataList_type;
        }

        public void setDataList_type(int dataList_type) {
            this.dataList_type = dataList_type;
        }
    }

    public static class CategoryDataItem {
        public int size;
        public String icon;
        public String trace;
        public String name;
        public String commendIcon;
        public String detailId;

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
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
    }
}
