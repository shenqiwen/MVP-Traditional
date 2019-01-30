package com.sqw.mvp_traditional.model.base;

import java.util.List;

/**
 * 列表视图页接口 基类 继承于IBaseView
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
     * 显示网络错误
     */
    void onShowNetError();

    /**
     * 设置适配器
     */
    void onSetAdapter(List<?> list);

    /**
     * 没有更多
     */
    void onShowNoMore();
}
