package com.example.jiangchuanfa.projecttraining.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.jiangchuanfa.projecttraining.R;
import com.example.jiangchuanfa.projecttraining.base.BaseActivity;
import com.example.jiangchuanfa.projecttraining.config.Api;
import com.example.jiangchuanfa.projecttraining.controller.Dialog.CartDialogFragment;
import com.example.jiangchuanfa.projecttraining.controller.Dialog.DialogCustomerService;
import com.example.jiangchuanfa.projecttraining.controller.Dialog.DialogShareFragment;
import com.example.jiangchuanfa.projecttraining.controller.adapter.MyPageAdapter;
import com.example.jiangchuanfa.projecttraining.controller.fragment.goodsinfofragment.BuyerReadingFragment;
import com.example.jiangchuanfa.projecttraining.controller.fragment.goodsinfofragment.ProductDetailsFragment;
import com.example.jiangchuanfa.projecttraining.controller.fragment.shoppingcart.AddSubView;
import com.example.jiangchuanfa.projecttraining.modle.bean.GoodInfoBean;
import com.example.jiangchuanfa.projecttraining.utils.MyToast;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

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


///////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////////////////////


    private String url;
    private ProductDetailsFragment productDetailsFragment;

    private DialogCustomerService dialogCustomerService;
    private CartDialogFragment cartDialogFragment;
    private DialogShareFragment dialogShareFragment;

    private BuyerReadingFragment buyerReadingFragment;
    private ViewPager viewPager;
    private LinearLayout ll_point_group;
    private ImageView iv_zhekou;

    private int prePosition;
    private GoodInfoBean goodsInfoBean;
    private GoodInfoBean.DataBean.ItemsBean itemsBean;
    private ArrayList<String> names;
    private ArrayList<String> img_paths;
    private ArrayList<String> names2;
    private String attribute1;//用于记录选中的属性1
    private String attribute2;//用于记录选中的属性2
    private int DefaultAttribute1;//临时记录属性1
    private int DefaultAttribute2;//临时记录属性2
    private int selectNumber = 1;//用于记录选中的商品的数量(默认是选中1个的)


    @Override
    public void initView() {
        initProductDetailsFragment();


        viewPager = (ViewPager) findViewById(R.id.viewPager);
        iv_zhekou = (ImageView) findViewById(R.id.iv_zhekou);
        ll_point_group = (LinearLayout) findViewById(R.id.ll_point_group);

        dialogShareFragment = new DialogShareFragment();
        Log.e(TAG, "initView: new DialogShareFragment()" + dialogShareFragment);
        dialogCustomerService = new DialogCustomerService();
        cartDialogFragment = new CartDialogFragment();

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
                break;
            case R.id.rb_buyer_reading:
                initBuyerReadingFragment();
                break;
        }

    }

    private void initBuyerReadingFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (buyerReadingFragment == null) {
            buyerReadingFragment = new BuyerReadingFragment();
            Log.e(TAG, "initBuyerReadingFragment: 难道每次都要new吗？");
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
        Log.e(TAG, "单个商品详情地址url==" + url);
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
        goodsInfoBean = new Gson().fromJson(json, GoodInfoBean.class);
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

        viewPager.setAdapter(new MyPageAdapter(this, goodsInfoBean.getData().getItems().getImages_item()));
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

                dialogShareFragment.show(getSupportFragmentManager(), "");
                break;
            case R.id.ll_size_choose:
                showToast("选择尺寸");
                break;
            case R.id.ll_brand_choose:
                showToast("选择品牌");
                break;
            case R.id.rl_after_sale:
//                showToast("这个是售后");

                dialogCustomerService.show(getSupportFragmentManager(), "");
                break;
            case R.id.rl_add_cart:

                showCartDialogFragment();

//                cartDialogFragment.show(getSupportFragmentManager(),"");

                break;
            case R.id.ib_back:
                showToast("byebye");
                finish();
                break;
            case R.id.ib_cart:
                Intent intent = new Intent(this, ShoppingCartAcrivity.class);
                startActivity(intent);
//                showToast("这是购物车");
                break;
        }
    }

    private void showCartDialogFragment() {

        final LayoutInflater mInflater = LayoutInflater.from(this);
        itemsBean = goodsInfoBean.getData().getItems();

        final Dialog dialog = new Dialog(this, R.style.CustomDatePickerDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // must be called before set content
        dialog.setContentView(R.layout.cart_fragment);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(true);

        // 设置宽度为屏宽、靠近屏幕底部。
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
//        wlp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);


        final ImageView ivGoods = dialog.findViewById(R.id.iv_goods);
        TextView tvPriceCart = dialog.findViewById(R.id.tv_price_cart_dialog);
        TextView tvBrandNameCart = dialog.findViewById(R.id.tv_brand_name);
        TextView tvGoodsNameCart = dialog.findViewById(R.id.tv_goods_name);
        TextView mtvOk = dialog.findViewById(R.id.mtv_ok);
        AddSubView mAddSubView = dialog.findViewById(R.id.av_mAddSubView);
        ImageView ivClose = dialog.findViewById(R.id.iv_close);
        final TagFlowLayout idFlowLayout = dialog.findViewById(R.id.id_flowlayout);
        final TagFlowLayout mFlowLayout = dialog.findViewById(R.id.m_flowlayout);


        if (itemsBean.getSku_info().size() > 0) {
            if (itemsBean.getSku_info().size() == 1) {
                names = new ArrayList<>();
                img_paths = new ArrayList<>();
                for (int i = 0; i < itemsBean.getSku_info().get(0).getAttrList().size(); i++) {
                    names.add(itemsBean.getSku_info().get(0).getAttrList().get(i).getAttr_name());
                    img_paths.add(itemsBean.getSku_info().get(0).getAttrList().get(i).getImg_path());
                }
                TextView tv_type_name = dialog.findViewById(R.id.tv_type_name);
                tv_type_name.setText(itemsBean.getSku_info().get(0).getType_name());
                idFlowLayout.setAdapter(new TagAdapter<String>(names) {

                    @Override
                    public View getView(FlowLayout parent, int position, String s) {
                        TextView tv = (TextView) mInflater.inflate(R.layout.tv,
                                idFlowLayout, false);
                        tv.setText(s);
                        return tv;
                    }

                    @Override
                    public boolean setSelected(int position, String s) {
                        attribute1 = names.get(0);
                        return s.equals(names.get(0));
                    }
                });

            }
            if (itemsBean.getSku_info().size() == 2) {
                names = new ArrayList<>();
                img_paths = new ArrayList<>();
                for (int i = 0; i < itemsBean.getSku_info().get(0).getAttrList().size(); i++) {
                    names.add(itemsBean.getSku_info().get(0).getAttrList().get(i).getAttr_name());
                    img_paths.add(itemsBean.getSku_info().get(0).getAttrList().get(i).getImg_path());
                }
                TextView tv_type_name = dialog.findViewById(R.id.tv_type_name);
                tv_type_name.setText(itemsBean.getSku_info().get(0).getType_name());
                idFlowLayout.setAdapter(new TagAdapter<String>(names) {
                    @Override
                    public View getView(FlowLayout parent, int position, String s) {
                        TextView tv = (TextView) mInflater.inflate(R.layout.tv, idFlowLayout, false);
                        tv.setText(s);
                        return tv;
                    }

                    @Override
                    public boolean setSelected(int position, String s) {
                        attribute1 = names.get(0);
                        return s.equals(names.get(0));
                    }
                });

                names2 = new ArrayList<>();
                for (int i = 0; i < itemsBean.getSku_info().get(1).getAttrList().size(); i++) {
                    names2.add(itemsBean.getSku_info().get(1).getAttrList().get(i).getAttr_name());
                }

                TextView tv_type_name2 = dialog.findViewById(R.id.tv_type_name2);
                tv_type_name2.setVisibility(View.VISIBLE);
                tv_type_name2.setText(itemsBean.getSku_info().get(1).getType_name());
                mFlowLayout.setVisibility(View.VISIBLE);

                mFlowLayout.setAdapter(new TagAdapter<String>(names2) {
                    @Override
                    public View getView(FlowLayout parent, int position, String s) {
                        TextView tv = (TextView) mInflater.inflate(R.layout.tv, mFlowLayout, false);
                        tv.setText(s);
                        return tv;
                    }

                    @Override
                    public boolean setSelected(int position, String s) {
                        attribute2 = names2.get(0);
                        return s.equals(names2.get(0));
                    }

                });

            }

            final List<String> finalImg_paths = img_paths;
            //属性1标签点击事件
            idFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                @Override
                public boolean onTagClick(View view, int position, FlowLayout parent) {
                    if (finalImg_paths.get(position) == "") {
                        //如果图片的地址为空加载默认的商品图片
                        Glide.with(GoodsInfoActivity.this).load(itemsBean.getGoods_image()).into(ivGoods);
                        Log.e(TAG, "itemsBean.getGoods_image():" + itemsBean.getGoods_image());
                        if (swichTag1State(position)) return true;
                        //吐司点中的标签的名称
//                        Toast.makeText(GoodsInfoActivity.this, finalNames.get(position), Toast.LENGTH_SHORT).show();
//                        attribute1 = finalNames.get(position);
                    } else {
                        //如果图片的地址不为空加载点击选中的位置对应的图片
                        Glide.with(GoodsInfoActivity.this).load(finalImg_paths.get(position)).into(ivGoods);
                        //吐司点中的标签的名称
                        if (swichTag1State(position)) return true;
//                        Toast.makeText(GoodsInfoActivity.this, finalNames.get(position), Toast.LENGTH_SHORT).show();
//                        attribute1 = finalNames.get(position);
                    }
                    return true;
                }
            });

            //属性2标签点击事件
            mFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                @Override
                public boolean onTagClick(View view, int position, FlowLayout parent) {
                    if (swichTag2State(position)) return true;
                    return true;
                }
            });

        }

        Glide.with(GoodsInfoActivity.this).load(itemsBean.getGoods_image()).into(ivGoods);
        tvPriceCart.setText(itemsBean.getPrice());
        tvBrandNameCart.setText(itemsBean.getBrand_info().getBrand_name());
        tvGoodsNameCart.setText(itemsBean.getGoods_name());

        mAddSubView.setOnChangeNumberListener(new AddSubView.onChangeNumberListener() {
            @Override
            public void onChangeNumber(int number) {
                showToast("选中的数量为：" + number);
                selectNumber = number;
            }
        });

        /**
         * 确认按钮点击事件
         */
        mtvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap source= BitmapFactory.decodeResource(getResources(),R.drawable.ic_dialog_addtocart);
                if (itemsBean.getSku_info().size() == 2) {
//                    showToast("选中的属性为：" + attribute1 + ";" + attribute2 + ";" + selectNumber);
                    MyToast.makeImage(GoodsInfoActivity.this,source, Toast.LENGTH_SHORT).show();
                } else {
//                    showToast("选中的属性为：" + attribute1 + ";" + selectNumber);
                    MyToast.makeImage(GoodsInfoActivity.this,source, Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();

            }
        });
        dialog.show();

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    /**
     * 根据位置切换标签2被选中的状态（当标签有选中变为未选中时将标签属性置为空，改变这种状态时赋值）
     *
     * @param position
     * @return
     */
    private boolean swichTag2State(int position) {
        if (position == DefaultAttribute2) {
            DefaultAttribute2 = -1;
            attribute2 = "";
            showToast(attribute2);
            return true;
        } else {
            DefaultAttribute2 = position;
            attribute2 = names2.get(position);
            showToast(attribute2);
        }
        return false;
    }

    /**
     * 根据位置切换标签1被选中的状态（当标签有选中变为未选中时将标签属性置为空，改变这种状态时赋值）
     *
     * @param position
     * @return
     */
    private boolean swichTag1State(int position) {
        if (position == DefaultAttribute1) {
            DefaultAttribute1 = -1;
            attribute1 = "";
            showToast(attribute1);
            return true;
        } else {
            DefaultAttribute1 = position;
            attribute1 = names.get(position);
            showToast(attribute1);
        }
        return false;
    }


    @OnClick(R.id.rl_outright_purchase)
    public void onViewClicked() {

        cartDialogFragment.show(getSupportFragmentManager(), "");
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
