package com.example.jiangchuanfa.projecttraining.controller.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.jiangchuanfa.projecttraining.R;
import com.example.jiangchuanfa.projecttraining.activity.GoodsInfoActivity;
import com.example.jiangchuanfa.projecttraining.modle.bean.GoodsListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by crest on 2017/7/8.
 */

public class GoodsListAdapter extends RecyclerView.Adapter<GoodsListAdapter.ViewHolder> {


    private final Context context;

    private final List<GoodsListBean.DataBean.ItemsBean> datas;


    public GoodsListAdapter(Context context, List<GoodsListBean.DataBean.ItemsBean> items) {
        this.context = context;
        this.datas = items;
    }

//    public void refresh(List<GoodsListBean.DataBean.ItemsBean> items) {
//        this.datas.addAll(items);
//        this.notifyDataSetChanged();
//    }

    @Override
    public int getItemCount() {
//        return 20;
        return datas == null ? 0 : datas.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(context, R.layout.item_goods_list_fragment, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        //1.根据位置得到数据
        GoodsListBean.DataBean.ItemsBean itemsBean = datas.get(position);
        Glide.with(context).load(itemsBean.getGoods_image())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivGoodsImage);
        holder.tvGoodsName.setText(itemsBean.getGoods_name());
        holder.tvBrandName.setText(itemsBean.getBrand_info().getBrand_name());
        holder.tvPrice.setText(itemsBean.getPrice());
        holder.tvLikeCount.setText(itemsBean.getLike_count());
        if (!TextUtils.isEmpty(itemsBean.getDiscount_price())) {
            holder.flDiscountPrice.setVisibility(View.VISIBLE);
            holder.tvDiscountPrice.setText(itemsBean.getDiscount_price());
            holder.tvDiscountPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }
        if (!TextUtils.isEmpty(itemsBean.getPromotion_imgurl())) {
            holder.ivPromotionImgurl.setVisibility(View.VISIBLE);
            Glide.with(context).load(itemsBean.getPromotion_imgurl())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.ivPromotionImgurl);
        }

    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_goods_image)
        ImageView ivGoodsImage;
        @BindView(R.id.iv_promotion_imgurl)
        ImageView ivPromotionImgurl;
        @BindView(R.id.tv_goods_name)
        TextView tvGoodsName;
        @BindView(R.id.tv_brand_name)
        TextView tvBrandName;
        @BindView(R.id.tv_like_count)
        TextView tvLikeCount;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_discount_price)
        TextView tvDiscountPrice;
        @BindView(R.id.fl_discount_price)
        FrameLayout flDiscountPrice;
        @BindView(R.id.rl_item_goods_list_fragment)
        RelativeLayout rlItemGoodsListFragment;
        Unbinder unbinder;

        public ViewHolder(View view) {
            super(view);
            unbinder = ButterKnife.bind(this, view);
            rlItemGoodsListFragment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, GoodsInfoActivity.class);
                    Bundle bundle = new Bundle(); //该类用作携带数据
                    bundle.putString("goods_id",datas.get(getLayoutPosition()).getGoods_id());
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
        }
    }
}
