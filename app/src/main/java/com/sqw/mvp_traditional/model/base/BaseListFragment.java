package com.sqw.mvp_traditional.model.base;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.sqw.mvp_traditional.R;
import com.sqw.mvp_traditional.bean.entity.LoadingEndBean;
import com.sqw.mvp_traditional.utils.SettingUtil;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * 碎片列表页基类 抽象类
 * 此层主要作用:
 *             实现上一层 懒加载类(抽象类LazyLoadFragment) 中定义的碎片页初次加载的回调方法
 *             实现上一层 碎片基类(抽象类BaseFragment) 中定义的抽象方法
 *             实现上一层 碎片基类(抽象类BaseFragment) 中实现的IBaseView 接口中定义的UI操作方法
 */

public abstract class BaseListFragment<T extends IBasePresenter> extends LazyLoadFragment<T> implements IBaseListView<T>, SwipeRefreshLayout.OnRefreshListener {

    public  final String TAG = getClass().getSimpleName();
    protected RecyclerView recyclerView;
    protected SwipeRefreshLayout swipeRefreshLayout;
    protected MultiTypeAdapter adapter;
    // 页面数据源 子类共享
    protected Items oldItems = new Items();
    // 是否可以加载更多的标记
    protected boolean canLoadMore = false;

    @Override
    protected int attachLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initView(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        swipeRefreshLayout = view.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(SettingUtil.getInstance().getColor());
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onShowLoading() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                // 展开上拉刷新动画
                swipeRefreshLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void onHideLoading() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                // 关闭上拉刷新动画
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    /**
     * 懒加载初次加载数据
     */
    @Override
    public void fetchData() {

    }

    /**
     * 网络异常
     */
    @Override
    public void onShowNetError() {
        Toast.makeText(getActivity(), R.string.network_error, Toast.LENGTH_SHORT).show();
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.setItems(new Items());
                adapter.notifyDataSetChanged();
                // 网络错误时 标记置为false
                canLoadMore = false;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        // 设置下拉刷新的按钮的颜色
        swipeRefreshLayout.setColorSchemeColors(SettingUtil.getInstance().getColor());
    }

    /**
     * 没有更多
     */
    @Override
    public void onShowNoMore() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (oldItems.size() > 0) { // 数据源有数据时 将无数据item添加到最后一项
                    Items newItems = new Items(oldItems);
                    newItems.remove(newItems.size() - 1);
                    newItems.add(new LoadingEndBean());
                    adapter.setItems(newItems);
                    adapter.notifyDataSetChanged();
                } else if (oldItems.size() == 0) { // 数据源无数据时 直接添加
                    oldItems.add(new LoadingEndBean());
                    adapter.setItems(oldItems);
                    adapter.notifyDataSetChanged();
                }
                // 没有更多时 标记置为false
                canLoadMore = false;
            }
        });
    }

    /**
     * 底部导航栏双击置顶
     * 置顶后再次双击刷新
     */
    @Override
    public void onRefresh() {
        // 加了一层判断 当第一个Item可见时才刷新
        int firstVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
        if (firstVisibleItemPosition == 0) {
            // 刷新数据
            presenter.doRefresh();
            return;
        }
        // 置顶滑动动画
        recyclerView.scrollToPosition(8);
        recyclerView.smoothScrollToPosition(0);
    }

}
