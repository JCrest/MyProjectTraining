package com.example.jiangchuanfa.projecttraining.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jiangchuanfa.projecttraining.R;
import com.example.jiangchuanfa.projecttraining.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by crest on 2017/7/19.
 */

public class ShoppingCartAcrivity extends BaseActivity {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ib_back)
    ImageButton ibBack;
    @BindView(R.id.tv_edit)
    TextView tvEdit;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.tv_money_off)
    TextView tvMoneyOff;
    @BindView(R.id.tv_discount)
    TextView tvDiscount;
    @BindView(R.id.tv_pack)
    TextView tvPack;
    @BindView(R.id.tv_postage)
    TextView tvPostage;
    @BindView(R.id.checkbox_all)
    CheckBox checkboxAll;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.tv_have_to_save)
    TextView tvHaveToSave;
    @BindView(R.id.tv_settlement)
    TextView tvSettlement;
    @BindView(R.id.ll_check_all)
    LinearLayout llCheckAll;

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

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_shopping_cart;
    }

}
