package com.example.jiangchuanfa.projecttraining.controller.fragment.BrandInfoFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.jiangchuanfa.projecttraining.R;
import com.example.jiangchuanfa.projecttraining.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by crest on 2017/7/16.
 */

public class StoryBrandFragment extends BaseFragment {
    private static final String TAG = StoryBrandFragment.class.getSimpleName();
    @BindView(R.id.tv_brand_desc)
    TextView tvBrandDesc;
    Unbinder unbinder;
    private String brand_desc;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_story_brand, null);
        Bundle bundle = getArguments();//从activity传过来的Bundle
        if (bundle != null) {
            brand_desc = bundle.getString("brand_desc");
        }
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        tvBrandDesc.setText(brand_desc);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
