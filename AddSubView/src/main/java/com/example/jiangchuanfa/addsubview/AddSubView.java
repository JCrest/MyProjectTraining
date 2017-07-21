package com.example.jiangchuanfa.addsubview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by crest on 2017/7/21.
 */

public class AddSubView extends LinearLayout implements View.OnClickListener {
    private ImageView ivAdd;
    private TextView tvValue;
    private ImageView ivSub;

    private int value = 1;
    private int minValue = 1;
    private int maxValue = 10;

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    //为了以后方便的改变最大值或者最小值的大小所以在这里设置set,get方法；
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        tvValue.setText(value + "");

    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public AddSubView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.add_sub_view, AddSubView.this);
        ivSub = findViewById(R.id.iv_sub);
        tvValue = findViewById(R.id.tv_value);
        ivAdd = findViewById(R.id.iv_add);


        ivSub.setOnClickListener(this);
        ivAdd.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_add:
                //value小于最大值的情况下累加并将值设置到文本中
                if (value < maxValue) {
                    value++;
                }
                setValue(value);
                //触发接口的方法，点击的时候触发接口将value的值传递给接口
                if(listener !=null) {
                    listener.onChangeNumber(value);
                }
                break;
            case R.id.iv_sub:
                //value大于最小值的情况下累减并将之设置到文本中
                if (value > minValue) {
                    value--;
                }
                setValue(value);
                //触发接口的方法，点击的时候触发接口将value的值传递给接口
                if(listener !=null) {
                    listener.onChangeNumber(value);
                }
                break;
        }
    }

    /**
     * 定义接口
     */
    public interface onChangeNumberListener {
        public void onChangeNumber(int number);
    }

    private onChangeNumberListener listener;

    /**
     * 设置数字变化的监听-->给外界调用使用的
     * 在Activity或适配器等中使用的
     *
     * @param listener
     */
    public void setOnChangeNumberListener(onChangeNumberListener listener) {
        this.listener = listener;
    }
}
