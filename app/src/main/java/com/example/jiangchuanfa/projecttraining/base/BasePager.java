package com.example.jiangchuanfa.projecttraining.base;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.jiangchuanfa.projecttraining.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by crest on 2017/7/6.
 */

public class BasePager {
    public Context context;

    public View rootView;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_text)
    TextView tvText;
    @BindView(R.id.ib_search)
    ImageButton ibSearch;
    @BindView(R.id.ib_cart)
    ImageButton ibCart;
    @BindView(R.id.ib_navigation_menu)
    ImageButton ibNavigationMenu;
    @BindView(R.id.ib_setting)
    ImageButton ibSetting;
    @BindView(R.id.fl_content)
    FrameLayout flContent;


    public BasePager(final Context context) {
        this.context = context;
        rootView = View.inflate(context, R.layout.base_pager, null);
    }

    public void initData() {
    }


    @OnClick({R.id.ib_search, R.id.ib_cart, R.id.ib_navigation_menu, R.id.ib_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_search:
                break;
            case R.id.ib_cart:
                break;
            case R.id.ib_navigation_menu:
                break;
            case R.id.ib_setting:
                break;
        }
    }
}
