package com.sqw.mvp_traditional.model.base;

/**
 * P层 接口基类
 */
public interface IBasePresenter {

    /**
     * 刷新数据
     */
    void doRefresh();

    /**
     * 加载更多
     */
    void doLoadMoreData();

    /**
     * 显示网络错误
     */
    void doShowNetError();
}
