package com.example.jiangchuanfa.projecttraining.controller.fragment.goodsinfofragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.jiangchuanfa.projecttraining.R;
import com.example.jiangchuanfa.projecttraining.activity.AftermarketInformationActivity;
import com.example.jiangchuanfa.projecttraining.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by crest on 2017/7/11.
 */

public class BuyerReadingFragment extends BaseFragment {
    @BindView(R.id.btn_buyer_reading)
    Button btnBuyerReading;
    Unbinder unbinder;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_buyer_reading, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_buyer_reading)
    public void onViewClicked() {
        Intent intent = new Intent(context, AftermarketInformationActivity.class);
        context.startActivity(intent);
    }
}
