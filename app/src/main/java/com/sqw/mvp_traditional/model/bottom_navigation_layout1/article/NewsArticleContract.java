package com.sqw.mvp_traditional.model.bottom_navigation_layout1.article;


import com.sqw.mvp_traditional.model.base.IBaseListPresent;
import com.sqw.mvp_traditional.model.base.IBaseListView;

/**
 * 具体业务V 和 P 层需要定义的接口
 */

public interface NewsArticleContract {

    // 具体视图页预留接口
    interface View extends IBaseListView<Presenter> {

    }

    interface Presenter extends IBaseListPresent {
        /**
         * 设置频道类别
         */
        void doSetChannelCategory(String... category);

    }
}
