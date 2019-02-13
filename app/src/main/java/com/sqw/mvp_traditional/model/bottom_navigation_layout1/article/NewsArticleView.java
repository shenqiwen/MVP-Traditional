package com.sqw.mvp_traditional.model.bottom_navigation_layout1.article;

import android.os.Bundle;
import android.view.View;

import com.sqw.mvp_traditional.Register;
import com.sqw.mvp_traditional.model.base.BaseListFragment;

import java.util.List;

import me.drakeet.multitype.MultiTypeAdapter;

public class NewsArticleView extends BaseListFragment<NewsArticleContract.Presenter> implements NewsArticleContract.View {

    private static final String TAG = "NewsArticleView";
    private String categoryId;

    public static NewsArticleView newInstance(String categoryId) {
        Bundle bundle = new Bundle();
        bundle.putString(TAG, categoryId);
        NewsArticleView view = new NewsArticleView();
        view.setArguments(bundle);
        return view;
    }

    @Override
    protected void initData() {
        categoryId = getArguments().getString(TAG);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        adapter = new MultiTypeAdapter(oldItems);
        Register.registerNewsArticleItem(adapter);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void fetchData() {
        onLoadData();
    }

    @Override
    public void onLoadData() {
        presenter.doSetChannelCategory(categoryId);
        onShowLoading();
    }

    @Override
    public void onSetAdapter(final List<?> list) {
        oldItems.addAll(list);
        adapter.notifyDataSetChanged();
        /**
         * https://medium.com/@hanru.yeh/recyclerview-and-appbarlayout-behavior-changed-in-v26-0-x-d9eb4de78fc0
         * support libraries v26 增加了 RV 惯性滑动，当 root layout 使用了 AppBarLayout Behavior 就会自动生效
         * 因此需要手动停止滑动
         */
     //   recyclerView.stopScroll();
    }

    @Override
    public void setPresenter(NewsArticleContract.Presenter presenter) {
        if (null == presenter) {
            this.presenter = new NewsArticlePresenter(this);
        }
    }
}
