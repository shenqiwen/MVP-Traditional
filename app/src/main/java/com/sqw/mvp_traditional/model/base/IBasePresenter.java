package com.sqw.mvp_traditional.model.base;

/**
 * P层 普通视图接口基类
 */
public interface IBasePresenter {

    /**
     * 加载数据
     */
    void doLoadData();

    /**
     * 显示网络错误
     */
    void doShowNetError();

}
