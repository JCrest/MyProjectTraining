package com.example.jiangchuanfa.projecttraining.controller.fragment.goodslistfragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jiangchuanfa.projecttraining.R;
import com.example.jiangchuanfa.projecttraining.activity.MainActivity;
import com.example.jiangchuanfa.projecttraining.base.BaseFragment;
import com.example.jiangchuanfa.projecttraining.controller.adapter.GoodsListAdapter;
import com.example.jiangchuanfa.projecttraining.modle.bean.GoodsListBean;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;
import okhttp3.Call;

/**
 * Created by crest on 2017/7/8.
 */

public class GoodsListFragment extends BaseFragment {

    private static final String TAG = GoodsListFragment.class.getSimpleName();


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ib_search)
    ImageButton ibSearch;
    @BindView(R.id.ib_back)
    ImageButton ibBack;
    @BindView(R.id.ib_cart)
    ImageButton ibCart;
    @BindView(R.id.ll_price_screening)
    LinearLayout llPriceScreening;
    @BindView(R.id.refreshable_recycle_view)
    XRecyclerView refreshableRecycleView;
    Unbinder unbinder;
    @BindView(R.id.ll_goods_fragment)
    LinearLayout llGoodsFragment;

    private String url;
    private GoodsListAdapter adapter;

//    private ClassifyFragment classifyFragment;

    @Override
    public View initView() {
        View view = View.inflate(getActivity(), R.layout.fragment_goods_list, null);
        unbinder = ButterKnife.bind(this, view);
        ibBack.setVisibility(View.VISIBLE);
        ibSearch.setVisibility(View.GONE);
        Log.e(TAG, "********************************************************************************");

        refreshableRecycleView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);

        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity mainActivity = (MainActivity) context;
//                mainActivity.fragmentExchange();
//                mainActivity.getRgMain().check(R.id.rb_shop);
                //退出当前fragment
               mainActivity.getSupportFragmentManager().popBackStack();
            }
        });

////        注册事件接收者(谁接收数据谁注册)
//        EventBus.getDefault().register(this);

        //得到数据
        Bundle arguments = getArguments();
        if(arguments != null){
            url = arguments.getString("data", "没有收到数据");
            getDataFromNet(url);

        }


//        if (getArguments() != null) {
//            String mParam1 = getArguments().getString("param");
//
//            url=getArguments().getString("param");
//        }


        return view;


    }
//
//    @Subscribe
//    public void onEvent(String data) {
//        url = data;
//        Log.e(TAG , "url+++++++++++"+url);
//        getDataFromNet(url);
//    }

//    public void onEventMainThread(FirstEvent event) {
//
//        url =  event.getMsg();
//        Log.e(TAG , "url+++++++++++"+url);
//        getDataFromNet(url);
//        tvTitle.setText("商店");
//    }

//    public static GoodsListFragment newInstance(String text) {
//        GoodsListFragment goodsListFragment = new GoodsListFragment();
//        Bundle args = new Bundle();
//        args.putString("param", text);
//        goodsListFragment.setArguments(args);
//        return goodsListFragment;
//    }




    @Override
    public void initData() {
        tvTitle.setText("商店");

//////////////////////////////////////////////////////////////////////////////////////////////////////////
       /* classifyFragment = new ClassifyFragment();
        classifyFragment.setOnDataTransmissionListener(new ClassifyFragment.OnDataTransmissionListener() {
            @Override
            public void dataTransmission(String data) {
                showToast(data);
            }
        });*/
////////////////////////////////////////////////////////////////////////////////////////////////////////////


//        //商店推荐为模板
//        url = Api.SHOP_RECOMMEND_URL;
//        Log.e(TAG, "商店分类推荐的网络地址=====" + url);

//        getDataFromNet(url);
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
                        Log.e(TAG, "okhttp商店分类推荐数据请求失败==" + e.getMessage());
                    }
                    @Override
                    public void onResponse(final String response, int id) {
                        Log.d(TAG, "onResponse:看看这边的网址： " + response);
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
        GoodsListBean goodsListBean = new Gson().fromJson(json, GoodsListBean.class);
        Log.e(TAG, "数组解析数据成功======" + goodsListBean.getData().getItems().get(0).getGoods_name());
        //设置RecyclerView的适配器
        adapter = new GoodsListAdapter(context,goodsListBean.getData().getItems());
        refreshableRecycleView.setAdapter(adapter);
        //设置布局管理器
        refreshableRecycleView.setLayoutManager(new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false));
//        adapter.refresh(goodsListBean.getData().getItems());
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }


}
