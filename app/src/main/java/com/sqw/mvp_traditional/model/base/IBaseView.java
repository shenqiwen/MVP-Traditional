package com.sqw.mvp_traditional.model.base;


/**
 * 普通视图接口基类
 * 适用于 普通视图页
 */

public interface IBaseView<T> {

    /**
     * 加载数据
     */
    void onLoadData();

    /**
     * 显示网络错误
     */
    void onShowNetError();

    /**
     * 设置 presenter
     */
    void setPresenter(T presenter);
}
