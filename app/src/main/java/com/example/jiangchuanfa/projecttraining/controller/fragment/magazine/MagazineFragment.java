package com.example.jiangchuanfa.projecttraining.controller.fragment.magazine;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jiangchuanfa.projecttraining.R;
import com.example.jiangchuanfa.projecttraining.base.BaseFragment;
import com.example.jiangchuanfa.projecttraining.config.Api;
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

public class MagazineFragment extends BaseFragment {


    private static final String TAG = MagazineFragment.class.getSimpleName();
    @BindView(R.id.tv_text)
    TextView tvText;
    @BindView(R.id.xrv_magazine)
    XRecyclerView xrvMagazine;
    Unbinder unbinder;
    @BindView(R.id.ll_magazine)
    LinearLayout llMagazine;
    private String magezineUrl;

    @Override
    public View initView() {
        View view = View.inflate(getActivity(), R.layout.fragment_magazine, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        getDataFromNet();
    }

    private void getDataFromNet() {
        magezineUrl = Api.EXPERT_ALL_URL;//网址错了
        Log.e(TAG, "杂志总的网络地址=====" + magezineUrl);
        OkHttpUtils
                .get()
                .url(magezineUrl)
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

    private void processData(String response) {


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
