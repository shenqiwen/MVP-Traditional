package com.sqw.mvp_traditional.model.bottom_navigation_layout3;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sqw.mvp_traditional.R;
import com.sqw.mvp_traditional.adapter.QuickAdapter;
import com.sqw.mvp_traditional.bean.entity.BannerDataBean;
import com.sqw.mvp_traditional.bean.entity.MultiNewsArticleDataBean;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

/**
 * 底部导航栏 3
 */
public class BottomNavigationFragment3 extends Fragment {

    private static BottomNavigationFragment3 instance = null;
    private RecyclerView recyclerView;
    private SmartRefreshLayout refreshLayout;
    private QuickAdapter mAdapter;
    private XBanner mBanner;

    public static BottomNavigationFragment3 getInstance() {
        if (instance == null) {
            instance = new BottomNavigationFragment3();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_navigation3, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        refreshLayout = view.findViewById(R.id.refreshLayout);
        mAdapter = new QuickAdapter();
        recyclerView.setAdapter(mAdapter);
        //添加Header
        View header = LayoutInflater.from(getActivity()).inflate(R.layout.item_banner_header, recyclerView, false);
        mBanner = (XBanner) header;
        mBanner.setBannerData(R.layout.item_banner, BANNER_ITEMS);
        mAdapter.addHeaderView(mBanner);
        mAdapter.openLoadAnimation();
    }

    private void initData() {
        final List<MultiNewsArticleDataBean> datas = new Gson().fromJson(JOSN_DATAS, new TypeToken<ArrayList<MultiNewsArticleDataBean>>() {}.getType());
        mAdapter.replaceData(datas);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mAdapter.getItemCount() < 2) {
                            List<MultiNewsArticleDataBean> datas = new Gson().fromJson(JOSN_DATAS, new TypeToken<ArrayList<MultiNewsArticleDataBean>>() {}.getType());
                            mAdapter.replaceData(datas);
                        }
                        refreshLayout.finishRefresh();
                    }
                },2000);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mAdapter.addData(datas);
                refreshLayout.finishLoadMoreWithNoMoreData();
            }
        });

        mBanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                BannerDataBean bannerDataBean = (BannerDataBean) model;
                Glide.with(view).load(bannerDataBean.getXBannerUrl()).into((ImageView)view);
            }
        });

        mBanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, Object model, View view, int position) {
                Toast.makeText(getActivity(), "点击了第"+position+"图片", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public static List<BannerDataBean> BANNER_ITEMS = new ArrayList<BannerDataBean>(){{
        add(new BannerDataBean("最后的骑士", R.mipmap.image_movie_header_48621499931969370));
        add(new BannerDataBean("三生三世十里桃花", R.mipmap.image_movie_header_12981501221820220));
        add(new BannerDataBean("豆福传", R.mipmap.image_movie_header_12231501221682438));
    }};


    public static String JOSN_DATAS = "[{ \"Title\":\"新闻标题: 1\" , \"Content\": \"新闻内容: 1\",\n" +
            "            \"Time\" :\"时间: 1 \",\n" +
            "            \"Type\":\"无\"},\n" +
            "  { \"Title\":\"新闻标题: 2\" ,\n" +
            "            \"Content\": \"新闻内容: 2\",\n" +
            "            \"Time\" :\"时间: 2 \",\n" +
            "            \"Type\":\"无\"},\n" +
            "  { \"Title\":\"新闻标题: 3\" ,\n" +
            "            \"Content\": \"新闻内容: 3\",\n" +
            "            \"Time\" :\"时间: 3 \",\n" +
            "            \"Type\":\"无\"},\n" +
            "  { \"Title\":\"新闻标题: 4\" ,\n" +
            "            \"Content\": \"新闻内容: 4\",\n" +
            "            \"Time\" :\"时间: 4 \",\n" +
            "            \"Type\":\"无\"},\n" +
            "  { \"Title\":\"新闻标题: 5\" ,\n" +
            "            \"Content\": \"新闻内容: 5\",\n" +
            "            \"Time\" :\"时间: 5 \",\n" +
            "            \"Type\":\"无\"},  \n" +
            "{ \"Title\":\"新闻标题: 6\" ,\n" +
            "            \"Content\": \"新闻内容: 6\",\n" +
            "            \"Time\" :\"时间: 6\",\n" +
            "            \"Type\":\"无\"}\n" +
            "\n" +
            "]" ;

    @Override
    public void onDestroy() {
        if (instance != null) {
            instance = null;
        }
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        mBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        mBanner.stopAutoPlay();
    }
}
