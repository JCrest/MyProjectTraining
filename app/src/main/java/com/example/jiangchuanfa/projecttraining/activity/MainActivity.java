package com.example.jiangchuanfa.projecttraining.activity;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.jiangchuanfa.projecttraining.R;
import com.example.jiangchuanfa.projecttraining.base.BaseActivity;
import com.example.jiangchuanfa.projecttraining.base.BaseFragment;
import com.example.jiangchuanfa.projecttraining.controller.fragment.expert.ExpertFragment;
import com.example.jiangchuanfa.projecttraining.controller.fragment.goodslistfragment.GoodsListFragment;
import com.example.jiangchuanfa.projecttraining.controller.fragment.magazine.MagazineFragment;
import com.example.jiangchuanfa.projecttraining.controller.fragment.self.ShelfFragment;
import com.example.jiangchuanfa.projecttraining.controller.fragment.share.ShareFragment;
import com.example.jiangchuanfa.projecttraining.controller.fragment.shop.ShopFragment;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;

public class MainActivity extends BaseActivity {


    @BindView(R.id.fl_main)
    FrameLayout flMain;
    @BindView(R.id.rb_shop)
    RadioButton rbShop;
    @BindView(R.id.rb_magazine)
    RadioButton rbMagazine;
    @BindView(R.id.rb_expert)
    RadioButton rbExpert;
    @BindView(R.id.rb_share)
    RadioButton rbShare;
    @BindView(R.id.rb_self)
    RadioButton rbSelf;
    @BindView(R.id.rg_main)
    RadioGroup rgMain;
    private GoodsListFragment goodsListFragment;
    private ShopFragment shopFragment;

    private ArrayList<BaseFragment> fragments;
    //Fragment页面的下标位置
    private int position;
    //缓存的Fragment
    private Fragment tempFragment;


    @Override
    public void initView() {
        fragments = new ArrayList<>();
        shopFragment = new ShopFragment();
        fragments.add(shopFragment);//商店
        fragments.add(new MagazineFragment());//杂志
        fragments.add(new ExpertFragment());//达人
        fragments.add(new ShareFragment());//分享
        fragments.add(new ShelfFragment());//个人
//        goodsListFragment = new GoodsListFragment();//如果要从集合中替换就创建出来//下面有创建的方法




    }


    @Override
    public void initListener() {

        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                switchFragment(checkedId);
            }
        });
    }



    private void switchFragment(int checkedId) {
        Fragment fragment = null;

        switch (checkedId) {
            case R.id.rb_shop:
                Log.e("TAG", "switchFragment: " + fragment);
                position = 0;
                break;
            case R.id.rb_magazine:
                position = 1;
                Log.e("TAG", "switchFragment: " + fragment);
                break;
            case R.id.rb_expert:
                position = 2;
                Log.e("TAG", "switchFragment: " + fragment);
                break;
            case R.id.rb_share:
                position = 3;
                Log.e("TAG", "switchFragment: " + fragment);
                break;
            case R.id.rb_self:
                position = 4;
                Log.e("TAG", "switchFragment: " + fragment);
                break;
        }
        //Fragment-当前的Fragment
        Fragment currentFragment = fragments.get(position);
        selectFragment(currentFragment);
    }

    private void selectFragment(Fragment currentFragment) {
        if (tempFragment != currentFragment) {
            //开启事务
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            if(getSupportFragmentManager().findFragmentByTag("goodsListFragment")!=null) {
//                ft.remove(getSupportFragmentManager().findFragmentByTag("goodsListFragment"));
                getSupportFragmentManager().popBackStack();
            }
            //切换
            if (currentFragment != null) {
                //是否添加过
                if (!currentFragment.isAdded()) {
                    //把之前显示的给隐藏
                    if (tempFragment != null) {
                        ft.hide(tempFragment);
                    }
                    //如果没有添加就添加
                    ft.add(R.id.fl_main, currentFragment);
                } else {
                    //把之前的隐藏
                    if (tempFragment != null) {
                        ft.hide(tempFragment);
                    }
                    //如果添加了就直接显示
                    ft.show(currentFragment);
                }
                //最后一步，提交事务
                ft.commit();
            }
            tempFragment = currentFragment;
        }
    }

    @Override
    public void initData() {

        switchFragment(R.id.rb_shop);
        rgMain.check(R.id.rb_shop);

        resizeDrawableTop();

    }

    /**
     * 改变底部标签的大小
     */
    private void resizeDrawableTop() {
        //定义RadioButton数组用来装RadioButton，改变drawableTop大小
        RadioButton[] rb = new RadioButton[5];
        //将RadioButton装进数组中
        rb[0] = rbShop;
        rb[1] = rbMagazine;
        rb[2] = rbExpert;
        rb[3] = rbShare;
        rb[4] = rbSelf;
        //for循环对每一个RadioButton图片进行缩放
        for (int i = 0; i < rb.length; i++) {
            //挨着给每个RadioButton加入drawable限制边距以控制显示大小
            Drawable[] drawables = rb[i].getCompoundDrawables();
            //获取drawables，2/5表示图片要缩小的比例
            Rect r = new Rect(0, 0, drawables[1].getMinimumWidth() * 3 / 4, drawables[1].getMinimumHeight() * 3 / 4);
            //定义一个Rect边界
            drawables[1].setBounds(r);
            //给每一个RadioButton设置图片大小
            rb[i].setCompoundDrawables(null, drawables[1], null, null);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 //以下的方法是其他方法实现的时候的方法

    public RadioGroup getRgMain() {
        return rgMain;
    }

    public void exchangeFragment() {
        Collections.replaceAll(fragments, fragments.get(0), goodsListFragment);
        switchFragment(R.id.rb_shop);
    }

    public void fragmentExchange() {
        Collections.replaceAll(fragments, fragments.get(0), shopFragment);
        switchFragment(R.id.rb_shop);
    }


}



