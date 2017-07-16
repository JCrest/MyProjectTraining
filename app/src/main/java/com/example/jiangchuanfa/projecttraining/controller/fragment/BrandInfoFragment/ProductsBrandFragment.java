package com.example.jiangchuanfa.projecttraining.controller.fragment.BrandInfoFragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.example.jiangchuanfa.projecttraining.R;
import com.example.jiangchuanfa.projecttraining.base.BaseFragment;
import com.example.jiangchuanfa.projecttraining.controller.adapter.ProductsBrandAdapter;
import com.example.jiangchuanfa.projecttraining.modle.bean.BrandInfosBean;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by crest on 2017/7/16.
 */

public class ProductsBrandFragment extends BaseFragment {

    private static final String TAG = ProductsBrandFragment.class.getSimpleName();
    @BindView(R.id.product_brand_fragment)
    XRecyclerView productBrandFragment;
    Unbinder unbinder;
    private String json;
    private ProductsBrandAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_products_brand_fragment, null);

        Bundle bundle = getArguments();//从activity传过来的Bundle
        if (bundle != null) {
            json = bundle.getString("json");
        }
        unbinder = ButterKnife.bind(this, view);
        processData(json);
        return view;

    }

    private void processData(String json) {
        BrandInfosBean brandInfosBean = new Gson().fromJson(json, BrandInfosBean.class);
        //设置RecyclerView的适配器
        adapter = new ProductsBrandAdapter(context, brandInfosBean.getData().getItems());
        productBrandFragment.setAdapter(adapter);
        //设置布局管理器
        productBrandFragment.setLayoutManager(new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false));
//        adapter.refresh(goodsListBean.getData().getItems());

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
