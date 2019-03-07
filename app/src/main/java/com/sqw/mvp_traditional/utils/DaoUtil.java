package com.sqw.mvp_traditional.utils;

import com.sqw.mvp_traditional.InitApp;
import com.sqw.mvp_traditional.db.gen.ChannelEditTableDao;

/**
 * 数据库表 操作工具类
 */

public class DaoUtil {

    public static ChannelEditTableDao getChannelEditTableDao(){
        return InitApp.getInstance().getDaoSession().getChannelEditTableDao();
    }

}
