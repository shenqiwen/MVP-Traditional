package com.sqw.mvp_traditional.utils;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


public abstract class OnLoadMoreListener extends RecyclerView.OnScrollListener {

    private LinearLayoutManager layoutManager;
    private int itemCount, lastPosition, lastItemCount;

    public abstract void onLoadMore();

    /**
     * 此方法会在RecycleView滚动过程中不断回调
     * @param recyclerView
     * @param dx
     * @param dy
     */
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            // 布局管理器
            layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            // 当前item 的数量
            itemCount = layoutManager.getItemCount();
            // 当前显示的最底部的item 位置
            lastPosition = layoutManager.findLastCompletelyVisibleItemPosition();
        } else {
            Log.e("OnLoadMoreListener", "The OnLoadMoreListener only support LinearLayoutManager");
            return;
        }
        /**
         * 两个判断 1. 记录的上一次item数量 不等于 当前item的数量
         *        2. 当前显示的item的位置 等于 当前item的数量-1
         * 满足这两个判断才会回调
         */
        if (lastItemCount != itemCount && lastPosition == itemCount - 1) {
            lastItemCount = itemCount;
            this.onLoadMore();
        }
    }

//    @Override
//    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//        super.onScrollStateChanged(recyclerView, newState);
//        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
//            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                if (!recyclerView.canScrollVertically(1)) {
//                    this.onLoadMore();
//                }
//            }
//        }
//    }
}