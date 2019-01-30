package com.sqw.mvp_traditional.model.base;


/**
 * View 视图接口基类
 */

public interface IBaseView<T> {
    /**
     * 设置 presenter
     */
    void setPresenter(T presenter);
}
