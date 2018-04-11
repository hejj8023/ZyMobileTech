package com.example.wanandroid.inter;

/**
 * Created by ubt on 2018/4/11.
 */

public interface OnArticleListItemClickListener {
    void onItemClick(String title, String url);

    void onCollectClick(int pos, int id, int originId);

    void onCollectClick(int pos, int id);

    void onTreeClick(int chapterId, String chapterName);
}
