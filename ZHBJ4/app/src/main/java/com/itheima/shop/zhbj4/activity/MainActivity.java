package com.itheima.shop.zhbj4.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioGroup;

import com.itheima.shop.zhbj4.R;
import com.itheima.shop.zhbj4.adapter.MenuAdapter;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

import com.itheima.shop.zhbj4.adapter.MainVPFragmentAdapter;
import com.itheima.shop.zhbj4.base.BaseFragment;
import com.itheima.shop.zhbj4.base.BaseLoadNetDataOperator;
import com.itheima.shop.zhbj4.bean.NewsCenterBean;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.itheima.shop.zhbj4.fragment.GovaffairsFragment;
import com.itheima.shop.zhbj4.fragment.HomeFragment;
import com.itheima.shop.zhbj4.fragment.NewsCenterFragment;
import com.itheima.shop.zhbj4.fragment.SettingFragment;
import com.itheima.shop.zhbj4.fragment.SmartServiceFragment;
import com.itheima.shop.zhbj4.view.NoScrollViewPager;

/**
 * Created by Apple on 2016/9/23.
 */
public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.tab_vp)
    NoScrollViewPager tabVp;//优化了之后的ViewPager
    @BindView(R.id.rg_tab)
    RadioGroup rgTab;

    private List<Fragment> fragments;
    public SlidingMenu slidingMenu;
    public MenuAdapter menuAdapter;
    private WindowManager windowManager;

    //侧滑菜单的数据
    private List<NewsCenterBean.NewsCenterMenuBean> newsCenterMenuBeanList;


    /**
     * 这个顺序呢，是先外部给他设置数据，然后传递，里面menuAdapter再设置，那么适配器里面就有数据了
     */
    //新闻碎片里的记载网络数据方法，传进来的新闻业务bean集合，成员变量被赋值
    public void setNewsCenterMenuBeanList(List<NewsCenterBean.NewsCenterMenuBean> newsCenterMenuBeanList) {
        this.newsCenterMenuBeanList = newsCenterMenuBeanList;
        /**
         * 这里并没有给成员变量newsCenterMenuBeanList赋值，
         * 因为只能值传递，成员变量是集合，所以依旧是null，但是在这个方法里，是赋了值的，所以可以set给侧滑适配器
         */
        menuAdapter.setNewsCenterMenuBeanList(newsCenterMenuBeanList);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去标题？应该是去掉原先的
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//上面一个ViewPager，下面RadioGroup，里面5个RadioButton
        ButterKnife.bind(this);

        initViewPager();
        //设置RadioGroup的选择改变监听
        rgTab.setOnCheckedChangeListener(this);

        initSlidingMenu();
        initRecyclerView();
    }

    //初始化侧滑菜单的RecycleView
    private void initRecyclerView() {
        //直接通过SlidingMenu里面找到RecyclerView
        RecyclerView rvMenu = slidingMenu.findViewById(R.id.rv_menu);
        //设置布局管理器
        rvMenu.setLayoutManager(new LinearLayoutManager(this));
        //创建适配器给RecyclerView进行数据绑定，
        menuAdapter = new MenuAdapter(this);
        rvMenu.setAdapter(menuAdapter);//我们去到这个侧滑菜单适配器...
    }

    //初始化侧滑菜单
    private void initSlidingMenu() {
        //创建侧滑菜单对象
        slidingMenu = new SlidingMenu(this);
        //设置菜单从左边滑出
        slidingMenu.setMode(SlidingMenu.LEFT);
        //设置侧滑菜单，默认不可以滑出
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        //设置侧滑菜单滑出后，主界面的宽度
//        windowManager = this.getWindowManager();
//        int width = windowManager.getDefaultDisplay().getWidth();//就是屏幕宽度像素480
//        slidingMenu.setBehindOffset(width*2/3);//背景的宽度是2/3，那么侧滑就是1/3
        int widthPixels = getResources().getDisplayMetrics().widthPixels;//这样也可以获取宽度480
        slidingMenu.setBehindOffset(widthPixels*2/3);
        //以内容的形式添加到Activity
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        //设置侧滑菜单的布局
        slidingMenu.setMenu(R.layout.activity_main_menu);//布局用的是RecyclerView，排布4个东西，接下来是初始化RecyclerView
    }


    //初始化ViewPager，里面有适配器，用的是大页面的适配器
    private void initViewPager() {
        //碎片集合
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new NewsCenterFragment());
        fragments.add(new SmartServiceFragment());
        fragments.add(new GovaffairsFragment());
        fragments.add(new SettingFragment());
        //tabvp优化了之后的ViewPager   ViewPager的适配器  传过去碎片的集合，我们进入这个适配器...
        tabVp.setAdapter(new MainVPFragmentAdapter(getSupportFragmentManager(), fragments));
        //让ViewPager缓存5个页面，默认是3个
        tabVp.setOffscreenPageLimit(5);
    }

    @Override//这个是配合上面那个RadioGroup的选择改变监视器的
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int item = 0;
        switch (checkedId) {
            case R.id.rb_home:
                item = 0;
                slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);//无法滑出侧滑菜单
                break;
            case R.id.rb_newscener:
                item = 1;
                slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
                break;
            case R.id.rb_smartservice:
                item = 2;
                slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
                break;
            case R.id.rb_govaffairs:
                item = 3;
                slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
                break;
            case R.id.rb_setting:
                item = 4;
                slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);//无法滑出侧滑菜单
                break;
        }
        //让上面的ViewPager切换到对应的页面，是ViewPager带的方法
        tabVp.setCurrentItem(item, false);//false是让它不要有平滑的效果

        //加载网络数据的入口，只有新闻碎片重写了加载网络数据的方法，我们去到那个方法...去新闻碎片里面
        BaseFragment baseFragment = (BaseFragment) fragments.get(item);
        if (baseFragment instanceof BaseLoadNetDataOperator && !baseFragment.hasLoadData) {
                                                                //并且没有加载数据
            ((BaseLoadNetDataOperator) baseFragment).loadNetData();
        }
    }

    //菜单适配器里面调用的方法，点哪个菜单，就切换到
    //获取当前选中的TabFragment
    public BaseFragment getCurrentTabFragment(){
        int currentItem = tabVp.getCurrentItem();//ViewPager自带的方法，获取条目
        BaseFragment baseFragment = (BaseFragment) fragments.get(currentItem);
        return baseFragment;
    }
}
