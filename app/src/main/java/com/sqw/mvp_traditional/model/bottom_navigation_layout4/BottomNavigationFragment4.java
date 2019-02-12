package com.sqw.mvp_traditional.model.bottom_navigation_layout4;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sqw.mvp_traditional.R;

/**
 * 底部导航栏 4
 */
public class BottomNavigationFragment4 extends Fragment {

    private final String TAG = getClass().getSimpleName();
    private static BottomNavigationFragment4 instance = null;

    public static BottomNavigationFragment4 getInstance() {
        if (instance == null) {
            instance = new BottomNavigationFragment4();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_navigation4, container, false);
        initView(view);
        initData();
        return view;
    }


    private void initData() {

    }


    private void initView(View view) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (instance != null) {
            instance = null;
        }
    }
}
