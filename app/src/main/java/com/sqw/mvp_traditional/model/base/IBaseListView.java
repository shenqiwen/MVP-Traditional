package com.sqw.mvp_traditional.model.base;

import java.util.List;

/**
 * 列表视图接口基类 继承于IBaseView
 * 适用于 列表视图页
 */

public interface IBaseListView<T> extends IBaseView<T> {

    /**
     * 显示下拉刷新加载动画
     */
    void onShowLoading();

    /**
     * 隐藏下拉刷新加载动画
     */
    void onHideLoading();

    /**
     * 隐藏上拉加载动画
     */
    void onHideLoadingMore();

    /**
     * 设置适配器
     */
    void onSetAdapter(List<?> list);

    /**
     * 没有更多
     */
    void onShowNoMore();
    /**
     * 双击刷新
     */
    void onDoubleClickRefresh();
}
