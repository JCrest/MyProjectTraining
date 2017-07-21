package com.example.jiangchuanfa.projecttraining.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jiangchuanfa.projecttraining.R;

/**
 * Created by crest on 2017/7/21.
 */

public class MyToast extends Toast {
    private static View v;
    public MyToast(Context context) {
        super(context);
    }
    public static MyToast makeImage(Context context, Bitmap img, int duration)
    {
        MyToast result = new MyToast(context);
        LayoutInflater inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);//这里把makeText中的代码复制过来就行了
        v = inflate.inflate(R.layout.mytoast, null); //找到自己定义的布局文件
        ImageView tv = (ImageView) v.findViewById(R.id.tv_msg);
        tv.setImageBitmap(img);
        result.setView(v);
        result.setGravity(Gravity.CENTER, 0, 0);
        result.setDuration(duration);
        return result;
    }

}
