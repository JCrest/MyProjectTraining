package com.example.jiangchuanfa.projecttraining.controller.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.jiangchuanfa.projecttraining.R;
import com.example.jiangchuanfa.projecttraining.activity.WebViewActivity;
import com.example.jiangchuanfa.projecttraining.modle.bean.HomeBean;
import com.example.jiangchuanfa.projecttraining.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by crest on 2017/7/7.
 */

public class HomeAdapter extends RecyclerView.Adapter {
    private static final String TAG = HomeAdapter.class.getSimpleName();
    private final Context context;
    private final List<HomeBean.DataBean.ItemsBean.ListBeanX> datas;

    //共4种类型的分类
    public static final int BANNER = 0;
    public static final int LINEAR = 1;
    public static final int GRID = 2;
    public static final int STAGGERED = 4;
    /**
     * 当前类型
     */
    public int currentType = BANNER;


    private LayoutInflater inflater;


    public HomeAdapter(Context context, List<HomeBean.DataBean.ItemsBean.ListBeanX> list) {
        Log.e(TAG, "HomeAdapter: " + list);
        this.context = context;
        this.datas = list;

        inflater = LayoutInflater.from(context);//如果不实例化的话就会报android.view.View android.view.LayoutInflater.inflate(int, android.view.ViewGroup)' on a null object reference空指针
    }


    /**
     * 根据位置得到当前类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (position == BANNER) {
            Log.e(TAG, "getItemViewType: " + position);
            Log.e(TAG, "datas.get(position % 8 ).getHome_type():BANNER--》" + datas.get(position % 8).getHome_type());
            currentType = BANNER;
        } else if (datas.get(position % 8).getHome_type() == LINEAR) {
            Log.e(TAG, "datas.get(position % 8 ).getHome_type():LINEAR--》" + datas.get(position % 8).getHome_type());
            Log.e(TAG, "getItemViewType: " + position);
            currentType = LINEAR;
        } else if (datas.get(position % 8).getHome_type() == GRID) {
            Log.e(TAG, "datas.get(position % 8 ).getHome_type():GRID--》" + datas.get(position % 8).getHome_type());
            Log.e(TAG, "getItemViewType: " + position);
            currentType = GRID;
        } else if (datas.get(position % 8).getHome_type() == STAGGERED) {
            Log.e(TAG, "datas.get(position % 8 ).getHome_type():STAGGERED--》" + datas.get(position % 8).getHome_type());
            Log.e(TAG, "getItemViewType: " + position);
            currentType = STAGGERED;
        }
//        if (position == BANNER) {
//            Log.e(TAG, "getItemViewType: " + position);
//            Log.e(TAG, "datas.get(position % 8 ).getHome_type():BANNER--》" + datas.get(position % 8).getHome_type());
//            currentType = BANNER;
//        } else if (position == LINEAR) {
//            Log.e(TAG, "datas.get(position % 8 ).getHome_type():LINEAR--》" + datas.get(position % 8).getHome_type());
//            Log.e(TAG, "getItemViewType: " + position);
//            currentType = LINEAR;
//        } else if (position == GRID) {
//            Log.e(TAG, "datas.get(position % 8 ).getHome_type():GRID--》" + datas.get(position % 8).getHome_type());
//            Log.e(TAG, "getItemViewType: " + position);
//            currentType = GRID;
//        } else if (position == STAGGERED) {
//            Log.e(TAG, "datas.get(position % 8 ).getHome_type():STAGGERED--》" + datas.get(position % 8).getHome_type());
//            Log.e(TAG, "getItemViewType: " + position);
//            currentType = STAGGERED;
//        }
        return currentType;
    }

    /**
     * 运行时实现了几个类型就返回几，全部实现返回3；
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            View viewtop = inflater.inflate(R.layout.banner_viewpager, parent, false);
//            BannerViewHolder bannerViewHolder = new BannerViewHolder(context, inflater.inflate(R.layout.banner_viewpager, parent, false));
            StaggeredGridLayoutManager.LayoutParams params =
                    (StaggeredGridLayoutManager.LayoutParams) viewtop.getLayoutParams();
            params.setFullSpan(true);//最为重要的一个方法，占满全屏,以下同理
            viewtop.setLayoutParams(params);
            return new BannerViewHolder(context, viewtop);
        } else if (viewType == LINEAR) {
            View view2 = inflater.inflate(R.layout.linear_item, parent, false);
            StaggeredGridLayoutManager.LayoutParams params =
                    (StaggeredGridLayoutManager.LayoutParams) view2.getLayoutParams();
            params.setFullSpan(true);
            view2.setLayoutParams(params);
            return new LinearViewHolder(context, view2);
        } else if (viewType == GRID) {
            View view4 = inflater.inflate(R.layout.grid_item, parent, false);
            StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) view4.getLayoutParams();
            params.setFullSpan(true);
            Log.e(TAG, "onCreateViewHolder: RecyclerView.ViewHolder" + "看看程序是否能执行到这里？");
            view4.setLayoutParams(params);
            return new GridViewHolder(context, view4);
        } else if (viewType == STAGGERED) {
            View view3 = inflater.inflate(R.layout.staggered_img_item, parent, false);
            StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) view3.getLayoutParams();
            params.setFullSpan(true);
            view3.setLayoutParams(params);
            return new StaggeredViewHolder(context, view3);
        }
        return null;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BannerViewHolder && datas.get(position).getList() != null) {
            //如果是TypeTopsliderHolder， 并且header有数据，并且TypeTopsliderHolder的linearLayout没有子view（因为这个布局只出现一次，如果他没有子view，
            // 也就是他是第一次加载，才加载数据）
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            bannerViewHolder.setData(datas.get(position).getList());//加载头部数据源
        } else if (holder instanceof GridViewHolder && datas.get(position) != null) {
            GridViewHolder gridViewHolder = (GridViewHolder) holder;
            //设置数据Banner的数据
            gridViewHolder.setData(datas.get(position));
        } else if (holder instanceof StaggeredViewHolder && datas.get(position) != null) {
            StaggeredViewHolder staggeredViewHolder = (StaggeredViewHolder) holder;
            //设置数据Banner的数据
            staggeredViewHolder.setData(datas.get(position));
            Log.e(TAG, "onBindViewHolder: " + "看看绑定数据是否出了什么问题？？？？" + datas.get(position).getFour().getPic_url());
        } else if (holder instanceof LinearViewHolder && datas.get(position) != null) {
            LinearViewHolder linearViewHolder = (LinearViewHolder) holder;
            //设置数据Banner的数据
            linearViewHolder.setData(datas.get(position));
        }
    }


    class BannerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.banner)
        Banner banner;
        private final Context context;


        public BannerViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            ButterKnife.bind(this, itemView);
        }

        public void setData(final List<HomeBean.DataBean.ItemsBean.ListBeanX.ListBean> list) {
            //设置Banner 数据
            if (list != null) {
                List<String> images = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    images.add(list.get(i).getPic_url());
                }

                banner.setImages(images)
                        .setImageLoader(new GlideImageLoader())
                        .setOnBannerListener(new OnBannerListener() {
                            @Override
                            public void OnBannerClick(int position) {
                                if (position < list.size()) {
                                    if (position == 0) {
//                                        Toast.makeText(context, "position==" + position, Toast.LENGTH_SHORT).show();
//                                        Toast.makeText(context, "position==" +position+ list.get(position).getTopic_url(), Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(context, WebViewActivity.class);
                                        intent.putExtra("topic_url", list.get(position).getTopic_url());
                                        intent.putExtra("topic_name", list.get(position).getTopic_name());
                                        context.startActivity(intent);
                                    } else if (position == 1) {
//                                        Toast.makeText(context, "position==" + position+list.get(position).getTopic_url(), Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(context, WebViewActivity.class);
                                        intent.putExtra("topic_url", list.get(position).getTopic_url());
                                        intent.putExtra("topic_name", list.get(position).getTopic_name());
                                        context.startActivity(intent);
                                    } else if (position == 2) {
//                                        Toast.makeText(context, "position==" + position, Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(context, WebViewActivity.class);
                                        intent.putExtra("topic_url", list.get(position).getTopic_url());
                                        intent.putExtra("topic_name", list.get(position).getTopic_name());
                                        context.startActivity(intent);
                                    }
                                }
                            }
                        }).start();
            }
        }
    }
}

class LinearViewHolder extends RecyclerView.ViewHolder {
    private final Context context;
    @BindView(R.id.iv_linear)
    ImageView ivLinear;


    public LinearViewHolder(Context context, View itemView) {
        super(itemView);
        this.context = context;
        ButterKnife.bind(this, itemView);
    }

    public void setData(final HomeBean.DataBean.ItemsBean.ListBeanX listBeanX) {
        Glide.with(context).load(listBeanX.getOne().getPic_url()).into(ivLinear);
        ivLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, listBeanX.getOne().getPic_url(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra("topic_url", listBeanX.getOne().getTopic_url());
                intent.putExtra("topic_name", listBeanX.getOne().getTopic_name());
                context.startActivity(intent);
            }
        });

    }

}

class GridViewHolder extends RecyclerView.ViewHolder {
    private final Context context;
    @BindView(R.id.iv_gv_left)
    ImageView ivGvLeft;
    @BindView(R.id.iv_gv_right)
    ImageView ivGvRight;


    public GridViewHolder(Context context, View itemView) {
        super(itemView);
        this.context = context;
        ButterKnife.bind(this, itemView);
    }

    public void setData(final HomeBean.DataBean.ItemsBean.ListBeanX listBeanX) {
        Glide.with(context).load(listBeanX.getOne().getPic_url()).into(ivGvLeft);
        Glide.with(context).load(listBeanX.getTwo().getPic_url()).into(ivGvRight);
        ItemsClick(listBeanX);
    }

    /**
     * 两个图片的点击事件
     *
     * @param listBeanX
     */
    private void ItemsClick(final HomeBean.DataBean.ItemsBean.ListBeanX listBeanX) {
        ivGvLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, listBeanX.getOne().getPic_url(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra("topic_url", listBeanX.getOne().getTopic_url());
                intent.putExtra("topic_name", listBeanX.getOne().getTopic_name());
                context.startActivity(intent);
            }
        });
        ivGvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, listBeanX.getTwo().getPic_url(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra("topic_url", listBeanX.getTwo().getTopic_url());
                intent.putExtra("topic_name", listBeanX.getTwo().getTopic_name());
                context.startActivity(intent);
            }
        });
    }


}


class StaggeredViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = StaggeredViewHolder.class.getSimpleName();
    @BindView(R.id.stagger_item_one)
    ImageView staggerItemOne;
    @BindView(R.id.stagger_item_two)
    ImageView staggerItemTwo;
    @BindView(R.id.stagger_item_three)
    ImageView staggerItemThree;
    @BindView(R.id.stagger_item_four)
    ImageView staggerItemFour;

    private final Context context;

    public StaggeredViewHolder(Context context, View itemView) {
        super(itemView);
        this.context = context;
        ButterKnife.bind(this, itemView);
    }

    public void setData(final HomeBean.DataBean.ItemsBean.ListBeanX listBeanX) {
        Glide.with(context).load(listBeanX.getOne().getPic_url()).into(staggerItemOne);
        Glide.with(context).load(listBeanX.getTwo().getPic_url()).into(staggerItemTwo);
        Glide.with(context).load(listBeanX.getThree().getPic_url()).into(staggerItemThree);
        Glide.with(context).load(listBeanX.getFour().getPic_url()).into(staggerItemFour);
        ItemsClick(listBeanX);

    }

    private void ItemsClick(final HomeBean.DataBean.ItemsBean.ListBeanX listBeanX) {
        staggerItemFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, listBeanX.getFour().getPic_url(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra("topic_url", listBeanX.getFour().getTopic_url());
                intent.putExtra("topic_name", listBeanX.getFour().getTopic_name());
                context.startActivity(intent);
            }
        });
        staggerItemThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, listBeanX.getThree().getPic_url(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra("topic_url", listBeanX.getThree().getTopic_url());
                intent.putExtra("topic_name", listBeanX.getThree().getTopic_name());
                context.startActivity(intent);
            }
        });
        staggerItemTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, listBeanX.getTwo().getPic_url(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra("topic_url", listBeanX.getTwo().getTopic_url());
                intent.putExtra("topic_name", listBeanX.getTwo().getTopic_name());
                context.startActivity(intent);
            }
        });
        staggerItemOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, listBeanX.getOne().getPic_url(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra("topic_url", listBeanX.getOne().getTopic_url());
                intent.putExtra("topic_name", listBeanX.getOne().getTopic_name());
                context.startActivity(intent);
            }
        });
    }


}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////
//http://download.csdn.NET/detail/u010687392/8868745   demo地址？