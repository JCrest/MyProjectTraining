package com.example.jiangchuanfa.projecttraining.controller.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.List;

/**
 * Created by crest on 2017/7/17.
 */

public class DialogFragmentPagerAdapter extends FragmentPagerAdapter {
    private static final String TAG = DialogFragmentPagerAdapter.class.getSimpleName();
    private final List<Fragment> mFragments;

    public DialogFragmentPagerAdapter(FragmentManager fragmentManager, List<Fragment> mFragments) {
        super(fragmentManager);
        this.mFragments = mFragments;
    }

    @Override
    public Fragment getItem(int position) {

        Log.e(TAG, "getItem  :  " + mFragments.get(position));
        Log.e(TAG, "getItem  :  " + mFragments.size());
        return this.mFragments.get(position);
    }


    @Override
    public int getCount() {
        return this.mFragments.size();
    }

}
