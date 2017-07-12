package com.example.jiangchuanfa.projecttraining.activity;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.jiangchuanfa.projecttraining.R;
import com.example.jiangchuanfa.projecttraining.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by crest on 2017/7/11.
 */

public class AftermarketInformationActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ib_search)
    ImageButton ibSearch;
    @BindView(R.id.ib_back)
    ImageButton ibBack;
    @BindView(R.id.ib_cart)
    ImageButton ibCart;

    @Override
    public void initListener() {
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    public void initData() {
        tvTitle.setText("售后须知");
        ibBack.setVisibility(View.VISIBLE);
        ibSearch.setVisibility(View.GONE);
        ibCart.setVisibility(View.GONE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_information_aftermarket;
    }


}
