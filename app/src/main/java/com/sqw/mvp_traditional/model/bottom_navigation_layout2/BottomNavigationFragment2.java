package com.sqw.mvp_traditional.model.bottom_navigation_layout2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sqw.mvp_traditional.R;

/**
 * 底部导航栏 2
 */
public class BottomNavigationFragment2 extends Fragment {

    private final String TAG = getClass().getSimpleName();
    private static BottomNavigationFragment2 instance = null;

    public static BottomNavigationFragment2 getInstance() {
        if (instance == null) {
            instance = new BottomNavigationFragment2();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_navigation2, container, false);
        initView(view);
        initData();
        return view;
    }


    private void initView(View view) {

    }

    private void initData() {

    }

    @Override
    public void onDestroy() {
        if (instance != null) {
            instance = null;
        }
        super.onDestroy();
    }
}
