package com.sqw.mvp_traditional.model;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.sqw.mvp_traditional.R;
import com.sqw.mvp_traditional.model.base.BaseActivity;
import com.sqw.mvp_traditional.model.bottom_navigation_layout1.BottomNavigationFragment1;
import com.sqw.mvp_traditional.model.bottom_navigation_layout2.BottomNavigationFragment2;
import com.sqw.mvp_traditional.model.bottom_navigation_layout3.BottomNavigationFragment3;
import com.sqw.mvp_traditional.model.bottom_navigation_layout4.BottomNavigationFragment4;
import com.sqw.mvp_traditional.widget.BottomNavigationViewHelper;


/**
 * 主界面
 */
public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private  final String TAG = getClass().getSimpleName();
    private static final String POSITION = "position";
    private static final String SELECT_ITEM = "bottomNavigationSelectItem";
    private static final int FRAGMENT_NAVIGATION1 = 0;
    private static final int FRAGMENT_NAVIGATION2 = 1;
    private static final int FRAGMENT_NAVIGATION3 = 2;
    private static final int FRAGMENT_NAVIGATION4 = 3;
    private BottomNavigationFragment1 bottomNavigationFragment1;
    private BottomNavigationFragment2 bottomNavigationFragment2;
    private BottomNavigationFragment3 bottomNavigationFragment3;
    private BottomNavigationFragment4 bottomNavigationFragment4;
    private Toolbar toolbar;
    private BottomNavigationView bottom_navigation;
    private long exitTime = 0;
    private long firstClickTime = 0;
    private int position;
    private NavigationView nav_view;
    private DrawerLayout drawer_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        if (savedInstanceState != null) {
            bottomNavigationFragment1 = (BottomNavigationFragment1) getSupportFragmentManager().findFragmentByTag(BottomNavigationFragment1.class.getName());
            bottomNavigationFragment2 = (BottomNavigationFragment2) getSupportFragmentManager().findFragmentByTag(BottomNavigationFragment2.class.getName());
            bottomNavigationFragment3 = (BottomNavigationFragment3) getSupportFragmentManager().findFragmentByTag(BottomNavigationFragment3.class.getName());
            bottomNavigationFragment4 = (BottomNavigationFragment4) getSupportFragmentManager().findFragmentByTag(BottomNavigationFragment4.class.getName());
            // 恢复 recreate 前的位置
            showFragment(savedInstanceState.getInt(POSITION));
            bottom_navigation.setSelectedItemId(savedInstanceState.getInt(SELECT_ITEM));
        } else {
            showFragment(FRAGMENT_NAVIGATION1);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // recreate 时记录当前位置 (在 Manifest 已禁止 Activity 旋转,所以旋转屏幕并不会执行以下代码)
        super.onSaveInstanceState(outState);
        outState.putInt(POSITION, position);
        outState.putInt(SELECT_ITEM, bottom_navigation.getSelectedItemId());
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        // 加载ToolBar 的menu布局
        toolbar.inflateMenu(R.menu.toolbar_menu);
        setSupportActionBar(toolbar);
        // 底部导航栏
        bottom_navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.disableShiftMode(bottom_navigation);
        // 底部导航栏 item监听
        bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bottom_navigation_menu1:
                        showFragment(FRAGMENT_NAVIGATION1);
                        doubleClick(FRAGMENT_NAVIGATION1);
                        break;
                    case R.id.bottom_navigation_menu2:
                        showFragment(FRAGMENT_NAVIGATION2);
                        doubleClick(FRAGMENT_NAVIGATION2);
                        break;
                    case R.id.bottom_navigation_menu3:
                        showFragment(FRAGMENT_NAVIGATION3);
                        doubleClick(FRAGMENT_NAVIGATION3);
                        break;
                    case R.id.bottom_navigation_menu4:
                        showFragment(FRAGMENT_NAVIGATION4);
                        break;
                }
                return true;
            }
        });
        // 抽屉栏
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //添加菜单拖动监听事件  根据菜单的拖动距离 将距离折算成旋转角度
        drawer_layout.addDrawerListener(toggle);
        //设置显示三横杠
        toggle.syncState();

        nav_view = (NavigationView) findViewById(R.id.nav_view);
        // 侧滑 导航栏监听
        nav_view.setNavigationItemSelectedListener(this);
    }
    // 双击刷新 处理
    public void doubleClick(int index) {
        long secondClickTime = System.currentTimeMillis();
        if ((secondClickTime - firstClickTime < 500)) { // 500 毫秒
            switch (index) {
                case FRAGMENT_NAVIGATION1:
                 //   bottomNavigationFragment1.onDoubleClick();
                    Toast.makeText(this,"双击刷新",Toast.LENGTH_SHORT).show();
                    break;
                case FRAGMENT_NAVIGATION2:
                 //   bottomNavigationFragment2.onDoubleClick();
                    Toast.makeText(this,"双击刷新",Toast.LENGTH_SHORT).show();
                    break;
                case FRAGMENT_NAVIGATION3:
                 //   bottomNavigationFragment3.onDoubleClick();
                    Toast.makeText(this,"双击刷新",Toast.LENGTH_SHORT).show();
                    break;
            }
        } else {
            firstClickTime = secondClickTime;
        }
    }

    private void showFragment(int index) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // 先 隐藏不为空的碎片页
        hideFragment(ft);
        position = index;
        switch (index) {
            case FRAGMENT_NAVIGATION1:
                toolbar.setTitle(R.string.bottom_navigation_title1);
                /**
                 * 如果Fragment为空，就新建一个实例
                 * 如果不为空，就将它从栈中显示出来
                 */
                if (bottomNavigationFragment1 == null) {
                    bottomNavigationFragment1 = BottomNavigationFragment1.getInstance();
                    ft.add(R.id.container, bottomNavigationFragment1, BottomNavigationFragment1.class.getName());
                } else {
                    ft.show(bottomNavigationFragment1);
                }
                break;

            case FRAGMENT_NAVIGATION2:
                toolbar.setTitle(R.string.bottom_navigation_title2);
                if (bottomNavigationFragment2 == null) {
                    bottomNavigationFragment2 = BottomNavigationFragment2.getInstance();
                    ft.add(R.id.container, bottomNavigationFragment2, BottomNavigationFragment2.class.getName());
                } else {
                    ft.show(bottomNavigationFragment2);
                }
                break;

            case FRAGMENT_NAVIGATION3:
                toolbar.setTitle(getString(R.string.bottom_navigation_title3));
                if (bottomNavigationFragment3 == null) {
                    bottomNavigationFragment3 = BottomNavigationFragment3.getInstance();
                    ft.add(R.id.container, bottomNavigationFragment3, BottomNavigationFragment3.class.getName());
                } else {
                    ft.show(bottomNavigationFragment3);
                }
                break;

            case FRAGMENT_NAVIGATION4:
                toolbar.setTitle(getString(R.string.bottom_navigation_title4));
                if (bottomNavigationFragment4 == null) {
                    bottomNavigationFragment4 = BottomNavigationFragment4.getInstance();
                    ft.add(R.id.container, bottomNavigationFragment4, BottomNavigationFragment4.class.getName());
                } else {
                    ft.show(bottomNavigationFragment4);
                }
        }

        ft.commit();
    }

    private void hideFragment(FragmentTransaction ft) {
        // 如果不为空，就先隐藏起来
        if (bottomNavigationFragment1 != null) {
            ft.hide(bottomNavigationFragment1);
        }
        if (bottomNavigationFragment2 != null) {
            ft.hide(bottomNavigationFragment2);
        }
        if (bottomNavigationFragment3 != null) {
            ft.hide(bottomNavigationFragment3);
        }
        if (bottomNavigationFragment4 != null) {
            ft.hide(bottomNavigationFragment4);
        }
    }

    /**
     * 返回键
     */
    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - exitTime) < 2000) { // 2秒
            super.onBackPressed();
        } else {
            Toast.makeText(this, R.string.double_click_exit, Toast.LENGTH_SHORT).show();
            exitTime = currentTime;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    // 顶部标题 菜单项被点击时
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_search) {
          //  startActivity(new Intent(MainActivity.this, SearchActivity.class));
            Toast.makeText(this,"搜索",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 侧滑导航栏 监听
     * @param item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_switch_night_mode:
//                int mode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
//                if (mode == Configuration.UI_MODE_NIGHT_YES) {
//                    SettingUtil.getInstance().setIsNightMode(false);
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//                } else {
//                    SettingUtil.getInstance().setIsNightMode(true);
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//                }
//                getWindow().setWindowAnimations(R.style.WindowAnimationFadeInOut);
//                recreate();
                Toast.makeText(this,"切换主题",Toast.LENGTH_SHORT).show();
                return false;

            case R.id.nav_setting:
//                startActivity(new Intent(this, SettingActivity.class));
//                drawer_layout.closeDrawers();
                Toast.makeText(this,"设置",Toast.LENGTH_SHORT).show();
                return false;

            case R.id.nav_share:
//                Intent shareIntent = new Intent()
//                        .setAction(Intent.ACTION_SEND)
//                        .setType("text/plain")
//                        .putExtra(Intent.EXTRA_TEXT, getString(R.string.share_app_text) + getString(R.string.source_code_url));
//                startActivity(Intent.createChooser(shareIntent, getString(R.string.share_to)));
//                drawer_layout.closeDrawers();
                Toast.makeText(this,"分享",Toast.LENGTH_SHORT).show();
                return false;
        }
        return false;
    }
}
