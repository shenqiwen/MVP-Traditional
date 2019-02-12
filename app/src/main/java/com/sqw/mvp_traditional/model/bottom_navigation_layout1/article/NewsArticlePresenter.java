package com.sqw.mvp_traditional.model.bottom_navigation_layout1.article;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.google.gson.Gson;
import com.sqw.mvp_traditional.bean.entity.MultiNewsArticleDataBean;
import com.sqw.mvp_traditional.utils.TimeUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class NewsArticlePresenter implements NewsArticleContract.Presenter {

    private static final String TAG = "NewsArticlePresenter";
    private NewsArticleContract.View view;
    // 数据源
    private List<MultiNewsArticleDataBean> dataList = new ArrayList<>();
    private String mCategory;
    private String time;
    private Gson gson = new Gson();

    public NewsArticlePresenter(NewsArticleContract.View view) {
        this.view = view;
        this.time = TimeUtil.getCurrentTimeStamp();
    }

    @Override
    public void doSetChannelCategory(String... category) {
        if (this.mCategory == null) {
            this.mCategory = category[0];
        }
    }

    @Override
    public void doLoadData() {

        // 释放内存
//        if (dataList.size() > 300) {
//            dataList.clear();
//        }

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
                                tempDataList.add(new MultiNewsArticleDataBean("标题"+i,"内容"+i,"时间"+i,mCategory));
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

        // 释放内存
//        if (dataList.size() > 300) {
//            dataList.clear();
//        }

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
                                tempDataList.add(new MultiNewsArticleDataBean("标题"+i,"内容"+i,"时间"+i,mCategory));
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
        dataList.addAll((Collection<? extends MultiNewsArticleDataBean>) list) ;
        view.onSetAdapter(dataList);
    }

    @Override
    public void doRefresh() {
        if (dataList.size() != 0) {
            dataList.clear();
            time = TimeUtil.getCurrentTimeStamp();
        }
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
