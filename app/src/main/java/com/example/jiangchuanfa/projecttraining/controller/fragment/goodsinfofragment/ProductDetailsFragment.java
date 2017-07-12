package com.example.jiangchuanfa.projecttraining.controller.fragment.goodsinfofragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.jiangchuanfa.projecttraining.R;
import com.example.jiangchuanfa.projecttraining.activity.GoodsInfoActivity;
import com.example.jiangchuanfa.projecttraining.base.BaseFragment;
import com.example.jiangchuanfa.projecttraining.config.Api;
import com.example.jiangchuanfa.projecttraining.controller.adapter.MyProductDetailAdapter;
import com.example.jiangchuanfa.projecttraining.modle.bean.GoodInfoBean;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;

/**
 * Created by crest on 2017/7/11.
 */

public class ProductDetailsFragment extends BaseFragment {

    private static final String TAG = ProductDetailsFragment.class.getSimpleName();
    Unbinder unbinder;
    @BindView(R.id.rv_product_details)
    RecyclerView rvProductDetails;

    private String url;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_new_product_details, null);
        unbinder = ButterKnife.bind(this, view);
        //获取随机一个分类网址

        return view;
    }



    @Override
    public void initData() {
        super.initData();
        GoodsInfoActivity goodsInfoActivity = (GoodsInfoActivity) context;
        Bundle bundle = goodsInfoActivity.getIntent().getExtras();
        String goods_id = bundle.getString("goods_id");
        url = Api.GOODS_DETAILS_HEAD + goods_id + Api.GOOD_DETAILS_TAIL;
        Log.e(TAG, "第一次---》加载数据应用到的网址：" + url);

        getDataFromNet(url);

    }

    private void getDataFromNet(String url) {
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "okhttp商品详情数据请求失败==" + e.getMessage());
                    }

                    @Override
                    public void onResponse(final String response, int id) {
                        Log.d(TAG, "第一次-----》这是getDataFromNet的联网数据: " + response);
                        processData(response);
                    }
                });

    }

    private void processData(String json) {
        GoodInfoBean goodsInfoBean = new Gson().fromJson(json, GoodInfoBean.class);
        rvProductDetails.setAdapter(new MyProductDetailAdapter(context,goodsInfoBean.getData().getItems()));
        rvProductDetails.setLayoutManager(new GridLayoutManager(context, 1));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
