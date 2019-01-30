package com.sqw.mvp_traditional.model.bottom_navigation_layout1.channel;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import com.sqw.mvp_traditional.GlobalConstants;
import com.sqw.mvp_traditional.R;
import com.sqw.mvp_traditional.adapter.ChannelEditAdapter;
import com.sqw.mvp_traditional.bean.entity.ChannelEditBean;
import com.sqw.mvp_traditional.bean.event.ChannelEditActivityRefreshEvent;
import com.sqw.mvp_traditional.database.dao.ChannelEditDao;
import com.sqw.mvp_traditional.model.base.BaseActivity;
import com.sqw.mvp_traditional.widget.ItemDragHelperCallback;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * 频道编辑 界面
 */

public class ChannelEditActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private ChannelEditAdapter adapter;
    private ChannelEditDao dao = new ChannelEditDao();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel_edit);
        initView();
        initData();
    }

    @Override
    protected void onStop() {
        super.onStop();
        onSaveData();
    }

    private void initView() {
        initToolBar((Toolbar) findViewById(R.id.toolbar), true, getString(R.string.title_channel_edit));
        recyclerView = findViewById(R.id.recycler_view);
    }

    private void initData() {
        final List<ChannelEditBean> enableItems = dao.query(GlobalConstants.CHANNEL_EDIT_ENABLE);
        final List<ChannelEditBean> disableItems = dao.query(GlobalConstants.CHANNEL_EDIT_DISABLE);

        GridLayoutManager manager = new GridLayoutManager(this, 4);
        recyclerView.setLayoutManager(manager);

        ItemDragHelperCallback callback = new ItemDragHelperCallback();
        final ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerView);

        adapter = new ChannelEditAdapter(this, helper, enableItems, disableItems);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int viewType = adapter.getItemViewType(position);
                return viewType == ChannelEditAdapter.TYPE_MY || viewType == ChannelEditAdapter.TYPE_OTHER ? 1 : 4;
            }
        });
        recyclerView.setAdapter(adapter);

        adapter.setOnMyChannelItemClickListener(new ChannelEditAdapter.OnMyChannelItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(ChannelEditActivity.this, enableItems.get(position).getChannelName() + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onSaveData() {

//        Observable
//                .create(new ObservableOnSubscribe<Boolean>() {
//                    @Override
//                    public void subscribe(@NonNull ObservableEmitter<Boolean> e) throws Exception {
//                        List<NewsChannelBean> oldItems = dao.query(Constant.NEWS_CHANNEL_ENABLE);
//                        e.onNext(!compare(oldItems, adapter.getmMyChannelItems()));
//                    }
//                })
//                .subscribeOn(Schedulers.io())
//                .doOnNext(new Consumer<Boolean>() {
//                    @Override
//                    public void accept(@NonNull Boolean aBoolean) throws Exception {
//                        if (aBoolean) {
//                            List<NewsChannelBean> enableItems = adapter.getmMyChannelItems(); // 订阅的频道
//                            List<NewsChannelBean> disableItems = adapter.getmOtherChannelItems();// 未订阅的频道
//                            dao.removeAll();
//                            for (int i = 0; i < enableItems.size(); i++) {
//                                NewsChannelBean bean = enableItems.get(i);
//                                dao.add(bean.getChannelId(), bean.getChannelName(), Constant.NEWS_CHANNEL_ENABLE, i);
//                            }
//                            for (int i = 0; i < disableItems.size(); i++) {
//                                NewsChannelBean bean = disableItems.get(i);
//                                dao.add(bean.getChannelId(), bean.getChannelName(), Constant.NEWS_CHANNEL_DISABLE, i);
//                            }
//                        }
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Boolean>() {
//                    @Override
//                    public void accept(@NonNull Boolean isRefresh) throws Exception {  // isRefresh 数据库查询的订阅频道 保存操作成功失败与否
//                        RxBus.getInstance().post(NewsTabLayout.TAG, isRefresh);
//                    }
//                }, ErrorAction.error());

        List<ChannelEditBean> oldItems = dao.query(GlobalConstants.CHANNEL_EDIT_ENABLE);
        // 我的订阅频道是否变动
        boolean isRefresh = !compare(oldItems, adapter.getmMyChannelItems()) ;
        if (isRefresh){
            List<ChannelEditBean> enableItems = adapter.getmMyChannelItems(); // 订阅的频道
            List<ChannelEditBean> disableItems = adapter.getmOtherChannelItems();// 未订阅的频道
            dao.removeAll();
            for (int i = 0; i < enableItems.size(); i++) {
                ChannelEditBean bean = enableItems.get(i);
                dao.add(bean.getChannelId(), bean.getChannelName(), GlobalConstants.CHANNEL_EDIT_ENABLE, i);
            }
            for (int i = 0; i < disableItems.size(); i++) {
                ChannelEditBean bean = disableItems.get(i);
                dao.add(bean.getChannelId(), bean.getChannelName(), GlobalConstants.CHANNEL_EDIT_DISABLE, i);
            }
        }
        // 发送是否刷新事件
        EventBus.getDefault().post(new ChannelEditActivityRefreshEvent(isRefresh));
    }

    public synchronized <T extends Comparable<T>> boolean compare(List<T> a, List<T> b) {
        if (a.size() != b.size())
            return false;
//        Collections.sort(a);
//        Collections.sort(b);
        for (int i = 0; i < a.size(); i++) {
            if (!a.get(i).equals(b.get(i)))
                return false;
        }
        return true;
    }
}




