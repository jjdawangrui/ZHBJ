package com.itheima.bottomtabdemo.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Apple on 2016/9/21.
 */
public class NoScrollViewPager extends ViewPager {
    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //拦截事件   默认  ViewPager会拦截掉Move UP 事件   不要拦截事件
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    //处理事件  不要处理事件
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}
