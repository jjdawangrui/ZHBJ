package com.itheima.shop.zhbj4.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.itheima.shop.zhbj4.base.NewsCenterContentTabPager;
import com.itheima.shop.zhbj4.bean.NewsCenterBean;
import com.itheima.shop.zhbj4.utils.Constant;

import java.util.List;

/**
 * Created by raynwang on 2017/6/14.
 */
//为ViewPager创建的适配器
public class NewsCenterTabVPAdapter extends PagerAdapter {
    private List<NewsCenterContentTabPager> views;
    private List<NewsCenterBean.NewsCenterNewsTabBean> tabBeanList;

    //构造，传进View的集合  新闻中心4个菜单的集合  新闻 专题 组图 互动
    public NewsCenterTabVPAdapter(List<NewsCenterContentTabPager> views, List<NewsCenterBean.NewsCenterNewsTabBean> tabBeanList) {
        this.views = views;
        this.tabBeanList = tabBeanList;
    }

    @Override
    public int getCount() {
//        System.out.println("NewsCenterTabVPAdapter的条目数----"+views.size());//打印12，说明是上面的indicator
        //其实indicator是12个，下面对应的ViewPager也是12个
        return views == null ? 0 : views.size();
    }

    //这是什么卵？
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = views.get(position).view;
        container.addView(view);

        //进行数据的加载
        NewsCenterContentTabPager tabPager = views.get(position);
        //  /10007/list_1.json 需要与前面进行路径的拼接
        String url = Constant.HOST + tabBeanList.get(position).url;
        tabPager.loadNetData(url);
        return tabPager.view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabBeanList.get(position).title;
    }
}
