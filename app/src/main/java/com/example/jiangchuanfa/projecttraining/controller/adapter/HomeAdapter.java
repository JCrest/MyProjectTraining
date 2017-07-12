package com.example.jiangchuanfa.projecttraining.controller.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.jiangchuanfa.projecttraining.R;
import com.example.jiangchuanfa.projecttraining.modle.bean.HomeBean;

import java.util.List;

/**
 * Created by crest on 2017/7/7.
 */

public class HomeAdapter extends RecyclerView.Adapter {
    private static final String TAG = HomeAdapter.class.getSimpleName();
    private final Context context;
    private final List<HomeBean.DataBean.ItemsBean.ListBean> datas;

    //共3种类型的分类
    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int FOUR = 4;
    /**
     * 当前类型
     */
    public int currentType = ONE;

    private LayoutInflater inflater;

    public HomeAdapter(Context context, List<HomeBean.DataBean.ItemsBean.ListBean> list) {
        this.context = context;
        this.datas = list;
    }


    /**
     * 根据位置得到当前类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (position == ONE) {
            currentType = ONE;
        } else if (position == TWO) {
            currentType = TWO;
        } else if (position == FOUR) {
            currentType = FOUR;
        }
        return currentType;
    }

    /**
     * 运行时实现了几个类型就返回几，全部实现返回3；
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ONE) {
            return new OneViewHolder(context, inflater.inflate(R.layout.one_item, null));
        }
//        else if (viewType == TWO) {
//            return new TwoViewHolder(context, inflater.inflate(R.layout.two_item, null));
//        }
//        else if (viewType == FOUR) {
//            return new FourViewHolder(context, inflater.inflate(R.layout.fourl_item, null));
//        }
        return null;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == ONE) {
            OneViewHolder oneViewHolder = (OneViewHolder) holder;
            //设置数据TypeOne的数据
            oneViewHolder.setData(datas.get(position).getHome_type());
        }

    }

    class OneViewHolder extends RecyclerView.ViewHolder {

        private final Context context;
        private ImageView iv_one;

        public OneViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            iv_one = itemView.findViewById(R.id.iv_one);
        }

        public void setData(String pic_url) {
            Log.e(TAG, "看看这个方法执行到了没有！！！！！！！！！！！！！");
            Glide.with(context).load(pic_url).into(iv_one);


        }
    }
}
