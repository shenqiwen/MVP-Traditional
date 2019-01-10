package com.sqw.mvp_traditional.model.base;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.sqw.mvp_traditional.utils.SettingUtil;

/**
 * 活动页基类
 */
public abstract class BaseActivity extends AppCompatActivity {

    private  final String TAG = getClass().getSimpleName();

    /**
     * 对子类提供初始化ToolBar的方法
     * 初始化 Toolbar
     */
    protected void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title) {
        // 设置 ToolBar 标题
        toolbar.setTitle(title);
        // 设置 ToolBar
        setSupportActionBar(toolbar);
        // 获取ToolBar 的实例 并设置是否显示导航按钮
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
        // 设置导航按钮图标
        //getSupportActionBar().setHomeAsUpIndicator();
    }

    @Override
    protected void onResume() {
        super.onResume();
        /**
         * 获取 SP 存储的 用户个人设置(主题颜色)
         * 并设置 ToolBar ,系统状态栏 ,底部导航栏
         */
        int color = SettingUtil.getInstance().getColor();
        // 设置ToolBar 的背景色 与主题一致
        if (getSupportActionBar() != null) {
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(color));
        }
        /**
         * Android 5.0(L) 新增API:
         * - setStatusBarColor: 设置顶部状态栏颜色,是5.0 新加入的方法
         *
         * - setNavigationBarColor :设置底部导航栏颜色
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 设置 系统状态栏 颜色 与主题保持一致
            getWindow().setStatusBarColor(color);
            // 设置底部导航栏 颜色值
            if (SettingUtil.getInstance().getNavBar()) {
                getWindow().setNavigationBarColor(color);
            } else {
                getWindow().setNavigationBarColor(Color.BLACK);
            }
        }
    }

    /**
     * 顶部 标题栏ToolBar 左边 HomeAsUp 按钮的点击事件
     *
     * 注意:
     *      HomeAsUp 默认为一个返回箭头 含义是返回上一个活动可以通过
     *      其id 固定为 android.R.id.home
     *      setDisplayHomeAsUpEnabled 方法设置显隐
     *      setHomeAsUpIndicator 方法 设置Icon
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * -AppcompatActivity 是v7 包下的兼容性Activity继承于FragmentActivity
     *                    所以可以直接使用 getSupportFragmentManager 方法获取当前 Activity 的碎片管理类
     *
     * 注意:当碎片嵌套碎片时 需要使用 getChildFragmentManager 获取碎片管理类
     */
    @Override
    public void onBackPressed() {
        // 获取 回退栈中 碎片的数量
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            super.onBackPressed();
        } else {
            //使用popBackStack()将最上层的操作弹出回退栈。 使用回退之前 首先需要使用addToBackStack 将操作加入到回退栈
            // fragment 依次出栈 这里会从栈顶开始出栈
            getSupportFragmentManager().popBackStack();
        }
    }
}
