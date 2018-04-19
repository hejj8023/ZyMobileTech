package com.example.wanandroid.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhiyang on 2018/4/19.
 */

public class TreeBean implements Serializable {
    private static final long serialVersionUID = 6698238212345793726L;
    /**
     * {
     * "children": [
     * {
     * "children": [],
     * "courseId": 13,
     * "id": 60,
     * "name": "Android Studio相关",
     * "order": 1000,
     * "parentChapterId": 150,
     * "visible": 1
     * },
     * {
     * "children": [],
     * "courseId": 13,
     * "id": 169,
     * "name": "gradle",
     * "order": 1001,
     * "parentChapterId": 150,
     * "visible": 1
     * },
     * {
     * "children": [],
     * "courseId": 13,
     * "id": 269,
     * "name": "官方发布",
     * "order": 1002,
     * "parentChapterId": 150,
     * "visible": 1
     * }
     * ],
     * "courseId": 13,
     * "id": 150,
     * "name": "开发环境",
     * "order": 1,
     * "parentChapterId": 0,
     * "visible": 1
     * }
     */
    private List<SubTreeBean> children;
    private int courseId;
    private int id;
    private String name;
    private int order;
    private int parentChapterId;
    private int visible;

    public TreeBean() {
    }

    public TreeBean(List<SubTreeBean> children, int courseId, int id, String name, int order, int parentChapterId, int visible) {
        this.children = children;
        this.courseId = courseId;
        this.id = id;
        this.name = name;
        this.order = order;
        this.parentChapterId = parentChapterId;
        this.visible = visible;
    }

    public List<SubTreeBean> getChildren() {
        return children;
    }

    public void setChildren(List<SubTreeBean> children) {
        this.children = children;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getParentChapterId() {
        return parentChapterId;
    }

    public void setParentChapterId(int parentChapterId) {
        this.parentChapterId = parentChapterId;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    @Override
    public String toString() {
        return "TreeBean{" +
                "children=" + children +
                ", courseId=" + courseId +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", order=" + order +
                ", parentChapterId=" + parentChapterId +
                ", visible=" + visible +
                '}';
    }

    public class SubTreeBean implements Serializable {
        private static final long serialVersionUID = -4197365376668437194L;
        private List<?> children;
        private int courseId;
        private int id;
        private String name;
        private int order;
        private int parentChapterId;
        private int visible;

        public SubTreeBean() {
        }

        public SubTreeBean(List<?> children, int courseId, int id, String name, int order, int parentChapterId, int visible) {
            this.children = children;
            this.courseId = courseId;
            this.id = id;
            this.name = name;
            this.order = order;
            this.parentChapterId = parentChapterId;
            this.visible = visible;
        }

        public List<?> getChildren() {
            return children;
        }

        public void setChildren(List<?> children) {
            this.children = children;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public int getParentChapterId() {
            return parentChapterId;
        }

        public void setParentChapterId(int parentChapterId) {
            this.parentChapterId = parentChapterId;
        }

        public int getVisible() {
            return visible;
        }

        public void setVisible(int visible) {
            this.visible = visible;
        }

        @Override
        public String toString() {
            return "SubTreeBean{" +
                    "children=" + children +
                    ", courseId=" + courseId +
                    ", id=" + id +
                    ", name='" + name + '\'' +
                    ", order=" + order +
                    ", parentChapterId=" + parentChapterId +
                    ", visible=" + visible +
                    '}';
        }
    }

}
