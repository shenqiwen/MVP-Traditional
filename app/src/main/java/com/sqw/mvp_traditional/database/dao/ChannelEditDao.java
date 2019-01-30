package com.sqw.mvp_traditional.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.sqw.mvp_traditional.GlobalConstants;
import com.sqw.mvp_traditional.InitApp;
import com.sqw.mvp_traditional.R;
import com.sqw.mvp_traditional.bean.entity.ChannelEditBean;
import com.sqw.mvp_traditional.database.DatabaseHelper;
import com.sqw.mvp_traditional.database.table.ChannelEditTable;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库操作类
 */

public class ChannelEditDao {

    private SQLiteDatabase db;

    public ChannelEditDao() {
        this.db = DatabaseHelper.getDatabase();
    }

    public void addInitData() {
        String categoryId[] = InitApp.AppContext.getResources().getStringArray(R.array.mobile_news_id);
        String categoryName[] = InitApp.AppContext.getResources().getStringArray(R.array.mobile_news_name);
        for (int i = 0; i < 8; i++) {
            add(categoryId[i], categoryName[i], GlobalConstants.CHANNEL_EDIT_ENABLE, i);
        }
        for (int i = 8; i < categoryId.length; i++) {
            add(categoryId[i], categoryName[i], GlobalConstants.CHANNEL_EDIT_DISABLE, i);
        }
    }

    public boolean add(String channelId, String channelName, int isEnable, int position) {
        ContentValues values = new ContentValues();
        values.put(ChannelEditTable.ID, channelId);
        values.put(ChannelEditTable.NAME, channelName);
        values.put(ChannelEditTable.IS_ENABLE, isEnable);
        values.put(ChannelEditTable.POSITION, position);
        long result = db.insert(ChannelEditTable.TABLENAME, null, values);
        return result != -1;
    }

    public List<ChannelEditBean> query(int isEnable) {
        Cursor cursor = db.query(ChannelEditTable.TABLENAME, null, ChannelEditTable.IS_ENABLE + "=?", new String[]{isEnable + ""}, null, null, null);
        List<ChannelEditBean> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            ChannelEditBean bean = new ChannelEditBean();
            bean.setChannelId(cursor.getString(ChannelEditTable.ID_ID));
            bean.setChannelName(cursor.getString(ChannelEditTable.ID_NAME));
            bean.setIsEnable(cursor.getInt(ChannelEditTable.ID_ISENABLE));
            bean.setPosition(cursor.getInt(ChannelEditTable.ID_POSITION));
            list.add(bean);
        }
        cursor.close();
        return list;
    }

    public List<ChannelEditBean> queryAll() {
        Cursor cursor = db.query(ChannelEditTable.TABLENAME, null, null, null, null, null, null);
        List<ChannelEditBean> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            ChannelEditBean bean = new ChannelEditBean();
            bean.setChannelId(cursor.getString(ChannelEditTable.ID_ID));
            bean.setChannelName(cursor.getString(ChannelEditTable.ID_NAME));
            bean.setIsEnable(cursor.getInt(ChannelEditTable.ID_ISENABLE));
            bean.setPosition(cursor.getInt(ChannelEditTable.ID_POSITION));
            list.add(bean);
        }
        cursor.close();
        return list;
    }

    public void updateAll(List<ChannelEditBean> list) {
    }

    public boolean removeAll() {
        int result = db.delete(ChannelEditTable.TABLENAME, null, null);
        return result != -1;
    }
}
