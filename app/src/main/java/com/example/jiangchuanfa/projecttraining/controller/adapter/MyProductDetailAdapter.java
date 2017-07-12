package com.example.jiangchuanfa.projecttraining.controller.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jiangchuanfa.projecttraining.R;
import com.example.jiangchuanfa.projecttraining.config.Api;
import com.example.jiangchuanfa.projecttraining.modle.bean.GoodInfoBean;
import com.example.jiangchuanfa.projecttraining.modle.bean.NewBean;
import com.example.jiangchuanfa.projecttraining.utils.FullyGridLayoutManager;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by crest on 2017/7/12.
 */

public class MyProductDetailAdapter extends RecyclerView.Adapter {
    private static final String TAG = MyProductDetailAdapter.class.getSimpleName();
    private final Context context;
    private final GoodInfoBean.DataBean.ItemsBean datas;

    //分情况
    int PICTURE;
    int TEXT;
    int GUESS_YOU_LIKE;
    int currentType;



    private LayoutInflater inflater;


    public MyProductDetailAdapter(Context context, GoodInfoBean.DataBean.ItemsBean items) {
        this.context = context;
        this.datas = items;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {


        if (datas.getGoods_info().size() > 0) {
             PICTURE = 0;
             TEXT = 1;
             GUESS_YOU_LIKE = 2;
            if (position == PICTURE) {
                currentType = PICTURE;
            } else if (position == TEXT) {
                currentType = TEXT;
            } else if (position == GUESS_YOU_LIKE) {
                currentType = GUESS_YOU_LIKE;
            }
        } else {
             TEXT = 0;
             GUESS_YOU_LIKE = 1;
            if (position == TEXT) {
                currentType = TEXT;
            } else if (position == GUESS_YOU_LIKE) {
                currentType = GUESS_YOU_LIKE;
            }
        }
        return currentType;
    }


    @Override
    public int getItemCount() {

        if (datas.getGoods_info().size() > 0) {
            return 3;
        } else {
            return 2;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (datas.getGoods_info().size() > 0) {
            if (viewType == PICTURE) {
                return new PictureViewHolder(context, inflater.inflate(R.layout.picture_item, null));
            } else if (viewType == TEXT) {
                return new TextViewHolder(context, inflater.inflate(R.layout.text_item, null));
            } else if (viewType == GUESS_YOU_LIKE) {
                return new GuessYouLikeHolder(context, inflater.inflate(R.layout.guess_you_like_item, null));
            }
        } else {
            if (viewType == TEXT) {
                return new TextViewHolder(context, inflater.inflate(R.layout.text_item, null));
            } else if (viewType == GUESS_YOU_LIKE) {
                return new GuessYouLikeHolder(context, inflater.inflate(R.layout.guess_you_like_item, null));
            }
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (datas.getGoods_info().size() > 0) {
            if (getItemViewType(position) == PICTURE) {
//                PictureViewHolder pivtureViewHolder = (PictureViewHolder) holder;
                //设置数据Banner的数据

                final FullyGridLayoutManager manager = new FullyGridLayoutManager(context, 1);
                manager.setOrientation(GridLayoutManager.VERTICAL);
                manager.setSmoothScrollbarEnabled(true);
                ((PictureViewHolder) holder).rvPicture.setLayoutManager(manager);
                ((PictureViewHolder) holder).rvPicture.setAdapter(new mListViewAdapter(context, datas.getGoods_info()));
            } else if (getItemViewType(position) == TEXT) {
                TextViewHolder textViewHolder = (TextViewHolder) holder;
                //绑定数据
                textViewHolder.setData(datas);
            } else if (getItemViewType(position) == GUESS_YOU_LIKE) {
                GuessYouLikeHolder guessYouLikeHolder = (GuessYouLikeHolder) holder;
                guessYouLikeHolder.setData();
            }

        } else {
            if (getItemViewType(position) == TEXT) {
                TextViewHolder textViewHolder = (TextViewHolder) holder;
                //绑定数据
                textViewHolder.setData(datas);
            } else if (getItemViewType(position) == GUESS_YOU_LIKE) {
                GuessYouLikeHolder guessYouLikeHolder = (GuessYouLikeHolder) holder;
                guessYouLikeHolder.setData();
            }
        }
        Log.e(TAG, "看看这边呢？？？？？？？？？？？？？" + datas.getGoods_info().size());

    }

    class PictureViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.rv_picture)
        RecyclerView rvPicture;
        private final Context context;

        public PictureViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            ButterKnife.bind(this, itemView);
        }


    }

    class TextViewHolder extends RecyclerView.ViewHolder {

        private final Context context;
        @BindView(R.id.tv_goods_desc)
        TextView tvGoodsDesc;
        @BindView(R.id.tv_brand_name)
        TextView tvBrandName;
        @BindView(R.id.tv_brand_desc)
        TextView tvBrandDesc;
        @BindView(R.id.iv_empty)
        ImageView ivEmpty;
        @BindView(R.id.tv_rec_reason)
        TextView tvRecReason;

        public TextViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            ButterKnife.bind(this, itemView);
        }

        public void setData(GoodInfoBean.DataBean.ItemsBean datas) {
            tvGoodsDesc.setText(datas.getGoods_desc());
            Log.e(TAG, "setData: " + datas.getGoods_desc());
            tvBrandName.setText(datas.getBrand_info().getBrand_name());
            tvBrandDesc.setText(datas.getBrand_info().getBrand_desc());
            tvRecReason.setText(datas.getRec_reason());

        }
    }

    class GuessYouLikeHolder extends RecyclerView.ViewHolder {
        private final Context context;
        @BindView(R.id.gv_you_like)
        RecyclerView gvYouLike;
        private GuessYouLikeAdapter adapter;

        public GuessYouLikeHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            ButterKnife.bind(this, itemView);
        }

        public void setData() {
            String url = Api.SHOP_ALL_URL[(int) (Math.random() * 19)];
            getMyDataFromNet(url);

        }

        private void getMyDataFromNet(String url) {
            OkHttpUtils.get().url(url).build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    Log.e(TAG, "请求失败" + e.getMessage());
                }

                @Override
                public void onResponse(String response, int id) {
                    Log.e(TAG, "onResponse: 请求成功，" + response);
                    processMyData(response);
                }
            });

        }

        private void processMyData(String json) {
            //创建目标对象
            NewBean newBean = new NewBean();
            try {
                //将json转换成json对象
                JSONObject jsonObject = new JSONObject(json);
                //从刚转化的json对象中获取名字为data的json对象1
                JSONObject jsonObject1 = jsonObject.optJSONObject("data");
                //创建NewBean.DataBean类型的对象用于存放数据
                NewBean.DataBean dataBean = new NewBean.DataBean();
                //将带有数据的dataBean放入到目标对象中
                newBean.setData(dataBean);
                JSONArray jsonArray = jsonObject1.optJSONArray("items");
                List<NewBean.DataBean.ItemsBean> items = new ArrayList<>();
                dataBean.setItems(items);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject2 = (JSONObject) jsonArray.get(i);
                    if (jsonObject2 != null) {
                        NewBean.DataBean.ItemsBean itemsBean = new NewBean.DataBean.ItemsBean();

                        String goods_name = jsonObject2.optString("goods_name");
                        itemsBean.setGoods_name(goods_name);

                        String goods_image = jsonObject2.optString("goods_image");
                        itemsBean.setGoods_image(goods_image);

                        String like_count = jsonObject2.optString("like_count");
                        itemsBean.setLike_count(like_count);

                        String price = jsonObject2.optString("price");
                        itemsBean.setPrice(price);

                        String discount_price = jsonObject2.optString("discount_price");
                        itemsBean.setDiscount_price(discount_price);

                        String promotion_imgurl = jsonObject2.optString("promotion_imgurl");
                        itemsBean.setPromotion_imgurl(promotion_imgurl);

                        String goods_id = jsonObject2.optString("goods_id");
                        itemsBean.setGoods_id(goods_id);

                        JSONObject brand_info = jsonObject2.optJSONObject("brand_info");

                        NewBean.DataBean.ItemsBean.BrandInfoBean brandInfoBean = new NewBean.DataBean.ItemsBean.BrandInfoBean();
                        String brand_name = brand_info.optString("brand_name");
                        brandInfoBean.setBrand_name(brand_name);
                        itemsBean.setBrand_info(brandInfoBean);
                        items.add(itemsBean);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            gvYouLike.setLayoutManager(new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false));
            adapter = new GuessYouLikeAdapter(context, newBean.getData().getItems());
            gvYouLike.setAdapter(adapter);
//        Log.e(TAG, "打个看看解析结果："+newBean.getData().getItems().get(0));


        }
    }
}
