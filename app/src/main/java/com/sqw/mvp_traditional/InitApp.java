package com.sqw.mvp_traditional;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Application
 */

public class InitApp extends Application {

    public static Context AppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        AppContext = getApplicationContext();

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
