package com.example.jiangchuanfa.projecttraining.controller.fragment.expert;

import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.jiangchuanfa.projecttraining.R;
import com.example.jiangchuanfa.projecttraining.base.BaseFragment;
import com.example.jiangchuanfa.projecttraining.config.Api;
import com.example.jiangchuanfa.projecttraining.controller.adapter.ExpertAdapter;
import com.example.jiangchuanfa.projecttraining.modle.bean.ExpertBean;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;

/**
 * Created by crest on 2017/7/6.
 */

public class ExpertFragment extends BaseFragment {
    private static final String TAG = ExpertFragment.class.getSimpleName();
    @BindView(R.id.Xrecyclerview)
    XRecyclerView Xrecyclerview;
    Unbinder unbinder;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ib_search)
    ImageButton ibSearch;
    @BindView(R.id.ib_cart)
    ImageButton ibCart;
    @BindView(R.id.ib_navigation_menu)
    ImageButton ibNavigationMenu;
    private ExpertAdapter adapter;
    private String url;

    @Override
    public View initView() {
        View view = View.inflate(getActivity(), R.layout.fragment_expert, null);
        unbinder = ButterKnife.bind(this, view);

        //设置布局管理器
        Xrecyclerview.setLayoutManager(new GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false));
        //设置RecyclerView的适配器
        adapter = new ExpertAdapter(context);
        Xrecyclerview.setAdapter(adapter);
        exchangeMenuState();
        return view;
    }

    private void exchangeMenuState() {
        ibCart.setVisibility(View.GONE);
        ibNavigationMenu.setVisibility(View.VISIBLE);


    }

    @Override
    public void initData() {
        super.initData();
        tvTitle.setText("达人");
        url = Api.EXPERT_ALL_URL;
        Log.e(TAG, "杂志总的网络地址=====" + url);
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
                        Log.e(TAG, "okhttp杂志数据请求失败==" + e.getMessage());
                    }

                    @Override
                    public void onResponse(final String response, int id) {
                        Log.d(TAG, "onResponse杂志数据解析结果: " + response);
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
        ExpertBean expertBean = new Gson().fromJson(json, ExpertBean.class);
        Log.e("TAG", "数组解析数据成功======" + expertBean.getData().getItems().get(0).getDuty());
        adapter.refresh(expertBean.getData().getItems());
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
