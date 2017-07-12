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
import com.example.jiangchuanfa.projecttraining.modle.bean.NewBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by crest on 2017/7/11.
 */

public class GuessYouLikeAdapter extends RecyclerView.Adapter<GuessYouLikeAdapter.ViewHolder> {
    private final List<NewBean.DataBean.ItemsBean> datas;
    private final Context context;

    public GuessYouLikeAdapter(Context context, List<NewBean.DataBean.ItemsBean> itemsBeens) {
        this.context = context;
        this.datas = itemsBeens;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(context, R.layout.item_goods_list_fragment, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //1.根据位置得到数据
        NewBean.DataBean.ItemsBean itemsBean = datas.get(position);
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

    @Override
    public int getItemCount() {


        return datas == null ? 0 : 6;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


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

        public ViewHolder(View view) {
            super(view);

            Unbinder unbinder = ButterKnife.bind(this, view);
            rlItemGoodsListFragment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, GoodsInfoActivity.class);
                    Bundle bundle = new Bundle(); //该类用作携带数据
                    bundle.putString("goods_id", datas.get(getLayoutPosition()).getGoods_id());
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
        }
    }
}
