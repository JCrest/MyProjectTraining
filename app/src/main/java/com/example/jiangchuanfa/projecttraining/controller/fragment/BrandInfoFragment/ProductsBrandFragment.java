package com.example.jiangchuanfa.projecttraining.controller.fragment.BrandInfoFragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.jiangchuanfa.projecttraining.base.BaseFragment;

/**
 * Created by crest on 2017/7/16.
 */

public class ProductsBrandFragment extends BaseFragment {
    @Override
    public View initView() {
        TextView textView = new TextView(context);
        textView.setTextSize(30);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.WHITE);

        textView.setText("我是品牌产品");
        return textView;
    }
}
