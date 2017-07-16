package com.example.jiangchuanfa.projecttraining.controller.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jiangchuanfa.projecttraining.R;
import com.example.jiangchuanfa.projecttraining.modle.bean.MagazineBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by crest on 2017/7/15.
 */

public class MagazineTopicAdapter extends XRecyclerView.Adapter<MagazineTopicAdapter.ViewHolder> {


    private static final String TAG = MagazineTopicAdapter.class.getSimpleName();


    private final Context context;

    private final List<MagazineBean> datas = new ArrayList<>();
    private final List<String> keys = new ArrayList<>();


    public MagazineTopicAdapter(Context context) {
        this.context = context;
    }


    //刷新适配器方法
    public void refresh(List<MagazineBean> items, List<String> keys) {
        this.datas.addAll(items);
        this.keys.addAll(keys);
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(context, R.layout.item_mag_fragment, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

            Glide.with(context).load(datas.get(position).getCover_img_new()).into(holder.ivMag);
            holder.tvTopicName.setText(datas.get(position).getTopic_name());
            holder.tvCatName.setText(datas.get(position).getCat_name());
            holder.tvDate.setText(keys.get(position).substring(5, 11));

            if(position == keys.size()) {

                holder.tvDate.setVisibility(View.VISIBLE);
                
            }else if(position<keys.size()-1) {

                String date = datas.get(position).getAddtime().substring(0, 10);
                String afterdate = datas.get(position+1).getAddtime().substring(0, 10);
                Log.e(TAG, "onBindViewHolder: "+keys.get(position).substring(5, 11) );
                Log.e("TAG", "afterdate"+afterdate);
                if(afterdate.equals(date)) {
                    holder.tvDate.setVisibility(View.GONE);
                }else {
                    holder.tvDate.setVisibility(View.VISIBLE);
                }
            }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_mag)
        ImageView ivMag;
        @BindView(R.id.tv_topic_name)
        TextView tvTopicName;
        @BindView(R.id.tv_cat_name)
        TextView tvCatName;
        @BindView(R.id.tv_date)
        TextView tvDate;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
