package com.sqw.mvp_traditional.model.base;

import java.util.List;

/**
 * 列表视图页接口 基类 继承于IBaseView
 * 相较于父接口IBaseView 增加了 设置适配器 没有更多 两个方法
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
     * 隐藏上拉刷新加载动画
     */
    void onHideLoadingMore();

    /**
     * 显示网络错误
     */
    void onShowNetError();

    /**
     * 设置 presenter
     */
    void setPresenter(T presenter);

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
