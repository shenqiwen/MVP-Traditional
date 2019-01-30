package com.sqw.mvp_traditional.bean.entity;

import android.support.annotation.NonNull;

/**
 * 频道编辑 实体类
 */

public class ChannelEditBean implements Comparable<ChannelEditBean> {

    private String channelId;
    private String channelName;
    private int isEnable;
    private int position;

    public ChannelEditBean() {
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public int getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(int isEnable) {
        this.isEnable = isEnable;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        ChannelEditBean bean = (ChannelEditBean) o;

        if (isEnable != bean.isEnable)
            return false;
        if (position != bean.position)
            return false;
        if (channelId != null ? !channelId.equals(bean.channelId) : bean.channelId != null)
            return false;
        return channelName != null ? channelName.equals(bean.channelName) : bean.channelName == null;

    }

    @Override
    public int hashCode() {
        int result = channelId != null ? channelId.hashCode() : 0;
        result = 31 * result + (channelName != null ? channelName.hashCode() : 0);
        result = 31 * result + isEnable;
        result = 31 * result + position;
        return result;
    }

    @Override
    public int compareTo(@NonNull ChannelEditBean o) {
        return this.position - o.getPosition();
    }
}
