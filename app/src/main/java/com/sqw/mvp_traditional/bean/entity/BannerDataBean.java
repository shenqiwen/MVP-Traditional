package com.sqw.mvp_traditional.bean.entity;

import com.stx.xhb.xbanner.entity.SimpleBannerInfo;

/**
 * 轮播图实体类
 */

public class BannerDataBean extends SimpleBannerInfo {

    public int pic;
    public String title;

    public BannerDataBean( String title,int pic) {
        this.pic = pic;
        this.title = title;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public Object getXBannerUrl() {
        return pic;
    }

    @Override
    public String getXBannerTitle() {
        return title;
    }
}
