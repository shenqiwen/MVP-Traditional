package com.sqw.mvp_traditional.db.table;

import android.support.annotation.NonNull;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 频道信息表
 */

@Entity
public class ChannelEditTable implements Comparable<ChannelEditTable> {

    private String channelName;
    private String channelId;
    private Integer isEnable;
    private Integer position;

    @Generated(hash = 1750366763)
    public ChannelEditTable(String channelName, String channelId, Integer isEnable, Integer position) {
        this.channelName = channelName;
        this.channelId = channelId;
        this.isEnable = isEnable;
        this.position = position;
    }
    @Generated(hash = 173456035)
    public ChannelEditTable() {
    }
    

    public String getChannelName() {
        return this.channelName;
    }
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
    public String getChannelId() {
        return this.channelId;
    }
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
    public Integer getIsEnable() {
        return this.isEnable;
    }
    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }
    public Integer getPosition() {
        return this.position;
    }
    public void setPosition(Integer position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        ChannelEditTable bean = (ChannelEditTable) o;

        if (isEnable != null ? !isEnable.equals(bean.isEnable) : bean.isEnable != null)
            return false;
        if (position != null ? !position.equals(bean.position) : bean.position != null)
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
    public int compareTo(@NonNull ChannelEditTable o) {
        return this.position - o.getPosition();
    }

}
