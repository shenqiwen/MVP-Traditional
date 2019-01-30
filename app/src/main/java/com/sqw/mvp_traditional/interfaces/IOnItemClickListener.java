package com.sqw.mvp_traditional.interfaces;

import android.view.View;


public interface IOnItemClickListener {

    /**
     * RecyclerView Item点击事件
     */
    void onClick(View view, int position);
}
