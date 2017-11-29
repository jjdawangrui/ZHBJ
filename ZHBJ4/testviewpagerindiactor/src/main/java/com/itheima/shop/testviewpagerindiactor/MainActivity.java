package com.itheima.shop.testviewpagerindiactor;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private String[] names = new String[]{
            "体育", "CBA", "NBA", "WCC", "TVB", "CCTV", "都市", "娱乐", "八卦", "旅游", "女性", "育儿", "购物", "音乐"
    };


    private TabPageIndicator tabPageIndicator;
    private ViewPager viewPager;
    private ArrayList<TextView> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabPageIndicator = (TabPageIndicator) findViewById(R.id.tabPagerIndicator);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        list = new ArrayList<>();
        for(int i=0; i < names.length; i++){
            TextView  textView = new TextView(this);
            textView.setText(names[i]);
            list.add(textView);
        }
        viewPager.setAdapter(new MyAdapter());
        tabPageIndicator.setViewPager(viewPager);
    }

    private class MyAdapter extends PagerAdapter {

        @Override
        public CharSequence getPageTitle(int position) {
            return names[position];
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TextView textView = list.get(position);
            container.addView(textView);
            return textView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(list.get(position));

        }
    }
}
