package com.sqw.mvp_traditional.model.base;

import android.os.Bundle;

/**
 * https://www.jianshu.com/p/c5d29a0c3f4c
 * 懒加载 的逻辑 控制抽象类
 */

public abstract class LazyLoadFragment<T> extends BaseFragment<T> {
    /**
     * 三个标记 界面是否创建  界面是否对用户可见  界面数据是否加载过
     */
    protected boolean isViewInitiated;
    protected boolean isVisibleToUser;
    protected boolean isDataInitiated;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewInitiated = true;
        prepareFetchData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        prepareFetchData();
    }

    /**
     * 实现抽象方法，在fetchData（）里做网络请求或者其他耗时操作
     */
    public abstract void fetchData();

    public boolean prepareFetchData() {
        return prepareFetchData(false);
    }

    public boolean prepareFetchData(boolean forceUpdate) {
        if (isVisibleToUser && isViewInitiated && (!isDataInitiated || forceUpdate)) {
            fetchData();
            isDataInitiated = true;
            return true;
        }
        return false;
    }

}