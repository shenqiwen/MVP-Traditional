package com.sqw.mvp_traditional.model.bottom_navigation_layout1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sqw.mvp_traditional.GlobalConstants;
import com.sqw.mvp_traditional.R;
import com.sqw.mvp_traditional.adapter.BasePagerAdapter;
import com.sqw.mvp_traditional.bean.entity.ChannelEditBean;
import com.sqw.mvp_traditional.bean.event.ChannelEditActivityRefreshEvent;
import com.sqw.mvp_traditional.database.dao.ChannelEditDao;
import com.sqw.mvp_traditional.model.base.BaseListFragment;
import com.sqw.mvp_traditional.model.bottom_navigation_layout1.article.NewsArticleView;
import com.sqw.mvp_traditional.model.bottom_navigation_layout1.channel.ChannelEditActivity;
import com.sqw.mvp_traditional.utils.SettingUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 底部导航栏 1
 */
public class BottomNavigationFragment1 extends Fragment {

    public  final String TAG = getClass().getSimpleName();
    private static BottomNavigationFragment1 instance = null;
    private ViewPager viewPager;
    private BasePagerAdapter adapter;
    private LinearLayout linearLayout;
    private ChannelEditDao dao = new ChannelEditDao();
    private List<Fragment> fragmentList;
    private List<String> titleList;
    private Map<String, Fragment> map = new HashMap<>();

    public static BottomNavigationFragment1 getInstance() {
        if (instance == null) {
            instance = new BottomNavigationFragment1();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_navigation1, container, false);
        initView(view);
        initData();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        linearLayout.setBackgroundColor(SettingUtil.getInstance().getColor());
    }

    private void initView(View view) {
        TabLayout tab_layout = view.findViewById(R.id.tab_layout_news);
        viewPager = view.findViewById(R.id.view_pager_news);
        // TabLayout 与 ViewPager 联动
        tab_layout.setupWithViewPager(viewPager);
        tab_layout.setTabMode(TabLayout.MODE_SCROLLABLE);
        ImageView add_channel_iv = view.findViewById(R.id.add_channel_iv);
        add_channel_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ChannelEditActivity.class));
            }
        });
        linearLayout = view.findViewById(R.id.header_layout);
        linearLayout.setBackgroundColor(SettingUtil.getInstance().getColor());
        EventBus.getDefault().register(this);
    }

    private void initData() {
        initTabs();
        adapter = new BasePagerAdapter(getChildFragmentManager(), fragmentList, titleList);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(15);
        // 获取NewsTabLayout 的事件
       // observable = RxBus.getInstance().register(NewsTabLayout.TAG);
        // 监听 NewsChannelActivity 的频道订阅操作的数据库保存 操作
//        observable.subscribe(new Consumer<Boolean>() {
//            @Override
//            public void accept(Boolean isRefresh) throws Exception {
//                if (isRefresh) {
//                    initTabs();
//                    adapter.recreateItems(fragmentList, titleList);
//                }
//            }
//        });
    }

    // 监听 ChannelEditActivity 的频道订阅操作的数据库保存 操作
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void accept(ChannelEditActivityRefreshEvent channelEditActivityRefreshEvent) {
        if (channelEditActivityRefreshEvent.isRefresh()) {
            initTabs();
            adapter.recreateItems(fragmentList, titleList);
        }
    }

    // 初始化Tab栏
    private void initTabs() {
        // 查询数据库 获取频道列表
        List<ChannelEditBean> channelList = dao.query(GlobalConstants.CHANNEL_EDIT_ENABLE);
        fragmentList = new ArrayList<>();
        titleList = new ArrayList<>();
        if (channelList.size() == 0) {
            dao.addInitData();
            channelList = dao.query(GlobalConstants.CHANNEL_EDIT_ENABLE);
        }

        for (ChannelEditBean bean : channelList) {

            Fragment fragment = null;
            String channelId = bean.getChannelId();

            if (map.containsKey(channelId)) { // 如果频道编辑页面没有添加新标签 刷新标签顺序
                fragmentList.add(map.get(channelId));
            } else {  // 如果频道编辑页面添加了新标签 则创建页面
                fragment = NewsArticleView.newInstance(channelId);
                fragmentList.add(fragment);
            }

            titleList.add(bean.getChannelName());

            if (fragment != null) {
                map.put(channelId, fragment);
            }
        }
    }

    // 双击刷新
    public void onDoubleClick() {
        if (titleList != null && titleList.size() > 0 && fragmentList != null && fragmentList.size() > 0) {
            int item = viewPager.getCurrentItem();
            ((BaseListFragment) fragmentList.get(item)).onDoubleClickRefresh();
        }
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        if (instance != null) {
            instance = null;
        }
        super.onDestroy();
    }
}
