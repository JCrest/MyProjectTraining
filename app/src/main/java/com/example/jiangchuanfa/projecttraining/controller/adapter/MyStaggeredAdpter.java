package com.example.jiangchuanfa.projecttraining.controller.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jiangchuanfa.projecttraining.R;

import java.util.ArrayList;

/**
 * Created by crest on 2017/7/13.
 */

public class MyStaggeredAdpter extends RecyclerView.Adapter<MyStaggeredAdpter.ViewHolder> {


    private final Context context;
    private final ArrayList<String> datas;

    public MyStaggeredAdpter(Context context, ArrayList<String> images) {
        this.context = context;
        this.datas = images;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.staggered_img_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        for(int i = 0; i <4 ; i++) {
//            Glide.with(context)
//                    .load(datas.get(i))
//                    .into(holder.staggerItemImg);
//        }
    }

    @Override
    public int getItemCount() {
        return datas==null?0:datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {



        public ViewHolder(View itemView) {
            super(itemView);

        }
    }
}
