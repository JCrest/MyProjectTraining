package com.example.jiangchuanfa.projecttraining.controller.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.jiangchuanfa.projecttraining.controller.fragment.shop.viewfragment.BrandFragment;
import com.example.jiangchuanfa.projecttraining.controller.fragment.shop.viewfragment.ClassifyFragment;
import com.example.jiangchuanfa.projecttraining.controller.fragment.shop.viewfragment.GiftFragment;
import com.example.jiangchuanfa.projecttraining.controller.fragment.shop.viewfragment.HomeFragment;
import com.example.jiangchuanfa.projecttraining.controller.fragment.shop.viewfragment.SpecialFragment;

/**
 * Created by crest on 2017/7/6.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private String[] mTitles = new String[]{"分类", "品牌", "首页", "专题", "礼物"};

    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 1) {
            Log.i("TAG", "MyFragmentPagerAdapter getItem()" + position);
            return new BrandFragment();
        } else if (position == 2) {
            Log.i("TAG", "MyFragmentPagerAdapter getItem()" + position);
            return new HomeFragment();

        } else if (position == 3) {
            Log.i("TAG", "MyFragmentPagerAdapter getItem()" + position);
            return new SpecialFragment();
        } else if (position == 4) {
            Log.i("TAG", "MyFragmentPagerAdapter getItem()" + position);
            return new GiftFragment();
        }
        return new ClassifyFragment();
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    //ViewPager与TabLayout绑定后，这里获取到PageTitle就是Tab的Text
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
