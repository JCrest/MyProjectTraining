package com.example.jiangchuanfa.projecttraining.controller.fragment.shop.viewfragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.jiangchuanfa.projecttraining.R;
import com.example.jiangchuanfa.projecttraining.base.BaseFragment;
import com.example.jiangchuanfa.projecttraining.config.Api;
import com.example.jiangchuanfa.projecttraining.controller.adapter.HomeAdapter;
import com.example.jiangchuanfa.projecttraining.modle.bean.HomeBean;
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

public class HomeFragment extends BaseFragment {
    private static final String TAG = HomeFragment.class.getSimpleName();
    @BindView(R.id.rv_home)
    RecyclerView rvHome;
    Unbinder unbinder;
    private String homeUrl;
    private HomeAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(getActivity(), R.layout.fragment_home, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        getDataFromNet();


    }

    private void getDataFromNet() {
        homeUrl = Api.SHOP_HOME_URL;
        Log.e(TAG, "商店首页总的网络地址=====" + homeUrl);
        OkHttpUtils
                .get()
                .url(homeUrl)
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
        //解析数据
        HomeBean homeBean = new Gson().fromJson(json, HomeBean.class);
        Log.e(TAG, "数据解析结果=="+homeBean.getData().getItems().getList().get(0).getHome_id());
        //设置适配器
        adapter = new HomeAdapter(context,homeBean.getData().getItems().getList());
        rvHome.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        rvHome.setLayoutManager(manager);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
