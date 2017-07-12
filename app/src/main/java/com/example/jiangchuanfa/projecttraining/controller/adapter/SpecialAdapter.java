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
import com.example.jiangchuanfa.projecttraining.modle.bean.SpecialBean;

import java.util.List;

/**
 * Created by crest on 2017/7/8.
 */

public class SpecialAdapter extends RecyclerView.Adapter<SpecialAdapter.ViewHolder> {

    private final Context context;
    private final List<SpecialBean.DataBean.ItemsBean> datas;


    public SpecialAdapter(Context context, List<SpecialBean.DataBean.ItemsBean> items) {
        this.context = context;
        this.datas = items;
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(context, R.layout.item_special_fragment, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SpecialBean.DataBean.ItemsBean itemsBean = datas.get(position);
        holder.tvSpecial.setText(datas.get(position).getTopic_name());
        Glide.with(context).load(itemsBean.getCover_img()).into(holder.iv_special);
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        //        @BindView(R.id.iv_special)
//        ImageView ivSpecial;
//        @BindView(R.id.btn_special_fragment)
//        Button btnSpecial;
        private ImageView iv_special;
        private TextView tvSpecial;

        public ViewHolder(View view) {
            super(view);
            iv_special = view.findViewById(R.id.iv_special);
            tvSpecial = view.findViewById(R.id.tv_special_fragment);

//            ButterKnife.bind(context, view);

//            ivSpecial = view.findViewById(R.id.iv_special);
//            btnSpecial = view.findViewById(R.id.btn_special_fragment);
            tvSpecial.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "在这还崩！", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
