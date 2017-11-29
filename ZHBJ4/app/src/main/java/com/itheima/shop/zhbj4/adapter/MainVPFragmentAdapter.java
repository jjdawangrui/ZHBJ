package com.itheima.shop.zhbj4.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Apple on 2016/9/24.
 */
public class MainVPFragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    //...构造，传碎片管理和碎片的集合，布置好了，5个页面。end
    public MainVPFragmentAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();//这里肯定是5
    }
}
