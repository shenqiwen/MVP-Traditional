package com.sqw.mvp_traditional.model.bottom_navigation_layout3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sqw.mvp_traditional.R;

/**
 * 底部导航栏 3
 */
public class BottomNavigationFragment3 extends Fragment {

    private static BottomNavigationFragment3 instance = null;

    public static BottomNavigationFragment3 getInstance() {
        if (instance == null) {
            instance = new BottomNavigationFragment3();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_navigation3, container, false);
        initView(view);
        initData();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
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
