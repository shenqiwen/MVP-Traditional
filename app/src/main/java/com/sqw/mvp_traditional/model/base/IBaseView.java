package com.sqw.mvp_traditional.model.base;


/**
 * View 视图接口基类
 * 主要用来定义 界面类的一些通用的UI操作
 */

public interface IBaseView<T> {

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
     * 设置 presenter
     */
    void setPresenter(T presenter);
}
