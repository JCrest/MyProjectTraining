package com.example.jiangchuanfa.projecttraining.controller.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.jiangchuanfa.projecttraining.R;
import com.example.jiangchuanfa.projecttraining.modle.bean.GoodInfoBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by crest on 2017/7/12.
 */

public class mListViewAdapter extends RecyclerView.Adapter<mListViewAdapter.ViewHolder> {


    private static final String TAG = mListViewAdapter.class.getSimpleName();


    private final Context context;
    private final List<GoodInfoBean.DataBean.ItemsBean.GoodsInfoBean> datas;

    public mListViewAdapter(Context context, List<GoodInfoBean.DataBean.ItemsBean.GoodsInfoBean> goods_info) {
        this.context = context;
        this.datas = goods_info;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(context, R.layout.item_detail_list, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //1.根据位置得到数据
        GoodInfoBean.DataBean.ItemsBean.GoodsInfoBean goodsInfoBean = datas.get(position);
        Glide.with(context)
                .load(goodsInfoBean.getContent().getImg())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivListDetail);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_list_detail)
        ImageView ivListDetail;

        public ViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);

        }

    }
}
