package com.sqw.mvp_traditional.model.bottom_navigation_layout1.article;


import com.sqw.mvp_traditional.bean.entity.MultiNewsArticleDataBean;
import com.sqw.mvp_traditional.model.base.IBaseListView;
import com.sqw.mvp_traditional.model.base.IBasePresenter;

import java.util.List;

public interface NewsArticleContract {

    interface View extends IBaseListView<Presenter> {

        /**
         * 具体业务界面需要定义的接口
         */
    }

    interface Presenter extends IBasePresenter {

        /**
         * 加载数据
         */
        void doLoadData(String... category);

        /**
         * 设置适配器
         */
        void doSetAdapter(List<MultiNewsArticleDataBean> dataBeen);

    }
}
