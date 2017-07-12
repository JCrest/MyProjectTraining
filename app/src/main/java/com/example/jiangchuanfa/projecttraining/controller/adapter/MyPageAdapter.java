package com.example.jiangchuanfa.projecttraining.controller.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by crest on 2017/7/11.
 */

public class MyPageAdapter extends PagerAdapter {
    private final Context context;
    private final List<String> datas;

    public MyPageAdapter(Context context, List<String> images_item) {
        this.context = context;
        this.datas = images_item;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        String s = datas.get(position);
        Log.e(TAG, "***************/////////////////////-----------------+++++++++++++++++++++------------------****************/********//////"+s);
        //联网请求
        Glide.with(context).load(s).into(imageView);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
