package com.sqw.mvp_traditional.utils;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;

import com.sqw.mvp_traditional.InitApp;
import com.sqw.mvp_traditional.R;


/**
 * 应用设置 工具类 通过SP 存储
 */

public class SettingUtil {

    public static SettingUtil getInstance() {
        return SettingsUtilInstance.instance;
    }

    private static final class SettingsUtilInstance {
        private static final SettingUtil instance = new SettingUtil();
    }

    // App 的用户设置 SP 存储
    private SharedPreferences setting = PreferenceManager.getDefaultSharedPreferences(InitApp.AppContext);

    //获取字体大小
    public int getTextSize() {
        return setting.getInt("textsize", 16);
    }

    //设置字体大小
    public void setTextSize(int textSize) {
        setting.edit().putInt("textsize", textSize).apply();
    }

    //是否是初次打开应用
    public boolean getIsFirstTime() {
        return setting.getBoolean("first_time", true);
    }

    // 设置是否是初次打开应用
    public void setIsFirstTime(boolean flag) {
        setting.edit().putBoolean("first_time", flag).apply();
    }

    /**
     * 获取主题颜色
     */
    public int getColor() {
        int defaultColor = InitApp.AppContext.getResources().getColor(R.color.colorPrimary);
        int color = setting.getInt("color", defaultColor);
        // ???
        if ((color != 0) && Color.alpha(color) != 255) {
            return defaultColor;
        }
        return color;
    }

    /**
     * 设置主题颜色
     */
    public void setColor(int color) {
        setting.edit().putInt("color", color).apply();
    }

    /**
     * 获取是否开启底部导航栏上色
     */
    public boolean getNavBar() {
        return setting.getBoolean("nav_bar", false);
    }
}
