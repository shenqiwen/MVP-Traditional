package com.sqw.mvp_traditional.model.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sqw.mvp_traditional.R;
import com.sqw.mvp_traditional.utils.SettingUtil;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * 碎片列表页基类 抽象类
 * 此层主要作用:
 *             实现上一层 懒加载类(抽象类LazyLoadFragment) 中定义的碎片页初次加载的回调方法
 *             实现上一层 碎片基类(抽象类BaseFragment) 中定义的抽象方法
 *             实现上一层 碎片基类(抽象类BaseFragment) 接口(IBaseView)中定义的UI操作方法
 */

public abstract class BaseListFragment<T extends IBaseListPresent> extends LazyLoadFragment<T> implements IBaseListView<T>, OnRefreshListener,OnLoadMoreListener {

    public  final String TAG = getClass().getSimpleName();
    protected RecyclerView recyclerView;
    protected SmartRefreshLayout smartRefreshLayout;
    protected MultiTypeAdapter adapter;
    // 页面数据源 子类共享
    protected Items oldItems = new Items();

    @Override
    protected int attachLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initView(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        smartRefreshLayout = view.findViewById(R.id.refresh_layout);
        smartRefreshLayout.setPrimaryColors(SettingUtil.getInstance().getColor());
        smartRefreshLayout.setOnRefreshListener(this);
        smartRefreshLayout.setOnLoadMoreListener(this);
    }

    @Override
    public void onShowLoading() {
        smartRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                // 展开下拉刷新动画
                smartRefreshLayout.autoRefresh();
            }
        });
    }

    @Override
    public void onHideLoading() {
        smartRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                // 关闭下拉刷新动画
                smartRefreshLayout.finishRefresh();
            }
        });
    }

    @Override
    public void onHideLoadingMore() {
        smartRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                // 关闭上拉刷新动画
                smartRefreshLayout.finishLoadMore();
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
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        // 设置下拉刷新的按钮的颜色
        smartRefreshLayout.setPrimaryColors(SettingUtil.getInstance().getColor());
    }

    /**
     * 没有更多
     */
    @Override
    public void onShowNoMore() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (oldItems.size() > 0) {
                    adapter.setItems(oldItems);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    /**
     * 下拉刷新数据
     * @param refreshLayout
     */
    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        // 刷新数据
        presenter.doRefresh();
    }

    /**
     * 加载更多
     * @param refreshLayout
     */
    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        presenter.doLoadMoreData();
    }

    /**
     * 底部导航栏双击置顶
     * 置顶后再次双击刷新
     */
    @Override
    public void onDoubleClickRefresh() {
        // 加了一层判断 当第一个Item可见时才刷新
        int firstVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
        if (firstVisibleItemPosition == 0) {
            // 刷新数据
            onShowLoading();
            return;
        }
        // 置顶滑动动画
        recyclerView.scrollToPosition(8);
        recyclerView.smoothScrollToPosition(0);
    }

}
