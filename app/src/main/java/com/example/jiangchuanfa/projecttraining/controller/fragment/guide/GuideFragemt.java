package com.example.jiangchuanfa.projecttraining.controller.fragment.guide;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.jiangchuanfa.projecttraining.R;

/**
 * Created by crest on 2017/7/6.
 */

public class GuideFragemt extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ImageView imageView = new ImageView(getActivity());
        /**获取参数，根据不同的参数播放GIF**/
        int index = getArguments().getInt("index");
        Uri uri = null;
        if (index == 1) {
            uri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.feature1);
        } else if (index == 2) {
            uri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.feature2);
        } else if (index == 3) {
            uri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.feature3);
        } else if (index == 4) {
            uri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.feature4);
        } else if (index == 5) {
            uri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.feature5);
        }
        /**播放Gif**/
        if (index == 5) {
            Glide.with(this)
                    .load(uri)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(imageView);
        } else {
            Glide.with(this)
                    .load(uri)
                    .asGif()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(imageView);
        }
        return imageView;
    }

}
