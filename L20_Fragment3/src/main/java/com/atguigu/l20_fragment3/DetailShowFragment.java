package com.atguigu.l20_fragment3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 作者：杨光福 on 2017/5/8 15:29
 * QQ：541433511
 * 微信：yangguangfu520
 * 作用：
 */

public class DetailShowFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setTextSize(25);
        textView.setGravity(Gravity.CENTER);
        //得到数据
        Bundle arguments = getArguments();
        if(arguments != null){
            String data = arguments.getString("data", "没有收到数据");
            textView.setText(data);
        }else{
            textView.setText("我是内容");
        }

        return textView;
    }
}
