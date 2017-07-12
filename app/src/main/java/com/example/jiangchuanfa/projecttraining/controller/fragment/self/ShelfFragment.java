package com.example.jiangchuanfa.projecttraining.controller.fragment.self;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.jiangchuanfa.projecttraining.base.BaseFragment;

/**
 * Created by crest on 2017/7/6.
 */

public class ShelfFragment extends BaseFragment {
    private TextView textView;

    @Override
    public View initView() {
        textView = new TextView(getActivity());
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("个人");
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(25);
        textView.setGravity(Gravity.CENTER);
    }
}
