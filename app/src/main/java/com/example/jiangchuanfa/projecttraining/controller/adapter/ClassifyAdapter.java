package com.example.jiangchuanfa.projecttraining.controller.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.jiangchuanfa.projecttraining.R;
import com.example.jiangchuanfa.projecttraining.modle.bean.ClassifyBean;
import com.example.jiangchuanfa.projecttraining.utils.CornersTransform;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by crest on 2017/7/7.
 */

public class ClassifyAdapter extends RecyclerView.Adapter<ClassifyAdapter.ViewHolder> {


    private static final String TAG = ClassifyAdapter.class.getSimpleName();

    private final Context context;
    private final List<ClassifyBean.DataBean.ItemsBean> datas = new ArrayList<>();
//
//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what) {
//                case NetCacheUtils.SECUSS://图片请求成功
//                    //位置
//                    int position = msg.arg1;
//                    Bitmap bitmap = (Bitmap) msg.obj;
//                   /* if (recyclerview.isShown()) {
//                        ImageView ivIcon = (ImageView) recyclerview.findViewWithTag(position);
//                        if (ivIcon != null && bitmap != null) {
//                            Log.e("TAG", "网络缓存图片显示成功" + position);
//                            ivIcon.setImageBitmap(bitmap);
//                        }
//                    }*/
//                    //Glide.with(context).load()
//                    break;
//
//                case NetCacheUtils.FAIL://图片请求失败
//                    position = msg.arg1;
//                    Log.e("TAG", "网络缓存失败" + position);
//                    break;
//            }
//        }
//    };
//    private BitmapCacheUtils bitmapCacheUtils;
//    private DisplayImageOptions options;

    public ClassifyAdapter(Context context) {
        this.context = context;
    }

    public void refresh(List<ClassifyBean.DataBean.ItemsBean> items) {
        //this.context = context;
        this.datas.addAll(items);
        //this.recyclerview = recyclerview;

        /*bitmapCacheUtils = new BitmapCacheUtils(handler);
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.news_pic_default)
                .showImageForEmptyUri(R.drawable.news_pic_default)
                .showImageOnFail(R.drawable.news_pic_default)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                //设置矩形圆角图片
                .displayer(new RoundedBitmapDisplayer(15))
                .build();*/

        this.notifyDataSetChanged();

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(context, R.layout.item_classify_fragment, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //1.根据位置得到数据
        ClassifyBean.DataBean.ItemsBean itemsBean = datas.get(position);
        Glide.with(context).load(itemsBean.getCover_new_img())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transform(new CornersTransform(context, 5))
                .placeholder(R.color.white)
                .error(R.drawable.news_pic_default)
                .into(holder.ivIcon);


//        holder.ivIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context, "在这还崩" + position, Toast.LENGTH_SHORT).show();
////                Toast.makeText(context, "在这还崩！", Toast.LENGTH_SHORT).show();
////
//                MainActivity mainActivity = (MainActivity) context;
//                mainActivity.exchangeFragment();
//                mainActivity.getRgMain().check(R.id.rb_shop);

//            }
//        });
//       holder.ivIcon.setImageResource(R.drawable.abc_ic_search_api_mtrl_alpha);
        //ImageLoader.getInstance().displayImage(itemsBean.getCover_new_img(), holder.ivIcon, options);
//        if (clickListener != null) {
//            clickListener.onItemClick(position);
//        }


    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivIcon;

        public ViewHolder(View view) {
            super(view);
            ivIcon = view.findViewById(R.id.iv_icon);
            ivIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener != null) {
                        clickListener.onItemClick(getLayoutPosition());
                        Log.e(TAG,"clickListener:"+clickListener);
                    }
                }
            });
        }

    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    public interface OnItemClickListener {
        public void onItemClick(int position);
    }

    private OnItemClickListener clickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.clickListener =  listener;

    }

}
