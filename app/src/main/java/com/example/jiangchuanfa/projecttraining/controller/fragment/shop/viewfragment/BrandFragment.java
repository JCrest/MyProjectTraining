package com.example.jiangchuanfa.projecttraining.controller.fragment.shop.viewfragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.jiangchuanfa.projecttraining.R;
import com.example.jiangchuanfa.projecttraining.base.BaseFragment;
import com.example.jiangchuanfa.projecttraining.config.Api;
import com.example.jiangchuanfa.projecttraining.controller.adapter.BrandAdapter;
import com.example.jiangchuanfa.projecttraining.modle.bean.BrandBean;
import com.example.jiangchuanfa.projecttraining.utils.CacheUtils;
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

public class BrandFragment extends BaseFragment {


    @BindView(R.id.rv)
    RecyclerView rv;
    Unbinder unbinder;
    private String url;
    private BrandAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(getActivity(), R.layout.fragment_brand, null);
        unbinder = ButterKnife.bind(this, view);
        rv.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        adapter = new BrandAdapter(context);
        rv.setAdapter(adapter);
        return view;

    }

    @Override
    public void initData() {
        super.initData();
        url = Api.SHOP_BRAND_URL;
        Log.e("TAG", "商店品牌总的网络地址=====" + url);
//        String saveJson = CacheUtils.getString(context, url);
//        if (!TextUtils.isEmpty(saveJson)) {
//            Log.e("TAG", "商店品牌总的json=====" + saveJson);
//            processData(saveJson);
//        }
        getDataFromNet(url);
    }

    /**
     * OkHttpUtils联网请求数据
     *
     * @param url
     */
    private void getDataFromNet(final String url) {
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "okhttp商店品牌数据请求失败==" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("TAG", "okhttp商店品牌数据请求成功==");
                        CacheUtils.putString(context, url, response);
                        processData(response);

                    }
                });
    }

    /**
     * 数据解析
     *
     * @param json
     */
    private void processData(String json) {
        BrandBean brandBean = new Gson().fromJson(json, BrandBean.class);
        Log.e("TAG", "数组解析数据成功======" + brandBean.getData().getItems().get(0).getBrand_name());
        adapter.refresh(brandBean.getData().getItems());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
