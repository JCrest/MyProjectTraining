package com.example.jiangchuanfa.projecttraining.controller.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.jiangchuanfa.projecttraining.R;
import com.example.jiangchuanfa.projecttraining.modle.bean.BrandBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by crest on 2017/7/7.
 */

public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.ViewHolder> {


    private final List<BrandBean.DataBean.ItemsBean> datas = new ArrayList<>();
    private final Context context;
//
//    public BrandAdapter(Context context, List<BrandBean.DataBean.ItemsBean> items) {
//
//        this.datas = items;
//    }

    public BrandAdapter(Context context) {
        this.context = context;
    }

    public void refresh(List<BrandBean.DataBean.ItemsBean> items) {
        this.datas.addAll(items);
        this.notifyDataSetChanged();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(context, R.layout.item_brand_fragment, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //1.根据位置得到数据
        BrandBean.DataBean.ItemsBean itemsBean = datas.get(position);
        holder.tvBrand.setText(itemsBean.getBrand_name());
        Glide.with(context).load(itemsBean.getBrand_logo()).into(holder.ivBrand);

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_brand)
        ImageView ivBrand;
        @BindView(R.id.tv_brand)
        TextView tvBrand;
        Unbinder unbinder;


        public ViewHolder(View view) {
            super(view);
            unbinder = ButterKnife.bind(this, view);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "在这还崩！", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
