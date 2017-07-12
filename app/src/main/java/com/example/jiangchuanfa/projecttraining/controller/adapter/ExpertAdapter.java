package com.example.jiangchuanfa.projecttraining.controller.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.jiangchuanfa.projecttraining.R;
import com.example.jiangchuanfa.projecttraining.modle.bean.ExpertBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by crest on 2017/7/10.
 */

public class ExpertAdapter extends RecyclerView.Adapter<ExpertAdapter.ViewHolder> {

    private final Context context;

    private ArrayList<ExpertBean.DataBean.ItemsBean> datas = new ArrayList<>();


    public ExpertAdapter(Context context) {
        this.context = context;
    }

    public void refresh(List<ExpertBean.DataBean.ItemsBean> items) {
        this.datas.addAll(items);
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(context, R.layout.item_expert_fragment, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //1.根据位置得到数据
        ExpertBean.DataBean.ItemsBean itemsBean = datas.get(position);
        Glide.with(context)
                .load(itemsBean.getUser_images().getOrig())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivIcon);
        holder.tvNickName.setText(itemsBean.getUsername());
        holder.tvDuty.setText(itemsBean.getDuty());

    }


    class ViewHolder extends RecyclerView.ViewHolder {
        Unbinder unbinder;
        @BindView(R.id.iv_icon)
        ImageView ivIcon;
        @BindView(R.id.tv_nick_name)
        TextView tvNickName;
        @BindView(R.id.tv_duty)
        TextView tvDuty;

        public ViewHolder(View view) {
            super(view);
            unbinder = ButterKnife.bind(this, view);
        }
    }
}
