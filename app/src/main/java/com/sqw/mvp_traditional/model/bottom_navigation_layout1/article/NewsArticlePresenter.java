package com.sqw.mvp_traditional.model.bottom_navigation_layout1.article;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.google.gson.Gson;
import com.sqw.mvp_traditional.bean.entity.MultiNewsArticleDataBean;
import com.sqw.mvp_traditional.utils.TimeUtil;

import java.util.ArrayList;
import java.util.List;


public class NewsArticlePresenter implements NewsArticleContract.Presenter {

    private final String TAG = getClass().getSimpleName();
    private NewsArticleContract.View view;
    private String mCategory;
    private Gson gson = new Gson();

    public NewsArticlePresenter(NewsArticleContract.View view) {
        this.view = view;
    }

    @Override
    public void doSetChannelCategory(String... category) {
        if (this.mCategory == null) {
            this.mCategory = category[0];
        }
    }

    @Override
    public void doLoadData() {
        // 模拟加载网络数据网络
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    Fragment fragment = (Fragment) view;
                    Activity mainActivity = fragment.getActivity();
                    mainActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            List<MultiNewsArticleDataBean> tempDataList = new ArrayList<>();
                            for (int i = 0; i < 50; i++) {
                                String time = TimeUtil.getCurrentMilliSecondTimeStamp();
                                tempDataList.add(new MultiNewsArticleDataBean("新闻标题: "+i,"新闻内容: "+i,"时间: "+time,"频道类型: "+mCategory));
                            }
                            doSetAdapter(tempDataList);
                            view.onHideLoading();
                        }
                    });

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void doLoadMoreData() {
        // 模拟加载网络数据网络
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    Fragment fragment = (Fragment) view;
                    Activity mainActivity = fragment.getActivity();
                    mainActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            List<MultiNewsArticleDataBean> tempDataList = new ArrayList<>();
                            for (int i = 0; i < 50; i++) {
                                String time = TimeUtil.getCurrentMilliSecondTimeStamp();
                                tempDataList.add(new MultiNewsArticleDataBean("新闻标题: "+i,"新闻内容: "+i,"时间: "+time,"频道类型: "+mCategory));
                            }
                            doSetAdapter(tempDataList);
                            view.onHideLoadingMore();
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void doSetAdapter(List<?> list) {
        view.onSetAdapter(list);
    }

    @Override
    public void doRefresh() {
        doLoadData();
    }

    @Override
    public void doShowNetError() {
        view.onHideLoading();
        view.onShowNetError();
    }

    @Override
    public void doShowNoMore() {
        view.onHideLoading();
        view.onShowNoMore();
    }

}
