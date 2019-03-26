package com.sqw.mvp_traditional.widget;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.lxj.xpopup.core.DrawerPopupView;
import com.sqw.mvp_traditional.R;
import com.sqw.mvp_traditional.interfaces.CustomClickListener;

import gdut.bsx.share2.Share2;
import gdut.bsx.share2.ShareContentType;

/**
 * 自定义抽屉弹窗
 */
public class CustomDrawerPopupView extends DrawerPopupView  {

    private Context mContext;

    public CustomDrawerPopupView(@NonNull Context context) {
        super(context);
        this.mContext = context ;
    }
    @Override
    protected int getImplLayoutId() {
        return R.layout.drawer_layout;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        // 此处可以获取自定义的抽屉布局控件 设置点击事件
         findViewById(R.id.lin_switch_night_mode).setOnClickListener(new CustomClickListener() {
             @Override
             protected void onSingleClick() {
                 Toast.makeText(getContext(), R.string.nav_switch_night_mode, Toast.LENGTH_SHORT).show();
             }

             @Override
             protected void onFastClick() {

             }
         });
         findViewById(R.id.lin_setting).setOnClickListener(new CustomClickListener() {
             @Override
             protected void onSingleClick() {
                 Toast.makeText(getContext(), R.string.nav_setting, Toast.LENGTH_SHORT).show();
             }

             @Override
             protected void onFastClick() {

             }
         });
         findViewById(R.id.lin_share).setOnClickListener(new CustomClickListener() {
             @Override
             protected void onSingleClick() {
                 //Toast.makeText(getContext(), R.string.nav_share, Toast.LENGTH_SHORT).show();
                 if (mContext instanceof Activity) {
                     dismissWith(new Runnable() {
                         @Override
                         public void run() {
                             new Share2.Builder((Activity) mContext)
                                     .setContentType(ShareContentType.TEXT)
                                     .setTextContent(getResources().getString(R.string.share_app_text)+getResources().getString(R.string.source_code_url))
                                     .setTitle(getResources().getString(R.string.share_to))
                                     .build()
                                     .shareBySystem();
                         }
                     });
                 }
             }

             @Override
             protected void onFastClick() {

             }
         });
    }
}