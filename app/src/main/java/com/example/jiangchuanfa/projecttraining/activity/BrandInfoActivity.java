package com.example.jiangchuanfa.projecttraining.activity;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.jiangchuanfa.projecttraining.R;
import com.example.jiangchuanfa.projecttraining.base.BaseActivity;
import com.example.jiangchuanfa.projecttraining.controller.fragment.BrandInfoFragment.ProductsBrandFragment;
import com.example.jiangchuanfa.projecttraining.controller.fragment.BrandInfoFragment.StoryBrandFragment;

import butterknife.BindView;

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

    private StoryBrandFragment storyBrandFragment;
    private ProductsBrandFragment productsBrandFragment;

    @Override
    public void initView() {
        tvTitle.setText("品牌产品");
        ibSearch.setVisibility(View.GONE);
        ibBack.setVisibility(View.VISIBLE);
        ibCart.setVisibility(View.GONE);
        initProductsBrandFragment();
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

                switchFragment(checkedId);
            }
        });

    }

    private void switchFragment(int checkedId) {

        switch (checkedId) {
            case R.id.rb_story_brand:
                initStoryBrandFragment();
                break;
            case R.id.rb_products_brand:
                initProductsBrandFragment();
                break;
        }

    }

    private void initProductsBrandFragment() {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (productsBrandFragment == null) {
            productsBrandFragment = new ProductsBrandFragment();
            Log.e(TAG, "initBuyerReadingFragment: 难道每次都要new吗？" );
            transaction.add(R.id.ll_fragment_container, productsBrandFragment);
//            transaction.addToBackStack(null);
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

        rgGoodsInfo.check(R.id.rb_products_brand);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_brand_info;
    }


}
