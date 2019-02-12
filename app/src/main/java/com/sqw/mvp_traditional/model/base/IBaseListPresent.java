package com.sqw.mvp_traditional.model.base;

import java.util.List;

/**
 * P层 列表视图接口基类 继承于IBasePresent
 */

public interface IBaseListPresent extends IBasePresenter {

    /**
     * 刷新数据
     */
    void doRefresh();

    /**
     * 加载更多
     */
    void doLoadMoreData();

    /**
     * 设置适配器
     */
    void doSetAdapter(List<?> list);

    /**
     * 没有更多
     */
    void doShowNoMore();

}
