package com.sqw.mvp_traditional;

import android.support.annotation.NonNull;

import com.sqw.mvp_traditional.bean.entity.LoadingBean;
import com.sqw.mvp_traditional.bean.entity.LoadingEndBean;
import com.sqw.mvp_traditional.bean.entity.MultiNewsArticleDataBean;
import com.sqw.mvp_traditional.binder.LoadingEndViewBinder;
import com.sqw.mvp_traditional.binder.LoadingViewBinder;
import com.sqw.mvp_traditional.binder.news.NewsArticleTextViewBinder;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * MultiType 库的Adapter的注册工具类
 */

public class Register {

    public static void registerNewsArticleItem(@NonNull MultiTypeAdapter adapter) {
        adapter.register(MultiNewsArticleDataBean.class,new NewsArticleTextViewBinder());
        adapter.register(LoadingBean.class, new LoadingViewBinder());
        adapter.register(LoadingEndBean.class, new LoadingEndViewBinder());
    }
}
