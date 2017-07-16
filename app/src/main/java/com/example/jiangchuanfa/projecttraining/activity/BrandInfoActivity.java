package com.example.jiangchuanfa.projecttraining.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jiangchuanfa.projecttraining.R;
import com.example.jiangchuanfa.projecttraining.base.BaseActivity;
import com.example.jiangchuanfa.projecttraining.config.Api;
import com.example.jiangchuanfa.projecttraining.controller.fragment.BrandInfoFragment.ProductsBrandFragment;
import com.example.jiangchuanfa.projecttraining.controller.fragment.BrandInfoFragment.StoryBrandFragment;
import com.example.jiangchuanfa.projecttraining.modle.bean.BrandInfosBean;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import okhttp3.Call;

public class BrandInfoActivity extends BaseActivity {


    private static final String TAG = BrandInfoActivity.class.getSimpleName();
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ib_search)
    ImageButton ibSearch;
    @BindView(R.id.ib_back)
    ImageButton ibBack;
    @BindView(R.id.ib_cart)
    ImageButton ibCart;
    @BindView(R.id.tv_brand_name)
    TextView tvBrandName;
    @BindView(R.id.rb_story_brand)
    RadioButton rbStoryBrand;
    @BindView(R.id.rb_products_brand)
    RadioButton rbProductsBrand;
    @BindView(R.id.rg_goods_info)
    RadioGroup rgGoodsInfo;
    @BindView(R.id.ll_fragment_container)
    LinearLayout llFragmentContainer;
    @BindView(R.id.iv_brand_logo)
    ImageView ivBrandLogo;

    private StoryBrandFragment storyBrandFragment;
    private ProductsBrandFragment productsBrandFragment;
    private String url;
    private String brand_id;

    @Override
    public void initView() {
        tvTitle.setText("品牌产品");
        ibSearch.setVisibility(View.GONE);
        ibBack.setVisibility(View.VISIBLE);
        ibCart.setVisibility(View.GONE);
        Intent intent = this.getIntent();
        brand_id = intent.getStringExtra("brand_id");
    }

    @Override
    public void initListener() {
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        rgGoodsInfo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                switchFragment(checkedId,"");
            }
        });

    }

    private void switchFragment(int checkedId,String a) {

        switch (checkedId) {
            case R.id.rb_story_brand:
                initStoryBrandFragment();
                break;
            case R.id.rb_products_brand:
                initProductsBrandFragment(a);
                break;
        }

    }

    private void initProductsBrandFragment(String json) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (productsBrandFragment == null) {
            productsBrandFragment = new ProductsBrandFragment();
            Bundle bundle = new Bundle();
            bundle.putString("json", json);
            this.productsBrandFragment.setArguments(bundle);
            Log.e(TAG, "initBuyerReadingFragment: 难道每次都要new吗？");
            transaction.add(R.id.ll_fragment_container, productsBrandFragment);
        }
        hideFragment(transaction);
        transaction.show(productsBrandFragment);
        rbProductsBrand.setBackgroundColor(getResources().getColor(R.color.clickableBackground));
        rbStoryBrand.setBackgroundColor(getResources().getColor(R.color.buyer_reading));
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (storyBrandFragment != null) {
            transaction.hide(storyBrandFragment);
        }
        if (productsBrandFragment != null) {
            transaction.hide(productsBrandFragment);
        }
    }

    private void initStoryBrandFragment() {

        //开启事务，fragment的控制是由事务来实现的
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //初始化fragment并添加到事务中，如果为null就new一个
        if (storyBrandFragment == null) {
            storyBrandFragment = new StoryBrandFragment();
            transaction.add(R.id.ll_fragment_container, storyBrandFragment);
//            transaction.addToBackStack(null);
        }
        //隐藏所有fragment
        hideFragment(transaction);
        //显示需要显示的fragment
        transaction.show(storyBrandFragment);
        rbStoryBrand.setBackgroundColor(getResources().getColor(R.color.clickableBackground));
        rbProductsBrand.setBackgroundColor(getResources().getColor(R.color.buyer_reading));
        //提交事务
        transaction.commit();

    }

    @Override
    public void initData() {
        url = Api.BRAND_INFO_HEAD_URL + brand_id + Api.BRAND_INFO_TAIL_URL;
        Log.e(TAG, "品牌详情地址url==" + url);
        getDataFromNet(url);


        rgGoodsInfo.check(R.id.rb_products_brand);
    }

    private void getDataFromNet(String url) {
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "okhttp商店品牌数据请求失败==" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, "okhttp商店品牌数据请求成功==" + response);
                        processData(response);
                        initProductsBrandFragment(response);

                    }
                });





    }

    private void processData(String json) {
        BrandInfosBean brandInfosBean = new Gson().fromJson(json, BrandInfosBean.class);
        String brand_desc = brandInfosBean.getData().getItems().get(0).getBrand_info().getBrand_desc();
        Log.e("TAG", "数组解析数据成功======" + brandInfosBean.getData().getItems().get(0).getBrand_info().getBrand_desc());
//        adapter.refresh(brandBean.getData().getItems());
        settingtital(brandInfosBean);
        byValue2Fragment(brand_desc);

    }

    private void byValue2Fragment(String brand_desc) {
        storyBrandFragment = new StoryBrandFragment();
        Bundle bundle = new Bundle();
        bundle.putString("brand_desc", brand_desc);
        this.storyBrandFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.ll_fragment_container, storyBrandFragment).commit();

    }

    private void settingtital(BrandInfosBean brandInfosBean) {
        tvBrandName.setText(brandInfosBean.getData().getItems().get(0).getBrand_info().getBrand_name());
        Glide.with(this).load(brandInfosBean.getData().getItems().get(0).getBrand_info().getBrand_logo()).into(ivBrandLogo);

    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_brand_info;
    }


}
