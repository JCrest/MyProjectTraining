package com.example.jiangchuanfa.projecttraining.controller.fragment.magazine;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.jiangchuanfa.projecttraining.base.BaseFragment;

/**
 * Created by crest on 2017/7/17.
 */

public class MagClassfilyFragment extends BaseFragment {
    @Override
    public View initView() {
        TextView textView = new TextView(context);
        textView.setText("怎么不出内容呢？？？？？？");
        textView.setTextColor(Color.BLUE);
        textView.setTextSize(25);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }
}
