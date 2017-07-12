package com.atguigu.l20_fragment3;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * 作者：杨光福 on 08/05/2017 15:16
 * QQ：541433511
 * 微信：yangguangfu520
 * 作用：
 */

public class MyListFragment extends ListFragment {

    /**
     * 已经为我们添加了：ListView和文本
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * 当Actvity被创建完成的时候回调这个方法
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //得到ListView并且设置适配器
        setListAdapter(new ArrayAdapter<String>(getActivity(), R.layout.item, DataUtils.TITLES));
        setData(DataUtils.DETAILS[0]);
        //设置ListView为单选模式
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        //设置默认选择第0条
        getListView().setItemChecked(0,true);
    }

    /**
     * 设置ListFragment中的ListView的item的点击事件
     */

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
//        Toast.makeText(getContext(), ""+DataUtils.TITLES[position], Toast.LENGTH_SHORT).show();
        //1.创建Fragment
        setData(DataUtils.DETAILS[position]);


    }

    private void setData(String detail) {
        DetailShowFragment detailShowFragment = new DetailShowFragment();

        Bundle bundle = new Bundle();
        bundle.putString("data", detail);
        detailShowFragment.setArguments(bundle);

        //传递数据

        //2.得到FragmentMnager
        FragmentManager fm = getFragmentManager();
        //3.开启事务
        FragmentTransaction ft = fm.beginTransaction();
        //4.替换
        ft.replace(R.id.fl_content, detailShowFragment);
        //5.提交事务
        ft.commit();
    }
}
