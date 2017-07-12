package com.example.jiangchuanfa.projecttraining.controller.fragment.shop.viewfragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.jiangchuanfa.projecttraining.R;
import com.example.jiangchuanfa.projecttraining.activity.MainActivity;
import com.example.jiangchuanfa.projecttraining.base.BaseFragment;
import com.example.jiangchuanfa.projecttraining.config.Api;
import com.example.jiangchuanfa.projecttraining.controller.adapter.ClassifyAdapter;
import com.example.jiangchuanfa.projecttraining.controller.fragment.goodslistfragment.GoodsListFragment;
import com.example.jiangchuanfa.projecttraining.modle.bean.ClassifyBean;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;

import static com.example.jiangchuanfa.projecttraining.config.Api.SHOP_ALL_URL;

/**
 * Created by crest on 2017/7/6.
 * 分类的碎片
 */

public class ClassifyFragment extends BaseFragment {
    private static final String TAG = ClassifyFragment.class.getSimpleName();
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    Unbinder unbinder;

    private String url;
    private ClassifyAdapter adapter;
    private GoodsListFragment goodsListFragment;

    @Override
    public View initView() {
        View view = View.inflate(getActivity(), R.layout.fragment_classify, null);
        unbinder = ButterKnife.bind(this, view);

        //设置布局管理器
        recyclerview.setLayoutManager(new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false));
        //设置RecyclerView的适配器
        adapter = new ClassifyAdapter(context);
        recyclerview.setAdapter(adapter);
        //自定义接口的调用
        adapter.setOnItemClickListener(new ClassifyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
//                showToast(SHOP_ALL_URL[position]);
                MainActivity mainActivity = (MainActivity) context;
//                mainActivity.exchangeFragment();
                //发送事件
//                EventBus.getDefault().post(SHOP_ALL_URL[position]);

//                EventBus.getDefault().post(
//                        new FirstEvent(SHOP_ALL_URL[position]));


//                GoodsListFragment fragment2 = GoodsListFragment.newInstance(SHOP_ALL_URL[position]);

                goodsListFragment = new GoodsListFragment();
                Bundle bundle = new Bundle();
                bundle.putString("data", SHOP_ALL_URL[position]);
                ClassifyFragment.this.goodsListFragment.setArguments(bundle);

                FragmentTransaction transaction = mainActivity.getSupportFragmentManager().beginTransaction();
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.add(R.id.fl_main, ClassifyFragment.this.goodsListFragment,"goodsListFragment");
//                transaction.add(R.id.fl_main, fragment2);
                transaction.addToBackStack(null);
//                transaction.addToBackStack(null);
                transaction.commit();
//                transaction.commit();



///////////////////////////////////////////////////////////////////////////////////////////////////////////
               /* //接口不好使？？？？mListener==null???
                Log.e(TAG, "onItemClick: "+mListener);
                if (mListener != null) {
                    mListener.dataTransmission(SHOP_ALL_URL[position]);
                    Log.e(TAG, "onItemClick: "+ SHOP_ALL_URL[position]);
                }*/
///////////////////////////////////////////////////////////////////////////////////////////////////////////

            }
        });

        return view;
    }


    @Override
    public void initData() {
        super.initData();
        url = Api.SHOP_CATEGORY_URL;
        Log.e(TAG, "商店分类总的网络地址=====" + url);
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
                        Log.e("TAG", "okhttp商店分类数据请求失败==" + e.getMessage());
                    }

                    @Override
                    public void onResponse(final String response, int id) {
                        Log.d("aaaaa", "onResponse: " + response);
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
        ClassifyBean classifyBean = new Gson().fromJson(json, ClassifyBean.class);
        Log.e("TAG", "数组解析数据成功======" + classifyBean.getData().getItems().get(0).getCat_name());
        adapter.refresh(classifyBean.getData().getItems());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
   /* //接口回调的方法
    public interface OnDataTransmissionListener {
        public void dataTransmission(String data);
    }

    private OnDataTransmissionListener mListener;

    public void setOnDataTransmissionListener(OnDataTransmissionListener mListener) {
        this.mListener = mListener;
    }*/
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
