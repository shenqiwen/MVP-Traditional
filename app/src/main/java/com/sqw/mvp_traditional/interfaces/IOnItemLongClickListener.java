package com.sqw.mvp_traditional.interfaces;

import android.view.View;

public interface IOnItemLongClickListener {

    /**
     * RecyclerView Item长按事件
     */
    void onLongClick(View view, int position);
}
