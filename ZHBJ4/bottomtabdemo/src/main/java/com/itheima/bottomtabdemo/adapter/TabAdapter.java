package com.itheima.bottomtabdemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Apple on 2016/9/20.
 */
public class TabAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    public TabAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    //获取对应位置的Fragment
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    //页面的数量
    @Override
    public int getCount() {
        return fragments.size();
    }
}
