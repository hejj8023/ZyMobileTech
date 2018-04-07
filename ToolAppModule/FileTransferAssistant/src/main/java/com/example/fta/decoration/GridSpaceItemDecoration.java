package com.example.fta.decoration;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by zzg on 2018/4/7.
 * GridLayoutManager 分割线
 */

public class GridSpaceItemDecoration extends RecyclerView.ItemDecoration {
    private final int space;

    public GridSpaceItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State
            state) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            if (gridLayoutManager != null) {
                // 判断总的数量是否可以整除
                int totalCount = gridLayoutManager.getItemCount();
                int surplusCount = totalCount % gridLayoutManager.getSpanCount();
                int childPosition = parent.getChildAdapterPosition(view);
                if (gridLayoutManager.getOrientation() == GridLayoutManager.VERTICAL) {
                    if (surplusCount == 0 && childPosition > totalCount - gridLayoutManager
                            .getSpanCount() - 1) {
                        // 后面几项需要bottom
                        outRect.bottom = space;
                    } else if (surplusCount != 0 && childPosition > totalCount - surplusCount - 1) {
                        outRect.bottom = space;
                    }

                    // 被带队的需要右边
                    if ((childPosition + 1) % gridLayoutManager.getSpanCount() == 0) {
                        outRect.right = space;
                    }
                    outRect.top = space;
                    outRect.left = space;
                } else {
                    if (surplusCount == 0 && childPosition > totalCount - gridLayoutManager
                            .getSpanCount() - 1) {
                        // 后面几项需要右边
                        outRect.right = space;
                    } else if (surplusCount != 0 && childPosition > totalCount - surplusCount - 1) {
                        outRect.right = space;
                    }

                    // 被带队的需要下边
                    if ((childPosition + 1) % gridLayoutManager.getSpanCount() == 0) {
                        outRect.bottom = space;
                    }
                    outRect.top = space;
                    outRect.left = space;
                }
            }
        }
    }
}
