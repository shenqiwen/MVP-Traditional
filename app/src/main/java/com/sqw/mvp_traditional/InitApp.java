package com.sqw.mvp_traditional;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.facebook.stetho.Stetho;
import com.github.yuweiguocn.library.greendao.MigrationHelper;
import com.sqw.mvp_traditional.db.MyOpenHelper;
import com.sqw.mvp_traditional.db.gen.DaoMaster;
import com.sqw.mvp_traditional.db.gen.DaoSession;
import com.tencent.bugly.crashreport.CrashReport;

import org.greenrobot.greendao.query.QueryBuilder;

/**
 * Application
 */

public class InitApp extends Application {

    public static Context AppContext;
    private static InitApp application ;
    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        AppContext = getApplicationContext();
        application = this;
      //  initBugly();
        initGreenDao();
        initStetho();
    }

    private void initStetho() {
        Stetho.initializeWithDefaults(this);
    }

    public static InitApp getInstance(){
        return application ;
    }

    private void initGreenDao() {
        MyOpenHelper mSQLiteOpenHelper = new MyOpenHelper(this, "MVPTraditional.db", null);
        DaoMaster mDaoMaster = new DaoMaster(mSQLiteOpenHelper.getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
    }

    //获取DaoSession，从而获取各个表的操作DAO类
    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    //获取DaoSession，从而获取各个表的操作DAO类
    public DaoSession getNewDaoSession() {
        //清空所有数据表的缓存
        mDaoSession.clear();
        return mDaoSession;
    }

    //是否开启Log
    private void setDebugMode(boolean flag) {
        //查看数据库更新版本时数据迁移的log
        MigrationHelper.DEBUG = flag;
        //数据库增删改查时的log
        QueryBuilder.LOG_SQL = flag;
        QueryBuilder.LOG_VALUES = flag;
    }

    private void initBugly() {
        CrashReport.initCrashReport(getApplicationContext(), "e0cd142900", false);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
