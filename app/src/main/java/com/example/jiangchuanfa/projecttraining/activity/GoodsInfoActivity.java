package com.example.jiangchuanfa.projecttraining.activity;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jiangchuanfa.projecttraining.R;
import com.example.jiangchuanfa.projecttraining.base.BaseActivity;
import com.example.jiangchuanfa.projecttraining.config.Api;
import com.example.jiangchuanfa.projecttraining.controller.adapter.MyPageAdapter;
import com.example.jiangchuanfa.projecttraining.controller.fragment.goodsinfofragment.BuyerReadingFragment;
import com.example.jiangchuanfa.projecttraining.controller.fragment.goodsinfofragment.ProductDetailsFragment;
import com.example.jiangchuanfa.projecttraining.modle.bean.GoodInfoBean;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

//import com.example.jiangchuanfa.projecttraining.modle.bean.GoodsInfoBean;

/**
 * Created by crest on 2017/7/8.
 */

public class GoodsInfoActivity extends BaseActivity {


    private static final String TAG = GoodsInfoActivity.class.getSimpleName();
    @BindView(R.id.brand_name)
    TextView brandName;
    @BindView(R.id.goods_name)
    TextView goodsName;
    @BindView(R.id.promotion_note)
    TextView promotionNote;
    @BindView(R.id.discount_price)
    TextView discountPrice;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.tv_like_count)
    TextView tvLikeCount;
    @BindView(R.id.ll_collect)
    LinearLayout llCollect;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    @BindView(R.id.ll_size_choose)
    LinearLayout llSizeChoose;
    @BindView(R.id.brand_logo)
    ImageView brandLogo;
    @BindView(R.id.ll_brand_choose)
    LinearLayout llBrandChoose;
    @BindView(R.id.rb_product_details)
    RadioButton rbProductDetails;
    @BindView(R.id.rb_buyer_reading)
    RadioButton rbBuyerReading;
    @BindView(R.id.rg_goods_info)
    RadioGroup rgGoodsInfo;
    @BindView(R.id.refreshable_recycle_view)
    ScrollView refreshableRecycleView;
    @BindView(R.id.rl_after_sale)
    RelativeLayout rlAfterSale;
    @BindView(R.id.rl_add_cart)
    RelativeLayout rlAddCart;
    @BindView(R.id.rl_outright_purchase)
    RelativeLayout rlOutrightPurchase;
    @BindView(R.id.ib_back)
    ImageButton ibBack;
    @BindView(R.id.ib_cart)
    ImageButton ibCart;
    @BindView(R.id.brand_name2)
    TextView brandName2;
    @BindView(R.id.ll_fragment_container)
    LinearLayout llFragmentContainer;

    private String url;
    private ProductDetailsFragment productDetailsFragment;
    private BuyerReadingFragment buyerReadingFragment;

    private ViewPager viewPager;
    private LinearLayout ll_point_group;
    private ImageView iv_zhekou;
    private int prePosition;

    @Override
    public void initView() {
        initProductDetailsFragment();
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        iv_zhekou = (ImageView) findViewById(R.id.iv_zhekou);
        ll_point_group = (LinearLayout) findViewById(R.id.ll_point_group);


    }

    @Override
    public void initListener() {
        rgGoodsInfo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                switchFragment(checkedId);
            }
        });

        viewPager.addOnPageChangeListener(new MyOnPageChangeListener());
    }

    private void switchFragment(int checkedId) {

        switch (checkedId) {
            case R.id.rb_product_details:
                initProductDetailsFragment();
//                rbProductDetails.setTextColor(getResources().getColor(R.color.clickableBackground));
                break;
            case R.id.rb_buyer_reading:
                initBuyerReadingFragment();
//                rbProductDetails.setTextColor(getResources().getColor(R.color.buyer_reading));
                break;
        }

    }

    private void initBuyerReadingFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (buyerReadingFragment == null) {
            buyerReadingFragment = new BuyerReadingFragment();
            Log.e(TAG, "initBuyerReadingFragment: 难道每次都要new吗？" );
            transaction.add(R.id.ll_fragment_container, buyerReadingFragment);
//            transaction.addToBackStack(null);
        }
        hideFragment(transaction);
        transaction.show(buyerReadingFragment);
        rbBuyerReading.setBackgroundColor(getResources().getColor(R.color.clickableBackground));
        rbProductDetails.setBackgroundColor(getResources().getColor(R.color.buyer_reading));

        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (productDetailsFragment != null) {
            transaction.hide(productDetailsFragment);
        }
        if (buyerReadingFragment != null) {
            transaction.hide(buyerReadingFragment);
        }
    }

    private void initProductDetailsFragment() {
        //开启事务，fragment的控制是由事务来实现的
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //初始化fragment并添加到事务中，如果为null就new一个
        if (productDetailsFragment == null) {
            productDetailsFragment = new ProductDetailsFragment();
            transaction.add(R.id.ll_fragment_container, productDetailsFragment);
//            transaction.addToBackStack(null);
        }
        //隐藏所有fragment
        hideFragment(transaction);
        //显示需要显示的fragment
        transaction.show(productDetailsFragment);
        rbProductDetails.setBackgroundColor(getResources().getColor(R.color.clickableBackground));
        rbBuyerReading.setBackgroundColor(getResources().getColor(R.color.buyer_reading));

        //提交事务
        transaction.commit();
    }


    @Override
    public void initData() {
        Bundle bundle = this.getIntent().getExtras();
        String goods_id = bundle.getString("goods_id");
        url = Api.GOODS_DETAILS_HEAD + goods_id + Api.GOOD_DETAILS_TAIL;
        Log.e(TAG, "详情地址url==" + url);
        getDataFromNet(url);
        rgGoodsInfo.check(R.id.rb_product_details);

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
                        Log.d(TAG, "onResponse: " + response);
                        processData(response);
                    }
                });

    }

    private void processData(String json) {
        GoodInfoBean goodsInfoBean = new Gson().fromJson(json, GoodInfoBean.class);
        Log.e(TAG, "processData: 解析数据：" + goodsInfoBean.getData().getItems().getGoods_info().size());
        brandName.setText(goodsInfoBean.getData().getItems().getBrand_info().getBrand_name());
        goodsName.setText(goodsInfoBean.getData().getItems().getGoods_name());
        if (TextUtils.isEmpty(goodsInfoBean.getData().getItems().getPromotion_note())) {
            promotionNote.setVisibility(View.GONE);
        } else {
            promotionNote.setText(goodsInfoBean.getData().getItems().getPromotion_note());
        }
        if (!TextUtils.isEmpty(goodsInfoBean.getData().getItems().getSku_inv().get(0).getDiscount_price())) {
            discountPrice.setText(goodsInfoBean.getData().getItems().getSku_inv().get(0).getDiscount_price());
            discountPrice.setVisibility(View.VISIBLE);
            discountPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }
        price.setText(goodsInfoBean.getData().getItems().getSku_inv().get(0).getPrice());
        tvLikeCount.setText(goodsInfoBean.getData().getItems().getLike_count());
        Glide.with(this).load(goodsInfoBean.getData().getItems().getBrand_info().getBrand_logo()).into(brandLogo);
        brandName2.setText(goodsInfoBean.getData().getItems().getBrand_info().getBrand_name());

        viewPager.setAdapter(new MyPageAdapter(this,goodsInfoBean.getData().getItems().getImages_item()));
        //根据图片集合的元素的数量设置指示点
        for (int i = 0; i < goodsInfoBean.getData().getItems().getImages_item().size(); i++) {
            //指示点在android中本质上是ImageView（创建一个ImageView）
            ImageView imageView = new ImageView(GoodsInfoActivity.this);
            //在布局文件中已经设置了小指示点但还需要在代码中稍微调整一下，如果已经调好则可不需要这段（以下3行）
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-2, ViewGroup.LayoutParams.WRAP_CONTENT);
            Log.e(TAG, "processData: /*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*");
            params.leftMargin = 10;
            imageView.setLayoutParams(params);

            //给小指示点设置背景（这里用selector+shape方法）
            imageView.setBackgroundResource(R.drawable.point_selector);
            //这段代码类似于设置第一条文本，让其一打开app便可选择第一个点
            if (i == 0) {
                imageView.setSelected(true);
            } else {
                imageView.setSelected(false);
            }
            ll_point_group.addView(imageView);
        }






    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_goods_list;
    }

    @OnClick({R.id.ll_collect, R.id.iv_share, R.id.ll_size_choose, R.id.ll_brand_choose, R.id.rl_after_sale, R.id.rl_add_cart, R.id.ib_back, R.id.ib_cart})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_collect:
                showToast("这个是收藏");
                break;
            case R.id.iv_share:
                showToast("这个是分享");
                break;
            case R.id.ll_size_choose:
                showToast("选择尺寸");
                break;
            case R.id.ll_brand_choose:
                showToast("选择品牌");
                break;
            case R.id.rl_after_sale:
                showToast("这个是售后");
                break;
            case R.id.rl_add_cart:
                showToast("加入购物车");
                break;
            case R.id.ib_back:
                showToast("byebye");
                finish();
                break;
            case R.id.ib_cart:
                showToast("这个是购物车");
                break;
        }
    }

    @OnClick(R.id.rl_outright_purchase)
    public void onViewClicked() {
        showToast("直接购买");
    }

    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            //设置页面滑动时指示点也随着变化(当选中当前的页面的时候之前的画面就变成false不在显示颜色)
            ll_point_group.getChildAt(prePosition).setSelected(false);
            ll_point_group.getChildAt(position).setSelected(true);
            prePosition = position;

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
