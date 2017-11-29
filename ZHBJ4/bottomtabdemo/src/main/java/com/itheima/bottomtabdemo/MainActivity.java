package com.itheima.bottomtabdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.itheima.bottomtabdemo.adapter.TabAdapter;
import com.itheima.bottomtabdemo.fragment.GovaffairsTabFragment;
import com.itheima.bottomtabdemo.fragment.HomeTabFragment;
import com.itheima.bottomtabdemo.fragment.NewsCenterTabFragment;
import com.itheima.bottomtabdemo.fragment.SettingTabFragment;
import com.itheima.bottomtabdemo.fragment.SmartServiceTabFragment;
import com.itheima.bottomtabdemo.view.NoScrollViewPager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {

    private ViewPager vp;
    private List<Fragment> fragments;
    private RadioButton rb_newscenter;
    private RadioButton rb_smartservice;
    private RadioButton rb_govaffairs;
    private RadioButton rb_setting;
    private RadioButton rb_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initVP();
        initSlidingMenu();
    }

    //初始化侧滑菜单
    private void initSlidingMenu() {
        //创建侧滑菜单
        SlidingMenu slidingmenu = new SlidingMenu(this);
        //设置菜单从左边滑出
        slidingmenu.setMode(SlidingMenu.LEFT);
        //设置全屏可以滑出菜单
        slidingmenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //设置侧滑菜单的宽度
        slidingmenu.setBehindWidth(250);
        //把侧滑菜单附加在Activity里面
        slidingmenu.attachToActivity(this,SlidingMenu.SLIDING_CONTENT);
        //设置侧滑菜单的布局
        slidingmenu.setMenu(R.layout.main_menu);
    }


    //初始化ViewPager
    private void initVP() {
        fragments = new ArrayList<>();
        fragments.add(new HomeTabFragment());
        fragments.add(new NewsCenterTabFragment());
        fragments.add(new SmartServiceTabFragment());
        fragments.add(new GovaffairsTabFragment());
        fragments.add(new SettingTabFragment());
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager(),fragments);
        vp.setAdapter(adapter);
        //给ViewPager设置页面滑动改变监听
        vp.addOnPageChangeListener(this);
    }

    //初始化控件
    private void initView() {
        vp = (NoScrollViewPager) findViewById(R.id.vp);
        RadioGroup rg_tab = (RadioGroup) findViewById(R.id.rg_tab);
        //设置选择改变监听
        rg_tab.setOnCheckedChangeListener(this);

        rb_home = (RadioButton) findViewById(R.id.rb_home);
        rb_newscenter = (RadioButton) findViewById(R.id.rb_newscenter);
        rb_smartservice = (RadioButton) findViewById(R.id.rb_smartservice);
        rb_govaffairs = (RadioButton) findViewById(R.id.rb_govaffairs);
        rb_setting = (RadioButton) findViewById(R.id.rb_setting);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int item = 0;
        switch (checkedId){
            case R.id.rb_home:
                item = 0;
                break;
            case R.id.rb_newscenter:
                item = 1;
                break;
            case R.id.rb_smartservice:
                item = 2;
                break;
            case R.id.rb_govaffairs:
                item = 3;
                break;
            case R.id.rb_setting:
                item = 4;
                break;
        }
        //ViewPager切换到对应的页面
        vp.setCurrentItem(item,false);//false 不需要Viewpager页面切换的时候有滑动的动画
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }
    @Override
    public void onPageSelected(int position) {
        switch (position){
            case 0:
                rb_home.setChecked(true);
                break;
            case 1:
                rb_newscenter.setChecked(true);
                break;
            case 2:
                rb_smartservice.setChecked(true);
                break;
            case 3:
                rb_govaffairs.setChecked(true);
                break;
            case 4:
                rb_setting.setChecked(true);
                break;
        }
    }
    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
