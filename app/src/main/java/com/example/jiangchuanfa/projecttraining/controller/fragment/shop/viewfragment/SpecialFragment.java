package com.example.jiangchuanfa.projecttraining.controller.fragment.shop.viewfragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.jiangchuanfa.projecttraining.R;
import com.example.jiangchuanfa.projecttraining.base.BaseFragment;
import com.example.jiangchuanfa.projecttraining.config.Api;
import com.example.jiangchuanfa.projecttraining.controller.adapter.SpecialAdapter;
import com.example.jiangchuanfa.projecttraining.modle.bean.SpecialBean;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;

/**
 * Created by crest on 2017/7/6.
 */

public class SpecialFragment extends BaseFragment {
    private static final String TAG = SpecialFragment.class.getSimpleName();
    @BindView(R.id.rv_special)
    RecyclerView rvSpecial;
    Unbinder unbinder;
    private String specialUrl;
    private SpecialAdapter adapter;

    @Override
    public View initView() {

        View view = View.inflate(getActivity(), R.layout.fragment_special, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        getDataFromNet();


    }

    private void getDataFromNet() {
        specialUrl = Api.SHOP_SPECIAL_URL;
        Log.e(TAG, "商店专题总的网络地址=====" + specialUrl);
        OkHttpUtils
                .get()
                .url(specialUrl)
                .build()
                .execute(new MyStringCallback());
    }

    class MyStringCallback extends StringCallback {

        @Override
        public void onError(Call call, Exception e, int id) {
            Log.e(TAG, "请求成功失败==" + e.getMessage());
        }

        @Override
        public void onResponse(String response, int id) {
            Log.e(TAG, "请求成功==" + response);
            processData(response);

        }
    }

    private void processData(String json) {
//        解析数据
        SpecialBean specialBean = new Gson().fromJson(json, SpecialBean.class);
        Log.e(TAG, "数据解析结果=="+specialBean.getData().getItems().get(0).getAccess_url());
        //设置适配器
        adapter = new SpecialAdapter(context,specialBean.getData().getItems());
        rvSpecial.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        rvSpecial.setLayoutManager(manager);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
