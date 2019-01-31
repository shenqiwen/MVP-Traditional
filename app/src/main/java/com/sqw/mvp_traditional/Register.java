package com.sqw.mvp_traditional;

import android.support.annotation.NonNull;

import com.sqw.mvp_traditional.bean.entity.MultiNewsArticleDataBean;
import com.sqw.mvp_traditional.binder.NewsArticleTextViewBinder;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * MultiType 库的Adapter的注册工具类
 */

public class Register {

    public static void registerNewsArticleItem(@NonNull MultiTypeAdapter adapter) {
        adapter.register(MultiNewsArticleDataBean.class,new NewsArticleTextViewBinder());
    }
}
