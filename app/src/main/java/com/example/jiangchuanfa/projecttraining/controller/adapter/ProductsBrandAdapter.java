package com.example.jiangchuanfa.projecttraining.controller.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.jiangchuanfa.projecttraining.R;
import com.example.jiangchuanfa.projecttraining.activity.GoodsInfoActivity;
import com.example.jiangchuanfa.projecttraining.modle.bean.BrandInfosBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by crest on 2017/7/16.
 */

public class ProductsBrandAdapter extends RecyclerView.Adapter<ProductsBrandAdapter.ViewHolder> {
    private final Context context;
    private final List<BrandInfosBean.DataBean.ItemsBean> datas;


    public ProductsBrandAdapter(Context context, List<BrandInfosBean.DataBean.ItemsBean> items) {
        this.context = context;
        this.datas = items;
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(context, R.layout.item_pruducts_brand_fragment, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        //1.根据位置得到数据
        BrandInfosBean.DataBean.ItemsBean itemsBean = datas.get(position);
        Glide.with(context).load(itemsBean.getGoods_image())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivGoodsImage);
        holder.tvGoodsName.setText(itemsBean.getGoods_name());
        holder.tvBrandName.setText(itemsBean.getBrand_info().getBrand_name());
        holder.tvShopPrice.setText(itemsBean.getShop_price());
        holder.tvLikeCount.setText(itemsBean.getLike_count());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_goods_image)
        ImageView ivGoodsImage;
        @BindView(R.id.tv_goods_name)
        TextView tvGoodsName;
        @BindView(R.id.tv_brand_name)
        TextView tvBrandName;
        @BindView(R.id.tv_like_count)
        TextView tvLikeCount;
        @BindView(R.id.tv_shop_price)
        TextView tvShopPrice;
        @BindView(R.id.rl_item_brand_list_fragment)
        RelativeLayout rlItemBrandListFragment;

        Unbinder unbinder;

        public ViewHolder(View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this, itemView);

            rlItemBrandListFragment.setOnClickListener(new View.OnClickListener() {
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
