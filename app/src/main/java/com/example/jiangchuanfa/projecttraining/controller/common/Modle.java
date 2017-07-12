package com.example.jiangchuanfa.projecttraining.controller.common;

import android.content.Context;

/**
 * Created by crest on 2017/7/6.
 * Modle单例模式
 */

public class Modle {
    private Modle() {
    }

    private Context context;

    private static Modle modle = new Modle();

    public static Modle getInstance() {
        return modle;
    }

    public void init(Context context) {
        this.context = context;
    }
}
