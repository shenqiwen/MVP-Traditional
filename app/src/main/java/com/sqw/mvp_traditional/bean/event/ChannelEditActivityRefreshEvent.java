package com.sqw.mvp_traditional.bean.event;

/**
 * 频道编辑页面 刷新事件
 */

public class ChannelEditActivityRefreshEvent {
    private boolean isRefresh ;

    public ChannelEditActivityRefreshEvent(boolean isRefresh) {
        this.isRefresh = isRefresh;
    }

    public boolean isRefresh() {
        return isRefresh;
    }

    public void setRefresh(boolean refresh) {
        isRefresh = refresh;
    }
}
