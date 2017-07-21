package com.example.jiangchuanfa.projecttraining.controller.fragment.shoppingcart;

import android.util.Log;
import android.view.View;

import com.example.jiangchuanfa.projecttraining.R;
import com.example.jiangchuanfa.projecttraining.base.BaseFragment;


/**
 * Created by crest on 2017/7/19.
 */

public class ShoppingCartFragment extends BaseFragment {

    private static final String TAG = ShoppingCartFragment.class.getSimpleName();

    @Override
    public View initView() {
        Log.e(TAG, "初始化购物车控件...");
        View view = View.inflate(context, R.layout.fragment_shopping_cart, null);
//        unbinder = ButterKnife.bind(this, view);
//        //设置tag
//        tvShopcartEdit.setTag(ACTION_EDIT);

        return view;

    }
}
