package com.example.jiangchuanfa.projecttraining.controller.fragment.shop.viewfragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;

import com.example.jiangchuanfa.projecttraining.R;
import com.example.jiangchuanfa.projecttraining.activity.MainActivity;
import com.example.jiangchuanfa.projecttraining.base.BaseFragment;
import com.example.jiangchuanfa.projecttraining.controller.fragment.goodslistfragment.GoodsListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.example.jiangchuanfa.projecttraining.config.Api.SHOP_ALL_URL;

/**
 * Created by crest on 2017/7/6.
 */

public class GiftFragment extends BaseFragment {
    @BindView(R.id.iv_present_7)
    ImageView ivPresent7;
    @BindView(R.id.iv_present_1)
    ImageView ivPresent1;
    @BindView(R.id.iv_present_2)
    ImageView ivPresent2;
    @BindView(R.id.iv_present_3)
    ImageView ivPresent3;
    @BindView(R.id.iv_present_4)
    ImageView ivPresent4;
    @BindView(R.id.iv_present_5)
    ImageView ivPresent5;
    @BindView(R.id.iv_present_6)
    ImageView ivPresent6;
    @BindView(R.id.iv_remind)
    ImageView ivRemind;
    Unbinder unbinder;

    private int position;
    private GoodsListFragment goodsListFragment;

    @Override
    public View initView() {
        View view = View.inflate(getActivity(), R.layout.fragment_gift, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_present_7, R.id.iv_present_1, R.id.iv_present_2, R.id.iv_present_3, R.id.iv_present_4, R.id.iv_present_5, R.id.iv_present_6, R.id.iv_remind})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_present_7:
//                showToast("妈的，什么鬼？");
                position =0;
                popuGoodsListFragment(position);
                break;
            case R.id.iv_present_1:
//                showToast("妈的，什么鬼？");
                position =0;
                popuGoodsListFragment(position);
                break;
            case R.id.iv_present_2:
//                showToast("妈的，什么鬼？");
                position =1;
                popuGoodsListFragment(position);

                break;
            case R.id.iv_present_3:
//                showToast("妈的，什么鬼？");
                position =2;
                popuGoodsListFragment(position);

                break;
            case R.id.iv_present_4:
//                showToast("妈的，什么鬼？");
                position =3;
                popuGoodsListFragment(position);

                break;
            case R.id.iv_present_5:
//                showToast("妈的，什么鬼？");
                position =4;
                popuGoodsListFragment(position);

                break;
            case R.id.iv_present_6:
//                showToast("妈的，什么鬼？");
                position =5;
                popuGoodsListFragment(position);

                break;
            case R.id.iv_remind:
//                showToast("妈的，什么鬼？");
                position =6;
                popuGoodsListFragment(position);

                break;
        }
    }

    private void popuGoodsListFragment(int position) {
        MainActivity mainActivity = (MainActivity) context;
        goodsListFragment = new GoodsListFragment();
        Bundle bundle = new Bundle();
        position= (int) (Math.random()*19);
        bundle.putString("data", SHOP_ALL_URL[position]);
        GiftFragment.this.goodsListFragment.setArguments(bundle);
        FragmentTransaction transaction = mainActivity.getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fl_main, GiftFragment.this.goodsListFragment,"goodsListFragment");
        transaction.addToBackStack(null);
        transaction.commit();

    }


}
