package com.sqw.mvp_traditional.model.bottom_navigation_layout1.article;


import com.sqw.mvp_traditional.bean.entity.MultiNewsArticleDataBean;
import com.sqw.mvp_traditional.model.base.IBaseListView;
import com.sqw.mvp_traditional.model.base.IBasePresenter;

import java.util.List;

public interface NewsArticleContract {

    interface View extends IBaseListView<Presenter> {

        /**
         * 加载数据
         */
        void onLoadData();

        /**
         * 刷新
         */
        void onRefresh();
    }

    interface Presenter extends IBasePresenter {

        /**
         * 加载数据
         */
        void doLoadData(String... category);

        /**
         * 加载更多
         */
        void doLoadMoreData();

        /**
         * 设置适配器
         */
        void doSetAdapter(List<MultiNewsArticleDataBean> dataBeen);

        /**
         * 加载完毕
         */
        void doShowNoMore();
    }
}
