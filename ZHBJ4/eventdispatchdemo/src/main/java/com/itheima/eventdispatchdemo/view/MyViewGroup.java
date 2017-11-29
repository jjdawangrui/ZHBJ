package com.itheima.eventdispatchdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Apple on 2016/9/20.
 */
public class MyViewGroup extends ViewGroup {

    public MyViewGroup(Context context) {
        super(context);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //测量
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        View view = getChildAt(0);
        view.measure(widthMeasureSpec,heightMeasureSpec);
    }

    //布局
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        View view = getChildAt(0);
        view.layout(l,t,r,b);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i("TAG","MyViewGroup            dispatchTouchEvent");
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.i("TAG","MainActivity          按下");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i("TAG","MainActivity          移动");
                break;
            case MotionEvent.ACTION_UP:
                Log.i("TAG","MainActivity          弹起");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i("TAG","MyViewGroup            onInterceptTouchEvent");
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
               return super.onInterceptTouchEvent(ev);//事件默认是不拦截  保证事件可以传递到最底层
            case MotionEvent.ACTION_MOVE:
                Log.i("TAG","MainActivity          移动");
                break;
            case MotionEvent.ACTION_UP:
                Log.i("TAG","MainActivity          弹起");
                break;
        }
        return true;//拦截事件
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("TAG","MyViewGroup             onTouchEvent");
        return true;//处理
    }
}
