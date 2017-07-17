package com.example.jiangchuanfa.projecttraining.controller.fragment.magazine;

import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jiangchuanfa.projecttraining.R;
import com.example.jiangchuanfa.projecttraining.base.BaseFragment;
import com.example.jiangchuanfa.projecttraining.config.Api;
import com.example.jiangchuanfa.projecttraining.controller.adapter.MagazineTopicAdapter;
import com.example.jiangchuanfa.projecttraining.modle.bean.MagazineBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;

/**
 * Created by crest on 2017/7/6.
 */

public class MagazineFragment extends BaseFragment {


    private static final String TAG = MagazineFragment.class.getName();
    @BindView(R.id.tv_text)
    TextView tvText;
    @BindView(R.id.xrv_magazine)
    XRecyclerView xrvMagazine;
    Unbinder unbinder;
    @BindView(R.id.ll_magazine)
    LinearLayout llMagazine;
    private String magezineUrl;
    private MagazineTopicAdapter adapter;
    private List<MagazineBean> mymagazines;
    private List<String> keys;
    private List<MagazineBean> magazines;

    DialogFragmentWindow fragmentWindow;
    @Override
    public View initView() {
        View view = View.inflate(getActivity(), R.layout.fragment_magazine, null);
        unbinder = ButterKnife.bind(this, view);

        //设置布局管理器

//
//        StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
//        params.setFullSpan(true);
//        Log.e(TAG, "onCreateViewHolder: RecyclerView.ViewHolder" + "看看程序是否能执行到这里？");
//        view.setLayoutParams(params);

//        xrvMagazine.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));

        xrvMagazine.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
//        xrvMagazine.setLayoutManager(new LinearLayoutManager(context,  LinearLayoutManager.VERTICAL,true));
        //设置RecyclerView的适配器  先设置适配器等拿到数据的时候再刷新适配器
        adapter = new MagazineTopicAdapter(context);
        xrvMagazine.setAdapter(adapter);
        fragmentWindow = new DialogFragmentWindow();

        //实现接口消失对话框  调用前面dialog fragment的接口
//        fragmentWindow.setOnDatePickerClickListener(new DialogFragmentWindow.OnDatePickerClickListener() {
//            @Override
//            public void onCancelClick() {
//                fragmentWindow.dismiss();
//            }
//        });

        llMagazine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentWindow.show(getFragmentManager(),"");
            }
        });



        return view;
    }



    @Override
    public void initData() {
        super.initData();
        getDataFromNet();
    }

    private void getDataFromNet() {
        magezineUrl = Api.MAG_TOPIC_URL;//杂志网址
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
            Log.e(TAG, "杂志请求成功数据==" + response);
            processData(response);

        }
    }

    //*************************************************************************************************************************************************************
    //手动解析json数据
    private void processData(String json) {

        try {
            //根据请求到的json数据创建jsonObject
            JSONObject jsonObject = new JSONObject(json);

            //从jsonObject中获取key为data的jsonObject1
            JSONObject jsonObject1 = jsonObject.optJSONObject("data");

            //从jsonObject1中获取key为items的jsonObject2
            JSONObject jsonObject2 = jsonObject1.optJSONObject("items");

            //从jsonObject2中获取key为infos的jsonObject3
            JSONObject jsonObject3 = jsonObject2.optJSONObject("infos");

            //从jsonObject2中获取key为keys的jsonArray
            JSONArray jsonArray = jsonObject2.optJSONArray("keys");

            //创建一个新的JSONArray数组用于盛新循环出来的jsonArray1
            JSONArray array = new JSONArray();

            keys = new ArrayList<>();

            //创建新的list集合用于存放解析出来的含有bean对象的List集合
            mymagazines = new ArrayList<>();

            //根据key从json对象中取值（这里的key为另外数组的value）
            for (int i = 0; i < jsonArray.length(); i++) {
//                //根据另一jsonArray中的value作为key获取自己的value
//                JSONArray jsonArray1 = jsonObject3.optJSONArray((String) jsonArray.get(i));

                //内循环根据子数组的元素个数而定
                for (int i1 = 0; i1 < jsonObject3.optJSONArray((String) jsonArray.get(i)).length(); i1++) {

                    keys.add(jsonArray.get(i).toString());
                    Log.e("keys", keys.get(i));

                    //利用Gson将json字符串数组解析成含有JavaBean的List集合
                    magazines = new Gson().fromJson(jsonObject3.optJSONArray((String) jsonArray.get(i)).toString(), new TypeToken<List<MagazineBean>>() {
                    }.getType());


                }
                //向集合中添加集合
                mymagazines.addAll(magazines);
            }
            Log.e(TAG, "jsonArray:" + array.length());
            Log.e(TAG, "keys:" + keys.size());
            Log.e(TAG, "jsonArray:" + array);
            Log.e(TAG, "mymagazines: "+mymagazines.size() );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //调用刷新适配器的方法（将解析出来的数据传入到适配器）
        adapter.refresh(mymagazines,keys);
    }
//*************************************************************************************************************************************************************

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
